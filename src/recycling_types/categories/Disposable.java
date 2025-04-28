package src.recycling_types.categories;

import src.recycling_types.*;

/**
 * {@link Disposable} is a simple interface for {@link src.recycling_types.Material Materials}
 * that are able to be put in a trash can. 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/28/2025
 */
public interface Disposable {
    /**
     * Attempt to place a supplied {@link src.recycling_types.Material Material} in a trash can.
     * 
     * @param m {@link src.recycling_types.Material Material} to attempt to dipose
     * @return T/F - whether the supplied material was recycled correctly
     * @throws failedTrashException
     */    
    public abstract boolean attemptDispose(Material m) throws failedTrashException;
}
