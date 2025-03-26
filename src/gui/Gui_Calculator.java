package src.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;

public class Gui_Calculator {
    protected static Scene setUpCalculatorScene(Stage mainStage) {
        VBox x = new VBox();
        Scene calculatorScene = new Scene(x);
        return calculatorScene;
    }
}
