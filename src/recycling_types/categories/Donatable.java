package src.recycling_types.categories;

import src.recycling_types.*;

/**
 * {@link Donatable} is a simple interface for {@link src.recycling_types.Material Materials}
 * that are able to be donated.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/28/2025
 */
public interface Donatable {
    /**
     * Attempt to donate supplied {@link src.recycling_types.Material Material}.
     * 
     * @param m {@link src.recycling_types.Material Material} to attempt to donate
     * @return T/F - whether the supplied material was recycled correctly
     * @throws failedTrashException
     */    
    public abstract boolean attemptDonate(Material material) throws failedTrashException;
}