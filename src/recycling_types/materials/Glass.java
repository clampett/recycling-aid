package src.recycling_types.materials;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/20/2025
 */
public class Glass extends Material implements Binnable, Disposable{
    private static final String[] possibleItems = {"Bottles", "Jars", "Cookware", "Light Bulbs", "Ornaments"};

    public Glass() {
        super(0.25, possibleItems);
    }

    public String getSpecial() {
        return("glass special");
    }
}
