package wscat.service;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import java.io.File;
import java.net.URI;
import javax.jws.WebService;
import org.w3c.dom.Document;
import utils.marfcat.MarfcatIn;

@WebService
public class WSCATService {

    //TODO: should return MarfcatOut
    public void submitWSDLToAnalyze(File wsdl) {
    }

    public MarfcatIn submitWSDLRepo(URI repoURI) {
        return null;
    }
    
    public void trainOnFile(File wsdl) {
        trainOnFile(new DocumentImpl());
    }
    
    public void trainOnFile(URI wsdlURI) {
        trainOnFile(new DocumentImpl());
    }
    
    private void trainOnFile(Document doc) {
        
    }
}
