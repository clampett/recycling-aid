package src;

import src.gui.Gui;
import src.recycling_types.materials.*;

import java.util.logging.*;

/**
 * {@link src.RecycleDriver RecycleDriver} is the main driver for the Recycling Project.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/4/2025
 */
public class RecycleDriver {
    public static final Logger L = Logger.getLogger(RecycleDriver.class.getName());

    /**
     * Central main method for running the application.
     * 
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        Gui.main(args, L);
    }

    /**
     * Utility method for reseting every serialized {@link src.recycling_types.Material Material} to their default state.
     */
    private static void resetSerialized() {
        Cardboard c = new Cardboard();
        Electronic e = new Electronic();
        Fabric f = new Fabric();
        Food_Waste fw = new Food_Waste();
        Glass g = new Glass();
        Metal m = new Metal();
        Paper p = new Paper();
        Plastic pl = new Plastic();
        Wood w = new Wood();

        Loader.serialize(c, "data/serialized/cardboard.txt", L);
        Loader.serialize(e, "data/serialized/electronic.txt", L);
        Loader.serialize(f, "data/serialized/fabric.txt", L);
        Loader.serialize(fw, "data/serialized/food_waste.txt", L);
        Loader.serialize(g, "data/serialized/glass.txt", L);
        Loader.serialize(m, "data/serialized/metal.txt", L);
        Loader.serialize(p, "data/serialized/paper.txt", L);
        Loader.serialize(pl, "data/serialized/plastic.txt", L);
        Loader.serialize(w, "data/serialized/wood.txt", L);
    }
}