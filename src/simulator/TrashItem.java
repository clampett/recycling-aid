package src.simulator;

import java.awt.Color;

/**
 * {@link TrashItem} is a simple representation of a piece of trash, used in 
 * {@link Field_View}. It has either been recycled correctly (Green) or incorrecly (Orange).
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/28/2025
 * @see src.gui.Gui_Simulator Gui_Simulator
 */
class TrashItem {
    /**Whether the {@link TrashItem} has been recycled correctly.*/
    private boolean recycled;

    public TrashItem(boolean recycled) {
        this.recycled = recycled;
    }

    /**
     * Get current color.
     * @return Either Color.GREEN or Color.ORANGE
     */
    public Color getColor() {
        return recycled ? Color.GREEN : Color.ORANGE;
    }
}