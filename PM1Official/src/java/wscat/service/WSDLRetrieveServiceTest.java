package wscat.service;

import java.net.MalformedURLException;
import java.util.LinkedList;

public class WSDLRetrieveServiceTest {
	final static String SEARCH_ROOT = "http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/5-check.wsdl";
	
	public static void main(String[] args) {
		WSDLRetrieveService service = new WSDLRetrieveService();
		
                LinkedList<String> wsdls = new LinkedList<String>();
		try {
			wsdls = (LinkedList<String>) service.retrieveWSDLs(null);
                        for (String string : wsdls) {
                            System.out.println(string);
                        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
