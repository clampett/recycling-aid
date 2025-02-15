package src;

import src.garbage_types.info.GarbageInfo;

public class RecycleDriver {
    public static void main(String[] args) {
        GarbageInfo a = new GarbageInfo("biodegradables");
        a.printGarbageName();
        System.out.println(a.getRecyclingInfo()[2]);
        System.out.println(a.getGarbageImagePath());
    }
}