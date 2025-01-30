package src;

import src.garbage_types.plastic;

public class RecycleDriver {
    public static void main(String[] args) {
        plastic p = new plastic(7);
        System.out.println(p.getPlasticNumber());
    }
}