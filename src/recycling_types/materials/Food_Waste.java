package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.gui.Gui;
import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Food_Waste} is a concrete representation of garbage made out of food and
 * is a subclass of {@link src.recycling_types.Material Material}.
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

    //Mutator methods

    /**
     * Sets impact score based on how long the food takes to decompose
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

    /**
     * Sets whether {@link Food_Waste} is organic and updates impact score.
     * @param isOrganic T/F - is {@link Food_Waste} organic?
     */
    public void setOrganic(boolean isOrganic) {
        this.isOrganic = isOrganic;
        if(isOrganic)
            super.setImpactScore(getImpactScore() - 0.1);
        else
            super.setImpactScore(getImpactScore() + 0.1);
    }

    /**
     * Sets the {@link Food_Waste} decomposition time and updates impact score.
     * @param decompositionTime How long does it take for {@link Food_Waste} to decompose
     */
     public void setDecompositionTime(int decompositionTime) {
        this.decompositionTime = decompositionTime;
        super.setImpactScore(setImpact(decompositionTime));
    }

    //Accessor methods

    /**
     * Gets whether {@link Food_Waste} is organic.
     * @return {@link Food_Waste} organic
     */
    public boolean getIsOrganic() {
        return isOrganic;
    }

    /**
     * Gets the amount of time it takes for {@link Food_Waste} to decompose.
     * @return {@link Food_Waste} decompostion time
     */
    public int getDecompositionTime() {
        return decompositionTime;
    }

    //Interface methods

    @Override
    public boolean attemptDispose(Material material){
        // Logic for disposing of the material
        if(((Food_Waste)material).getIsOrganic()){
            Gui.L.info("Material successfully disposed of.");
            return true;
        }
        else return false;
        
    }

    @Override
    public boolean attemptCompost(Material material){
        // Logic for composting the material
        if(((Food_Waste)material).getIsOrganic()){
            Gui.L.info("Material successfully composted of.");
            return true;
        }
        else {
            Gui.L.warning("Material cannot be composted.");
            Gui.L.warning("Material is not organic.");
            return false;
        }
    }

    //Material superclass methods

    @Override
    public String getSpecial() {
        return("May be nonorganic, which is more harmful for the environment" + 
               "\nDecomposition time may also vary, which can impact how good the food waste is for the environment");
    }
}