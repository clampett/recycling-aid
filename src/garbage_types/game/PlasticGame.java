package src.garbage_types.game;

import src.garbage_types.*;

public class PlasticGame extends Plastic implements GamePiece {
    private String recyclingPlace;

    public PlasticGame(int plasticNum) {
        super(plasticNum);
        setRecyclingPlace();
    }

    public void setRecyclingPlace() {
        switch(super.getPlasticType()) {
            case 1:
                this.recyclingPlace = "curbP";
                break;
            case 2:
                this.recyclingPlace = "curbP";
                break;
            case 3:
                this.recyclingPlace = "center";
                break;
            case 4:
                this.recyclingPlace = "center";
                break;
            case 5:
                this.recyclingPlace = "curbP";
                break;
            case 6:
                this.recyclingPlace = "center";
                break;
            case 7:
                this.recyclingPlace = "n/a";
                break;
            default:
                System.out.println("ERROR - Invalid Plastic Number");
                this.recyclingPlace = "error";
        }        
    }

    public String getRecyclingPlace() {
        return recyclingPlace;
    }
}
