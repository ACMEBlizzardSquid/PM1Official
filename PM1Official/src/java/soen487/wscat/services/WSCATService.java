package soen487.wscat.services;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import soen487.retriever.services.client.*;
import utils.marfcat.MarfcatIn;

@WebService
public class WSCATService {

    //TODO: should return MarfcatOut
    @WebMethod(operationName = "submitWSDLToAnalyze")
    public void submitWSDLToAnalyze(@WebParam(name = "wsdlFile") File wsdlFile) {
        //TODO: remove --debug ?
        String[] args = {"--batch-ident", "test-quick-marf-cve marfcat-in.xml", "-nopreprep", "-raw", "-fft", "-cheb", "--debug"};
        marf.apps.MARFCAT.MARFCATApp.main(args);
    }

    /*
     Submit a repository URI where WSDLs are linked (for now will
     simply call WSDLRetrieverService’s default retrieveWSDLs() web method and return 
     MARFCAT-IN file or its entries on success.
     */
    @WebMethod(operationName = "submitWSDLRepo")
    public MarfcatIn submitWSDLRepo(@WebParam(name = "repoURI") URI repoURI) {
        WSDLRetrieveServiceService wsdlRetrieverServiceService = new WSDLRetrieveServiceService();
        WSDLRetrieveService wsdlRetrieverService = wsdlRetrieverServiceService.getWSDLRetrieveServicePort();
        
        try {
            wsdlRetrieverService.retrieveWSDLs(null, null);
        } catch (MalformedURLException_Exception ex) {
            Logger.getLogger(WSCATService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //TODO: get and return MarfcatIn that Retrieve will return
        return null;
    }

    @WebMethod(operationName = "trainOnFile")
    /*
    Can train on a file OR a file from URI
    */
    public void trainOnFile(@WebParam(name = "wsdlFile") File wsdlFile, @WebParam(name = "wsdlURI") URI wsdlURI) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document wsdlDoc = null;
        
        if(wsdlFile != null) {
            wsdlDoc = docBuilder.parse(wsdlFile);
        } else if(wsdlURI != null) {
            wsdlDoc = docBuilder.parse(wsdlURI.toString());
        }
        
        trainOnFile(wsdlDoc);
    }

    private void trainOnFile(Document doc) {
        //TODO: remove --debug ?
        
        //TODO: doc to marfcat-in.xml file
        
        String[] args = {"--train", "-nopreprep", "-raw", "-fft", "-eucl marfcat-in.xml", "--debug"};
        marf.apps.MARFCAT.MARFCATApp.main(args);
    }
}
