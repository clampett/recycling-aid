package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.gui.Gui;
import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Electronic} is a concrete representation of electronic garbage and
 * is a subclass of {@link src.recycling_types.Material Material}.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Electronic extends Material implements Centerable, Donatable, Disposable {
    /**Whether the electronic has a battery*/
    private boolean hasBattery;

    /**
     * Some possible items that electronic could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("electronic", "phone", "battery", "headphone", "laptop", "tv", "gaming console")
    );

    public Electronic() {
        this(true);
    }

    
    public Electronic(boolean hasBattery) {
        super(0.65, possibleItems);
        this.hasBattery = hasBattery;

        if(hasBattery)
            super.setImpactScore(0.80);
    }

    // Field mutator methods
    public void setBatteryStatus(boolean hasBattery) {
        this.hasBattery = hasBattery;
        if(hasBattery)
            super.setImpactScore(0.80);
        else
            super.setImpactScore(0.65);
    }

    // Field accessor methods
    public boolean getHasBattery() {
        return hasBattery;
    }

    @Override
    public boolean attemptDonate(Material material) {
        return true; // Example: Always allow donation
    }

    @Override
    public boolean attemptCenter(Material material){
        // Logic for taking the material to a center
       return true; // Example: Always allow center
    }

    @Override
    public boolean attemptDispose(Material material){
        // Logic for disposing of the material
        return true;
    }

    public String getSpecial() {
        return("May have a battery, which is more harmful for the environment");
    }
}