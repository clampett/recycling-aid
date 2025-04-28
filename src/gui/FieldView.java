package src.gui;

class FieldView extends JPanel {
    private TrashItem[][] field; // 2D array of your "items"

    public FieldView(int width, int height) {
        field = new TrashItem[height][width];
        // Optional: fill field with initial trash/recycled randomly
    }

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

    // Add more methods: update field, move items, etc.
}
