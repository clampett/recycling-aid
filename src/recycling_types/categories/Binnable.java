package src.recycling_types.categories;

import src.customExceptions.failedBinException;
import src.recycling_types.*;

/**
 * 
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/26/2025
 */
public interface Binnable{
    //@Override
    //public boolean attemptRecycle(Material m);

     public abstract boolean attemptBin(Material m) throws failedBinException;
}
