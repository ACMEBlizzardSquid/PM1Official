package soen487.wscat.services;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URI;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.w3c.dom.Document;
import utils.marfcat.Marfcat;
import utils.marfcat.MarfcatIn;
import utils.marfcat.MarfcatInItem;
import soen487.retriever.services.client.*;

@WebService
public class WSCATService {

    //TODO: should return MarfcatOut
    @WebMethod(operationName = "submitWSDLToAnalyze")
    public String submitWSDLToAnalyze(@WebParam(name = "wsdlFile") String wsdlFile) 
            throws IOException, InterruptedException {
        String localPath = utils.io.FileDownloader.download(wsdlFile);
        MarfcatIn marfIn = new MarfcatIn();
        Marfcat marf = new Marfcat();
        marfIn.addItem(new MarfcatInItem(localPath));
        String marfInPath = marfIn.write();
        String MARFCAT_OUT = marf.analyze(marfInPath);    
        BufferedReader br = new BufferedReader(new FileReader(MARFCAT_OUT));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        br.close();
        return sb.toString();
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
    public void trainOnFile(@WebParam(name = "wsdlFile") File wsdlFile, @WebParam(name = "wsdlURI") URI wsdlURI) {
        trainOnFile(new DocumentImpl());
    }

    private void trainOnFile(Document doc) {

    }
}
