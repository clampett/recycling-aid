package src.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Gui_Game {
    protected static Scene setUpGameScene(Stage mainStage) {
        Gui.L.info("Setting up game scene");

        // Create text for gameScene
        Text titleText = new Text("Recycling game!");
        titleText.setFont(new Font("Impact", 30));
        titleText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
        titleText.setTranslateX(300);
        titleText.setTranslateY(50);

        // Create the game scene
        VBox v = new VBox();

        // Set the size of the Vbox
        v.setPrefSize(800,500);
        v.setStyle(Gui.APP_CSS);
        v.autosize();
        v.getChildren().add(titleText);

        // Create buttons and add them to the Vbox
        Button startButton = new Button("Start Game"); // Starts the game
        Button exitButton = new Button("Exit Game"); // Closes the program
        
        Button donateableButton = new Button("Donateable"); // Donateable button
        Button disposeableButton = new Button("Disposeable"); // Disposeable button
        Button compostableButton = new Button("Compostable"); // Compostable button
        Button centerableButton = new Button("Centerable"); // Centerable button
        Button binnableButton = new Button("Binnable"); // Binnable button

        // Set button Icons and their sizes
        ImageView donateableIcon = new ImageView("data/images/donatable.png");
        donateableIcon.setFitWidth(64);
        donateableIcon.setFitHeight(64);
        donateableButton.setGraphic(donateableIcon);
        
        ImageView disposeableIcon = new ImageView("data/images/disposable.png");
        disposeableIcon.setFitWidth(64);
        disposeableIcon.setFitHeight(64);
        disposeableButton.setGraphic(disposeableIcon);
        
        ImageView compostableIcon = new ImageView("data/images/compostable.png");
        compostableIcon.setFitWidth(64);
        compostableIcon.setFitHeight(64);
        compostableButton.setGraphic(compostableIcon);
        
        ImageView centerableIcon = new ImageView("data/images/centerable.png");
        centerableIcon.setFitWidth(64);
        centerableIcon.setFitHeight(64);
        centerableButton.setGraphic(centerableIcon);
        
        ImageView binnableIcon = new ImageView("data/images/binnable.png");
        binnableIcon.setFitWidth(64);
        binnableIcon.setFitHeight(64);
        binnableButton.setGraphic(binnableIcon);

        // Set button locations
        startButton.setTranslateX(0);
        startButton.setTranslateY(20);
        exitButton.setTranslateX(730);
        exitButton.setTranslateY(0);

        // Add buttons to the Vbox
        v.getChildren().addAll(startButton, exitButton, donateableButton, disposeableButton, compostableButton, centerableButton, binnableButton);

        Scene gameScene = new Scene(v);
        return gameScene;
    }    
}
