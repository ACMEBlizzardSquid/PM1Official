/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soen487.pm1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.jasper.tagplugins.jstl.ForEach;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xmlsoap.schemas.wsdl.ObjectFactory;
import org.xmlsoap.schemas.wsdl.TBinding;
import org.xmlsoap.schemas.wsdl.TDefinitions;
import org.xmlsoap.schemas.wsdl.TDocumented;
import org.xmlsoap.schemas.wsdl.TExtensibleDocumented;
import org.xmlsoap.schemas.wsdl.TImport;
import org.xmlsoap.schemas.wsdl.TMessage;
import org.xmlsoap.schemas.wsdl.TOperation;
import org.xmlsoap.schemas.wsdl.TPort;
import org.xmlsoap.schemas.wsdl.TPortType;
import org.xmlsoap.schemas.wsdl.TService;
import org.xmlsoap.schemas.wsdl.TTypes;
import soen487.xml.WSDLParser;
import soen487.xml.XMLParser;
import soen487.xml.XMLReader;

/**
 *
 * @author c_bode
 */
public class WSDL {
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
        
        Document doc = XMLReader.readAsDOM("http://users.encs.concordia.ca/~s487_4/examples/soap/faultmessage/faultSample.wsdl");
        String result = XMLParser.prettyPrint(doc.getDocumentElement());
        System.out.println(result);
//        String result = getWSDL("http://users.encs.concordia.ca/~s487_4/examples/soap/faultmessage/faultSample.wsdl");
//        String result = getWSDL("http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/5-check.wsdl");
//        System.out.println(result);
    }
    
    public static String getWSDL(String url)  throws ParserConfigurationException, SAXException, IOException{
        String result = "";
        try {
            JAXBContext jc = JAXBContext.newInstance(TDefinitions.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            ObjectFactory objectFactory = new ObjectFactory();
            JAXBElement<TDefinitions> definitions = (JAXBElement<TDefinitions>) unmarshaller.unmarshal(new URL(url));
            
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            marshaller.marshal(definitions, baos);
            result = baos.toString("UTF-8");
            
            //TODO: add pretty print
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
}
