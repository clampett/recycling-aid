package src.recycling_types.materials;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/20/2025
 */
public class Plastic extends Material implements Binnable, Centerable, Disposable{
    public enum plasticType {
        PET, HDPE, PVC, LDPE, PP, PS, OTHER
    };

    private int plasticNum;
    private String plasticSign;
    private plasticType type;

    private static final String[] possibleItems = {"Bottles", "Straws", "Packaging", "Pens", "Toys"};
    private static final String[] resinCodes = {"♳", "♴", "♵", "♶", "♷", "♸", "♹"};

    public Plastic(int plasticNum) {
        super(0.8, possibleItems);
        
        this.plasticNum = plasticNum;
        this.plasticSign = resinCodes[this.plasticNum];
        setPlasticType(this.plasticNum);
    }
    
    private void setPlasticType(int plasticNum) {
        switch(plasticNum) {
            case 1:
                type = plasticType.PET;
                break;
            case 2:
                type = plasticType.HDPE;
                break;
            case 3:
                type = plasticType.PVC;
                break;
            case 4:
                type = plasticType.LDPE;
                break;
            case 5:
                type = plasticType.PP;
                break;
            case 6:
                type = plasticType.PS;
                break;
            case 7:
                type = plasticType.OTHER;
                break;
            default:
                type = null;
        }
    }

    public String getSpecial() {
        String specialBlurb = "There are 7 Plastic Types:\n";

        for(int i = 0; i < 7; i++) {
            specialBlurb += (i + 1) + ": " + plasticType.values()[i] + " (" + resinCodes[i] + ")\n"; 
        }

        return specialBlurb;
    }
}
