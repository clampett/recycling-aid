package src.simulator;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

/**
 * {@link FieldView} is a representation of the {@link src.gui.Gui_Simulator Gui_Simulator}.
 * It displays many {@link TrashItem} to show when an item is recycled properly.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/28/2025
 * @see TrashItem
 * @see src.gui.Gui_Simulator Gui_Simulator
 */
class FieldView extends JPanel {
    private TrashItem[][] field; // 2D array of your "items"

    public FieldView(int width, int height) {
        field = new TrashItem[height][width];
    }

    /**
     * Sets the field with {@link TrashItem TrashItems}.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellWidth = getWidth() / field[0].length;
        int cellHeight = getHeight() / field.length;

        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                TrashItem item = field[row][col];
                if (item != null) {
                    g.setColor(item.getColor());
                } else {
                    g.setColor(Color.WHITE); // Empty space
                }
                g.fillRect(col * cellWidth, row * cellHeight, cellWidth, cellHeight);
            }
        }
    }
}