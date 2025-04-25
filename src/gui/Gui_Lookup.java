package src.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gui_Lookup {
    private static Button yesMaterialButton = new Button("Yes"); 
    private static Button noMaterialButton = new Button("No");
    private static Button cardboardButton = new Button("Cardboard"); 
    private static Button electronicButton = new Button("Electronic");
    private static Button fabricButton = new Button("Fabric");
    private static Button foodWasteButton = new Button("Food Waste");
    private static Button glassButton = new Button("Glass");
    private static Button metalButton = new Button("Metal");
    private static Button paperButton = new Button("Paper");
    private static Button plasticButton = new Button("Plastic");
    private static Button woodButton = new Button("Wood");
    private static Button exitButton = new Button("Exit Game");
    private static Text questionText = new Text("Do you know the material of your item?");
    protected static Scene setUpLookupScene(Stage mainStage) {
        Gui.L.info("Setting up game scene");

        // Create text for gameScene
        Text titleText = new Text("Recycling Lookup!");
        titleText.setFont(new Font("Impact", 30));
        titleText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
        titleText.setTranslateX(300);
        titleText.setTranslateY(50);

        questionText.setFont(new Font("Impact", 23));
        questionText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
        questionText.setTranslateX(300);
        questionText.setTranslateY(150);

        VBox v = new VBox();

        v.setPrefSize(1100,700);
        v.setStyle(Gui.APP_CSS);
        v.autosize();
        v.getChildren().add(titleText);
        v.getChildren().add(questionText);
        v.getChildren().addAll(exitButton, yesMaterialButton, noMaterialButton);

        yesMaterialButton.setTranslateX(400);
        yesMaterialButton.setTranslateY(200);
        noMaterialButton.setTranslateX(630);
        noMaterialButton.setTranslateY(200);
        exitButton.setTranslateX(1030);
        exitButton.setTranslateY(0);

        cardboardButton.setTranslateX(0);
        cardboardButton.setTranslateY(200);
        electronicButton.setTranslateX(125);
        electronicButton.setTranslateY(200);
        fabricButton.setTranslateX(251);
        fabricButton.setTranslateY(200);
        foodWasteButton.setTranslateX(376);
        foodWasteButton.setTranslateY(200);
        glassButton.setTranslateX(502);
        glassButton.setTranslateY(200);
        metalButton.setTranslateX(627);
        metalButton.setTranslateY(200);
        paperButton.setTranslateX(753);
        paperButton.setTranslateY(200);
        plasticButton.setTranslateX(878);
        plasticButton.setTranslateY(200);
        woodButton.setTranslateX(1030);
        woodButton.setTranslateY(200);

        yesMaterialButton.setOnAction(e -> {
            Gui.L.info("Answered yes to material question");
            questionText.setText("What is the material?");
            v.getChildren().removeAll(yesMaterialButton, noMaterialButton);
            v.getChildren().addAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
        });
        noMaterialButton.setOnAction(e -> {
            Gui.L.info("Answered no to material question");
            questionText.setText("What is your item?");

        });

        cardboardButton.setOnAction(e -> {
            Gui.L.info("Cardboard button pressed");
            questionText.setText("Cardboard is recyclable!");
            v.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
        });

        exitButton.setOnAction(e -> {
            Gui.L.info("Exit button pressed");
            mainStage.setScene(Gui.titleScene); // Close the application 
        });

        Scene lookupScene = new Scene(v);
        return lookupScene;
    }
}
