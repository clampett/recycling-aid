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
public class Electronic extends Material implements Centerable, Donatable, Disposable {
    private boolean hasBattery;

    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("electronic", "phone", "battery", "headphone", "laptop", "tv", "gaming console")
    );


    public Electronic(boolean hasBattery) {
        super(0.65, possibleItems);

        if(hasBattery)
            setImpactScore(0.80);
    }

    public Electronic() {
        this(true);
    }

    public String getSpecial() {
        return("electronic special");
    }
}
