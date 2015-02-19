package soen487.retriever.services.client;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/**
 * This class is used to demo WSDLRetrieveService
 * @author shake0
 *
 */
public class WSDLRetrieveServiceTest {
//	final static String SEARCH_ROOT = "http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/5-check.wsdl";
	final static String SEARCH_ROOT = "http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/";
	public static void main(String[] args) throws MalformedURLException_Exception, IOException_Exception {
		WSDLRetrieveServiceService wsdlRetrieveServiceService = new WSDLRetrieveServiceService();
                WSDLRetrieveService wsdlRetrieveService = wsdlRetrieveServiceService.getWSDLRetrieveServicePort();
                
                List<String> wsdls = wsdlRetrieveService.retrieveWSDLs(SEARCH_ROOT, null);
                for (String wsdl : wsdls) {
                    System.out.println(wsdl);
                }
	}
}
