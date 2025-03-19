package src.recycling_types.materials;

import src.recycling_types.Material;
import src.recycling_types.categories.*;

public class Plastic extends Material implements Binnable, Centerable, Disposable{
    public enum plasticType {
        PET, HDPE, PVC, LDPE, PP, PS, OTHER
    };
    
}
