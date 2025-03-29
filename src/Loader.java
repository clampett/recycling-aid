package src;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.*;
import java.util.stream.Collectors;

/**
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/29/2025
 */
public class Loader {
    /**
     * A simple CSV file loader.
     * 
     * @param file_path Path to CSV file
     * @param log JUL {@code Logger}; pass null if you don't want to use
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
     * @param log JUL {@code Logger}; pass null if you don't want to use
     * @return Supplied file's URI as a String
     */
    public static String get_URI(String file_path, Logger log) {
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

    /**
     * Serializes the given Object.
     * 
     * @param to_save Object to save
     * @param save_path path to save location
     * @param log JUL {@code Logger}; pass null if you don't want to use
     */
    public static void serialize(Object to_save, String save_path, Logger log) {
        try {
            FileOutputStream file_out_stream = new FileOutputStream(save_path);
            ObjectOutputStream obj_out_stream = new ObjectOutputStream(file_out_stream);

            obj_out_stream.writeObject(to_save);
            obj_out_stream.flush();
            obj_out_stream.close();

            log.info("Successfully saved: " + to_save);
        } catch(Exception e) {
            log.severe("Could NOT serialize: " + to_save.toString() + "; at: " + save_path + " - " + e.toString());
        }
    }

    /**
     * Deserializes and returns a serialized object.
     * 
     * @param load_path path to file 
     * @param log JUL {@code Logger}; pass null if you don't want to use
     * @return Deserialized Object
     */
    public static Object deserialize(String load_path, Logger log) {
        Object obj = null;

        try {
            FileInputStream file_in_stream = new FileInputStream(load_path);
            ObjectInputStream obj_in_stream = new ObjectInputStream(file_in_stream);

            obj = obj_in_stream.readObject();
            obj_in_stream.close();

            log.info("Successfully deserialized Object at: " + load_path);
        } catch(Exception e) {
            log.severe("Could NOT deserialize Object at: " + load_path + " - " + e.toString());
        }

        return obj;
    }

    /**
     * Completely wipes a file by deleting it and recreating it.
     * 
     * @param file_path path to file
     * @param log JUL {@code Logger}; pass null if you don't want to use
     */
    public static void wipe_file(String file_path, Logger log) {
        try {
            File to_wipe = new File(file_path);

            if(to_wipe.delete()) {
                to_wipe.createNewFile();
                log.info("Wiped: " + file_path);
            }
        } catch(NullPointerException e) {
            log.severe("Could NOT open: " + file_path + " - " + e.toString());
        } catch(SecurityException e) {
            log.severe("Do NOT have correct permissions for: " + file_path + " - " + e.toString());
        } catch(IOException e) {
            log.severe("Could NOT create replacement file for: " + file_path + " - " + e.toString());
        } catch(Exception e) {
            log.severe("General Error - " + e.toString());
        }
    }

    /**
     * Opens a directory and returns an array of all the files inside.
     * 
     * @param dir_path path to directory
     * @param log JUL {@code Logger}; pass null if you don't want to use
     * @return File array of all files inside the given directory
     */
    public static File[] load_dir(String dir_path, Logger log) {
        File[] dir_list = null;

        try {
            File dir = new File(dir_path);

            if(dir.exists()) {
                if(dir.isDirectory())
                    dir_list = dir.listFiles();
                else
                    log.severe(dir_path + " is NOT a directory.");
            } 
            else
                log.severe(dir_path + " does NOT exist.");
        } catch(NullPointerException e) {
            log.severe("Could NOT load " + dir_path + " - " + e.toString());
        }

        return dir_list;
    }

    /**
     * Opens a directory and returns an {@code List<String>} of every path to each File.
     * 
     * @param dir_path path to directory
     * @param log JUL {@code Logger}; pass null if you don't want to use
     * @return {@code List<String>} of the paths to of each file inside the directory.
     */
    public static List<String> load_dir_paths(String dir_path, Logger log) {
        List<File> files = new ArrayList<>(Arrays.asList(load_dir(dir_path, log)));

        return files.stream().map(File::getAbsolutePath).collect(Collectors.toList());
    }

    /**
     * Opens a directory containing serialized objects and returns all objects deserialized.
     * 
     * @param dir_path path to directory
     * @param log JUL {@code Logger}; pass null if you don't want to use
     * @return Object array of all deserialized objects inside the given directory
     */
    public static List<Object> deserialize_dir(String dir_path, Logger log) {
        File[] dir_list = load_dir(dir_path, log);
        List<Object> deserialized_objects = new ArrayList<>(dir_list.length);

        for(int i = 0; i < dir_list.length; i++) {
            deserialized_objects.add(deserialize(dir_list[i].getAbsolutePath(), log));
        }

        return deserialized_objects;
    }
}