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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.*;

/**
 * {@link Gui} is the main and central gui for the Recycling Aid project.
 * <p>
 * Gui sets up and starts the JavaFX Application, loads every scene and provides constants to maintain a single look.
 * Simple {@code Scenes}, such as {@link #setUpTitleScene(Stage) title} and {@link #setUpCreditScene(Stage) credit},
 * are included within the main gui class. {@code Scenes} that require different methods, such as:
 * <ul>
 *      <li>{@link Gui_Info}
 *      <li>{@link Gui_Game}
 *      <li>{@link Gui_Calculator}
 * </ul>
 * are each in their own seperate classes.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Gui extends Application {
    //Gui constants
    protected static final double APP_HEIGHT = 1000;
    protected static final double APP_WIDTH = 500;
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
    protected static Scene titleScene, gameScene, infoScene, calculatorScene, simulatorScene, lookupScene;
    private Scene creditScene;

    /**Logger used throughout the application.*/
    public static Logger L;

    /**
     * Same as start(), but with standard Java main().
     * 
     * @param args CLI arguments
     * @param log JUL {@code Logger}
     */
    public static void main(String[] args, final Logger log) {
        L = log;
        L.setLevel(Level.ALL);
        launch(args);
    }

    /**
     * Start up method for the application. Creates all of the main {@code Scenes}
     * and loads the title {@code Scene}. Also sets the application title and icon.
     * 
     * @param mainStage main {@code Stage} supplied at runtime
     */
    @Override
    public void start(Stage mainStage) {
        L.info("Setting up GUI");

        threadedStartup(mainStage);

        mainStage.setScene(titleScene);
        mainStage.setTitle("Recycling Aid");
        mainStage.getIcons().add(new Image("data/images/recycling_arrow.png"));
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
        Image titleRecyclingArrowImage = new Image("data/images/recycling_arrow.png");
        ImageView titleImageView = new ImageView(titleRecyclingArrowImage);

        //Image Sizes
        titleImageView.setFitWidth(200);
        titleImageView.setFitHeight(150);
        titleImageView.setPreserveRatio(true);


        //Buttons
        Button lookupButton = new Button("Look Up");
        Button simulatorButton = new Button("Simulator");
        Button calculatorButton = new Button("Calculate");
        Button gameButton = new Button("Game");
        Button infoButton = new Button("Info");
        Button creditButton = new Button("Credits");
        Button[] buttonArr = {lookupButton, simulatorButton, calculatorButton, gameButton, infoButton, creditButton};

        //Button Actions
        lookupButton.setOnAction(e -> mainStage.setScene(lookupScene));
        simulatorButton.setOnAction(e -> mainStage.setScene(simulatorScene));
        calculatorButton.setOnAction(e -> mainStage.setScene(calculatorScene));
        gameButton.setOnAction(e -> mainStage.setScene(gameScene));
        infoButton.setOnAction(e -> mainStage.setScene(infoScene));
        creditButton.setOnAction(e -> mainStage.setScene(creditScene));
        
        //Button Size & Style
        for(Button b : buttonArr) {
            b.setPrefSize(BUTTON_WIDTH * 1.5, BUTTON_HEIGHT);
            b.setStyle(BUTTON_CSS);
        }

        //VBox
        VBox titleBox = new VBox(20);

        //VBox Setting
        titleBox.setPrefSize(APP_HEIGHT, APP_WIDTH);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle(APP_CSS);
        titleBox.autosize();

        HBox row2 = new HBox(10);
        HBox row3 = new HBox(10);

        row2.setAlignment(Pos.CENTER);
        row2.setStyle(APP_CSS);

        row2.getChildren().addAll(gameButton, infoButton);

        row3.setAlignment(Pos.CENTER);
        row3.setStyle(APP_CSS);

        row3.getChildren().addAll(calculatorButton, simulatorButton);

        //Add nodes to VBox
        titleBox.getChildren().addAll(
            titleText,
            titleImageView,
            lookupButton,
            row2,
            row3,
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

        Text andrew = new Text("\t• Andrew Casey\t\n");
        Text saadat = new Text("\t• Saadat Emilbekova\t\n");
        Text dylan = new Text("\t• Dylan Jablonski\t\n");
        Text jason = new Text("\t• Jason Mele\t\n");
        Text will = new Text("\t• Will Zakroff\t");

        Text creditInfoI = new Text("\tRowan University");
        Text creditInfoII = new Text("Honors OOPDA");
        Text creditInfoIII = new Text("Spring 2025\t");

        //Text Style
        creditTitle.setFont(Font.font(TITLE_FONT, 45));
        creditTitle.setFill(Color.WHITE);
        creditTitle.setStroke(Color.BLACK);
        creditTitle.setStrokeWidth(2);

        andrew.setFont(Font.font("Cambria", 25));
        saadat.setFont(Font.font("Jokerman", 25));
        dylan.setFont(Font.font(BODY_FONT, 25));
        jason.setFont(Font.font("Elephant", 25));
        will.setFont(Font.font(BODY_FONT, 25));

        andrew.setFill(Color.BLACK);
        saadat.setFill(Color.PURPLE);
        dylan.setFill(Color.BLACK);
        jason.setFill(Color.rgb(75, 97, 209));
        will.setFill(Color.BLACK);

        creditInfoI.setFont(Font.font(BODY_FONT, 15));
        creditInfoII.setFont(Font.font(BODY_FONT, 15));
        creditInfoIII.setFont(Font.font(BODY_FONT, 15));


        //Seperator
        Separator topBar = new Separator(Orientation.HORIZONTAL);
        topBar.setStyle("-fx-background-color: #FFFFFF;");
        topBar.autosize();

        Separator bottomBar = new Separator(Orientation.HORIZONTAL);
        bottomBar.setStyle("-fx-background-color: #FFFFFF;");
        bottomBar.autosize();


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

        Region leftSpacerII = new Region();
        Region rightSpacerII = new Region();
        HBox.setHgrow(leftSpacerII, Priority.ALWAYS);
        HBox.setHgrow(rightSpacerII, Priority.ALWAYS);

        Region leftSpacerIII = new Region();
        Region rightSpacerIII = new Region();
        HBox.setHgrow(leftSpacerIII, Priority.ALWAYS);
        HBox.setHgrow(rightSpacerIII, Priority.ALWAYS);

        HBox creditTopBox = new HBox(creditBackButton, leftSpacer, creditTitle, rightSpacer);
        VBox listBox = new VBox(andrew, saadat, dylan, jason, will);
        HBox creditBottomBox = new HBox(creditInfoI, leftSpacerIII, creditInfoII, rightSpacerIII, creditInfoIII);

        listBox.setStyle("-fx-background-color: #FFFFFFFF;");

        HBox middle = new HBox(leftSpacerII, listBox, rightSpacerII);
        VBox bottom = new VBox(bottomBar, creditBottomBox);
        
        mainBox.getChildren().addAll(
            creditTopBox,
            topBar,
            new Text("\n"),
            middle,
            new Text("\n"),
            bottom
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

    /**
     * A threaded startup for the base {@code Scenes}.
     * 
     * @param mainStage main {@code Stage} supplied at runtime
     */
    private void threadedStartup(Stage mainStage) {
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(9);

        Runnable title = () -> { 
            titleScene = this.setUpTitleScene(mainStage);
            latch.countDown();
        };
        Runnable credit = () -> {
            creditScene = this.setUpCreditScene(mainStage);
            latch.countDown();
        };
        Runnable calculator = () -> {
            calculatorScene = Gui_Calculator.setUpCalculatorScene(mainStage);
            latch.countDown();
        };
        Runnable game = () -> {
            gameScene = Gui_Game.setUpGameScene(mainStage);
            latch.countDown();
        };
        Runnable info = () -> {
            infoScene = Gui_Info.setUpInfoScene(mainStage);
            latch.countDown();
        };
        Runnable sim = () -> {
            simulatorScene = Gui_Simulator.setUpSimulatorScene(mainStage);
            latch.countDown();
        };
        Runnable lookup = () -> {
            lookupScene = Gui_Lookup.setUpLookupScene(mainStage);
            latch.countDown();
        };

        Runnable calculatorII = () -> {
            Gui_Calculator.setUpAddScene(mainStage);
            latch.countDown();
        };
        Runnable infoII = () -> {
            Gui_Info.materialInfoScene = Gui_Info.setUpInfoSceneII(mainStage);
            latch.countDown();
        };


        try {
            service.execute(title);
            service.execute(credit);
            service.execute(calculator);
            service.execute(game);
            service.execute(info);
            service.execute(sim);
            service.execute(lookup);

            service.execute(calculatorII);
            service.execute(infoII);

            latch.await();

            L.fine("Loaded all Scenes");
        } catch(InterruptedException e) {
            L.severe("Could NOT load Scenes - " + e.getMessage());
        }
    }
}