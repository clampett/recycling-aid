package src.recycling_types;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import src.recycling_types.materials.*;

/**
 * {@link src.recycling_types.Material Material} is an abstract representation of potential materials that garbage could be.
 * <p>
 * All Material objects have these attributes:
 * <ul>
 *      <li>Name
 *      <li>A list of categories the material fits into (aka a subclass' interfaces)
 *      <li>A {@code Set} of some potential items that are made of the material
 *      <li>An impact score (i.e. the material's impact on the environment)
 * </ul>
 * All Material subclasses include these attributes and often include other unique attributes.
 * <p>
 * Material also has numerous helper methods, increasing its usefulness for display and usability purposes.
 * </p>
 *
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/4/2025
 */
public abstract class Material implements Serializable {
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
     * Gets categories ready to display as a String, delimited by ", ".
     * 
     * @return categories as a String
     */
    public String displayCategories() {
        return String.join(", ", categories);
    }

    /**
     * Creates all Material subclasses, minus Nuclear_Waste. Used in
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

    /**
     * Gets possibleItems ready to display by capitalizing the first word and removing brackets.
     * @return possibleItems cleaned and as a String
     */
    public String displayPossibleItems() {
        Set<String> cleanedPossibleItems = 
        possibleItems.stream()
                     .map(item -> 
                        item.substring(0,1).toUpperCase() + item.substring(1))
                     .collect(Collectors.toSet());

        return cleanedPossibleItems.toString().replaceAll("\\[|\\]", "");
    }

    /**
     * Gets {@link src.recycling_types.Material Material} name
     * @return {@link src.recycling_types.Material Material} name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets {@link src.recycling_types.Material Material} categories
     * @return {@link src.recycling_types.Material Material} categories
     */
    public String[] getCategories() {
        return this.categories;
    }

    /**
     * Gets {@link src.recycling_types.Material Material} possibleItems
     * @return {@link src.recycling_types.Material Material} possibleItems
     */
    public Set<String> getPossibleItems() {
        return this.possibleItems;
    }

    /**
     * Gets {@link src.recycling_types.Material Material} impact score
     * @return {@link src.recycling_types.Material Material} impact
     */
    public double getImpactScore() {
        return this.impact;
    }

    /**
     * Sets {@link src.recycling_types.Material Material} impact score
     * @param impact new impact score
     */
    public void setImpactScore(double impact) {
        this.impact = impact;
    }

    /**
     * Gets {@link src.recycling_types.Material Material} special characteristics
     * @return {@link src.recycling_types.Material Material} special
     */
    public abstract String getSpecial();
}