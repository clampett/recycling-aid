package src.gui;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.geometry.*;
import javafx.stage.*;

public class Gui extends Application {
    private final double app_height = 750;
    private final double app_width = 500;
    private final String app_CSS = "-fx-background-color: #00b400;";

    private final double button_height = 50;
    private final double button_width = 75;
    private final double back_button_height = 30;
    private final double back_button_width = 50;
    private final String button_CSS = "-fx-background-color: #FFFFFF;" 
                                            + "-fx-font-size: 15;"
                                            + "-fx-border-color: #000000;"
                                            + "-fx-border-width: 3;";

    private final String titleFont = "Comic Sans MS";
    private final String bodyFont = "Comic Sans MS";

    private Scene titleScene, gameScene, infoSceneI, creditScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) {
        VBox root = setUpTitleScene(mainStage);
        setUpGameScene(mainStage);
        setUpInfoSceneI(mainStage);
        setUpCreditScene(mainStage);

        titleScene = new Scene(root);
        mainStage.setScene(titleScene);
        mainStage.setTitle("Recycling Aid");
        mainStage.getIcons().add(new Image("src/data/images/recycling_arrow.png"));
        mainStage.show();
    }

    private VBox setUpTitleScene(Stage mainStage) {
        //Text
        Text titleText = new Text(150, 100, "Recycling Aid");

        //Text Style
        titleText.setFont(Font.font("Impact", 60));
        titleText.setFill(Color.WHITE);
        titleText.setStroke(Color.BLACK);
        titleText.setStrokeWidth(2);


        //Image & ImageView
        Image titleRecyclingArrowImage = new Image("src/data/images/recycling_arrow.png");
        ImageView titleImageView = new ImageView(titleRecyclingArrowImage);

        //Image Sizes
        titleImageView.setFitWidth(200);
        titleImageView.setFitHeight(150);
        titleImageView.setPreserveRatio(true);


        //Buttons
        Button gameButton = new Button("Game");
        Button infoButton = new Button("Info");
        Button creditButton = new Button("Credits");
        Button[] buttonArr = {gameButton, infoButton, creditButton};

        //Button Actions
        gameButton.setOnAction(e -> mainStage.setScene(gameScene));
        infoButton.setOnAction(e -> {
            mainStage.setScene(infoSceneI);
        });
        creditButton.setOnAction(e -> mainStage.setScene(creditScene));
        
        //Button Size & Style
        for(Button b : buttonArr) {
            b.setPrefSize(button_width, button_height);
            b.setStyle(button_CSS);
        }


        //VBox
        VBox titleBox = new VBox(20);

        //VBox Setting
        titleBox.setPrefSize(app_height, app_width);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle(app_CSS);

        //Add nodes to VBox
        titleBox.getChildren().addAll(
            titleText,
            titleImageView,
            gameButton,
            infoButton,
            creditButton
        );   

        return titleBox;
    }

    private void setUpGameScene(Stage mainStage) {
        System.out.println("Game scene created");
    }

    private void setUpInfoSceneI(Stage mainStage) {
        //Text
        Text infoTitle = new Text("How to Recycle?");

        //Text Style
        infoTitle.setFont(Font.font(titleFont, 45));


        //Images & ImageView


        //Layout Managers
        //HBox
        HBox infoTitleBox = new HBox();

        //HBox Style
        infoTitleBox.setAlignment(Pos.CENTER);

        //Add Nodes to HBox
        infoTitleBox.getChildren().addAll(
            infoTitle
        );


        //GridPane
        GridPane infoPaneI = new GridPane();

        //GridPane Style

        //Add Nodes to GridPane


        //Pane
        Pane mainPane = new Pane();

        //Pane Style
        mainPane.setStyle(app_CSS);
        mainPane.setPrefSize(app_height, app_width);

        //Add Nodes to Pane
        mainPane.getChildren().addAll(
            infoTitleBox,
            infoPaneI
        );

        infoSceneI = new Scene(mainPane);
    }

    private void setUpCreditScene(Stage mainStage) {
        //Text
        Text creditTitle = new Text("CREDITS:");
        Text creditList = new Text("•Andrew Casey\n\n•Saadat Emilbekova\n\n•Dylan Jablonski\n\n•Jason Mele\n\n•Will Zakroff");
        Text creditInfo = new Text("Rowan University\t\tHonors OOPDA\t\tSpring 2025");

        //Text Style
        creditTitle.setFont(Font.font(titleFont, 45));
        creditList.setFont(Font.font(bodyFont, 25));
        creditInfo.setFont(Font.font(bodyFont, 15));
        creditTitle.setFill(Color.WHITE);
        creditTitle.setStroke(Color.BLACK);
        creditTitle.setStrokeWidth(2);


        //Buttons
        Button creditBackButton = new Button("↩");

        //Button Actions
        creditBackButton.setOnAction(e -> mainStage.setScene(titleScene));

        //Button Style
        creditBackButton.setPrefSize(back_button_width, back_button_height);
        creditBackButton.setStyle(button_CSS);


        //AnchorPane
        AnchorPane creditPane = new AnchorPane();

        //AnchorPane Setting
        creditPane.setStyle(app_CSS);
        creditPane.setPrefSize(app_height, app_width);

        //Add Nodes to Pane
        //Button
        AnchorPane.setTopAnchor(creditBackButton, 10.0);
        AnchorPane.setLeftAnchor(creditBackButton, 10.0);
        
        //Title
        HBox titleBox = new HBox(creditTitle);
        titleBox.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(titleBox, 10.0);
        AnchorPane.setLeftAnchor(titleBox, app_width/2);

        //Credit List
        VBox listBox = new VBox(creditList);
        listBox.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(listBox, app_height/7);
        AnchorPane.setLeftAnchor(listBox, app_width/2);

        //Credit Info
        AnchorPane.setRightAnchor(creditInfo, 10.0);
        AnchorPane.setBottomAnchor(creditInfo, 10.0);

        //Add All Nodes to AnchorPane
        creditPane.getChildren().addAll(
            creditBackButton,
            titleBox,
            listBox,
            creditInfo
        );
        
        creditScene = new Scene(creditPane);
    }
}