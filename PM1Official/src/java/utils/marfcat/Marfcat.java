package utils.marfcat;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.lang.InterruptedException;
import java.util.UUID;
import utils.io.StreamMonitor;

/**
 * MARFCAT
 * A facade around the MARFCAT library
 * 
 * @author c_bode
 */
public class Marfcat {
    
    String rootPath;        // the root application path
    String marfcatPath;     // the path to the marfcat JAR
    String marfcatExec;     // the command to execute the marfcat JAR
    
    /**
     * Initializes the facade
     * @throws IOException 
     */
    public Marfcat ()
            throws IOException {
        rootPath = new File(".").getCanonicalPath();
        marfcatPath = rootPath + "/lib/marfcat/marfcat.jar";
        marfcatExec = "java -jar " + marfcatPath;  
    }
    
    /**
     * Trains MARFCAT on a supplied MARFCAT_IN file
     * @param inputFilePath The path to the MARFCAT_IN file
     * @throws IOException
     */
    public void train (String inputFilePath) 
            throws IOException, InterruptedException {
        
        // execute job
        String options = " --train -nopreprep -raw -fft -eucl ";
        Process process = Runtime.getRuntime().exec(marfcatExec + options + inputFilePath);
        
        // redirect Marfcat output streams
        InputStream in = process.getInputStream();
        InputStream err = process.getErrorStream();
        StreamMonitor inMonitor = new StreamMonitor(in);
        StreamMonitor errMonitor = new StreamMonitor(err, true);
        inMonitor.run();
        errMonitor.run();
        
        // wait for process to finish
        process.waitFor();
    }
    
    /**
     * Analyzes the MARFCAT_IN file and returns a path to the generated
     * MARFCAT_OUT file.
     * @param inputFilePath The path to the MARFCAT_IN file
     * @return The path to the MARFCAT_OUT file
     * @throws IOException
     * @throws InterruptedException 
     */
    public String analyze (String inputFilePath)
            throws IOException, InterruptedException {
        
        // generate UUID for file
        String uuid = UUID.randomUUID().toString().replace("-", "");
        
        // execute job
        String options = " --batch-ident " + uuid + " ";
        String options2 = " -nopreprep -raw -fft -cheb";
        Process process = Runtime.getRuntime().exec(marfcatExec + options + inputFilePath + options2);
        
        // redirect Marfcat output streams
        InputStream in = process.getInputStream();
        InputStream err = process.getErrorStream();
        StreamMonitor inMonitor = new StreamMonitor(in);
        StreamMonitor errMonitor = new StreamMonitor(err, true);
        inMonitor.run();
        errMonitor.run();
        
        // wait for process to finish
        process.waitFor();
        
        // return path to output file
        return rootPath + "/report-noprepreprawfftcheb-" + uuid + ".xml";
    }
    
}
