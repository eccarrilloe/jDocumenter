/*
// Certificaci�n en Java2 Developer
// Gabriel Ma�ana
// Of. 118, Ed. 453
// Departamento de Ingenier�a de Sistemas
// Facultad de Ingenier�a
// Universidad Nacional de Colombia
//
*/

package sockets.server;

import java.net.*; 
import java.io.*; 
import java.util.*;

public class RequestProcessor implements Runnable  
{
    private static List pool = new LinkedList();

    private File m_docRootDir;
    private String m_indexFileName = "index.html";
    
    public RequestProcessor( File docRootDir, String indexFileName )   
    {
        if ( docRootDir.isFile() )   
        { 
            throw new IllegalArgumentException(
                    "m_docRootDir must be a directory, not a file" ); 
        }

        m_docRootDir = docRootDir; 
        try 
        {
            m_docRootDir = m_docRootDir.getCanonicalFile(); 
        }
        catch ( IOException e )   
        {
        }
        
        if ( indexFileName != null ) m_indexFileName = indexFileName;
    }
    
    public static void processRequest( Socket request ) 
    {
        synchronized ( pool ) 
        {
            pool.add( pool.size(), request ); 
            pool.notifyAll();
        }
    }
    
    public void run() 
    {
        // for security checks
        String root = m_docRootDir.getPath();
        
        while ( true ) 
        {
            Socket connection; 
            
            synchronized ( pool ) 
            { 
                while ( pool.isEmpty() ) 
                { 
                    try 
                    {
                        pool.wait(); 
                    } 
                    catch ( InterruptedException e ) 
                    {
                    }
                }
                
                connection = (Socket)pool.remove( 0 );
            }
            
            try 
            {
                String filename;
                String contentType;
                
                OutputStream raw = new BufferedOutputStream(
                                    connection.getOutputStream() );
                Writer out = new OutputStreamWriter( raw ); 
                Reader in = new InputStreamReader (
                                new BufferedInputStream( 
                                    connection.getInputStream() ), "ASCII" );
                
                StringBuffer requestLine = new StringBuffer(); 
                
                int c;
                while ( true ) 
                { 
                    c = in.read();
                    if ( c == '\r' || c == '\n' ) break; 
                    requestLine.append( (char)c );
                }
                
                String get = requestLine.toString(); // log the request
                System.out.println( get );
                StringTokenizer st = new StringTokenizer( get );
                String method = st.nextToken();
        
                String version = "";
                
                if ( method.equals( "GET" ) ) 
                {
                    filename = st.nextToken();
                    
                    if ( filename.endsWith( "/" ) )
                    {
                        filename += m_indexFileName;
                    }
                    
                    contentType = guessContentTypeFromName( filename );
        
                    if ( st.hasMoreTokens() ) 
                    { 
                        version = st.nextToken();
                    }
                    
                    File theFile = new File( m_docRootDir,
                                 filename.substring( 1, filename.length() ) ); 
                    
                    // don't let clients outside the document root 					
                    if ( theFile.canRead() && 
                         theFile.getCanonicalPath().startsWith( root ) ) 
                    { 
                        DataInputStream fis = new DataInputStream(
                                    new BufferedInputStream( 
                                      new FileInputStream( theFile ) ) );
                        byte[] theData = new byte[ (int)theFile.length() ];
                        fis.readFully( theData );
                        fis.close();
                    
                        if ( version.startsWith( "HTTP" ) )  
                        {  
                            // send a MIME header
                            out.write( "HTTP/1.0 200 OK\r\n" );
                            Date now = new Date();
                            out.write( "Date: " + now + "\r\n" );
                            out.write( "Server: JHTTP 1.0\r\n" );
                            out.write( "Content-length: " + theData.length + "\r\n" );
                            out.write( "Content-type: " + contentType + "\r\n\r\n" );
                            out.flush(); 
                        }
                        
                        // send the file; it may be an image or 
                        // other binary data so use the underlying 
                        // output stream instead of the writer 
                        raw.write( theData ); 
                        raw.flush(); 
                    }
                    else 
                    { 
                        // can't find the file
                        if ( version.startsWith( "HTTP" ) ) 
                        {  
                            // send a MIME header
                            out.write( "HTTP 1.0 404 File Not Found\r\n" );
                            Date now = new Date();
                            out.write( "Date: " + now + "\r\n" );
                            out.write( "Server: JHTTP 1.0\r\n" );	
                            out.write( "Content-type: text/html\r\n\r\n" );
                        }
                        out.write( "<HTML>\r\n" );
                        out.write( "<HEAD><TITLE>File Not Found</TITLE>\r\n" );
                        out.write( "</HEAD>\r\n" );
                        out.write( "<BODY>" );
                        out.write( "<H1>HTTP Error 404: File Not Found</H1>\r\n" );
                        out.write( "</BODY></HTML>\r\n" );
                        out.flush();
                    }
                }
                else 	// method does not equal "GET"
                { 
                    if ( version. startsWith( "HTTP" ) ) 
                    {  
                        // send a MIME header
                        out.write( "HTTP 1.0 501 Not Implemented\r\n" );
                        Date now = new Date();
                        out. write( "Date: " + now + "\r\n" );
                        out. write( "Server: JHTTP 1.0\r\n" );
                        out. write( "Content-type: text /html \r\n\r\n" ); 
                    }
                    out.write( "<HTML>\r\n" );
                    out.write( "<HEAD><TITLE>Not Implemented</TITLE>\r\n" ); 
                    out.write( "</HEAD>\r\n" ); 
                    out.write( "<BODY>" );
                    out.write( "<H1>HTTP Error 501: Not Implemented</H1>\r\n" ); 
                    out.write( "</BODY></HTML>\r\n" ); 
                    out.flush();
                }
            }
            catch ( IOException e ) 
            {
            }
            finally 
            { 
                try 
                {
                    connection.close(); 
                }
                catch ( IOException e ) {}
            }
        }

    }	// run
    
    public static String guessContentTypeFromName( String name ) 
    { 
        if ( name.endsWith( ".htm" ) || name.endsWith( ".html" ) ) 
        {
            return "text/html"; 
        } 
        else if ( name.endsWith( ".txt " ) || name.endsWith( ".java" ) ) 
        {
            return "text/plain";
        }
        else if ( name.endsWith( ".gif" ) )   
        {
            return "image/gif"; 
        } 
        else if ( name.endsWith( ".class" ) ) 
        {
            return "application/octet-stream"; 
        } 
        else if ( name.endsWith( ".jpg" ) || name.endsWith( ".jpeg" ) ) 
        {
            return " image/jpeg"; 
        } 
        else 
        {
            return "text/plain";
        }
    }
                        
} 	// RequestProcessor

