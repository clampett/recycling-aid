package src.recycling_types.materials;

import src.recycling_types.Material;
import src.recycling_types.categories.Compostable;

/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/20/2025
 */
public class Food_Waste extends Material implements Compostable{
    private static final String[] possibleItems = {"Scraps", "Eggshells", "Coffee Grounds", "Tea Bags", "Meat Bones", "Stale Bread"};

    public Food_Waste() {
        super(0.1, possibleItems);
    }

    public String getSpecial() {
        return("food special");
    }
}
