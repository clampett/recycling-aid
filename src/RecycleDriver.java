package src;

import src.gui.*;
import src.recycling_types.materials.*;

/**
 * {@link src.RecycleDriver RecycleDriver} is the main driver for the Recycling Project.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/4/2025
 */
public class RecycleDriver {
    public static void main(String[] args) {
        resetSerialized();
        //Gui.main(args);
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

        Loader.serialize(c, "data/serialized/cardboard.txt", Gui.L);
        Loader.serialize(e, "data/serialized/electronic.txt", Gui.L);
        Loader.serialize(f, "data/serialized/fabric.txt", Gui.L);
        Loader.serialize(fw, "data/serialized/food_waste.txt", Gui.L);
        Loader.serialize(g, "data/serialized/glass.txt", Gui.L);
        Loader.serialize(m, "data/serialized/metal.txt", Gui.L);
        Loader.serialize(p, "data/serialized/paper.txt", Gui.L);
        Loader.serialize(pl, "data/serialized/plastic.txt", Gui.L);
        Loader.serialize(w, "data/serialized/wood.txt", Gui.L);
    }
}