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
     * Opens a folder and returns an array of all files inside.
     * 
     * @param dir_path Path to the folder
     * @param extensions True - remove extensions; False - include extensions
     * @param log JUL logger; pass null if you don't want to use
     * @return String array of all files inside a given folder
     */
    public static String[] load_folder(String dir_path, boolean extensions, Logger log) {
        String[] directoryList = null;
        File dir = new File(dir_path);
        boolean hasLogger = (log != null);

        if(hasLogger)
            log.info("Opening " + dir_path);

        if(dir.exists()) {
            if(dir.isDirectory()) {
                directoryList = dir.list();

                //remove extensions
                if(extensions && directoryList != null) {
                    for(int i = 0; i < directoryList.length; i++)
                        directoryList[i] = directoryList[i].replaceFirst("\\.[^.]+$", "");
                }
            }
            else {
                if(hasLogger)
                    log.severe("File path is not a directory");
                else
                    System.err.println("File path is not a directory");
            }
        }
        else {
            if(hasLogger)
                log.severe("Directory does not exist");
            else
                System.err.println("Directory does not exist");
        }

        return directoryList;
    }
}
