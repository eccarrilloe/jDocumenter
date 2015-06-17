package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import server.servlet.DbServlet;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init( final ServletConfig sc ) {
		
		try {
			super.init( sc );
			
			view = sc.getInitParameter( "view" );
			System.err.println( "TestServlet::init(): view=" + view );
			
		} catch ( Exception exc ) {
			
			System.err.println( "TestServlet::init(): " + exc );
		}
	}
	
	@Override
	public void doGet( 
			final HttpServletRequest req, final HttpServletResponse res ) 
					throws ServletException, IOException {
		
		System.err.println( "TestServlet::doGet():");
		
		res.setContentType( "text/html" );
		res.setCharacterEncoding( "UTF-8" );
		
		String value = req.getParameter( "param" );
		
		PrintWriter writer = res.getWriter();
		writer.print( "<H1>date=!!" + value + "</H1>" );
	}
	
	@Override
	public void doPost( 
			final HttpServletRequest req, final HttpServletResponse res ) 
					throws ServletException, IOException {
		
		
		
		res.setContentType( "text/html");
		res.setCharacterEncoding( "UTF-8" );
		
		PrintWriter writer = res.getWriter();
    
		String usr = req.getParameter( LAT );
		String pwd = req.getParameter( LONG );
		
		System.err.println( "TestServlet::doPost():"+usr);
		
		Connection conn = DbServlet.getConnection();
		
		System.err.println( "TestServlet::doPost():"+pwd);
		
		try {
			Statement stat = conn.createStatement();
			ResultSet rs = stat.executeQuery( "select * from " 	+ DbServlet.TEST_TABLE	+ " WHERE NAME='" + usr + "' AND " + "PASSWORD='" + pwd	+ "'");
			String r = "No encontrado";
			if ( rs.next() ) {
				
				r = "OK";
			}
			
			conn.close();
			
			System.err.println( r);
			
			ServletContext sc = getServletContext();
			
			String rjsp = view 
					+ "?" 
					+ "status"
					+ "="
					+ r;
			
			RequestDispatcher rq = sc.getRequestDispatcher( rjsp );
			rq.forward( req, res );
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private String view;
	
	public static final String USER = "USR";
	public static final String PSWD = "PWD";
	public static final String LAT = "LAT";
	public static final String LONG = "LON";
}














