package src.recycling_types.categories;

import java.lang.reflect.Field;

import src.customExceptions.failedCenterException;
import src.recycling_types.*;


/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/26/2025
 */
public interface Centerable {
    
    public abstract boolean attemptCenter(Material m, Field[] fields) throws failedCenterException;

}
