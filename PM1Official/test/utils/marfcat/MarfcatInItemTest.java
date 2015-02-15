/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.marfcat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author connorbode
 */
public class MarfcatInItemTest {
    
    String path;
    MarfcatInItem marfItem;
    
    public MarfcatInItemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        path = "test/utils/marfcat/test.xml";
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void initializesFile() {
        try {
            marfItem = new MarfcatInItem(path);
            assertEquals(marfItem.getPath(), path);
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void initializesCVENull () {
        try {
            marfItem = new MarfcatInItem(path);
            assertEquals(marfItem.getCVE(), "");
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void initializesCVEWithValue () {
        try {
            String cve = "category";
            marfItem = new MarfcatInItem(path, cve);
            assertEquals(marfItem.getCVE(), cve);
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void initializesType () {
        try {
            marfItem = new MarfcatInItem(path);
            assertEquals(marfItem.getType(), "XML  document text");
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void initializesLines () {
        try {
            marfItem = new MarfcatInItem(path);
            assertEquals(marfItem.getLines(), 281);
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void initializesWords () {
        try {
            marfItem = new MarfcatInItem(path);
            assertEquals(marfItem.getWords(), 972);
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void initializesBytes () {
        try {
            marfItem = new MarfcatInItem(path);
            assertEquals(marfItem.getBytes(), 12578);
        } catch (Exception e) {
            fail();
        }
    }
}
