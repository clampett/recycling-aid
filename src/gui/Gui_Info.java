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
import javafx.scene.media.*;
import java.util.List;
import java.util.ArrayList;

import src.Loader;
import src.recycling_types.Material;

/**
 * {@link Gui_Info} is apart of the Recycling Aid's {@link Gui}. It creates and 
 * handles all of the scenes that display recycling categories and materials.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/19/2025
 */
public class Gui_Info {
    //Scenes
    private static Scene materialInfoScene, binnableScene, centerableScene, compostableScene, disposableScene, donatableScene;

    /**
     * Sets up the first recycling categories info scene. Reycling categories 
     * are based on interfaces found at {@link src.recycling_types.categories}. 
     * Data is loaded by createCategoryRows() with data from categories.csv.
     * 
     * @param mainStage the main stage from {@link Gui}
     * @return Info {@code Scene} for recycling categories
     */
    protected static Scene setUpInfoScene(Stage mainStage) {
        Gui.L.info("Setting up categories info scene");

        //Text
        Text title = new Text("Recycling Categories");
        title.setFont(new Font(Gui.TITLE_FONT, 35));

        //Button
        Button back = Gui.createBackButton();
        Button next = new Button("Materials ->");

        next.setPrefSize(Gui.BUTTON_WIDTH * 2, Gui.BUTTON_HEIGHT);
        next.setStyle(Gui.BUTTON_CSS);

        back.setOnAction(e -> mainStage.setScene(Gui.titleScene));
        next.setOnAction(e -> {
            if(materialInfoScene == null)
                materialInfoScene = setUpInfoSceneII(mainStage);
                
            mainStage.setScene(materialInfoScene);
        });

        //Layouts
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        HBox titleBox = new HBox(back, leftSpacer, title, rightSpacer);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        HBox[] rows = createCategoryRows(mainStage);

        VBox mainBox = new VBox(10);
        
        mainBox.getChildren().add(titleBox);

        for(HBox row : rows) {
            mainBox.getChildren().add(row);
        }

        Region bottomSpacer = new Region();
        HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

        HBox nextBox = new HBox(bottomSpacer, next);

        mainBox.getChildren().add(nextBox);

        mainBox.setStyle(Gui.APP_CSS);
        mainBox.setPrefSize(Gui.APP_HEIGHT, Gui.APP_WIDTH);

        Scene infoScene = new Scene(mainBox);
        return infoScene;
    }

    /**
     * Sets up the recycling material info scene. Recycling materials are based on
     * classes found at {@link src.recycling_types.materials} and are all subclasses of
     * {@link src.recycling_types.Material Material}. Data is gotten from each class and
     * put in a {@code TableView}, which is created by createMaterialRow().
     * 
     * @param mainStage the main stage from {@link Gui}
     * @return Info {@code Scene} for recycling materials
     */
    private static Scene setUpInfoSceneII(Stage mainStage) {
        Gui.L.info("Setting up material info scene");

        //Text
        Text title = new Text("Recycling Materials");
        title.setFont(new Font(Gui.TITLE_FONT, 35));

        //Buttons
        Button back = Gui.createBackButton();

        back.setOnAction(e -> mainStage.setScene(Gui.infoScene));

        TableView<String[]> materials = createCSVTable(createMaterialData());

        //Layouts
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        HBox titleBox = new HBox(back, leftSpacer, title, rightSpacer);
        titleBox.setAlignment(Pos.CENTER_LEFT);

        VBox mainBox = new VBox(titleBox, materials);

        mainBox.setPrefSize(Gui.APP_HEIGHT, Gui.APP_WIDTH);
        mainBox.setStyle(Gui.APP_CSS);

        Scene infoSceneII = new Scene(mainBox);
        return infoSceneII;
    }

    /**
     * Gets data from categories.csv and puts it in a {@code HBox} to display. 
     * Data includes a picture, a name and info.
     * 
     * @param mainStage the main stage from {@link Gui}
     * @return {@code HBox} array of recycling category data
     */
    private static HBox[] createCategoryRows(Stage mainStage) {
        String[][] defaultCategories = Loader.load_csv("src/data/text/categories.csv", Gui.L);
        HBox[] rows = new HBox[defaultCategories.length];
        Button[] seeMoreButtons = new Button[defaultCategories.length];

        for(int i = 0; i < rows.length; i++) {
            ImageView im = new ImageView(new Image(defaultCategories[i][0]));
            Text name = new Text(defaultCategories[i][1]);
            Text info = new Text(defaultCategories[i][2]);
            Button more = new Button("See More");
            rows[i] = new HBox(30);

            seeMoreButtons[i] = more;

            im.setFitHeight(200);
            im.setFitWidth(150);
            im.setPreserveRatio(true);

            name.setFont(new Font(Gui.BODY_FONT, 30));

            info.setFont(new Font(Gui.BODY_FONT, 25));

            more.setPrefSize(Gui.BIGGER_BUTTON_WIDTH, Gui.BUTTON_HEIGHT);
            more.setStyle(Gui.BUTTON_CSS);
            
            rows[i].getChildren().addAll(im, name, info, more);
        }

        setSeeMoreButtons(mainStage, seeMoreButtons);

        return rows;
    }
    
    /**
     * Sets the actions for each of the "See More" buttons. Not every dynamic, but
     * this is the easiest way to do.
     * 
     * @param mainStage the main stage from {@link Gui}
     * @param seeMoreButtons array of buttons that need to be set
     */
    private static void setSeeMoreButtons(Stage mainStage, Button[] seeMoreButtons) {
        //Binnable
        seeMoreButtons[0].setOnAction(e -> {
            if(binnableScene == null)
                createBinScene(mainStage);
            
            mainStage.setScene(binnableScene);
        });

        //Centerable
        seeMoreButtons[1].setOnAction(e -> {
            if(centerableScene == null)
                createCentScene(mainStage);
            
            mainStage.setScene(centerableScene);
        });

        //Compostable
        seeMoreButtons[2].setOnAction(e -> {
            if(compostableScene == null)
                createComScene(mainStage);
            
            mainStage.setScene(compostableScene);
        });

        //Disposable
        seeMoreButtons[3].setOnAction(e -> {
            if(disposableScene == null)
                createDisScene(mainStage);
            
            mainStage.setScene(disposableScene);
        });

        //Donatable
        seeMoreButtons[4].setOnAction(e -> {
            if(donatableScene == null)
                createDonScene(mainStage);
            
            mainStage.setScene(donatableScene);
        });
    }

    /**
     * Creates a table using data from a 2D String array, usually loaded CSV data.
     * 
     * @param csvData 2D String array containing data
     * @return {@code TableView<String[]>} of recycling material data
     */
    private static TableView<String[]> createCSVTable(String[][] csvData) {
        TableView<String[]> table = new TableView<>();
        ObservableList<String[]> data = FXCollections.observableArrayList();

        for(int i = 0; i < csvData[0].length; i++) {
            final int columnIndex = i;

            TableColumn<String[], String> column = new TableColumn<>(csvData[0][i]);

            column.setCellValueFactory(cellData -> 
                javafx.beans.binding.Bindings.createStringBinding(
                    () -> cellData.getValue()[columnIndex]));

                    table.getColumns().add(column);
        }

        for(int j = 1; j < csvData.length; j++) {
            data.add(csvData[j]);
        }

        table.setItems(data);

        return table;
    }

    /**
     * Creates every possible recycling material and returns a 2D array containing all of its information.
     * This information is sent and used to display using a {@code TableView} in 
     * method createMaterialTable(). This is then displayed in materialInfoScene variable.
     * 
     * @return 2D array containing all of relevant information about recycling materials.
     */
    private static String[][] createMaterialData() {
        List<String[]> data = new ArrayList<>();
        List<Material> materials = new ArrayList<>();
        List<Class<? extends Material>> b = Material.ALL_MATERIALS;

        for(Class<? extends Material> c : b) {
            try {
                materials.add((Material) c.getDeclaredConstructors()[0].newInstance());
            } catch(Exception e) {
                Gui.L.severe("Could Create Material - " + e.toString());
            }
        }

        data.add(Material.DISPLAY_HEADERS);;

        for(Material material : materials) {
            String[] row = 
                {   material.getName(), 
                    material.getCategories().toString().replace("[", "").replace("]", ""),
                    String.valueOf(material.getImpactScore()), 
                    material.getSpecial(),
                    material.getPossibleItems().toString().replace("[", "").replace("]", "")
                };

            data.add(row);
        }
        return data.toArray(new String[0][]);
    }

    /**
     * Creates the {@link src.recycling_types.categories.Binnable Binnable} "See More"
     * {@code Scene}.
     * 
     * @param mainStage the main stage from {@link Gui}
     */
    private static void createBinScene(Stage mainStage) {
        //Video
        Media videoMedia = new Media(Loader.getURI("src/data/videos/binnable.mp4", Gui.L));
        MediaPlayer mediaPlayer = new MediaPlayer(videoMedia);
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaView.setFitHeight(450);
        mediaView.setFitWidth(500);

        //Buttons
        Button back = Gui.createBackButton();
        Button play = new Button("Play");
        Button pause = new Button("Pause");

        back.setOnAction(e -> {
            mediaPlayer.pause();
            mainStage.setScene(Gui.infoScene);
        });
        play.setOnAction(e -> mediaPlayer.play());
        pause.setOnAction(e -> mediaPlayer.pause());

        play.setPrefSize(Gui.BUTTON_WIDTH, Gui.BUTTON_HEIGHT);
        play.setStyle(Gui.BUTTON_CSS);
        pause.setPrefSize(Gui.BUTTON_WIDTH, Gui.BUTTON_HEIGHT);
        pause.setStyle(Gui.BUTTON_CSS);

        //Layout
        Region leftSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);

        HBox top = new HBox(back, leftSpacer);

        HBox player = new HBox(mediaView);

        HBox controls = new HBox(play, pause);

        VBox main = new VBox(20, top, player, controls);
        main.setPrefSize(Gui.APP_WIDTH, Gui.APP_HEIGHT);
        main.setStyle(Gui.APP_CSS);

        binnableScene = new Scene(main);
    }
    
    /**
     * Creates the {@link src.recycling_types.categories.Centerable Centerable} "See More"
     * {@code Scene}.
     * 
     * @param mainStage the main stage from {@link Gui}
     */
    private static void createCentScene(Stage mainStage) {
        //TableView
        String[][] centerData = Loader.load_csv("src/data/text/recyclingCenters.csv", Gui.L);
        
        for(int i = 0; i < centerData.length; i++) {
            for(int j = 0; j < centerData[0].length; j++) {
                centerData[i][j] = centerData[i][j].replaceAll("\\*", ",");
            }
        }

        TableView<String[]> centerableTable = createCSVTable(centerData);

        centerableTable.setPrefSize(Gui.APP_WIDTH, Gui.APP_HEIGHT/2);
        
        //Buttons
        Button back = Gui.createBackButton();
        back.setOnAction(e -> mainStage.setScene(Gui.infoScene));

        //Layouts
        HBox title = new HBox(back);

        HBox table = new HBox(centerableTable);

        VBox main = new VBox(title, table);
        main.setPrefSize(Gui.APP_WIDTH, Gui.APP_HEIGHT);
        main.setStyle(Gui.APP_CSS);

        centerableScene = new Scene(main);
    }

    /**
     * Creates the {@link src.recycling_types.categories.Compostable Compostable} "See More"
     * {@code Scene}.
     * 
     * @param mainStage the main stage from {@link Gui}
     */
    private static void createComScene(Stage mainStage) {
        //Buttons
        Button back = Gui.createBackButton();

        back.setOnAction(e -> mainStage.setScene(Gui.infoScene));
    }

    /**
     * Creates the {@link src.recycling_types.categories.Disposable Disposable} "See More"
     * {@code Scene}.
     * 
     * @param mainStage the main stage from {@link Gui}
     */
    private static void createDisScene(Stage mainStage) {
        //Buttons
        Button back = Gui.createBackButton();

        back.setOnAction(e -> mainStage.setScene(Gui.infoScene));
    }

    /**
     * Creates the {@link src.recycling_types.categories.Donatable Donatable} "See More"
     * {@code Scene}.
     * 
     * @param mainStage the main stage from {@link Gui}
     */
    private static void createDonScene(Stage mainStage) {
        //Buttons
        Button back = Gui.createBackButton();

        back.setOnAction(e -> mainStage.setScene(Gui.infoScene));
    }
}