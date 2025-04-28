package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.recycling_types.*;
import src.recycling_types.categories.*;

/**
 * {@link Wood} is a concrete representation of garbage made out of wood and
 * is a subclass of {@link src.recycling_types.Material Material}.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Wood extends Material implements Compostable, Disposable {
    /**Whether the {@link Wood} was manufactured.*/
    private boolean manufactured;

    /**Whether the {@link Wood} is contaminated.*/
    private boolean contaminated;

    /**
     * Some possible items that wood could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("wood", "log", "furniture", "popsicle stick", "chopstick", "pencil")
    );
    
    public Wood() {
        this(true, false);
    }

    public Wood(boolean manufactured, boolean contaminated) {
        super(0.1, possibleItems);
        this.manufactured = manufactured;

        if(this.manufactured)
            super.setImpactScore(0.5);
    }

    //Mutator methods

    /**
     * Sets whether {@link Wood} is manufactured and updates impact score.
     * @param manufactured T/F - is {@link Wood} manufactured?
     */
    public void setManufactured(boolean manufactured){
        this.manufactured = manufactured;
        if(manufactured)
            super.setImpactScore(0.5); // Example impact score for manufactured wood
        else
            super.setImpactScore(0.1); // Example impact score for non-manufactured wood
    }

    /**
     * Sets whether {@link Wood} is contaminated and updates impact score.
     * @param isContaminated T/F - is {@link Wood} contaminated? 
     */
    public void setContaminated(boolean isContaminated) {
        this.contaminated = isContaminated;
        if(isContaminated) 
            super.setImpactScore(0.5); // Example impact score for contaminated wood
        else 
            super.setImpactScore(0.1); // Example impact score for non-contaminated wood
    }

    //Accessor methods

    /**
     * Gets whether {@link Wood} is manufactured.
     * @return {@link Wood} manufactured
     */
    public boolean getManufactured() {
        return manufactured;
    }

    /**
     * Gets whether {@link Wood} is contaminated.
     * @return {@link Wood} contaminated
     */
    public boolean getContaminated() {
        return contaminated;
    }

    //Interface methods

    @Override
    public boolean attemptCompost(Material material) {

        if(((Wood)material).getContaminated() == true){
            return false;
        }
        else if(((Wood)material).getManufactured() == true){
            return false;
        }
        else return true;

    }

    @Override
    public boolean attemptDispose(Material material) {
        if(((Wood)material).getContaminated() == true){
            return true;
        }
        else if(((Wood)material).getManufactured() == true){
            return true;
        }
        else return false;
    }
    //Material superclass methods

    @Override
    public String getSpecial() {
        return("May be manufactured, which could be more harmful for the environment");
    }
}