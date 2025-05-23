package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.recycling_types.*;
import src.recycling_types.categories.*;

/**
 * {@link Glass} is a concrete representation of garbage made out of glass and
 * is a subclass of {@link src.recycling_types.Material Material}.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Glass extends Material implements Binnable, Disposable {
    /**Whether the glass is tempered*/
    private boolean isTempered;

    /**
     * Some possible items that glass could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("glass", "bottle", "jar", "cookware", "light bulb", "ornament")
    );

    public Glass() {
        this(false);
    }

    public Glass(boolean isTempered) {
        super(0.25, possibleItems);

        if(isTempered)
            super.setImpactScore(0.35);
    }

    //Mutator methods

    /**
     * Sets whether {@link Glass} is tempered and updates impact score.
     * @param isTempered T/F - is {@link Glass} tempered?
     */
    public void setTempered(boolean isTempered) {
        this.isTempered = isTempered;
        if(isTempered)
            super.setImpactScore(0.35);
        else
            super.setImpactScore(0.25);
    }

    //Accessor methods

    /**
     * Gets whether {@link Glass} is tempered, as a String.
     * @return {@link Glass} tempering status
     */
    public boolean getIsTempered() {
        if(isTempered)
            return true;
        else
            return false;
    }

    //Interface methods

    @Override
    public boolean attemptBin(Material material) {
        if(((Glass)material).getIsTempered() == true){
            return false;
        }
        else return true;
        

    }

    @Override
    public boolean attemptDispose(Material material) {
        if(((Glass)material).getIsTempered() == true){
            return true;
        }
        else return false;
    }


    //Material superclass methods

    @Override
    public String getSpecial() {
        return("May be tempered, which is more harmful for the environment");
    }
}