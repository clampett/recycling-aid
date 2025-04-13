package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Wood} is a concrete representation of garbage made out of wood and
 * is a subclass of {@link src.recycling_types.Material Material}
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Wood extends Material implements Compostable, Disposable {
    /**Whether the {@link Wood} was manufactured.*/
    private boolean manufactured;
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
        this.contaminated = contaminated;

        if(this.manufactured)
            // TODO: add impact score for contaminated wood
            super.setImpactScore(0.5);
    }

    @Override
    public boolean attemptCompost(Material material){
        // Logic for composting the material
        return !manufactured && !contaminated; // Example: Only allow composting if not manufactured and not contaminated
    }

    @Override
    public boolean attemptDispose(Material material){
        // Logic for disposing of the material
        return !manufactured && !contaminated; // Example: Only allow disposal if not manufactured and not contaminated
    }
    
    public String getSpecial() {
        return("May be manufactured, which could be more harmful for the environment");
    }

}
