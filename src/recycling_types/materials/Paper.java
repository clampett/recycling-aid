package src.recycling_types.materials;

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

    private static final String[] possibleItems = {"Newspaper", "Homework", "Junk Mail", "Paper Towels", "Paper Plates"};

    public Paper(String shape) {
        super(0.1, possibleItems);

        this.shape = shape;
    }

    public String getSpecial() {
        return("paper special");
    }
}
