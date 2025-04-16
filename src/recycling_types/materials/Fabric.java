package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Fabric} is a concrete representation of garbage made out of fabric and
 * is a subclass of {@link src.recycling_types.Material Material}
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Fabric extends Material implements Centerable, Donatable, Disposable {
    /**Whether the fabric is dyed*/
    private boolean dyed;

    /**Whether the fabric has harmful dyes*/
    private boolean harmfulDyes;

    /**
     * Some possible items that fabric could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("fabric", "clothing", "bedsheet", "shoe", "tablecloth", "curtain")
    );

    public Fabric() {
        this(false, false);
    }

    public Fabric(boolean dyed, boolean harmfulDyes) {
        super(0.3, possibleItems);
        this.dyed = dyed;
        this.harmfulDyes = harmfulDyes;

        if(dyed)
            super.setImpactScore(getImpactScore() + 0.1);

        if(harmfulDyes)
            super.setImpactScore(getImpactScore() + 0.1);
    }

    // Field mutator methods

    /*
     * Sets status of thet fabric to dyed or not dyed
     * and updates the impact score accordingly
     * @param dyed true if the fabric is dyed, false otherwise
     */
    
    public void setDyed(boolean dyed) {
        this.dyed = dyed;
        if(dyed)
            super.setImpactScore(getImpactScore() + 0.1);
        else
            super.setImpactScore(getImpactScore() - 0.1);
    }

    /*
     * Sets status of that fabric's harmful dyes status to true or false
     * and updates the impact score accordingly
     * @param harmfulDyes true if the fabric has harmful dyes, false otherwise
     */
    public void setHarmfulDyed(boolean harmfulDyes) {
        this.harmfulDyes = harmfulDyes;
        if(harmfulDyes)
            super.setImpactScore(getImpactScore() + 0.1);
        else
            super.setImpactScore(getImpactScore() - 0.1);
    }

    // Field accessor methods

    public boolean isDyed() {
        return dyed;
    }

    public boolean isHarmfulDyed(){
        return harmfulDyes;
    }

    @Override
    public boolean attemptDonate(Material material) {
        // Logic for taking the material to a donation center
        return !harmfulDyes; // Example: Only allow donation if not dyed and no harmful dyes
    }

    @Override
    public boolean attemptCenter(Material material){
        // Logic for taking the material to a center
        return !harmfulDyes; // Example: Only allow center if not dyed and no harmful dyes
    }

    @Override
    public boolean attemptDispose(Material material){
        return !harmfulDyes; // Example: Only allow disposal if no harmful dyes
    }

    public String getSpecial() {
        return("May have harmful dyes, which is more harmful for the environment");
    }
}