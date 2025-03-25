package src;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.*;

public class Loader {
    /**
     * A simple CSV file loader.
     * 
     * @param file_path Path to CSV file
     * @param log JUL logger; pass null if you don't want to use
     * @return 2D String array containing all the CSV data
     */
    public static String[][] load_csv(String file_path, Logger log) {
        List<String[]> vals = new ArrayList<>();
        Scanner sc = null;

        try {
            sc = new Scanner(new File(file_path), "UTF-8");
        } catch(IOException e) {
            if(log != null)
                log.severe("Loading error - " + e.toString());
            else
                e.printStackTrace();
        } finally {
            while(sc.hasNextLine())
                vals.add(sc.nextLine().split(","));

            if(log != null)
                log.info("Loaded " + file_path);

            //Removes the byte order mark if present
            if(!vals.isEmpty())
                vals.get(0)[0] = vals.get(0)[0].replace("\uFEFF", "");
            
            sc.close();
        }
        return vals.toArray(new String[0][]);
    }

    /**
     * Gets the Uniform Resource Identifier (URI) from a file.
     * 
     * @param file_path path to file
     * @param log JUL logger; pass null if you don't want to use
     * @return Supplied file's URI as a String
     */
    public static String getURI(String file_path, Logger log) {
        File videoFile = new File(file_path);
        String URI = "";
        boolean hasLogger = (log != null);

        if(videoFile.exists() && videoFile.isFile()) {
            URI = videoFile.toURI().toString();

            if(hasLogger)
                log.info("Got URI from: " + videoFile);
        }
        else if(hasLogger && !videoFile.exists())
            log.severe(file_path + " Does NOT Exist");
        else if(hasLogger && !videoFile.isFile())
            log.severe(file_path + " is NOT a File");

        return URI;
    }
}
