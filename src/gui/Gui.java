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

/**
 * {@link src.gui.Gui Gui} is the main and central gui for the Recycling Aid project.
 * <p>
 * Gui sets up and starts the JavaFX Application, loads every scene and provides constants to maintain a single look.
 * Simple {@code Scenes}, such as {@link src.gui.Gui#setUpTitleScene(Stage) title} and {@link src.gui.Gui#setUpCreditScene(Stage) credit},
 * are included within the main gui class. {@code Scenes} that require different methods, such as:
 * <ul>
 *      <li>{@link src.gui.Gui_Info Gui_Info}
 *      <li>{@link src.gui.Gui_Game Gui_Game}
 *      <li>{@link src.gui.Gui_Calculator Gui_Calculator}
 * </ul>
 * are each in their own seperate classes.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/4/2025
 */
public class Gui extends Application {
    //Gui constants
    protected static final double APP_HEIGHT = 500;
    protected static final double APP_WIDTH = 400;
    protected static final String APP_CSS = "-fx-background-color: #00b400;";

    protected static final double BUTTON_HEIGHT = 35;
    protected static final double BUTTON_WIDTH = 75;
    protected static final double BIGGER_BUTTON_WIDTH = BUTTON_WIDTH * 2;
    protected static final double BACK_BUTTON_HEIGHT = 20;
    protected static final double BACK_BUTTON_WIDTH = 50;
    protected static final String BACK_BUTTON_TEXT = "↩";
    protected static final String BUTTON_CSS = "-fx-background-color: #FFFFFF;" 
                                            + "-fx-font-size: 15;"
                                            + "-fx-border-color: #000000;"
                                            + "-fx-border-width: 3;";

    protected static final String TITLE_FONT = "Impact";
    protected static final String BODY_FONT = "Comic Sans MS";

    //Scenes
    protected static Scene titleScene, gameScene, infoScene, calculatorScene;
    private Scene creditScene;

    /**Logger used throughout the application.*/
    public static final Logger L = Logger.getLogger(Gui.class.getName());

    /**
     * Same as start(), but with standard Java main().
     * 
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        L.setLevel(Level.ALL);
        launch(args);
    }

    /**
     * Start up method for the application. Creates all of the main {@code Scenes}
     * and loads the title {@code Scene}. Also sets the application title and icon.
     * 
     * @param mainStage main stage supplied at runtime
     */
    @Override
    public void start(Stage mainStage) {
        L.info("Setting up GUI");

        titleScene = setUpTitleScene(mainStage);
        creditScene = setUpCreditScene(mainStage);
        calculatorScene = Gui_Calculator.setUpCalculatorScene(mainStage);
        gameScene = Gui_Game.setUpGameScene(mainStage);
        infoScene = Gui_Info.setUpInfoScene(mainStage);

        mainStage.setScene(titleScene);
        mainStage.setTitle("Recycling Aid");
        mainStage.getIcons().add(new Image("src/data/images/recycling_arrow.png"));
        mainStage.show();
    }

    /**
     * Sets up the Gui's title scene. Includes a title, an image, and buttons to
     * move between the different scenes in the Gui.
     * 
     * @param mainStage the main stage from start()
     * @return Setup title {@code Scene}
     */
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
        calculatorButton.setOnAction(e -> mainStage.setScene(calculatorScene));
        gameButton.setOnAction(e -> mainStage.setScene(gameScene));
        infoButton.setOnAction(e -> mainStage.setScene(infoScene));
        creditButton.setOnAction(e -> mainStage.setScene(creditScene));
        
        //Button Size & Style
        for(Button b : buttonArr) {
            b.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
            b.setStyle(BUTTON_CSS);
        }

        calculatorButton.setPrefWidth(BIGGER_BUTTON_WIDTH * 1.5);

        //VBox
        VBox titleBox = new VBox(20);

        //VBox Setting
        titleBox.setPrefSize(APP_HEIGHT, APP_WIDTH);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle(APP_CSS);
        titleBox.autosize();

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

    /**
     * Sets up the Gui's credit scene. Includes credit for the authors of this
     * application. Since it is completely self-contained (only 1 method to set up),
     * it is included in the main {@link Gui} class.
     * 
     * @param mainStage the main stage from start()
     * @return Setup credit {@code Scene}
     */
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
        Button creditBackButton = createBackButton();

        //Button Actions
        creditBackButton.setOnAction(e -> mainStage.setScene(titleScene));

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
        mainBox.autosize();

        Scene creditScene = new Scene(mainBox);
        return creditScene;
    }

    /**
     * Creates a back button to be used throughout the Gui. Action still needs
     * to be set.
     * 
     * @return A Back {@code Button}
     */
    protected static Button createBackButton() {
        Button back = new Button(Gui.BACK_BUTTON_TEXT);

        back.setPrefSize(Gui.BACK_BUTTON_WIDTH, Gui.BACK_BUTTON_HEIGHT);
        back.setStyle(Gui.BUTTON_CSS);

        return back;
    }
}