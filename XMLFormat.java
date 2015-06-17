import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;


public class XMLFormat {
    public static String format(String xml) {

        try {
            final InputSource src = new InputSource(new StringReader(xml));
            final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();
            final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));

            final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
            final LSSerializer writer = impl.createLSSerializer();
            
            LSOutput lsOutput =  impl.createLSOutput();
            lsOutput.setEncoding("UTF-8");
            Writer stringWriter = new StringWriter();
            lsOutput.setCharacterStream(stringWriter);
            
            writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE); // Set this to true if the output needs to be beautified.
            writer.getDomConfig().setParameter("xml-declaration", Boolean.TRUE); // Set this to true if the declaration is needed to be outputted.
    		writer.write(document, lsOutput);     
    		String result = stringWriter.toString();
    		
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
