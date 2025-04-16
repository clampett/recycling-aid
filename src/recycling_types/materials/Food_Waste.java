package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Food_Waste} is a concrete representation of garbage made out of food and
 * is a subclass of {@link src.recycling_types.Material Material}
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Food_Waste extends Material implements Compostable, Disposable {
    /**Whether the food waste is organic*/
    private boolean isOrganic;

    /**How long the food takes to decompose*/
    private int decompositionTime;

    /**
     * Some possible items that food waste could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("food waste", "scrap", "eggshell", "coffee ground", "tea bag", "meat bone", "stale bread")
    );

    public Food_Waste() {
        this(true, 1);
    }

    public Food_Waste(boolean isOrganic, int decompositionTime) {
        super(0.1, possibleItems);
        super.setImpactScore(setImpact(decompositionTime));

        this.isOrganic = isOrganic;
        this.decompositionTime = decompositionTime;

        if(!isOrganic)
            super.setImpactScore(getImpactScore() + 0.1);
    }

    public Food_Waste(boolean isOrganic) {
        this(isOrganic, 1);
    }

    /**
     * Sets impact score based on how long the food takes to decompose
     * 
     * @param decompositionTime number of days it takes to decompose
     * @return impact score
     */
    private double setImpact(int decompositionTime) {
        double impact;

        if(decompositionTime <= 0) //nonvalid
            impact = Double.MAX_VALUE;
        else if(decompositionTime <= 3) //1-3 days
            impact = 0.1;
        else if(decompositionTime <= 7) //4-7 days
            impact = 0.15;
        else if(decompositionTime <= 14) // 8-14 days
            impact = 0.2;
        else
            impact = 0.35;
        
        return impact;
    }

    @Override
    public boolean attemptDispose(Material material){
        // Logic for disposing of the material
        return isOrganic; // Example: Only allow disposal if organic
    }

    @Override
    public boolean attemptCompost(Material material){
        // Logic for composting the material
        return isOrganic; // Example: Only allow composting if organic
    }

    public String getSpecial() {
        return("May be nonorganic, which is more harmful for the environment" + 
               "\nDecomposition time may also vary, which can impact how good the food waste is for the environment");
    }

    // Field mutator methods

    /*
     * Sets status of the food waste to organic or not organic
     * and updates the impact score accordingly
     * @param isOrganic will set the food's organic status to true or false
     */
    public void setOrganic(boolean isOrganic) {
        this.isOrganic = isOrganic;
        if(isOrganic)
            super.setImpactScore(getImpactScore() - 0.1);
        else
            super.setImpactScore(getImpactScore() + 0.1);
    }

    /*
     * Sets the deconposition time field to a given value
     * @param decompositionTime integer number of days it takes to decompose
     */

    public void setDecompositionTime(int decompositionTime) {
        this.decompositionTime = decompositionTime;
        super.setImpactScore(setImpact(decompositionTime));
    }

    // Field accessor methods

    // Returns a boolean that describes the food's organic status
    //@return isOrganic
    public boolean getIsOrganic() {
        return isOrganic;
    }

    // Returns the time it takes for food to decompose
    //@return decompositionTime
    public int getDecompositionTime() {
        return decompositionTime;
    }
}