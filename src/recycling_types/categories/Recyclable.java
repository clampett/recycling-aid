package src.recycling_types.categories;

import java.lang.reflect.Field;

import src.customExceptions.failedRecycleException;
import src.recycling_types.Material;

public interface Recyclable {
    public boolean attemptRecycle(Material m, Field[] fields) throws failedRecycleException;
}
