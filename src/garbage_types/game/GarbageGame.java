package src.garbage_types.game;

import src.garbage_types.*;

public class GarbageGame extends Garbage implements GamePiece{
    private String recyclingPlace;

    public GarbageGame(String garbageName) {
        super(garbageName);
        setRecyclingPlace();
    }
    
    public void setRecyclingPlace() {
        switch(super.getGarbageName()) {
            case "biodegradables":
                this.recyclingPlace = "compost";
                break;
            case "electronics":
                this.recyclingPlace = "center";
                break;
            case "hazardous":
                this.recyclingPlace = "center";
                break;
            case "recyclables":
                this.recyclingPlace = "curbG";
                break;
            case "donatables":
                this.recyclingPlace = "centerD";
                break;
            default:
                System.out.println("Invalid Garbage Name - Could set recycling place");
                this.recyclingPlace = "error";
        }
    }

    public String getRecyclingPlace() {
        return recyclingPlace;
    }
}