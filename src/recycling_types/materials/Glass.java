package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Glass} is a concrete representation of garbage made out of glass and
 * is a subclass of {@link src.recycling_types.Material Material}
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

    @Override
    public boolean attemptBin(Material material){
        // Logic for taking the material to a bin
        return !isTempered; // Example: Only allow binning if not tempered
    }

    @Override
    public boolean attemptDispose(Material material){
        // Logic for disposing of the material
        return !isTempered; // Example: Only allow disposal if not tempered
    }

    public String getSpecial() {
        return("May be tempered, which is more harmful for the environment");
    }
}