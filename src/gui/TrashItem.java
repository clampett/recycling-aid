package src.gui;
import java.awt.Color;

class TrashItem {
    private boolean recycled;

    public TrashItem(boolean recycled) {
        this.recycled = recycled;
    }

    public Color getColor() {
        return recycled ? Color.GREEN : Color.ORANGE;
    }

    // Optional: behavior like moving, disappearing, etc.
}