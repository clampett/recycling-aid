package src.garbage_types.info;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public interface InfoPiece {
    public void setRecyclingInfo();
    public String[] getRecyclingInfo();

    public default String[] loader(String file_path) {
        try {
            File data = new File(file_path);
            Scanner scan = new Scanner(data);
            String dataString = "";

            while(scan.hasNextLine()) {
                dataString += scan.nextLine();
            } scan.close();

            String[] dataList = dataString.split("~");

            return dataList;
        } catch(FileNotFoundException e) {
            System.out.println("Error - Invalid File Path");
            e.printStackTrace();
        }

        return null;
    }    
}
