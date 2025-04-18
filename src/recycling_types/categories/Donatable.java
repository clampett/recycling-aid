package src.recycling_types.categories;

import src.customExceptions.failedDonateException;
import src.recycling_types.*;

/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/26/2025
 */
public interface Donatable {
    
    public abstract boolean attemptDonate(Material m) throws failedDonateException;

    
}
