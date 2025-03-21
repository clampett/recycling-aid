package src.recycling_types.materials;

import src.recycling_types.categories.Donatable;
import src.recycling_types.Material;
import src.recycling_types.categories.Centerable;

/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/20/2025
 */
public class Electronic extends Material implements Centerable, Donatable{
    private boolean hasBattery;

    private static final String[] possibleItems = {"Phones", "Batteries", "Headphones", "Laptops", "TVs", "Gaming Console"};

    public Electronic(boolean hasBattery) {
        super(0.65, possibleItems);

        if(hasBattery)
            setImpactScore(0.80);
    }

    public String getSpecial() {
        return("electronic special");
    }
}
