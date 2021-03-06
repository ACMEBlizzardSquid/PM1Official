package soen487.xml.service;

import java.io.File;
import java.io.IOException;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.namespace.QName;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import soen487.xml.XMLReader;

@WebService
public class EchoXMLReader {
	public static enum XMLType { RSS, NN, MARFCAT_IN, MARFCAT_OUT, WSDL }

	@WebMethod
	public String getType(@WebParam(name="type") String type) 
			throws ParserConfigurationException, SAXException, IOException{
            
            String[] args = new String[]{};
            
            switch (type) {
                case "MARFCAT_IN":
                    return soen487.pm1.MARFCAT_IN.run();
                case "MARFCAT_OUT":
                    return soen487.pm1.MARFCAT_OUT.run();
                case "NN":
                    return soen487.pm1.NN.run();
                case "RSS":
                    return soen487.pm1.RSS.run();
                case "WSDL":
                    return soen487.pm1.WSDL.run();
            }
            
            return "Invalid type!  Type must be one of the following: "
                    + "[\"MARFCAT_IN\", \"MARFCAT_OUT\", \"NN\", \"RSS\", \"WSDL\"]";
	}
	
	@WebMethod
	public String readFromUrl(@WebParam(name="url") String url) 
			throws ParserConfigurationException, SAXException, IOException{
		return prettyPrint(XMLReader.readAsDOM(url));
	}
	
	private String prettyPrint(Document doc){
		String doc_string = new String();
		String indentation = new String();
		Element root = doc.getDocumentElement();
		
		doc_string += indentation + root.getNodeName() + "\n";
		NodeList nl = root.getChildNodes();
		indentation += "  ";
		for (int i = 0; i < nl.getLength(); i++) {
			doc_string += _prettyPrint(nl.item(i), indentation);
		}
		indentation = indentation.substring(2);
		return doc_string;
	}
	
	private String _prettyPrint(Node node, String indentation){
		String doc_string = new String();
		
		if(node.getNodeType() == Node.TEXT_NODE)
			doc_string += indentation + node.getNodeValue() + "\n";
		else
			doc_string += indentation + node.getNodeName() + "\n";
		NodeList nl = node.getChildNodes();
		indentation += "  ";
		for (int i = 0; i < nl.getLength(); i++) {
			doc_string += _prettyPrint(nl.item(i), indentation);
		}
		indentation = indentation.substring(2);
		return doc_string;
	}
}
