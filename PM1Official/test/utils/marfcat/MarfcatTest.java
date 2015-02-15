/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.marfcat;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import utils.marfcat.Marfcat;

/**
 *
 * @author c_bode
 */
public class MarfcatTest {
    
    Marfcat marf;
    
    public MarfcatTest() {
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
    public void test1() {
        try {
            marf = new Marfcat();
            marf.train("test/utils/marfcat/train.xml");
        } catch (Exception e) {
            fail();
        }
    }
    
    @Test
    public void test2() {
        try {
            marf = new Marfcat();
            String filepath = marf.analyze("test/utils/marfcat/test.xml");
            File file = new File(filepath);
            assertTrue(file.exists());
            file.delete();
        } catch (Exception e) {
            fail();
        }
    }
}
