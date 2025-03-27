package src.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.Loader;
import src.recycling_types.Material;

/**
 * {@link src.gui.Impact_Calculator Impact_Calculator} calcuates the user's impact score.
 * <p> 
 * The user supplies a list of items that they are disposing. If present in a
 * {@link src.recycling_types.Material Material's} possibleItem {@code Set}, then that 
 * Material counts towards the score. Materials are also serialized so the user can save
 * custom items inbetween session
 * </p>
 * 
 * @see src.Loader Loader
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/27/2025
 */
public class Impact_Calculator {
    /**A {@HashMap} of every item and it's corresponding {@link src.recycling_types.Material Material}.*/
    private Map<String, Material> allItems;

    /**Serialized {@link src.recycling_types.Material Material} objects.*/
    private List<Material> deserializedMaterials;

    public Impact_Calculator() {
        deserializedMaterials = deserializeMaterials();
        allItems = collateItems();
    }

    /**
     * Deserializes materials and returns them as a {@code List<>}
     * 
     * @return {@code List<Material>} deserialized materials
     */
    private List<Material> deserializeMaterials() {
        List<Object> loaded = Loader.deserialize_dir("src/data/serialized", Gui.L);
        List<Material> loadedMaterials = new ArrayList<>(loaded.size());

        for(Object mat : loaded)
            loadedMaterials.add((Material) mat);

        return loadedMaterials;
    }

    /**
     * Collates all items from all {@link src.recycling_types.Material Material} classes
     * 
     * @return {@code HashMap<String, Material>} containing all items and their {@link src.recycling_types.Material Material}
     */
    private HashMap<String, Material> collateItems() {
        HashMap<String, Material> allItems = new HashMap<>(64);

        for(Material mat : deserializedMaterials) {
            for(String item : mat.getPossibleItems()) {
                allItems.put(item, mat);
            }
        }
        return allItems;
    }

    /**
     * Checks if given item is present in the allItems {@code HashMap}
     * 
     * @param item item to check
     * @return TRUE if present; FALSE otherwise
     */
    public boolean checkItemList(String item) {
        if(allItems.containsKey(item.toLowerCase()))
            return true;
        return false;
    }

    /**
     * Deserializes the {@link src.recycling_types.Material Material} objects.
     * Used to bring the deserializedMaterials {@code List} up to date with any changes.
     */
    public void reloadMaterials() {
        deserializedMaterials = deserializeMaterials();
    }

    /**
     * Updates the allItems {@code List} with any changes.
     */
    public void reCollate() {
        allItems = collateItems();
    }

    /**
     * Searches the deserializedMaterials {@code List} for a {@link src.recycling_types.Material Material}
     * whose name matches the given name.
     * 
     * @param newMat {@link src.recycling_types.Material Material} name to search for
     * @return Matching {@link src.recycling_types.Material Material}
     */
    public Material getMaterialToAdd(String newMat) {
        Material toAdd = null;

        for(Material mat : deserializedMaterials)
            if(mat.getName().equals(newMat)) {
                toAdd = mat;
                break;
            }

        if(toAdd == null)
            Gui.L.severe("Could not find " + newMat);

        return toAdd;
    }

    /**
     * Searches every item and returns a {@code List} of every item's {@link src.recycling_types.Material Material}.
     * 
     * @param selectedTrash {@code List} of items
     * @return {@code List} of {@link src.recycling_types.Material Materials}
     */
    public List<Material> getMaterials(List<String> selectedTrash) {
        List<Material> selectedMaterials = new ArrayList<>(16);

        for(String item : selectedTrash) {
            if(checkItemList(item)) {
                selectedMaterials.add(allItems.get(item));
            }
            else
                Gui.L.severe(item + " NOT found in item list.");
        }

        return selectedMaterials;
    }

    /**
     * Loops through the given {@code List} and returns the average impact score from every {@link src.recycling_types.Material Material}.
     * 
     * @param selectedMaterials {@code List} of {@link src.recycling_types.Material Materials}
     * @return Average Impact Score
     */
    public double getImpactScore(List<Material> selectedMaterials) {
        double score = 0.0;

        for(Material mat : selectedMaterials) {
            score += mat.getImpactScore();
        }

        return (score / selectedMaterials.size());
    }

    /**
     * Serializes every deserialized {@link src.recycling_types.Material Material}.
     */
    public void reSerialize() {
        List<String> paths = Loader.load_dir_paths("src/data/serialized", Gui.L);
        Collections.sort(paths);

        for(int i = 0; i < paths.size(); i++) {
            Loader.serialize(deserializedMaterials.get(i), paths.get(i), Gui.L);
        }
    }
}