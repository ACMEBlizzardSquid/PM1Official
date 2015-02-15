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
	public static final int DEFAULT_LIMIT = 1;
	
	public List<String> retrieveWSDLs(String pstrSeedURI) throws MalformedURLException{
		WSDLRetriever parser;
		try {
			// By default the Parser do not fork
			// By default the Parser do not use history
			parser = new WSDLRetriever(pstrSeedURI, DEFAULT_LIMIT);
			new ForkJoinPool().invoke(parser);
			
			/*
			TODO: From the parser object you should be able
			to access all the informations that you have grabbed
			after the "invoke".
			
			It's up to you to build an index in MARFCAT-IN.
			getWSDLDescription gives you a list of "struct Documentation".
			that allowed me to build a one to many relationship (filename -> {description, ...})
			
			If you are crazy, you can do recursive search using reflection
			in order to support Map.class (in parser). I advice you that once in parser.java
			it's like the limbo in Inception... (Do you think we can prioritize links for a better search? lool)
			*/
			
			return parser.getWSDL();
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> retrieveWSDLsS(String pstrSeedURI, int piLimit) throws MalformedURLException{
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
