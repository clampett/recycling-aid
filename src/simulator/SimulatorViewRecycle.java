package src.simulator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * {@link SimulatorViewRecycle} is the {@code awt} display for {@link src.gui.Gui_Simulator Gui_Simulator}.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/28/2025
 * @see src.gui.Gui_Simulator Gui_Simulator
 * @see FieldView
 */
public class SimulatorViewRecycle extends JFrame {
    private SimulationResult result;
    private String selectedEnvironment;
    private int selectedDays;
    private int selectedRecycleCount;
    private double fervor;
    private static final Color RECYCLED_COLOR = Color.green;
    private static final Color TRASH_COLOR = Color.orange;
    private static final Color EMPTY_COLOR = Color.white;
    

    private FieldView fieldView;
    private Timer timer;
    private JLabel stepLabel;

    public SimulatorViewRecycle(SimulationResult result, String selectedEnvironment, int selectedDays, int selectedRecycleCount, double fervor) {
        this.result = result;
        this.selectedEnvironment = selectedEnvironment;
        this.selectedDays = selectedDays;
        this.selectedRecycleCount = selectedRecycleCount;
        this.fervor = fervor;
        

        setTitle("Recycle Simulator");
        setSize(600, 600);
        
        setLocation(100, 50);

        fieldView = new FieldView(50, 50); 
        stepLabel = new JLabel("Step 0 of " + (selectedDays * 10) + " (Day 0 of " + selectedDays + ")");
        stepLabel.setHorizontalAlignment(JLabel.CENTER);
        Container contents = getContentPane();
        contents.add(fieldView, BorderLayout.CENTER);
        contents.add(stepLabel, BorderLayout.SOUTH);
        setVisible(true);

        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                fieldView.updateField();
                if (fieldView.stepsCompleted >= selectedDays * 10) {
                    timer.stop(); // Stop the timer once all steps are completed
                    System.out.println("Simulation Complete!");
                } 
            }
        });
        timer.start();
    }

    /**
     * Inner class for creating a 2D Field
     */
    private class FieldView extends JPanel {
        int recycledCount = result.getRecycledCount();
        int trashedCount = result.getTrashedCount();
        int totalMateerials = result.getTotalCount();
        private final int GRID_SIZE = 10;
        private int cols, rows;
        private int[][] field;
        private int stepsCompleted = 0;
        

        public FieldView(int cols, int rows) {
            this.cols = cols;
            this.rows = rows;
            field = new int[rows][cols];

            int totalSpots = rows * cols;
            int neededSpots = recycledCount + trashedCount;
            if (neededSpots > totalSpots) {
                throw new IllegalArgumentException("Grid is too small for the number of recycled and trashed items.");
            }
          
            List<Point> allPositions = new ArrayList<>();
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    allPositions.add(new Point(row, col));
                }
            }
            Collections.shuffle(allPositions);
            

            for (int i = 0; i < recycledCount; i++) {
                Point p = allPositions.get(i);
                field[p.x][p.y] = 2;
            }
            for (int i = recycledCount; i < recycledCount + trashedCount; i++) {
                Point p = allPositions.get(i);
                field[p.x][p.y] = 1;
            }
        }

        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (field[row][col] == 1) {
                        g.setColor(TRASH_COLOR);
                    } else if (field[row][col] == 2) {
                        g.setColor(RECYCLED_COLOR);
                    } else {
                        g.setColor(EMPTY_COLOR);
                    }
                    g.fillRect(col * GRID_SIZE, row * GRID_SIZE, GRID_SIZE - 1, GRID_SIZE - 1);
                }
            }
        }

        public void updateField() {
            int totalSteps = selectedDays*10;
           
            
                int row = (int)(Math.random() * rows);
                int col = (int)(Math.random() * cols);
                
                if(stepsCompleted>= totalSteps)
                {
                    return;
                }

                if (selectedEnvironment.equals("Industrial")) {
                    
                    field[row][col] = 2; 
                } else if (selectedEnvironment.equals("Neighborhood")) {
                    
                    field[row][col] = 2; 
                } else if (selectedEnvironment.equals("School")) {
                   
                    if (Math.random() > fervor) {
                        field[row][col] = 2; 
                    }
                }
                stepsCompleted++;
                int currentDay = stepsCompleted / 10 + 1;
                if (currentDay > selectedDays) currentDay = selectedDays;

                stepLabel.setText("Step " + stepsCompleted + " of " + (selectedDays * 10) + " (Day " + currentDay + " of " + selectedDays + ")");

                
                repaint();
                
            }
        }
    }

