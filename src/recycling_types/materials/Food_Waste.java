package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.recycling_types.Material;
import src.recycling_types.categories.Compostable;

/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/20/2025
 */
public class Food_Waste extends Material implements Compostable{
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("food waste", "scrap", "eggshell", "coffee ground", "tea bag", "meat bone", "stale bread")
    );

    public Food_Waste() {
        super(0.1, possibleItems);
    }

    public String getSpecial() {
        return("food special");
    }
}
