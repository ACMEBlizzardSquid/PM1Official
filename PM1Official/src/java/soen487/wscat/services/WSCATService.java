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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import utils.marfcat.Marfcat;
import org.xml.sax.SAXException;
import soen487.retriever.services.client.*;
import utils.marfcat.MarfcatIn;
import utils.marfcat.MarfcatInItem;
import soen487.retriever.services.client.*;
import java.lang.InterruptedException;

@WebService
public class WSCATService {

    /**
     * Submits a WSDL file to analyze 
     * @param wsdlFile The string representation of the WSDL file
     * @return The MARFCAT_IN output
     * @throws IOException
     * @throws InterruptedException 
     */
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

    
    /**
     * Trains Marfcat on a provided WSDL file
     * @param wsdlFile A string representation of the WSDL file 
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @throws InterruptedException 
     */
    @WebMethod(operationName = "trainOnWSDL")
    public void trainOnWSDL (@WebParam(name = "wsdlFile") String wsdlFile) 
            throws ParserConfigurationException, SAXException, IOException,
                    InterruptedException {
        
        // format MARFCAT_IN file
        String localPath = utils.io.FileDownloader.download(wsdlFile);
        MarfcatIn marfIn = new MarfcatIn();
        String documentation = utils.wsdl.WSDL.getDocumentation(wsdlFile);
        marfIn.addItem(new MarfcatInItem(localPath, "CVE-2009-3548"));
        String marfPath = marfIn.write();
        
        // train on MARFCAT_IN file
        Marfcat marf = new Marfcat();
        marf.train(marfPath);
    }

    private void trainOnFile(Document doc) {
        //TODO: remove --debug ?
        
        //TODO: doc to marfcat-in.xml file
        
        String[] args = {"--train", "-nopreprep", "-raw", "-fft", "-eucl marfcat-in.xml", "--debug"};
        marf.apps.MARFCAT.MARFCATApp.main(args);
    }
}
