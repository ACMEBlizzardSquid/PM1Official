package soen487.retriever.services;

import java.net.MalformedURLException;
import java.util.LinkedList;
import java.io.IOException;

public class WSDLRetrieveServiceTest {
//	final static String SEARCH_ROOT = "http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/5-check.wsdl";
	final static String SEARCH_ROOT = "http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/";
	public static void main(String[] args) {
		WSDLRetrieveService service = new WSDLRetrieveService();
		
                LinkedList<String> wsdls = new LinkedList<String>();
		try {
			wsdls = (LinkedList<String>) service.retrieveWSDLs(SEARCH_ROOT, 10);
                        for (String string : wsdls) {
                            System.out.println(string);
                        }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
