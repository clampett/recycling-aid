package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Paper} is a concrete representation of garbage made out of cardboard and
 * is a subclass of {@link src.recycling_types.Material Material}
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Cardboard extends Material implements Binnable, Disposable {
    /**Whether the carboard is soiled*/
    private boolean soiled;

    /**
     * Some possible items that cardboard could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("cardboard", "box", "packing material")
    );

    public Cardboard() {
        this(true);
    }
    
    public Cardboard(boolean soiled) {
        super(0.2, possibleItems);
        this.soiled = soiled;

        if(soiled)
            super.setImpactScore(0.3);
    }

    public String getSpecial() {
        return("May be soiled, which is more harmful for the environment");
    }
}