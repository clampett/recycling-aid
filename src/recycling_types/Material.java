package src.recycling_types;

import java.util.List;
import java.util.ArrayList;

import src.recycling_types.materials.*;
import src.Loader;

/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/20/2025
 */
public abstract class Material {
    /**The material's name.*/
    private String name;

    /**The material's interfaces.*/
    private String[] categories;

    /**Some possible items that a material could be.*/
    private String[] possibleItems;

    /**The quantified impact that a material has on an environment, between 0 & 1.*/
    private double impact;

    /**
     * Header for the {@code TableView} used in {@link src.gui.Gui_Info Gui_Info}.
     * It contains all of a material's variables.
    */
    public static final String[] DISPLAY_HEADERS = {"Name", "Category", "Impact Score", "Special Characteristics", "Possible Items"};

    /**
     * A {@code List} of all possible {@link Material Materials}.
     */
    public static final List<Material> ALL_MATERIALS = createAllMaterials();

    public Material(double impact, String[] possibleItems) {
        this.name = this.getClass().getSimpleName().replaceAll("_", " ");
        this.categories = setCategories();
        this.impact = impact;
        this.possibleItems = possibleItems;
    }

    /**
     * Gets a class' interfaces and converts them into Strings.
     * Used in {@link src.gui.Gui_Info Gui_Info} to display information.
     * 
     * @return String array of all class' interfaces
     */
    private String[] setCategories() {
        Class<?>[] interfaces = this.getClass().getInterfaces();
        String[] categories = new String[interfaces.length];

        for(int i = 0; i < interfaces.length; i++)
            categories[i] = interfaces[i].getSimpleName();

        return categories;
    }

    private static List<Material> createAllMaterials() {
        List<Material> materials = new ArrayList<>();

        materials.add(new Cardboard());
        materials.add(new Electronic(false));
        materials.add(new Fabric());
        materials.add(new Food_Waste());
        materials.add(new Glass());
        materials.add(new Metal());
        materials.add(new Paper("Rectangle"));
        materials.add(new Plastic(1));
        materials.add(new Wood(true));

        return materials;
    }

    public String getName() {
        return this.name;
    }

    public String[] getCategories() {
        return this.categories;
    }

    public String[] getPossibleItems() {
        return this.possibleItems;
    }

    public double getImpactScore() {
        return this.impact;
    }

    public void setImpactScore(double impact) {
        this.impact = impact;
    }

    public abstract String getSpecial();
}