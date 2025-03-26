package src.recycling_types;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import src.recycling_types.materials.*;

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
    private Set<String> possibleItems;

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
    public static final List<Class<? extends Material>> ALL_MATERIALS = createAllMaterials();

    public Material(double impact, Set<String> possibleItems) {
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

    /**
     * Creates all Material subclasses, minus Nuclear_Waste & Evidence. Used in
     * {@link src.gui.Gui_Info Gui_Info} to display material variables.
     * 
     * @return {@code List<Class<? extends Material>>} of all Material subclasses
     */
    private static List<Class<? extends Material>> createAllMaterials() {
        List<Class<? extends Material>> materials = new ArrayList<>();

        materials.add(Cardboard.class);
        materials.add(Electronic.class);
        materials.add(Fabric.class);
        materials.add(Food_Waste.class);
        materials.add(Glass.class);
        materials.add(Metal.class);
        materials.add(Paper.class);
        materials.add(Plastic.class);
        materials.add(Wood.class);

        return materials;
    }

    /**
     * Adds a new item to possibleItems {@code Set}.
     * 
     * @param toAdd String to add
     */
    public void addToPossibleItems(String toAdd) {
        possibleItems.add(toAdd);
    }

    public String getName() {
        return this.name;
    }

    public String[] getCategories() {
        return this.categories;
    }

    public Set<String> getPossibleItems() {
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