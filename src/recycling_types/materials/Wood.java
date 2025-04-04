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
public class Wood extends Material implements Compostable, Disposable {
    /**Whether the {@link src.recycling_types.materials.Wood Wood} was manufactured.*/
    private boolean manufactured;

    /**
     * Some possible items that {@link src.recycling_types.materials.Wood Wood} could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("wood", "log", "furniture", "popsicle stick", "chopstick", "pencil")
    );
    
    public Wood(boolean manufactured) {
        super(0.1, possibleItems);
        this.manufactured = manufactured;

        if(this.manufactured)
            setImpactScore(0.5);
    }

    public Wood() {
        this(true);
    }

    public String getSpecial() {
        return("May be manufactured, which could be more harmful for the environment");
    }

}
