package src.garbage_types;
import java.util.Map.Entry;

public interface Plastics extends Garbage{
    public void setPlasticType(int plastic_num);
    public Entry<Integer, String> getPlasticType();
}
