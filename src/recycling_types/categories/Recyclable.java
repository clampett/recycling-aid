package src.recycling_types.categories;

import src.recycling_types.*;

public interface Recyclable {
    public boolean attemptRecycle(Material m) throws failedTrashException;
}
