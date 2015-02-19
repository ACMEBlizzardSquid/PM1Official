package soen487.retriever.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import soen487.xml.WSDLParser;
import utils.marfcat.MarfcatInItem;
import utils.parser.Parser;
import utils.parser.Reduced;


/**
 * This implamentation of the parser is optimized 
 * to grab documentation from the wsdl:documentation node
 * 
 * This is used as a general parser and result effective
 * against "http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/"
 * @author shake0
 *
 */
public class WSDLRetriever extends Parser {

	public WSDLRetriever(String url, int downloadLimit) throws MalformedURLException,
			NoSuchMethodException, SecurityException, IOException {
		super(url);
		this.wsdl        = new LinkedList<String>();
		this.wsdlDoc     = new LinkedList<MarfcatInItem>();
		setSearchDepth(downloadLimit + 1);
	}

	public WSDLRetriever(WSDLRetriever os) {
		super(os);
		this.wsdl        = new LinkedList<String>();
		this.wsdlDoc     = new LinkedList<MarfcatInItem>();
	}
	
	@Override
	protected Parser clone() {
		return new WSDLRetriever(this);
	}

	@Override
	protected boolean parsePage(URL domain, String page) {
		final String path = domain.getPath();
		if(path.endsWith("WSDL") || path.endsWith("wsdl")){
			try {
				String fileName = generateFileName(domain.toString());
				System.out.println("Saving "+fileName+" in /tmp");
				String savedPath = saveInTmp(page, fileName);
				String description = getDescriptors(fileName, page);
				System.out.println(description);
                wsdlDoc.add(new MarfcatInItem(savedPath, "CVE-2009-2902"));
			} catch (IOException | InterruptedException e) {
				System.err.println("File not saved");
				e.printStackTrace();
			}
		}
		return true;
	}
	
	//--------------------------------------------
	// WSDL
	
	protected String getDescriptors(String fileName, String page) {
		//WSDLParser wsdlParser = new WSDLParser(page);
		//String documentation = wsdlParser.printDocumentation(wsdlParser.getServiceDocumentation());
		String documentation = getDescriptorsREX(page);
		if(documentation != null)
			return documentation;
		
		return "";
	}
	
	// Fallback wsdl:documentation grabber
	private String getDescriptorsREX(String page){
		String documentationRegex = "<wsdl:service .*>.*(<wsdl:doc.*>(.*)</wsdl:doc.*>).*</wsdl:service";
		Matcher match = Pattern.compile(documentationRegex, Pattern.DOTALL).matcher(page);
		if(match.find())
			return match.group(2);
		return "";
	}
	
	protected String saveInTmp(String page, String fileName) throws IOException{
        if(!Files.exists(Paths.get("/tmp"))){
            Files.createDirectory(Paths.get("/tmp"));
        }
        Path path = Paths.get("/tmp", fileName);
		Files.write(path, page.getBytes(), 
				StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING, // Remove to detect collisions.
				StandardOpenOption.WRITE);
                return path.toString();
	}
	
	protected String generateFileName(String name){
		String bkName = UUID.randomUUID().toString();
		try {
			MessageDigest encoder  = MessageDigest.getInstance("MD5");
			StringBuilder sbuilder = new StringBuilder();
			byte[] bName = encoder.digest(name.getBytes());
			for (int i = 0; i < bName.length; i++) sbuilder.append(String.format("%02x", bName[i]));
			bkName = sbuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Name cannot be generated!\n\tUsing: "+bkName);
		}
		return bkName;
	}

	//--------------------------------------------
	// Data
	
	public List<String> getWSDL(){
		return this.wsdl;
	}
	
	public List<MarfcatInItem> getWSDLDescription(){
		return this.wsdlDoc;
	}
	
	//--------------------------------------------
	// Filters
	
	@Override
	protected void parseLinks(URL origin, List<String> anchors) {
		/*
		Propagate search only to WSDL file. - subdirectory are ignored
		*/
		final String wsdlRegex = "(http|https)://[-a-zA-Z0-9+&/?=~_:,.]*(\\.|\\?)(wsdl|WSDL)";
		for(ListIterator<String> it = anchors.listIterator(); it.hasNext(); ){
			String link = it.next();
			if(Pattern.matches(wsdlRegex, link) && ! wsdl.contains(link)){
				wsdl.add(link);
			}
			else
				it.remove();
		}
	}

	
	private static final long serialVersionUID = 1L;
	
	private @Reduced List<String>          wsdl;
	private @Reduced List<MarfcatInItem>   wsdlDoc;
}
