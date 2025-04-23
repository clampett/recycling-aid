package src.recycling_types.categories;

import src.recycling_types.*;

import java.lang.reflect.Field;

import src.customExceptions.failedDisposeException;
/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/26/2025
 */
public interface Disposable {
    
    public abstract boolean attemptDispose(Material m, Field[] fields) throws failedDisposeException;
}
