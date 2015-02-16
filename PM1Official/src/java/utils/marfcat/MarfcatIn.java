package utils.marfcat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.InterruptedException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import utils.io.StreamMonitor;

/**
 * This class is used to generate a MARFCAT_IN file.
 * @author connorbode
 */
public class MarfcatIn {
    
    private String rootPath;                // root application path
    private ArrayList<MarfcatInItem> items = new ArrayList<MarfcatInItem>();
    
    /**
     * Initializes the MarfcatIn object
     */
    public MarfcatIn () 
            throws IOException {
        
        // set root path
        rootPath = new File(".").getCanonicalPath();
    }
    
    /**
     * Adds an item to the items list
     * @param item The item to add
     */
    public void addItem (MarfcatInItem item) {
        items.add(item);
    }
    
    /**
     * Gets the unix 'file' utility version
     * @return The unix 'file' utility version
     */
    public String getFileUtilityVersion ()
            throws IOException, InterruptedException {
        // get file utility version
        Process process = Runtime.getRuntime().exec("file --version");
        StreamMonitor in = new StreamMonitor(process.getInputStream());
        in.setSave(true);
        in.run();
        process.waitFor();
        return in.getOutput();
    }
    
    /**
     * Gets the unix 'find' utility version
     * @return The unix 'find' utility version
     */
    public String getFindUtilityVersion () 
            throws IOException, InterruptedException {
        
        // get find utility version
        Process process = Runtime.getRuntime().exec("find --version");
        StreamMonitor in = new StreamMonitor(process.getInputStream());
        in.setSave(true);
        in.run();
        process.waitFor();
        return in.getOutput();
    }
    
    /**
     * Gets the marf.jar version
     * @return The marf.jar version
     */
    public String getMarfUtilityVersion () 
            throws IOException, InterruptedException {
        
        // get marf utility version 
        String marfcatLibPath = rootPath + Marfcat.MARFCAT_LIB + "/marf.jar";
        String command = "java -jar " + marfcatLibPath + " --diagnostic";
        Process process = Runtime.getRuntime().exec(command);
        StreamMonitor in = new StreamMonitor(process.getInputStream());
        in.setSave(true);
        in.run();
        process.waitFor();
        return in.getOutput();
    }
    
    
    
    /**
     * Writes the MARFCAT_IN file to the path provided
     * @param path The path to write the MARFCAT_IN file to
     */
    public void write(String path) 
            throws FileNotFoundException, IOException, InterruptedException {
        
        // set up print writer
        FileWriter fw = new FileWriter(path, false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        
        // get current date
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        String dateString = timestamp.toString();
        
        // print header
        out.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        out.println("<dataset generated-by=\"WSCAT\" generated-on=\"" + dateString + "\">");
        out.println("  <description>");
        out.println("    <file-type-tool>");
        out.println(getFileUtilityVersion());
        out.println("    </file-type-tool>");
        out.println("    <find-tool>");
        out.println(getFindUtilityVersion());
        out.println("    </find-tool>");
        out.println("    <marf-tool>");
        out.println(getMarfUtilityVersion());
        out.println("    </marf-tool>");
        out.println("  </description>");
        
        // iterate items and print
        for(int i = 0; i < items.size(); i++) {
            MarfcatInItem item = items.get(i);
            out.println("  <file id=\"" + i + "\" path=\"" + item.getPath() + "\">");
            out.println("    <meta>");
            out.println("      <type>" + item.getType() + "</type>");
            out.println("      <length lines=\"" + item.getLines() + "\" words=\"" 
                    + item.getWords() + "\" bytes=\"" 
                    + item.getBytes() + "\" />");
            out.println("    </meta>");
            out.println("    <location line=\"\" fraglines=\"\">");
            out.println("      <meta>");
            out.println("        <cve>\"" + item.getCVE() + "\"</cve>");
            out.println("        <name cweid=\"\"></name>");
            out.println("      </meta>");
            out.println("      <fragment>");
            out.println("      </fragment>");
            out.println("      <explanation>");
            out.println("      </explanation>");
            out.println("    </location>");
            out.println("  </file>");
        }
        
        // print closing tag
        out.println("</dataset>");
        out.close();
    }
}
