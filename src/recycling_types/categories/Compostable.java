package src.recycling_types.categories;

import src.recycling_types.*;
import src.customExceptions.failedCompostException;

/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/26/2025
 */
public interface Compostable {
    
    public boolean attemptCompost(Material m) throws failedCompostException;

}
