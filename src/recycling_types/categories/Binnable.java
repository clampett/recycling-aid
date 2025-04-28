package src.recycling_types.categories;

import src.recycling_types.*;

/**
 * {@link Binnable} is a simple interface for {@link src.recycling_types.Material Materials}
 * that are able to be put in a recycling bin.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/28/2025
 */
public interface Binnable {
    /**
     * Attempts to place supplied {@link src.recycling_types.Material Material} in a recycling bin.
     * 
     * @param m {@link src.recycling_types.Material Material} to attempt to bin
     * @return T/F - whether the supplied material was recycled correctly
     * @throws failedTrashException
     */
    public abstract boolean attemptBin(Material m) throws failedTrashException; 
}