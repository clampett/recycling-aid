package src.recycling_types.materials;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

import src.customExceptions.failedBinException;
import src.customExceptions.failedCompostException;
import src.customExceptions.failedDisposeException;
import src.recycling_types.Material;
import src.recycling_types.categories.*;

/**
 * {@link Paper} is a concrete representation of garbage made out of paper and
 * is a subclass of {@link src.recycling_types.Material Material}.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Paper extends Material implements Binnable, Compostable, Disposable {
    /**Whether the paper has ink*/
    private boolean hasInk;

    /**
     * Some possible items that paper could be.
     * @see src.recycling_types.Material Material
     */
    private static Set<String> possibleItems = new HashSet<>(
        Arrays.asList("paper", "newspaper", "homework", "junk mail", "paper towel", "paper plate")
    );

    public Paper() {
        this(true);
    }

    public Paper(boolean hasInk) {
        super(0.1, possibleItems);
        this.hasInk = hasInk;

        if(this.hasInk)
            super.setImpactScore(0.25);
    }

    //Mutator methods

    /**
     * Sets whether {@link Paper} has ink and updates impact score
     * @param hasInk T/F - does {@link Paper} have ink?
     */
    public void setHasInk(boolean hasInk) {
        this.hasInk = hasInk;
        if(this.hasInk)
            super.setImpactScore(0.25);
        else
            super.setImpactScore(0.1);
    }

    //Accessor Methods

    /**
     * Returns whether the {@link Paper} has ink or not, as a String.
     * @return {@link Paper} ink status
     */
    public String hasInk() {
        if(this.hasInk)
            return "This paper has ink";
        else
            return "This paper does not have ink";
    }

    //Interface methods

    @Override
    public boolean attemptBin(Material material, Field[] fields) throws failedBinException{
        boolean binnable = false;
        for (Field f : fields) {
            System.out.println(f.getName());
            if (f.getName().equals("hasInk")) {
                if(f.toString().contains("false")) {
                    binnable = true;
                }
            }
        }
        return binnable;
    }

    @Override
    public boolean attemptCompost(Material material, Field[] fields) throws failedCompostException{
        boolean compostable = false;
        for (Field f : fields) {
            System.out.println(f.getName());
            if (f.getName().equals("hasInk")) {
                if(f.toString().contains("false") ) {
                    compostable = true;
                }
            }
        }
        return compostable;
    }

    @Override
    public boolean attemptDispose(Material material, Field[] fields) throws failedDisposeException{
        boolean disposable = false;
        for (Field f : fields) {
            System.out.println(f.getName());
            if (f.getName().equals("hasInk")) {
                if(f.toString().contains("true")) {
                    disposable = true;
                }
            }
        }
        return disposable;
    }

    //Material superclass methods

    @Override
    public String getSpecial() {
        return("May have ink, which is more harmful for the environment");
    }
}