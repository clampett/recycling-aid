package src.gui;

import java.util.*;
import java.util.function.Function;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import src.recycling_types.*;
import src.recycling_types.materials.*;
import src.recycling_types.categories.*;
import src.simulator.*;

/**
 * {@link Gui_Simulator} is apart of Recycling Aid's {@link Gui}.
 * It allows the user to run a simulation to see the effects of different levels
 * of enviromentalism in a population.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/28/2025
 */
public class Gui_Simulator {
    /**
     * Each scenario creates a {@code List} of {@link src.recycling_types.Material Material}
     * objects based on a number of scenarios, such as:
     * <ul>
     *      <li><b><i>Neighborhood</i></b> - equal mix of all garbage.</li>
     *      <li><b><i>Industrial</i></b> - more metals and plastics.</li>
     *      <li><b><i>School</i></b> - more paper, wood, and electronics.</li>
     * </ul>
     */
    private static HashMap<RecyclerType, Function<Integer, List<Material>>> scenarios;
    static {
        scenarios = new HashMap<>(3);

        scenarios.put(RecyclerType.NEIGHBORHOOD, garbageSize -> { //Neighborhood - equal mix of all garbage
            List<Material> g = new ArrayList<>(garbageSize);
            Random rand = new Random();

            for(int i = 0; i < garbageSize; i++) {
                switch(rand.nextInt(9) + 1) {
                    case 1:
                        g.add(new Cardboard());
                        break;
                    case 2:
                        g.add(new Electronic());
                        break;
                    case 3:
                        g.add(new Fabric());
                        break;
                    case 4:
                        g.add(new Food_Waste());
                        break;
                    case 5:
                        g.add(new Glass());
                        break;
                    case 6:
                        g.add(new Metal());
                        break;
                    case 7:
                        g.add(new Paper());
                        break;
                    case 8:
                        g.add(new Plastic());
                        break;
                    case 9:
                        g.add(new Wood());
                        break;
                    default:
                        break;
                }
            }
            
            return g;
        });

        scenarios.put(RecyclerType.INDUSTRIAL, garbageSize -> { //Industrial - more metals and plastics
            List<Material> g = new ArrayList<>(garbageSize);
            Random rand = new Random();

            for(int i = 0; i < garbageSize; i++) {
                int r = rand.nextInt(27) + 1;

                if(r >= 10) g.add(new Metal());
                else if(r >= 20) g.add(new Plastic());
                else {
                    switch(r) {
                        case 21:
                            g.add(new Cardboard());
                            break;
                        case 22:
                            g.add(new Electronic());
                            break;
                        case 23:
                            g.add(new Fabric());
                            break;
                        case 24:
                            g.add(new Food_Waste());
                            break;
                        case 25:
                            g.add(new Glass());
                            break;
                        case 26:
                            g.add(new Paper());
                            break;
                        case 27:
                            g.add(new Wood());
                            break;
                        default:
                            break;
                    }
                }
            }
            
            return g;
        });

        scenarios.put(RecyclerType.SCHOOL, garbageSize -> { //School - more paper, wood, and electronics
            List<Material> g = new ArrayList<>(garbageSize);
            Random rand = new Random();

            for(int i = 0; i < garbageSize; i++) {
                int r = rand.nextInt(36) + 1;

                if(r >= 10) g.add(new Paper());
                else if(r >= 20) g.add(new Wood());
                else if(r >= 30) g.add(new Electronic());
                else {
                    switch(r) {
                        case 31:
                            g.add(new Cardboard());
                            break;
                        case 32:
                            g.add(new Fabric());
                            break;
                        case 33:
                            g.add(new Food_Waste());
                            break;
                        case 34:
                            g.add(new Glass());
                            break;
                        case 35:
                            g.add(new Metal());
                            break;
                        case 36:
                            g.add(new Plastic());
                            break;
                        default:
                            break;
                    }
                }
            }
            
            return g;
        });
    }

    /**
     * Sets up the simulator {@code Scene}. The user selects a scenario, the number
     * of days to simulate, the number of people to simulate and fervor (how likely
     * the people are to recycle).
     * 
     * @param mainStage the main stage from {@link Gui}
     * @return Simulator {@code Scene}
     */
    protected static Scene setUpSimulatorScene(Stage mainStage) {
        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        box.setStyle(Gui.APP_CSS);
        box.setPrefSize(Gui.APP_HEIGHT, Gui.APP_WIDTH);

        Text title = new Text("Recycling Simulation");
        title.setFont(Font.font(Gui.TITLE_FONT, 45));
        title.setFill(Color.WHITE);

        ComboBox<String> recyclerTypeBox = new ComboBox<>();
        recyclerTypeBox.getItems().addAll("Neighborhood", "Industrial", "School");
        recyclerTypeBox.setValue("Neighborhood");

        TextField daysField = new TextField();
        daysField.setPromptText("Days to Simulate");
        daysField.setMaxWidth(Gui.APP_WIDTH);

        TextField recyclerCountField = new TextField();
        recyclerCountField.setPromptText("# of Recyclers");
        recyclerCountField.setMaxWidth(Gui.APP_WIDTH);

        Slider fervorSlider = new Slider(0, 1, 0.7);
        fervorSlider.setShowTickLabels(true);
        fervorSlider.setShowTickMarks(true);
        fervorSlider.setMajorTickUnit(0.25);
        fervorSlider.setMaxWidth(Gui.APP_WIDTH * (3/2));

        Button startButton = new Button("Start Simulation");
        startButton.setStyle(Gui.BUTTON_CSS);

        TextArea resultsArea = new TextArea();
        resultsArea.setEditable(false);
        resultsArea.setPrefHeight(300);
        resultsArea.setVisible(false);

        Button backButton = Gui.createBackButton();
        backButton.setOnAction(e -> mainStage.setScene(Gui.titleScene));

        startButton.setOnAction(e -> {
            try {
                String recyclerChoice = recyclerTypeBox.getValue();
                int days = Integer.parseInt(daysField.getText());
                int recyclerCount = Integer.parseInt(recyclerCountField.getText());
                double fervor = fervorSlider.getValue();

                SimulationResult result = runSimulation(RecyclerType.valueOf(recyclerChoice.toUpperCase()), days, recyclerCount, fervor);

                resultsArea.setText(result.toString());
                resultsArea.setVisible(true);

                SimulatorViewRecycle simulatorView = new SimulatorViewRecycle(result, recyclerChoice, days, recyclerCount, fervor);
                simulatorView.setVisible(true);
            } catch (Exception ex) {
                resultsArea.setText("Invalid input. Please enter valid numbers for days and recyclers.");
                resultsArea.setVisible(true);
            }
        });

        HBox topBar = new HBox(backButton);
        topBar.setAlignment(Pos.TOP_LEFT);

        VBox inputFields = new VBox(10, recyclerTypeBox, daysField, recyclerCountField, fervorSlider, startButton);
        inputFields.setAlignment(Pos.CENTER);

        box.getChildren().addAll(topBar, title, inputFields, resultsArea);

        Scene simulatorScene = new Scene(box);
        return simulatorScene;
    }

    /**
     * Runs the simulation the number of days supplied. Everyday, a {@code List} of 
     * {@link src.recycling_types.Material Materials} is randomly generated using
     * {@link Gui_Simulator#scenarios scenarios}.
     * 
     * @param type {@code enum} 
     * @param days number of days to simulate
     * @param recyclerCount number of recyclers to simulate
     * @param fervor how likely the recyclers are to recycle
     * @return {@link SimulationResult} completed simulation to display
     */
    private static SimulationResult runSimulation(RecyclerType type, int days, int recyclerCount, double fervor) {
        Random rand = new Random();
        int totalMaterials = 0;
        int successfullyRecycled = 0;

        for (int day = 0; day < days; day++) {
            for (int r = 0; r < recyclerCount; r++) {
                List<Material> dailyMaterials = scenarios.get(type).apply(rand.nextInt(5) + 5);
                for (Material material : dailyMaterials) {
                    totalMaterials++;
                    if (rand.nextDouble() <= fervor) {
                        if (attemptRecycle(material)) {
                            successfullyRecycled++;
                        }
                    }
                }
            }
        }

        int trashed = totalMaterials - successfullyRecycled;
        return new SimulationResult(totalMaterials, successfullyRecycled, trashed);
    }

    /**
     * Attempts to recycle a supplied {@link src.recycling_types.Material Material}
     * using it's attempt method.
     * 
     * @param m {@link src.recycling_types.Material Material} to attempt to recycle
     * @return Whether the supplied {@link src.recycling_types.Material Material} was recycled properly
     */
    private static boolean attemptRecycle(Material m) {
        try {
            if (m instanceof Compostable compostable) return compostable.attemptCompost(m);
            if (m instanceof Binnable binnable) return binnable.attemptBin(m);
            if (m instanceof Centerable centerable) return centerable.attemptCenter(m);
            if (m instanceof Donatable donatable) return donatable.attemptDonate(m);
            if (m instanceof Disposable disposable) return disposable.attemptDispose(m);
        } catch (failedTrashException e) {
            Gui.L.warning("Failed to recycle: " + m + " - " + e.getMessage());
            return false;
        }
        return false;
    }
}