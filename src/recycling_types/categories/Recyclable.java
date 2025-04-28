package src.recycling_types.categories;

import src.recycling_types.*;

/**
 * {@link Recyclable} is a simple interface for {@link src.recycling_types.Material Materials}
 * that are able to be put in a recycled, generally.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/28/2025
 */
public interface Recyclable {
    /**
     * Attempt to recycle supplied {@link src.recycling_types.Material Material}.
     * 
     * @param m {@link src.recycling_types.Material Material} to attempt to recycled
     * @return T/F - whether the supplied material was recycled correctly
     * @throws failedTrashException
     */    
    public boolean attemptRecycle(Material m) throws failedTrashException;
}
