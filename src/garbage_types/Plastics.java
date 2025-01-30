package src.garbage_types;
import java.util.Map;

public interface Plastics extends Garbage{
    public void setPlasticType(int plastic_num);
    public Map<Integer, String> getPlasticType();
}
