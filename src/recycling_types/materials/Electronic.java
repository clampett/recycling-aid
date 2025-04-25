package src.recycling_types.materials;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.lang.reflect.Field;

import src.customExceptions.failedCenterException;
import src.customExceptions.failedDisposeException;
import src.customExceptions.failedDonateException;
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

    //Mutator methods

    /**
     * Sets whether {@link Electronic} has a battery and updates impact score.
     * @param hasBattery T/F - does {@link Electronic} have a battery?
     */
    public void setBatteryStatus(boolean hasBattery) {
        this.hasBattery = hasBattery;
        if(hasBattery)
            super.setImpactScore(0.80);
        else
            super.setImpactScore(0.65);
    }

    //Accessor methods

    /**
     * Gets whether {@link Electronic} has a battery.
     * @return {@link Electronic} battery
     */
    public boolean getHasBattery() {
        return hasBattery;
    }

    //Interface methods

    @Override
    public boolean attemptDonate(Material material) throws failedDonateException{
        return true; // Example: Always allow donation
    }

    @Override
    public boolean attemptCenter(Material material) throws failedCenterException{
        if(((Electronic)material).getHasBattery() == true){
            return false;
        }
        else return true;
    }

    @Override
    public boolean attemptDispose(Material material) throws failedDisposeException{
        if(((Electronic)material).getHasBattery() == true){
            return false;
        }
        else return true;
    }

    //Material superclass methods

    @Override
    public String getSpecial() {
        return("May have a battery, which is more harmful for the environment");
    }
}