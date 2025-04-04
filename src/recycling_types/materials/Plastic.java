package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/20/2025
 */
public class Plastic extends Material implements Binnable, Centerable, Disposable {
    /**All possible plastic names.*/
    private enum plasticType {
        PET, HDPE, PVC, LDPE, PP, PS, OTHER
    };

    private int plasticNum;
    private String plasticSign;
    private plasticType type;

    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("plastic", "bottle", "straw", "packaging", "pen", "toy")
    );

    private static final String[] resinCodes = {"♳", "♴", "♵", "♶", "♷", "♸", "♹"};

    public Plastic(int plasticNum) {
        super(0.8, possibleItems);
        
        this.plasticNum = plasticNum;
        this.plasticSign = resinCodes[this.plasticNum - 1];
        this.type = plasticType.values()[this.plasticNum - 1];
    }

    public Plastic() {
        this(7);
    }
    
    public plasticType getType() {
        return this.type;
    }

    public String getSpecial() {
        String specialBlurb = "There are 7 Plastic Types:\n";
        String specialFormat = "\t%d: %s (%s)\n";

        for(int i = 0; i < 7; i++) {
            specialBlurb += String.format(specialFormat, (i + 1), plasticType.values()[i], resinCodes[i]);
        }

        return specialBlurb;
    }
}
