package src;

import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.*;
import java.util.stream.Collectors;

/**
 * {@link src.Loader Loader} is a helper class dealing with loading and saving data.
 * <p>
 * The 2 main functions that it covers is loading CSV files and serialization/deserialization,
 * both used throughout the project.
 * </p>
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/4/2025
 */
public class Loader {
    //Error colors
    private static final String RESET = "\u001B[0m";
    private static final String ERROR = "\u001B[31m";
    private static final String PATH = "\u001B[43m";
    private static final String OBJECT = "\u001B[46m";

    /**
     * A simple CSV file loader.
     * 
     * @param file_path Path to CSV file
     * @param log JUL {@code Logger}; pass null if you don't want to use
     * @return 2D String array containing all the CSV data
     */
    public static String[][] load_csv(String file_path, Logger log) {
        List<String[]> vals = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(
                                new FileReader(file_path))) {
            String current;
            while((current = br.readLine()) != null)
                vals.add(current.split(","));

            log.info("Loaded - " + PATH + file_path + RESET);

            if(!vals.isEmpty())
                vals.get(0)[0] = vals.get(0)[0].replace("\uFEFF", "");

        } catch(IOException e) {log.severe("Loading error - " + ERROR + e.getMessage() + RESET);}
    
        return vals.toArray(new String[0][]);
    }

    /**
     * Serializes the given Object.
     * 
     * @param to_save Object to save
     * @param save_path path to save location
     * @param log JUL {@code Logger}; pass null if you don't want to use
     */
    public static void serialize(Object to_save, String save_path, Logger log) {
        try(ObjectOutputStream obj_out = new ObjectOutputStream(
                                         new BufferedOutputStream(
                                         new FileOutputStream(save_path)))) {
            obj_out.writeObject(to_save);
            obj_out.flush();
            log.info("Successfully serialized object at: " + PATH + save_path + RESET);
        } catch(Exception e) {log.severe("Could NOT serialize: " + 
                                         OBJECT + to_save.toString() + RESET +"; at: " + 
                                         PATH + save_path + RESET + " - " + 
                                         ERROR + e.getMessage() + RESET);}
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

        try(ObjectInputStream obj_in = new ObjectInputStream(
                                       new BufferedInputStream(
                                       new FileInputStream(load_path)))) {
            obj = obj_in.readObject();
            log.info("Successfully deserialized Object at: " + PATH + load_path + RESET);
        } catch(Exception e) {log.severe("Could NOT deserialize Object at: " + 
                                         PATH + load_path + RESET + " - " + 
                                         ERROR + e.getMessage() + RESET);}
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
                log.info("Wiped: " + PATH + file_path + RESET);
            }
        } 
        catch(NullPointerException e) {log.severe("Could NOT open: " + 
                                                    PATH + file_path + RESET + " - " + 
                                                    ERROR + e.getMessage() + RESET);}
        catch(SecurityException e) {log.severe("Do NOT have correct permissions for: " + 
                                               PATH + file_path + RESET + " - " + 
                                               ERROR + e.getMessage() + RESET);} 
        catch(IOException e) {log.severe("Could NOT create replacement file for: " + 
                                         PATH + file_path + RESET + " - " + 
                                         ERROR + e.getMessage() + RESET);} 
        catch(Exception e) {log.severe("General Error - " + 
                                       ERROR + e.getMessage() + RESET);}
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
                    log.severe(PATH + dir_path + RESET + ERROR + " is NOT a directory." + RESET);
            } 
            else
                log.severe(PATH + dir_path + RESET + ERROR + " does NOT exist." + RESET);
        } catch(NullPointerException e) {
            log.severe("Could NOT load " + PATH + dir_path + RESET + " - " +
                       ERROR + e.getMessage() + RESET);}
                       
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