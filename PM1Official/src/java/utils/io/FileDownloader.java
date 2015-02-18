package utils.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

/**
 * Used to save a file received via SOAP to disk
 * @author connorbode
 */
public class FileDownloader {
    
    /**
     * Persists a file disk in a random, unique and temporary location
     * @param receivedFile The file to persist
     * @return The location of the temporary file
     * @throws IOException 
     */
    public static String download (File receivedFile) 
            throws IOException {
        String uuid = UUID.randomUUID().toString();
        String path = "/tmp/" + uuid;
        File tmp = new File(path);
        receivedFile.renameTo(tmp);
        receivedFile.createNewFile();
        return path;
    }
}
