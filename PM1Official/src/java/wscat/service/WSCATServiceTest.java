package wscat.service;

import java.net.MalformedURLException;
import java.util.LinkedList;

public class WSCATServiceTest {

    public static void main(String[] args) {
        WSCATService wscatService = new WSCATService();

        wscatService.submitWSDLRepo(null);
        wscatService.submitWSDLToAnalyze(null);
    }
}
