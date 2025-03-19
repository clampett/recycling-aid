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
     * @param logger JUL logger; pass null if you don't want to use
     * @return 2D String array containing all the CSV data
     */
    public static String[][] load_csv(String file_path, Logger logger) {
        List<String[]> vals = new ArrayList<>();
        Scanner sc = null;

        try {
            sc = new Scanner(new File(file_path), "UTF-8");
        } catch(IOException e) {
            if(logger != null)
                logger.severe("Loading error - " + e.toString());
            else
                e.printStackTrace();
        } finally {
            while(sc.hasNextLine())
                vals.add(sc.nextLine().split(","));

            if(logger != null)
                logger.info("Loaded " + file_path);

            //Removes the byte order mark if present
            if(!vals.isEmpty())
                vals.get(0)[0] = vals.get(0)[0].replace("\uFEFF", "");
            
            sc.close();
        }
        return vals.toArray(new String[0][]);
    }
}
