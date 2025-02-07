package src.garbage_types;
import java.util.Map.Entry;
import java.util.ArrayList;

public class Plastic implements Plastics{
    private String garbage_name, plastic_name;
    private int plastic_num;
    private ArrayList<String> recyclingInfo;
    private Entry<Integer, String> plastic_entry;

    public Plastic() {
        recyclingInfo = new ArrayList<>();
        setPlasticType(-1);
    }

    public Plastic(int plastic_num) {
        recyclingInfo = new ArrayList<>();
        //setPlasticType(plastic_num);
        this.plastic_num = plastic_num;        
    }

    public void printGarbageName() {
        System.out.printf("Plastic %d\n", plastic_num);
    }

    public void setGarbageName(String garbage_name) {
        this.garbage_name = garbage_name;
    }

    public String getGarbageName() {
        return garbage_name;
    }

    public int getPlasticNumber() {
        return plastic_num;
    }

    public String getPlasticName() {
        return plastic_name;
    }

    public void setPlasticType(int plastic_num){}
    /**
     * https://en.wikipedia.org/wiki/Resin_identification_code
     * 
     * @param plastic_num 
     */
    // public void setPlasticType(int plastic_num) {
    //     switch(plastic_num) {
    //         case 1:
    //             plastic_entry.put(this.plastic_num = plastic_num, plastic_name = "PET");
    //             break;
    //             plastic_entry.setValue(plastic_name);

    //         case 2:
    //             plastic_map.put(this.plastic_num = plastic_num, plastic_name = "HDPE");
    //             break;

    //         case 3:
    //             plastic_map.put(this.plastic_num = plastic_num, plastic_name = "PVC");
    //             break;

    //         case 4:
    //             plastic_map.put(this.plastic_num = plastic_num, plastic_name = "LDPE");
    //             break;

    //         case 5:
    //             plastic_map.put(this.plastic_num = plastic_num, plastic_name = "PP");
    //             break;

    //         case 6:
    //             plastic_map.put(this.plastic_num = plastic_num, plastic_name = "PS");
    //             break;

    //         case 7:
    //             plastic_map.put(this.plastic_num = plastic_num, plastic_name = "Other");
    //             break;

    //         default:
    //             plastic_map.put(this.plastic_num = -1, plastic_name = "Invalid Plastic Type");
    //             System.out.println("Error - Unkown Plastic Type");
    //     }
    // }

    public Entry<Integer, String> getPlasticType() {
        return plastic_entry;
    }

    public String getRecyclingInfo() {
        return "";
    }

    public void printRecyclingInfo() {
        System.out.println("Plastic " + plastic_num + " Recycling");
    }
}