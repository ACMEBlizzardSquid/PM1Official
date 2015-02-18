package soen487.wscat.services;

import java.net.MalformedURLException;
import java.io.IOException;
import java.util.LinkedList;

public class WSCATServiceTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        WSCATService wscatService = new WSCATService();

        //wscatService.submitWSDLRepo(null);
        wscatService.submitWSDLToAnalyze(null);
    }
}
