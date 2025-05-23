package src.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.stage.*;

import java.util.List;
import java.util.ArrayList;

import src.Loader;
import src.recycling_types.Material;

/**
 * {@link Gui_Info} is apart of the Recycling Aid's {@link Gui}. It creates and 
 * handles all of the {@code Scenes} that display recycling categories and materials.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Gui_Info {
    //Scenes
    protected static Scene materialInfoScene;

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
        next.setOnAction(e -> mainStage.setScene(materialInfoScene));

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
            mainBox.getChildren().addAll(row, new Separator(Orientation.HORIZONTAL));
        }

        Region bottomSpacer = new Region();
        HBox.setHgrow(bottomSpacer, Priority.ALWAYS);

        HBox nextBox = new HBox(bottomSpacer, next);

        mainBox.getChildren().add(nextBox);

        mainBox.setStyle(Gui.APP_CSS);
        mainBox.setPrefSize(Gui.APP_HEIGHT, Gui.APP_WIDTH);
        mainBox.autosize();

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
    protected static Scene setUpInfoSceneII(Stage mainStage) {
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
        mainBox.autosize();

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
        String[][] defaultCategories = Loader.load_csv("data/text/categories.csv", Gui.L);
        HBox[] rows = new HBox[defaultCategories.length];

        for(int i = 0; i < rows.length; i++) {
            ImageView im = new ImageView(new Image(defaultCategories[i][0]));
            Text name = new Text(defaultCategories[i][1]);
            Text info = new Text(defaultCategories[i][2].replaceAll("\\*", ","));
            rows[i] = new HBox(30);

            im.setFitHeight(75);
            im.setFitWidth(100);
            im.setPreserveRatio(true);

            name.setFont(new Font(Gui.BODY_FONT, 30));

            info.setFont(new Font(Gui.BODY_FONT, 15));
            info.setWrappingWidth(600);

            rows[i].getChildren().addAll(im, name, info);
        }

        return rows;
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
        List<Material> materials = Loader.deserialize_dir("data/serialized", Gui.L)
                                         .stream()
                                         .map(mat -> (Material) mat)
                                         .toList();

        data.add(Material.DISPLAY_HEADERS);

        for(Material material : materials) {
            String[] row = 
                {   material.getName(), 
                    material.displayCategories(),
                    String.valueOf(material.getImpactScore()), 
                    material.getSpecial(),
                    material.displayPossibleItems()
                };

            data.add(row);
        }
        return data.toArray(new String[0][]);
    }
}