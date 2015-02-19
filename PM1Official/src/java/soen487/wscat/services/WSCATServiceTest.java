package soen487.wscat.services;

import java.net.MalformedURLException;
import java.io.IOException;
import java.util.LinkedList;

public class WSCATServiceTest {

    public static void main(String[] args){
    	System.out.println("asdasd");
    	
        WSCATService wscatService = new WSCATService();

        //wscatService.submitWSDLRepo(null);
        try {
			wscatService.submitWSDLToAnalyze(null);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
    }
}
