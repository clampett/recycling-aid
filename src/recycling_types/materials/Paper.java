package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Paper} is a concrete representation of garbage made out of paper and
 * is a subclass of {@link src.recycling_types.Material Material}.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Paper extends Material implements Binnable, Compostable, Disposable {
    /**Whether the paper has ink*/
    private boolean hasInk;

    /**
     * Some possible items that paper could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("paper", "newspaper", "homework", "junk mail", "paper towel", "paper plate")
    );

    public Paper() {
        this(true);
    }

    public Paper(boolean hasInk) {
        super(0.1, possibleItems);
        this.hasInk = hasInk;

        if(this.hasInk)
            super.setImpactScore(0.25);
    }

    /*
     * Sets hasInk to true or false
     * and updates the impact score accordingly
     * @pararm hasInk whether the paper has ink or not
     */
    public void setHasInk(boolean hasInk) {
        this.hasInk = hasInk;
        if(this.hasInk) {
            super.setImpactScore(0.25);
        } else {
            super.setImpactScore(0.1);
        }
    }

    @Override
    public boolean attemptBin(Material material){
        // Logic for taking the material to a bin
        return !hasInk; // Example: Only allow binning if not hasInk
    }

    @Override
    public boolean attemptCompost(Material material){
        // Logic for composting the material
        return !hasInk; // Example: Only allow composting if not hasInk
    }

    @Override
    public boolean attemptDispose(Material material){
        // Logic for disposing of the material
        return !hasInk; // Example: Only allow disposal if not hasInk
    }

    public String getSpecial() {
        return("May have ink, which is more harmful for the environment");
    }

    /*
     * Returns whether the paper has ink or not
     */
    public String hasInk() {
        if(this.hasInk) {
            return "This paper has ink";
        } else {
            return "This paper does not have ink";
        }
    }
}