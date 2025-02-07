package src.gui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Gui extends Application {
    Stage gameStage, learnStage, creditStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) {
        Scene s = new Scene(null);
        mainStage.setScene(s);
        mainStage.show();
    }
    
    private void setUpPlayStage() {

    }

    private static void setStartStage(Stage mainStage, Scene scene) {
        mainStage.setWidth(500);
        mainStage.setHeight(500);
        mainStage.setTitle("Recycle Aid");
        mainStage.setScene(scene);
        mainStage.show();
    }
}