/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.wsdl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import soen487.xml.XMLReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.w3c.dom.Document;

/**
 *
 * @author connorbode
 */
public class WSDLTest {
    
    public WSDLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getDocumentation () {
        try {
            File file = new File("test/utils/wsdl/test.xml");
            Document dom = XMLReader.readAsDOM(file);
            String docs = WSDL.getDocumentation(dom);
            assertEquals(docs, "This is the documentation");
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void getDocumentationString () {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get("test/utils/wsdl/test.xml"));
            String xml = new String(encoded);
            System.out.println(xml);
            String docs = WSDL.getDocumentation(xml);
            assertEquals(docs, "This is the documentation");
        } catch (Exception e) {
            fail();
        }
    }
}
