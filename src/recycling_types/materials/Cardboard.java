package src.recycling_types.materials;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.customExceptions.failedBinException;
import src.customExceptions.failedDisposeException;
import src.customExceptions.failedRecycleException;
import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Paper} is a concrete representation of garbage made out of cardboard and
 * is a subclass of {@link src.recycling_types.Material Material}.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Cardboard extends Material implements Binnable, Disposable, Recyclable {
    /**Whether the carboard is soiled*/
    private boolean soiled;

    /**
     * Some possible items that cardboard could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("cardboard", "box", "packing material")
    );

    public Cardboard() {
        this(true);
    }
    
    public Cardboard(boolean soiled) {
        super(0.2, possibleItems);
        this.soiled = soiled;

        if(soiled)
            super.setImpactScore(0.3);
    }

    //Mutator methods
    
    /**
     * Sets whether {@link Cardboard} is soiled and updates impact score.
     * @param soiled T/F - is {@link Cardboard} soiled?
     */
    public void setSoiled(boolean soiled) {
        this.soiled = soiled;
        if(soiled)
            super.setImpactScore(0.3);
        else
            super.setImpactScore(0.2);
    }

    //Accessor methods

    /**
     * Gets whether {@link Cardboard} is soiled.
     * @return {@link Cardboard} soiled
     */
    public boolean getIsSoiled() {
        return soiled;
    }

    //Interface methods

    @Override
    public boolean attemptBin(Material material, Field[] fields) throws failedBinException {
        boolean binable = true;
        for (Field f : fields) {
            f.getName();
            if (f.getName().equals("soiled")) {
                if(f.equals(true)) {
                    binable = false;
                }
            }
        }
        
        return binable;
    }

    @Override
    public boolean attemptDispose(Material material, Field[] fields) throws failedDisposeException {
        boolean disposable = false;
        for (Field f : fields) {
            f.getName();
            if (f.getName().equals("soiled")) {
                if(f.equals(true)) {
                    disposable = true;
                }
            }
        }

        return disposable;
    }

    @Override
    public boolean attemptRecycle(Material material, Field[] fields) throws failedRecycleException {
        boolean recyclable = true;
        for (Field f : fields) {
            f.getName();
            if (f.getName().equals("soiled")) {
                if(f.equals(true)) {
                    recyclable = false;
                }
            }
        }

        return recyclable;
    }
    
    //Material superclass methods

    @Override
    public String getSpecial() {
        return("May be soiled, which is more harmful for the environment");
    }
}