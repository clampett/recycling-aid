package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Plastic} is a concrete representation of garbage made out of plastic and
 * is a subclass of {@link src.recycling_types.Material Material}
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Plastic extends Material implements Binnable, Centerable, Disposable {
    /**All possible plastic names.*/
    private enum plasticType {
        PET, HDPE, PVC, LDPE, PP, PS, OTHER
    };

    /**The plastic's RIC number - from 1-7*/
    private int plasticNum;

    /**The plastic's RIC symbol*/
    private String plasticSign;

    /**The plastic's type*/
    private plasticType type;

    /**
     * Some possible items that plastic could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("plastic", "bottle", "straw", "packaging", "pen", "toy")
    );

    /**List of all RIC symbols*/
    private static final String[] resinCodes = {"♳", "♴", "♵", "♶", "♷", "♸", "♹"};

    public Plastic() {
        this(1);
    }

    public Plastic(int plasticNum) {
        super(0.0, possibleItems);
        super.setImpactScore(setImpact(plasticNum));

        this.plasticNum = plasticNum;
        this.plasticSign = resinCodes[this.plasticNum - 1];
        this.type = plasticType.values()[this.plasticNum - 1];
    }
    
    /**
     * Sets impact score based on the type of plastic
     * 
     * @param plasticNum RIC number from 1-7
     * @return impact score
     */
    private double setImpact(int plasticNum) {
        double impact = 0.0;

        switch(plasticNum) {
            case 1:
                impact = 0.6;
                break;
            case 2:
                impact = 0.3;
                break;
            case 3:
                impact = 0.95;
                break;
            case 4:
                impact = 0.7;
                break;
            case 5:
                impact = 0.4;
                break;
            case 6:
                impact = 0.9;
                break;
            case 7:
                impact = 0.99;
                break;
        }
        return impact;
    }

    @Override
    public boolean attemptBin(Material material){
        // Logic for taking the material to a bin
        return plasticNum != 3; // Example: Only allow binning if not PVC (3)
    }

    @Override
    public boolean attemptCenter(Material material){
        // Logic for taking the material to a center
        return plasticNum != 3; // Example: Only allow center if not PVC (3)
    }

    @Override
    public boolean attemptDispose(Material material){
        // Logic for disposing of the material
        return plasticNum != 3; // Example: Only allow disposal if not PVC (3)
    }
    

    public String getSpecial() {
        String specialBlurb = "There are 7 Plastic Types:\n";
        String specialFormat = "\t%d: %s (%s)\n";

        for(int i = 0; i < plasticType.values().length; i++) {
            specialBlurb += String.format(specialFormat, (i + 1), plasticType.values()[i], resinCodes[i]);
        }

        return specialBlurb;
    }
}