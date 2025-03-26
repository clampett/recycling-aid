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
public class Cardboard extends Material implements Binnable, Disposable{
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("cardboard", "box", "packing material")
    );

    public Cardboard() {
        super(0.2, possibleItems);
    }

    public String getSpecial() {
        return("cardboard special");
    }
}
