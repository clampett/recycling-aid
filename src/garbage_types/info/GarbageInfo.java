package src.garbage_types.info;

import src.garbage_types.*;

public class GarbageInfo extends Garbage implements InfoPiece {
    private String[] recyclingInfo;
    private int garbageIndex;

    public GarbageInfo(String garbageName) {
        super(garbageName);
        setGarbageIndex();
        setRecyclingInfo();
    }

    public void setGarbageIndex() {
        switch(super.getGarbageName()) {
            case "biodegradables":
                garbageIndex = 0;
                break;
            case "electronics":
                garbageIndex = 1;
                break;
            case "hazardous":
                garbageIndex = 2;
                break;
            case "recyclables":
                garbageIndex = 3;
                break;
            case "donatables":
                garbageIndex = 4;
                break;
            default:
                System.out.println("Invalid Garbage Name - Could set garbage index");
                garbageIndex = -1;
        }
    }

    public String[] getRecyclingInfo() {
        return recyclingInfo;
    }
    
    public void setRecyclingInfo() {
        recyclingInfo = loader("src/data/text/garbage_info.txt")[garbageIndex].split("\\*");
    }
}