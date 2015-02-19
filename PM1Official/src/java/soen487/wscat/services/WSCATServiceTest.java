package soen487.wscat.services;

import java.net.MalformedURLException;
import java.io.IOException;
import java.util.LinkedList;
import soen487.retriever.services.client.IOException_Exception;

public class WSCATServiceTest {

    public static void main(String[] args) 
            throws IOException_Exception, IOException, InterruptedException {
        WSCATService wscatService = new WSCATService();

        String wsdl = wscatService.submitWSDLRepo("http://data.serviceplatform.org/wsdl_grabbing/service_repository-wsdls/valid_WSDLs/");
        System.out.println(wsdl);
        /*try {
                wscatService.submitWSDLToAnalyze(null);
        } catch (IOException | InterruptedException e) {
                e.printStackTrace();
        }*/
    }
}
