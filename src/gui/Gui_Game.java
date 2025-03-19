package src.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;

public class Gui_Game {
    protected static Scene setUpGameScene(Stage mainStage) {
        Gui.L.info("Setting up game scene");

        VBox v = new VBox();

        Scene gameScene = new Scene(v);
        return gameScene;
    }    
}
