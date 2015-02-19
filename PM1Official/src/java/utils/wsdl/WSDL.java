package utils.wsdl;

import java.io.IOException;
import java.io.StringWriter;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * This class will provide functionality for WSDL documents
 * @author connorbode
 */
public class WSDL {
    
    /**
     * Returns the documentation contents from a WSDL document;
     * @param doc A WSDL document
     * @return The contents of the service documentation node
     */
    public static String getDocumentation (Document doc) {
        String documentation = "";
        NodeList nodes = doc.getElementsByTagNameNS("http://schemas.xmlsoap.org/wsdl/", "service");
        
        for (int i = 0; i < nodes.getLength(); i += 1) {
            Node node = nodes.item(i);
            Element elem = (Element) node;
            NodeList otherNodes = elem.getElementsByTagNameNS("*", "documentation");
            
            for (int j = 0; j < otherNodes.getLength(); j += 1) {
                Node otherNode = otherNodes.item(j);
                Element otherElem = (Element) otherNode;
                documentation += otherElem.getTextContent();
            }
        }
        
        return documentation;
    }
    
    /**
     * Returns the documentation contents from a string which represents a WSDL
     * document
     * @param doc A string representation of the WSDL document
     * @return The contents of the service documentation node
     */
    public static String getDocumentation (String doc)
            throws ParserConfigurationException, SAXException, IOException {
        Document d = soen487.xml.XMLParser.parseDOM(doc);
        String documentationString = getDocumentation(d);
        return documentationString;
    }
}
