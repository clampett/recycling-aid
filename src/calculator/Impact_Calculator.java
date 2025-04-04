package src.calculator;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.gui.Gui;
import src.Loader;
import src.recycling_types.Material;

/**
 * {@link src.calculator.Impact_Calculator Impact_Calculator} calcuates the user's impact score.
 * <p> 
 * The user supplies a list of items that they are disposing. If present in a
 * {@link src.recycling_types.Material Material's} possibleItem {@code Set}, then that 
 * Material counts towards the score. Materials are also serialized so the user can save
 * custom items inbetween sessions.
 * </p>
 * 
 * @see src.Loader Loader
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/4/2025
 */
public class Impact_Calculator {
    /**A {@HashMap} of every item and it's corresponding {@link src.recycling_types.Material Material}.*/
    private Map<String, Material> allItems;

    /**Serialized {@link src.recycling_types.Material Material} objects.*/
    private List<Material> deserializedMaterials;

    /**
     * {@link src.calculator.Impact_Calculator Impact_Calculator} constructor.
     * <ul>
     *      <li>Deserializes the serialized Material objects.
     *      <li>Collates all of the possible items from every deserialized Material to a single {@code HashMap}.
     * </ul>
     */
    public Impact_Calculator() {
        deserializedMaterials = deserializeAllMaterials();
        allItems = collateItems();
    }

    /**
     * Deserializes materials and returns them as a {@code List<>}
     * 
     * @see src.Loader#deserialize_dir(String, java.util.logging.Logger) Deserializer
     * @return {@code List<Material>} deserialized materials
     */
    private List<Material> deserializeAllMaterials() {
        return Loader.deserialize_dir("src/data/serialized", Gui.L)
                     .stream()
                     .map(mat -> (Material) mat)
                     .toList();
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
     * @throws ItemNotFoundException
     * @param item item to check
     * @return TRUE if present; FALSE otherwise
     */
    public boolean checkItemList(String item) throws ItemNotFoundException {
        if(allItems.containsKey(item.toLowerCase()))
            return true;

        throw new ItemNotFoundException(item);
    }

    /**
     * Deserializes the {@link src.recycling_types.Material Material} objects.
     * Used to bring the deserializedMaterials {@code List} up to date with any changes.
     */
    public void reloadMaterials() {
        deserializedMaterials = deserializeAllMaterials();
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
        Material toAdd = deserializedMaterials.stream()
                                              .filter(mat -> mat.getName().equals(newMat))
                                              .findAny()
                                              .orElse(null);

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
        return selectedTrash.parallelStream()
                            .map(item -> allItems.get(item))
                            .toList();
    }

    /**
     * Loops through the given {@code List} and returns the average impact score from every {@link src.recycling_types.Material Material}.
     * 
     * @param selectedMaterials {@code List} of {@link src.recycling_types.Material Materials}
     * @return Average Impact Score
     */
    public double getImpactScore(List<Material> selectedMaterials) {
        return selectedMaterials.stream()
                                .mapToDouble(Material::getImpactScore)
                                .average()
                                .getAsDouble();
    }

    /**
     * Serializes every deserialized {@link src.recycling_types.Material Material}.
     * 
     * @see src.Loader#serialize(Object, String, java.util.logging.Logger) Serializer
     */
    public void reSerialize() {
        List<String> paths = Loader.load_dir_paths("src/data/serialized", Gui.L);
        Collections.sort(paths);

        for(int i = 0; i < paths.size(); i++) {
            Loader.serialize(deserializedMaterials.get(i), paths.get(i), Gui.L);
        }
    }
}