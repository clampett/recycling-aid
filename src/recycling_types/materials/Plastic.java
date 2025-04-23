package src.recycling_types.materials;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.customExceptions.failedBinException;
import src.customExceptions.failedCenterException;
import src.customExceptions.failedDisposeException;
import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Plastic} is a concrete representation of garbage made out of plastic and
 * is a subclass of {@link src.recycling_types.Material Material}.
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
        this.plasticSign = resinCodes[plasticNum - 1];
        setPlasticType(plasticNum);
    }
    
    //Mutator methods

    public void setPlasticType(int plasticNum) {
        switch(plasticNum) {
            case 1:
            this.type = plasticType.PET;
            break;
            case 2:
            this.type = plasticType.HDPE;
            break;
            case 3:
            this.type = plasticType.PVC;
            break;
            case 4:
            this.type = plasticType.LDPE;
            break;
            case 5:
            this.type = plasticType.PP;
            break;
            case 6:
            this.type = plasticType.PS;
            break;
            case 7:
            this.type = plasticType.OTHER;
            break;
        }
    }
    /**
     * Sets impact score based on the type of plastic.
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

    /**
     * Sets the {@link Plastic} RIC number, along with the sign and impact score.
     * @param plasticNum 
     */
    public void setPlasticNum(int plasticNum) {
       this.plasticNum = plasticNum;
       this.plasticSign = resinCodes[this.plasticNum - 1];
       setImpact(plasticNum);
    }
    
    //Accesor methods

    /**
     * Get {@link Plastic} RIC number.
     * @return {@link Plastic} plasticNum
     */
    public int getPlasticNum() {
        return plasticNum;
    }

    /**
     * Get {@link Plastic} RIC sign.
     * @return {@link Plastic} plasticSign
     */
    public String getPlasticSign() {
        return plasticSign;
    }

    /**
     * Get {@link Plastic} name {@code Enum}.
     * @return {@link Plastic} type
     */
    public plasticType getPlasticType() {
        return type;
    }

    //Interface methods

    @Override
    public boolean attemptBin(Material material, Field[] fields) throws failedBinException{
        boolean binable = false;
        for (Field f : fields) {
            f.getName();
            if (f.getName().equals("plasticNum")) {
                if(f.equals(1) || f.equals(2) || f.equals(5)) {
                    binable = true;
                }
            }
        }
        return binable;
    }

    @Override
    public boolean attemptCenter(Material material, Field[] fields) throws failedCenterException{
        boolean centerable = false;
        for (Field f : fields) {
            f.getName();
            if (f.getName().equals("plasticNum")) {
                if(f.equals(4)) {
                    centerable = true;
                }
            }
        }
        return centerable;
    }

    @Override
    public boolean attemptDispose(Material material, Field[] fields) throws failedDisposeException{
        boolean disposable = false;
        for (Field f : fields) {
            f.getName();
            if (f.getName().equals("plasticNum")) {
                if(f.equals(3) || f.equals(6)) {
                    disposable = true;
                }
            }
        }
        return disposable;
    }
    
    //Material superclass methods

    @Override
    public String getSpecial() {
        String specialBlurb = "There are 7 Plastic Types:\n";
        String specialFormat = "\t%d: %s (%s)\n";

        for(int i = 0; i < plasticType.values().length; i++) {
            specialBlurb += String.format(specialFormat, (i + 1), plasticType.values()[i], resinCodes[i]);
        }

        return specialBlurb;
    }
}