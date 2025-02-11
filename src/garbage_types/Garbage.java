package src.garbage_types;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public interface Garbage {
    public String getGarbageName();
    public void setGarbageName(String garbage_name);
    public void printGarbageName();
    public void setGarbageIndex();
    public String getGarbageImagePath();
    public void setGarbageImagePath();

    public default List<String> loader(String file_path) {
        try {
            File data = new File(file_path);
            Scanner scan = new Scanner(data);
            String dataString = "";

            while(scan.hasNextLine()) {
                dataString += scan.nextLine();
            } scan.close();

            List<String> dataList = new ArrayList<>(Arrays.asList(dataString.split("~")));

            return dataList;
        } catch(FileNotFoundException e) {
            System.out.println("Error");
            e.printStackTrace();
        }

        return null;
    }
}
