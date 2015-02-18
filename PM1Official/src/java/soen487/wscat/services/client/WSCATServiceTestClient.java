package soen487.wscat.services.client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import soen487.pm1.WSDL;

public class WSCATServiceTestClient {

    public static void main(String[] args) throws IOException_Exception, InterruptedException_Exception{
        WSCATServiceService wscatServiceService = new WSCATServiceService();
        WSCATService wscatService = wscatServiceService.getWSCATServicePort();
        
        String wsdl = "";
        try {
            wsdl = WSDL.getWSDL("http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/5-check.wsdl");
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(WSCATServiceTestClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(WSCATServiceTestClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WSCATServiceTestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        wscatService.submitWSDLToAnalyze(wsdl);
    }
}
