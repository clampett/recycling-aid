package src.recycling_types.materials;


import src.recycling_types.Material;
import src.recycling_types.categories.Compostable;

/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/20/2025
 */
public class Wood extends Material implements Compostable{
    private boolean manufactured;

    private static final String[] possibleItems = {"log", "furniture", "Popsicle Sticks", "Chopsticks", "Pencils"};

    public Wood(boolean manufactured) {
        super(0.1, possibleItems);
        this.manufactured = manufactured;

        if(this.manufactured)
            setImpactScore(0.5);
    }

    public String getSpecial() {
        return("May be manufactured, which could be more harmful for the environment");
    }

}
