package src.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import src.SimulationResult;


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

    public SimulatorViewRecycle(SimulationResult result, String selectedEnvironment, int selectedDays, int selectedRecycleCount, double fervor) {
        this.result = result;
        this.selectedEnvironment = selectedEnvironment;
        this.selectedDays = selectedDays;
        this.selectedRecycleCount = selectedRecycleCount;
        this.fervor = fervor;

        setTitle("Recycle Simulator");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 50);

        fieldView = new FieldView(50, 50); // (width, height) or (cols, rows)
        Container contents = getContentPane();
        contents.add(fieldView, BorderLayout.CENTER);
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

    // Inner class: FieldView
    private class FieldView extends JPanel {
        private final int GRID_SIZE = 10;
        private int cols, rows;
        private int[][] field;
        private int stepsCompleted = 0;

        public FieldView(int cols, int rows) {
            this.cols = cols;
            this.rows = rows;
            field = new int[rows][cols];

          
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    double rand = Math.random();
                    if (rand < fervor) {
                        field[row][col] = 2; // Recycled or green
                    } else if (rand < fervor + 0.1) {
                        field[row][col] = 1; // Trash or orange
                    } else {
                        field[row][col] = 0; // Empty or white
                    }
                }
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
                repaint();
                
            }
        }
    }

