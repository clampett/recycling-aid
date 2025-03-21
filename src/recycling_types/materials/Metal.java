package src.recycling_types.materials;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/20/2025
 */
public class Metal extends Material implements Centerable, Disposable{
    private static final String[] possibleItems = {"Aluminum Cans", "Tin Cans", "Aluminum Foil", "Razor Blades", "Nails", "Screws"};
    
    public Metal() {
        super(0.25, possibleItems);
    }

    public String getSpecial() {
        return("metal special");
    }
}
