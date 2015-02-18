/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.io;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
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
            String path = FileDownloader.download("test");
            File file = new File(path);
            assertTrue(file.exists());
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            String saved = new String(encoded);
            assertEquals(saved, "test");
            file.delete();
        } catch (Exception e) {
            fail();
        }

    }
}
