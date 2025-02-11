package src.garbage_types;

import java.util.List;

public class Info_Garbage implements Garbage {
    int garbage_index;
    String garbage_name, garbage_image_path;
    String[] recycling_info;

    public Info_Garbage(String garbage_name) {
        this.garbage_name = garbage_name;
        setGarbageIndex();
        setGarbageImagePath();
        setReyclingInfo();
    }

    public String getGarbageName() {
        return this.garbage_name;
    }

    public void setGarbageName(String garbage_name) {
        this.garbage_name = garbage_name;
    }

    public void printGarbageName() {
        System.out.println(garbage_name);
    }

    public void setGarbageIndex() {
        switch (this.garbage_name) {
            case "biodegradables":
                garbage_index = 0;
                break;
            case "electronics":
                garbage_index = 1;
                break;
            case "hazardous":
                garbage_index = 2;
                break;
            case "recyclables":
                garbage_index = 3;
                break;
            case "donatables":
                garbage_index = 4;
                break;
            default:
                System.out.println("Invalid Garbage Name");
                garbage_index = -1;
        }        
    }

    public String getGarbageImagePath() {
        return this.garbage_image_path;
    }

    public void setGarbageImagePath() {
        String path = "src/data/images/";

        switch (this.garbage_index) {
            case 0:
                path += "biodegradable.png";
                break;
            case 1:
                path += "electronic.png";
                break;
            case 2:
                path += "hazardous.png";
                break;
            case 3:
                path += "recyclables.png";
                break;
            case 4:
                path += "donatables.png";
                break;
            default:
                System.out.println("Invalid Garbage Name");
                path = null;
        }

        garbage_image_path = path;
    }

    private void setReyclingInfo() {
        List<String> garbageInfo = loader("src/data/text/garbage_info.txt");
        recycling_info = garbageInfo.get(garbage_index).split("\\*");
    }

    public String[] getRecyclingInfo() {
        return recycling_info;
    }

    public void printRecyclingInfo() {
        for(String l : recycling_info)
            System.out.println(l);
    }
}