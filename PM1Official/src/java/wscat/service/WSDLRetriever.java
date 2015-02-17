package wscat.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import soen487.xml.WSDLParser;

import wscat.parser.Parser;
import wscat.parser.Reduced;

public class WSDLRetriever extends Parser {

	public WSDLRetriever(String url, int downloadLimit) throws MalformedURLException,
			NoSuchMethodException, SecurityException {
		super(url);
		this.wsdl        = new LinkedList<String>();
		this.wsdlDoc     = new LinkedList<WSDLDescription>();
		setSearchDepth(downloadLimit + 1);
	}

	public WSDLRetriever(WSDLRetriever os) {
		super(os);
		this.wsdl        = new LinkedList<String>();
		this.wsdlDoc     = new LinkedList<WSDLDescription>();
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
				saveInTmp(page, fileName);
				getDescriptors(fileName, page);
			} catch (IOException e) {
				System.err.println("File not saved");
				e.printStackTrace();
			}
		}
		return true;
	}
	
	//--------------------------------------------
	// WSDL
	
	/*
	TODO: Getting all wsdl:documentation is not usefull
	some of them describe methods but if there are any, there is one
	for sure that describe the service. It's usually at the end, nested
	in the node <wsdl:service>.
	*/
	// I'm assuming a well formatted WSDL, otherwise why bother.
	protected void getDescriptors(String fileName, String page){
                WSDLDescription doc = new WSDLDescription(fileName);
                StringBuilder sb = new StringBuilder();
                WSDLParser wsdlParser = new WSDLParser(page);
                doc.descriptions.add(wsdlParser.printDocumentation(wsdlParser.getServiceDocumentation()));
                wsdlDoc.add(doc);
	}
	
	protected void saveInTmp(String page, String fileName) throws IOException{
                if(!Files.exists(Paths.get("/tmp"))){
                    Files.createDirectory(Paths.get("/tmp"));
                }
		Files.write(Paths.get("/tmp", fileName), page.getBytes(), 
				StandardOpenOption.CREATE,
				StandardOpenOption.TRUNCATE_EXISTING, // Remove to detect collisions.
				StandardOpenOption.WRITE);
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
	
	public List<WSDLDescription> getWSDLDescription(){
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
	private @Reduced List<WSDLDescription> wsdlDoc;
	
	public class WSDLDescription {
		public WSDLDescription(String fileName) {
			this.fileName     = fileName;
			this.descriptions = new LinkedList<String>();
		}
		public String       fileName;
		public List<String> descriptions;
	}
}
