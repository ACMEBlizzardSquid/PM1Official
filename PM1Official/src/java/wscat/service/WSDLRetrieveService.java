package wscat.service;

import java.net.MalformedURLException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import javax.jws.WebService;

@WebService
public class WSDLRetrieveService {
	/*
	In order for this to work, in the first pass
	will only get the WSDL listing.
	Therefore for downloading N WDSL, the limit is N + 1
	*/
	public static final int    DEFAULT_LIMIT = 1;
	public static final String DEFAULT_URL   = "http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/";
	
	public List<String> retrieveWSDLs(String pstrSeedURI) throws MalformedURLException{
		return retrieveWSDLsS(pstrSeedURI, DEFAULT_LIMIT);
	}
	
	public List<String> retrieveWSDLsS(String pstrSeedURI, int piLimit) throws MalformedURLException{
		// Input validation
		pstrSeedURI = (pstrSeedURI != null)?pstrSeedURI:DEFAULT_URL;
		piLimit     = (piLimit > 0)?piLimit:DEFAULT_LIMIT;
		
		// Execution
		WSDLRetriever parser;
		try {
			parser = new WSDLRetriever(pstrSeedURI, piLimit +1);
			new ForkJoinPool().invoke(parser);
			return parser.getWSDL();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}
