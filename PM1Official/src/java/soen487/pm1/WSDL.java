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
import org.apache.jasper.tagplugins.jstl.ForEach;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xmlsoap.schemas.wsdl.ObjectFactory;
import org.xmlsoap.schemas.wsdl.TBinding;
import org.xmlsoap.schemas.wsdl.TDefinitions;
import org.xmlsoap.schemas.wsdl.TDocumented;
import org.xmlsoap.schemas.wsdl.TExtensibleDocumented;
import org.xmlsoap.schemas.wsdl.TImport;
import org.xmlsoap.schemas.wsdl.TMessage;
import org.xmlsoap.schemas.wsdl.TOperation;
import org.xmlsoap.schemas.wsdl.TPortType;
import org.xmlsoap.schemas.wsdl.TService;
import org.xmlsoap.schemas.wsdl.TTypes;
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
            JAXBElement<TDefinitions> definitions = (JAXBElement<TDefinitions>) unmarshaller.unmarshal(new URL(url));
               
            for (TDocumented documented : definitions.getValue().getAnyTopLevelOptionalElement()) {
                switch (documented.getClass().getSimpleName()) {
                    case "TImport":
                        TImport tImport = (TImport) documented;
                        break;
                    case "TTypes":
                        TTypes tTypes = (TTypes) documented;
                        System.err.println(tTypes.getAny().toString());
                        break;
                    case "TMessage":
                        TMessage tMessage = (TMessage) documented;
                        System.err.println(tMessage.getPart().toString());
                        break;
                    case "TPortType":
                        TPortType tPortType = (TPortType) documented;
                        System.err.println(tPortType.getOperation().toString());
                        break;
                    case "TBinding":
                        TBinding tBinding = (TBinding) documented;
                        System.err.println(tBinding.getAny().toString());
                        break;
                    case "TOperation":
                        TOperation tOperation = (TOperation) documented;
                        System.err.println(tOperation.getAny().toString());
                        break;
                    case "TService":
                        TService tService = (TService) documented;
                        System.err.println(tService.getAny().toString());
                        break;
                }
            }
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
        
        
        return result;
    }
    
}
