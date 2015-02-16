/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soen487.pm1;

import java.io.IOException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xmlsoap.schemas.wsdl.ObjectFactory;
import org.xmlsoap.schemas.wsdl.TBinding;
import org.xmlsoap.schemas.wsdl.TDefinitions;
import org.xmlsoap.schemas.wsdl.TExtensibleDocumented;
import static soen487.pm1.RSS.getRss;
import soen487.xml.XMLParser;
import soen487.xml.XMLReader;

/**
 *
 * @author c_bode
 */
public class WSDL {
    
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
        String result = getWSDL("http://users.encs.concordia.ca/~s487_4/examples/soap/faultmessage/faultSample.wsdl");
        System.out.println(result);
    }
    
    public static String getWSDL(String url)  throws ParserConfigurationException, SAXException, IOException{
        
        String result = "";
        try{
            JAXBContext jc = JAXBContext.newInstance(TDefinitions.class);
            
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            ObjectFactory objectFactory = new ObjectFactory();
            //JAXBElement<TBinding> definitions = objectFactory.createDefinitions(tDefinitions);
            JAXBElement<TDefinitions> definitions = (JAXBElement<TDefinitions>) unmarshaller.unmarshal(new URL(url));
               
            //return definitions.getValue().getAnyTopLevelOptionalElement().toString() == null ? "true" : "false";
            return definitions.getValue().getAnyTopLevelOptionalElement().toString();
            /*Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(definitions, System.out);*/
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
        
        
        return result;
    }
    
}
