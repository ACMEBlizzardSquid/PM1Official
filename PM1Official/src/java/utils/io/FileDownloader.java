package utils.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Used to save a file received via SOAP to disk
 * @author connorbode
 */
public class FileDownloader {
    
    /**
     * Persists a file to disk in a random, unique and temporary location
     * @param receivedFile The file to persist
     * @return The location of the temporary file
     * @throws IOException 
     */
    public static String download (String receivedFile) 
            throws IOException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String directory = "/tmp/";
        String path = directory + uuid;
        new File(directory).mkdirs();
        FileOutputStream fos = new FileOutputStream(path);
        PrintStream out = new PrintStream(fos);
        out.print(receivedFile);
        out.close();
        File file = new File(path);
        file.setReadable(true);
        file.setWritable(true);
        file.setExecutable(true);
        return path;
    }
}
