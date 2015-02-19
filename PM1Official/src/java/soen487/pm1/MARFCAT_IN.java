/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soen487.pm1;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import soen487.xml.XMLParser;
import soen487.xml.XMLReader;

/**
 *
 * @author connorbode
 */
public class MARFCAT_IN {
    
    public static void main(String[] args) 
            throws ParserConfigurationException, SAXException, IOException {
        
        String result = run();
        System.out.println(result);
    }
    
    public static String run () 
            throws ParserConfigurationException, SAXException, IOException {
        
        Document doc = XMLReader.readAsDOM("http://users.encs.concordia.ca/~s487_4/project/marfcat-in.xml", "soen487-w15", "H-633");
        String result = XMLParser.prettyPrint(doc.getDocumentElement());
        return result;
    }
}
