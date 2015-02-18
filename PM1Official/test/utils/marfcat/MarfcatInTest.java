/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.marfcat;

import java.io.File;
import java.nio.file.Files;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import static org.junit.Assert.*;
import soen487.xml.XMLReader;

/**
 *
 * @author connorbode
 */
public class MarfcatInTest {
    
    MarfcatIn marfIn;
    
    public MarfcatInTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            marfIn = new MarfcatIn();
        } catch (Exception e) {
            fail();
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void initializesFileUtility() {
        try {
            assertNotNull(marfIn.getFileUtilityVersion());
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void initializesFindUtility() {
        try {
            assertNotNull(marfIn.getFindUtilityVersion());
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void initializesMarfUtility() {
        try {
            assertNotNull(marfIn.getMarfUtilityVersion());
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test 
    public void generatesMarfcatInFile() {
        try {
            MarfcatInItem item1 = new MarfcatInItem("test/utils/marfcat/test.xml");
            MarfcatInItem item2 = new MarfcatInItem("test/utils/marfcat/train.xml");
            marfIn.addItem(item1);
            marfIn.addItem(item2);
            marfIn.write("test/utils/marfcat/.tmp.xml");
            File file = new File("test/utils/marfcat/.tmp.xml");
            assertTrue(file.exists());
            Document dom = XMLReader.readAsDOM(file);
            NodeList files = dom.getElementsByTagName("file");
            assertEquals(files.getLength(), 2);
            file.delete();
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void appends() {
        try {
            File file = new File("test/utils/marfcat/test.xml");
            File file2 = new File("test/utils/marfcat/.tmp.xml");
            Files.copy(file.toPath(), file2.toPath());
            marfIn.addItem(new MarfcatInItem("test/utils/marfcat/test.xml"));
            marfIn.append("test/utils/marfcat/.tmp.xml");
            Document dom = XMLReader.readAsDOM(file2);
            NodeList files = dom.getElementsByTagName("file");
            assertEquals(files.getLength(), 2);
            file2.delete();
        } catch (Exception e) {
            System.out.println(e.toString());
            fail();
        }
    }
}
