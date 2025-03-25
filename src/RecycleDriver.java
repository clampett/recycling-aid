package src;

import src.gui.Gui;
import src.recycling_types.*;
import src.recycling_types.materials.*;

public class RecycleDriver {
    public static void main(String[] args) {
        String[][] a = Loader.load_csv("src/data/text/recyclingCenters.csv", Gui.L);

        for(String[] b : a) {
            for(String c : b)
                System.out.println(c);
        }
    }
}