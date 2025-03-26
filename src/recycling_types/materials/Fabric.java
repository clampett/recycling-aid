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
public class Fabric extends Material implements Centerable, Donatable, Disposable{
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("fabric", "clothing", "bedsheet", "shoe", "tablecloth", "curtain")
    );

    public Fabric() {
        super(0.3, possibleItems);
    }

    public String getSpecial() {
        return("fabric special");
    }
}
