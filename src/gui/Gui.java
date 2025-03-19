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

import java.util.logging.*;

public class Gui extends Application {
    protected static final double APP_HEIGHT = 1200;
    protected static final double APP_WIDTH = 1000;
    protected static final String APP_CSS = "-fx-background-color: #00b400;";

    protected static final double BUTTON_HEIGHT = 35;
    protected static final double BUTTON_WIDTH = 75;
    protected static final double BACK_BUTTON_HEIGHT = 20;
    protected static final double BACK_BUTTON_WIDTH = 50;
    protected static final String BACK_BUTTON_TEXT = "↩";
    protected static final String BUTTON_CSS = "-fx-background-color: #FFFFFF;" 
                                            + "-fx-font-size: 15;"
                                            + "-fx-border-color: #000000;"
                                            + "-fx-border-width: 3;";

    protected static final String TITLE_FONT = "Impact";
    protected static final String BODY_FONT = "Comic Sans MS";

    protected static Scene titleScene, gameScene, infoScene, creditScene;

    public static final Logger L = Logger.getLogger(Gui.class.getName());

    public static void main(String[] args) {
        L.setLevel(Level.ALL);
        launch(args);
    }

    @Override
    public void start(Stage mainStage) {
        L.info("Setting up GUI");

        titleScene = setUpTitleScene(mainStage);
        creditScene = setUpCreditScene(mainStage);
        gameScene = Gui_Game.setUpGameScene(mainStage);
        infoScene = Gui_Info.setUpInfoScene(mainStage);

        mainStage.setScene(titleScene);
        mainStage.setTitle("Recycling Aid");
        mainStage.getIcons().add(new Image("src/data/images/recycling_arrow.png"));
        mainStage.show();
    }

    private Scene setUpTitleScene(Stage mainStage) {
        L.info("Setting up title scene");
        //Text
        Text titleText = new Text(150, 100, "Recycling Aid");

        //Text Style
        titleText.setFont(Font.font(TITLE_FONT, 60));
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
        Button calculatorButton = new Button("Calculate Your Impact");
        Button gameButton = new Button("Game");
        Button infoButton = new Button("Info");
        Button creditButton = new Button("Credits");
        Button[] buttonArr = {calculatorButton, gameButton, infoButton, creditButton};

        //Button Actions
        gameButton.setOnAction(e -> mainStage.setScene(gameScene));
        infoButton.setOnAction(e -> mainStage.setScene(infoScene));
        creditButton.setOnAction(e -> mainStage.setScene(creditScene));
        
        //Button Size & Style
        for(Button b : buttonArr) {
            b.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            b.setStyle(BUTTON_CSS);
        }

        calculatorButton.setPrefWidth(BUTTON_WIDTH * 3);

        //VBox
        VBox titleBox = new VBox(20);

        //VBox Setting
        titleBox.setPrefSize(APP_HEIGHT, APP_WIDTH);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle(APP_CSS);

        HBox topButtons = new HBox(10);

        topButtons.setAlignment(Pos.CENTER);
        topButtons.setStyle(APP_CSS);

        topButtons.getChildren().addAll(gameButton, infoButton);

        //Add nodes to VBox
        titleBox.getChildren().addAll(
            titleText,
            titleImageView,
            calculatorButton,
            topButtons,
            creditButton
        );   

        Scene titleScene = new Scene(titleBox);

        return titleScene;
    }

    private Scene setUpCreditScene(Stage mainStage) {
        L.info("Setting up credit scene");

        //Text
        Text creditTitle = new Text("CREDITS:");
        Text creditList = new Text("•Andrew Casey\n\n•Saadat Emilbekova\n\n•Dylan Jablonski\n\n•Jason Mele\n\n•Will Zakroff");
        Text creditInfo = new Text("Rowan University\t\tHonors OOPDA\t\tSpring 2025");

        //Text Style
        creditTitle.setFont(Font.font(TITLE_FONT, 45));
        creditList.setFont(Font.font(BODY_FONT, 25));
        creditInfo.setFont(Font.font(BODY_FONT, 15));
        creditTitle.setFill(Color.WHITE);
        creditTitle.setStroke(Color.BLACK);
        creditTitle.setStrokeWidth(2);


        //Buttons
        Button creditBackButton = new Button(BACK_BUTTON_TEXT);

        //Button Actions
        creditBackButton.setOnAction(e -> mainStage.setScene(titleScene));

        //Button Style
        creditBackButton.setPrefSize(BACK_BUTTON_WIDTH, BACK_BUTTON_HEIGHT);
        creditBackButton.setStyle(BUTTON_CSS);

        creditBackButton.setLayoutX(50);
        creditBackButton.setLayoutY(50);

        //Layouts
        VBox mainBox = new VBox();

        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        HBox creditTopBox = new HBox(creditBackButton, leftSpacer, creditTitle, rightSpacer);
        HBox creditListBox = new HBox(creditList);
        HBox creditBottomBox = new HBox(creditInfo);
        
        mainBox.getChildren().addAll(
            creditTopBox,
            creditListBox,
            creditBottomBox
        );

        mainBox.setPrefSize(APP_HEIGHT, APP_WIDTH);
        mainBox.setStyle(APP_CSS);

        Scene creditScene = new Scene(mainBox);
        return creditScene;
    }
}