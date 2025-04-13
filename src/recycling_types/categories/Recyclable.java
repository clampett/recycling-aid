package src.recycling_types.categories;

import src.customExceptions.failedRecycleException;
import src.recycling_types.Material;

public interface Recyclable {
    public abstract boolean attemptRecycle(Material m) throws failedRecycleException;
}
