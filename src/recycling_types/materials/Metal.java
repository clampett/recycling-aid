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
public class Metal extends Material implements Centerable, Disposable{
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("metal", "aluminum Can", "tin can", "aluminum foil", "razor blade", "nail", "screw")
    );
    
    public Metal() {
        super(0.25, possibleItems);
    }

    public String getSpecial() {
        return("metal special");
    }
}
