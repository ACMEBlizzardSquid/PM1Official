package soen487.retriever.services;

import java.net.MalformedURLException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;

import utils.marfcat.MarfcatIn;
import utils.marfcat.MarfcatInItem;


/**
 * WSDLRetriever in WebService
 * @author shake0
 *
 */
@WebService
public class WSDLRetrieveService {
	/*
	In order for this to work, in the first pass
	will only get the WSDL listing.
	Therefore for downloading N WDSL, the limit is N + 1
	*/
	public static final int    DEFAULT_LIMIT = 1;
	public static final String DEFAULT_URL   = "http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/";
	
        @WebMethod(operationName = "retrieveWSDLs")
	public List<String> retrieveWSDLs(@WebParam(name = "wsdlURI") String pstrSeedURI, @WebParam(name = "limit") Integer piLimit) throws MalformedURLException, IOException {
		// Input validation
		pstrSeedURI = (pstrSeedURI != null)?pstrSeedURI:DEFAULT_URL;
		piLimit     = (piLimit != null && piLimit > 0)?piLimit:DEFAULT_LIMIT;
		
		// Marfcat
		MarfcatIn marf = new MarfcatIn();
		
		// Execution
		WSDLRetriever parser;
		try {
			parser = new WSDLRetriever(pstrSeedURI, piLimit);
			new ForkJoinPool().invoke(parser);
			List<String> localfiles = new LinkedList<>();
			for(MarfcatInItem item : parser.getWSDLDescription()){
				localfiles.add(item.getPath());
				marf.addItem(item);
			}
			marf.appendWithJAXB("MARFCAT_IN.xml");
			return localfiles;
		} catch (NoSuchMethodException | SecurityException | InterruptedException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
	}
}
