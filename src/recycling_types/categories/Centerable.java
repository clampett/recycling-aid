package src.recycling_types.categories;

import src.recycling_types.*;

/**
 * {@link Centerable} is a simple interface for {@link src.recycling_types.Material Materials}
 * that are able to be recycled in a recycling center.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/28/2025
 */
public interface Centerable {
    /**
     * Attempts to place suppled {@link src.recycling_types.Material Material} in a recycling center.
     * 
     * @param m {@link src.recycling_types.Material Material} to attempt give to center
     * @return T/F - whether the supplied material was recycled correctly
     * @throws failedTrashException
     */
    public abstract boolean attemptCenter(Material m) throws failedTrashException;
}