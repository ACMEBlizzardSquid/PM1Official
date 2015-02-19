package soen487.wscat.services.client;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import soen487.pm1.WSDL;

public class WSCATServiceTestClient {

    public static void main(String[] args) throws IOException_Exception, InterruptedException_Exception, ParserConfigurationException_Exception, SAXException_Exception{
        WSCATServiceService wscatServiceService = new WSCATServiceService();
        WSCATService wscatService = wscatServiceService.getWSCATServicePort();
        
        String wsdlRepo = "http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/";
        String wsdlUri = "http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/5-check.wsdl";
        String wsdl = "";
        try {
            wsdl = WSDL.getWSDL(wsdlRepo);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(WSCATServiceTestClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(WSCATServiceTestClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WSCATServiceTestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<String> wsdls = wscatService.submitWSDLRepo(wsdlRepo);
        wscatService.trainOnURI(wsdlUri);
        
        wscatService.submitWSDLToAnalyze(wsdlUri);
    }
}
