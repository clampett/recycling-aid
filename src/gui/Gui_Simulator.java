package src.gui;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

import src.recycling_types.Material;
import src.recycling_types.materials.*;
import src.recycling_types.categories.*;
import src.SimulationResult;

public class Gui_Simulator {

    private enum RecyclerType {
        NEIGHBORHOOD, INDUSTRIAL, SCHOOL
    }

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

        TextField recyclerCountField = new TextField();
        recyclerCountField.setPromptText("# of Recyclers");

        Slider fervorSlider = new Slider(0, 1, 0.7);
        fervorSlider.setShowTickLabels(true);
        fervorSlider.setShowTickMarks(true);
        fervorSlider.setMajorTickUnit(0.25);

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

    private static SimulationResult runSimulation(RecyclerType type, int days, int recyclerCount, double fervor) {
        Random rand = new Random();
        int totalMaterials = 0;
        int successfullyRecycled = 0;

        for (int day = 0; day < days; day++) {
            for (int r = 0; r < recyclerCount; r++) {
                List<Material> dailyMaterials = generateMaterials(type, rand.nextInt(5) + 5);
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

    private static List<Material> generateMaterials(RecyclerType type, int count) {
        List<Material> materials = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            switch (type) {
                case NEIGHBORHOOD -> materials.add(randomMaterial(rand, "Paper", "Plastic", "Food_Waste", "Glass"));
                case INDUSTRIAL -> materials.add(randomMaterial(rand, "Metal", "Plastic", "Electronic", "Glass"));
                case SCHOOL -> materials.add(randomMaterial(rand, "Paper", "Plastic", "Food_Waste", "Fabric"));
            }
        }
        return materials;
    }

    private static Material randomMaterial(Random rand, String... types) {
        String pick = types[rand.nextInt(types.length)];
        return switch (pick) {
            case "Paper" -> new Paper();
            case "Plastic" -> new Plastic();
            case "Food_Waste" -> new Food_Waste();
            case "Glass" -> new Glass();
            case "Metal" -> new Metal();
            case "Electronic" -> new Electronic();
            case "Fabric" -> new Fabric();
            default -> new Paper();
        };
    }

    private static boolean attemptRecycle(Material m) {
        try {
            if (m instanceof Compostable compostable) return compostable.attemptCompost(m);
            if (m instanceof Binnable binnable) return binnable.attemptBin(m);
            if (m instanceof Centerable centerable) return centerable.attemptCenter(m);
            if (m instanceof Donatable donatable) return donatable.attemptDonate(m);
            if (m instanceof Disposable disposable) return disposable.attemptDispose(m);
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    //jkjkjjk
    //that's me

}
