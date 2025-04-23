package src.recycling_types.materials;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.customExceptions.failedCenterException;
import src.customExceptions.failedDisposeException;
import src.customExceptions.failedDonateException;
import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Fabric} is a concrete representation of garbage made out of fabric and
 * is a subclass of {@link src.recycling_types.Material Material}.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Fabric extends Material implements Centerable, Donatable, Disposable {
    /**Whether the fabric is dyed*/
    private boolean dyed;

    /**Whether the fabric has harmful dyes*/
    private boolean harmfulDyes;

    /**
     * Some possible items that fabric could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("fabric", "clothing", "bedsheet", "shoe", "tablecloth", "curtain")
    );

    public Fabric() {
        this(false, false);
    }

    public Fabric(boolean dyed, boolean harmfulDyes) {
        super(0.3, possibleItems);
        this.dyed = dyed;
        this.harmfulDyes = harmfulDyes;

        if(dyed)
            super.setImpactScore(getImpactScore() + 0.1);

        if(harmfulDyes)
            super.setImpactScore(getImpactScore() + 0.1);
    }

    //Mutator methods

    /**
     * Sets whether {@link Fabric} is dyed and updates impact score.
     * @param dyed T/F - is {@link Fabric} dyed?
     */
    public void setDyed(boolean dyed) {
        this.dyed = dyed;
        if(dyed)
            super.setImpactScore(getImpactScore() + 0.1);
        else
            super.setImpactScore(getImpactScore() - 0.1);
    }

    /**
     * Sets whether {@link Fabric} has harmful dyes and updates impact score.
     * @param harmfulDyes T/F - does {@link Fabric} have harmful dyes?
     */
    public void setHarmfulDyed(boolean harmfulDyes) {
        this.harmfulDyes = harmfulDyes;
        if(harmfulDyes)
            super.setImpactScore(getImpactScore() + 0.1);
        else
            super.setImpactScore(getImpactScore() - 0.1);
    }

    //Accessor methods

    /**
     * Gets whether {@link Fabric} is dyed.
     * @return {@link Fabric} dyed
     */
    public boolean isDyed() {
        return dyed;
    }

    /**
     * Gets whether {@link Fabric} has harmful dyes.
     * @return {@link Fabric} harmfulDyed
     */
    public boolean isHarmfulDyed(){
        return harmfulDyes;
    }

    //Interface methods

    @Override
    public boolean attemptDonate(Material material, Field[] fields) throws failedDonateException{
        boolean donatable = true;
        for (Field f : fields) {
            f.getName();
            if (f.getName().equals("harmfulDyes")) {
                if(f.equals(true)) {
                    donatable = false;
                }
                }
            }
        return donatable;
    }

    @Override
    public boolean attemptCenter(Material material, Field[] fields) throws failedCenterException{
        boolean donatable = true;
        for (Field f : fields) {
            f.getName();
            if (f.getName().equals("harmfulDyes")) {
                if(f.equals(true)) {
                    donatable = false;
                }
            }
        }
        return donatable;
    }

    @Override
    public boolean attemptDispose(Material material, Field[] fields) throws failedDisposeException{
        boolean disposable = false;
        for (Field f : fields) {
            f.getName();
            if (f.getName().equals("harmfulDyes")) {
                if(f.equals(true)) {
                    disposable = true;
                }
            }
        }
        return disposable;
    }

    //Material superclass methods

    @Override
    public String getSpecial() {
        return("May have harmful dyes, which is more harmful for the environment");
    }
}