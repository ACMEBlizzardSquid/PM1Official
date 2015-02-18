/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.io;

import java.io.File;
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
public class FileDownloaderTest {
    
    public FileDownloaderTest() {
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
    public void createsFile() {
        try {
            File file = new File("test/utils/io/test.xml");
            String path = FileDownloader.download(file);
            File newFile = new File(path);
            assertTrue(newFile.exists());
            newFile.delete();
        } catch (Exception e) {
            fail();
        }

    }
}
