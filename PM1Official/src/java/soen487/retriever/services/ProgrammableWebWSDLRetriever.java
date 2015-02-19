package soen487.retriever.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import java.util.concurrent.ForkJoinPool;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;

import utils.marfcat.MarfcatIn;
import utils.marfcat.MarfcatInItem;
import utils.parser.Parser;
import utils.parser.Reduced;


/**
 * This is a parser that aim at 
 * "http://www.programmableweb.com/apis/directory"
 * 
 * It is not optimized, we are just brute forcing all the links
 * 
 * @author shake0
 *
 */
public class ProgrammableWebWSDLRetriever extends Parser {


	public ProgrammableWebWSDLRetriever(String url)
			throws MalformedURLException, NoSuchMethodException,
			SecurityException {
		super(url);
		this.endpoint = new LinkedList<>();
		this.category = new LinkedList<>();
		this.pageNumber = 0;
	}
	
	public ProgrammableWebWSDLRetriever(ProgrammableWebWSDLRetriever p) {
		super(p);
		this.endpoint = new LinkedList<>();
		this.category = new LinkedList<>();
		this.pageNumber = p.pageNumber + LAST_PAGE / getSearchDepth();
	}

	@Override
	protected Parser clone() {
		return new ProgrammableWebWSDLRetriever(this);
	}

	@Override
	protected boolean parsePage(URL domain, String page) {
		String categoryRegex = "Primary Category</label>\\s*.span.\\s*.a href=\"([a-zA-Z0-9/]*)\"";
		Matcher match = Pattern.compile(categoryRegex, Pattern.DOTALL).matcher(page);
		if(match.find()){
			System.out.println("Category:" + match.group(0));
			this.categoryString = match.group(1);
		}
		return true;
	}

	@Override
	protected void parseLinks(URL origin, List<String> anchors) {
		if(isListingPage(origin)){
			ListIterator<String> it = anchors.listIterator();
			while(it.hasNext()){
				if(! isServicePage(it.next()))
					it.remove();
			}
			if(++pageNumber < LAST_PAGE)
				anchors.add(makeDirectoryURL(pageNumber));
		}
		else{
			ListIterator<String> it = anchors.listIterator();
			String url, wsdl=null, category=null;
			while(it.hasNext()){
				url = it.next();
				if(isWSDLLink(url))     wsdl = url;
				if(isCategoryLink(url)) category = url;
			}
			if(wsdl != null){
				this.endpoint.add(wsdl);
				this.category.add(getCategory(this.categoryString));
			}
			anchors.clear();
		}
	}
	
	//--------------------------------------------
	// Listing
	
	private boolean isListingPage(URL domain){
		if(domain.getFile().contains("apis/directory"))
			return true;
		return false;
	}
	
	private int getListingPageNumber(URL domain){
		if(domain.getQuery() == null)
			return 0;
		
		int start = domain.getQuery().indexOf('=');
		return Integer.valueOf(domain.getQuery().substring(start + 1));
	}
	
	private String makeDirectoryURL(int page){
		String base = "http://www.programmableweb.com/apis/directory";
		if(page > 0)
			base += "?page="+page;
		return base;
	}
	
	//--------------------------------------------
	// Specs
	
	private boolean isServicePage(String url){
		return url.startsWith("http://www.programmableweb.com/api/");
	}
	
	private boolean isWSDLLink(String url){
		if(url.contains("WSDL") || url.contains("wsdl"))
			return true;
		return false;
	}
	
	private boolean isCategoryLink(String url){
		if(url.contains("/category/"))
			return true;
		return false;
	}
	
	private String getCategory(String url){
		if(url != null)
			return url.substring(url.lastIndexOf('/') + 1);
		return "";
	}
	
	//--------------------------------------------
	// Data
	
	public List<String> getWSDLURL(){
		return this.endpoint;
	}
	
	public List<String> getCategories(){
		return this.category;
	}
	
	//--------------------------------------------
	// Downloading
	
	protected static boolean download(String url, String filename) throws IOException{
		URL www = new URL(url);
		FileOutputStream fos = new FileOutputStream(filename);
		InputStream is = null;
		try{
			is = www.openStream();
		} catch(IOException e){ System.err.println(e.getLocalizedMessage()); return false; } //URL connections problem
		int ic = 0;
		while((ic = is.read()) != -1)
			fos.write(ic);
		return true;
	}
	
	private static String generateFileName(String name){
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

	private @Reduced List<String> endpoint;
	private @Reduced List<String> category;
	private int pageNumber;
	private String categoryString;
	
	private static final int LAST_PAGE   = 115;
	
	//--------------------------------------------
	// Main
	
	private static final String SEACH_ROOT     = "http://www.programmableweb.com/apis/directory";
	private static final String DEFAULT_FOLDER = "/tmp";
	private static final String DEFAULT_MARF   = "MARFCAT_IN.xml";
	
	public static void main(String[] args) {
		String downloadpath = (args.length<=0)?DEFAULT_FOLDER:args[0];
		String marfpath     = (args.length<=1)?DEFAULT_MARF  :args[1];
		String chunkSize       = (args.length<=2)?"10":args[2];
		String searchDepth     = (args.length<=3)?"50":args[3];
		System.out.println("Download Dir: "+downloadpath);
		System.out.println("Marf Dir: "+marfpath);
		try {
			MarfcatIn marf = new MarfcatIn();
			ProgrammableWebWSDLRetriever parser = new ProgrammableWebWSDLRetriever(SEACH_ROOT);
			parser.setChunkSize(Integer.valueOf(chunkSize)); // Enabling multithreading
			parser.setSearchDepth(Integer.valueOf(searchDepth)); // Urls per thread
			new ForkJoinPool().invoke(parser);
			ListIterator<String> wsdlIt     = parser.getWSDLURL().listIterator();
			ListIterator<String> categoryIt = parser.getCategories().listIterator();
			while(wsdlIt.hasNext()){
				String wsdl     = wsdlIt.next();
				String cate     = categoryIt.next();
				String filename = generateFileName(wsdl);
				String absPath  = downloadpath + "/" + filename;
				if(download(wsdl, downloadpath + "/" + filename)){
					System.out.println("Saving "+absPath);
					marf.addItem(new MarfcatInItem(absPath, cate));
				}
			}
			marf.appendWithJAXB(marfpath);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
	}
}
