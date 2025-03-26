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
public class Paper extends Material implements Binnable, Compostable, Disposable{
    public String shape;

    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("paper", "newspaper", "homework", "junk mail", "paper towel", "paper plate")
    );

    public Paper(String shape) {
        super(0.1, possibleItems);

        this.shape = shape;
    }

    public Paper() {
        this("Rectangle");
    }

    public String getSpecial() {
        return("paper special");
    }
}
