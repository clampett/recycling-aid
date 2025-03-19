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

import src.Loader;

/**
 * {@link Gui_Info} is apart of the Recycling Aid's {@link Gui}. It creates and 
 * handles all of the scenes that display recycling categories and materials.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/19/2025
 */
public class Gui_Info {
    private static final double IMAGE_HEIGHT = 200;
    private static final double IMAGE_WIDTH = 150;

    private static Scene materialInfoScene = null;

    /**
     * Setup 
     * 
     * @param mainStage the main stage from Gui
     * @return 
     */
    protected static Scene setUpInfoScene(Stage mainStage) {
        Gui.L.info("Setting up categories info scene");

        //Text
        Text title = new Text("Recycling Categories");
        title.setFont(new Font(Gui.TITLE_FONT, 35));

        //Button
        Button back = new Button(Gui.BACK_BUTTON_TEXT);
        Button next = new Button("Materials ->");

        back.setPrefSize(Gui.BACK_BUTTON_WIDTH, Gui.BACK_BUTTON_HEIGHT);
        back.setStyle(Gui.BUTTON_CSS);
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

        HBox[] rows = createCategoryRows();

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

    private static Scene setUpInfoSceneII(Stage mainStage) {
        Gui.L.info("Setting up material info scene");

        //Text
        Text title = new Text("Recycling Materials");
        title.setFont(new Font(Gui.TITLE_FONT, 35));

        //Buttons
        Button back = new Button(Gui.BACK_BUTTON_TEXT);

        back.setPrefSize(Gui.BACK_BUTTON_WIDTH, Gui.BACK_BUTTON_HEIGHT);
        back.setStyle(Gui.BUTTON_CSS);

        back.setOnAction(e -> mainStage.setScene(Gui.infoScene));

        TableView<String[]> materials = createMaterialTable();

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

    private static HBox[] createCategoryRows() {
        String[][] defaultCategories = Loader.load_csv("src/data/text/categories.csv", Gui.L);
        HBox[] rows = new HBox[defaultCategories.length];

        for(int i = 0; i < rows.length; i++) {
            ImageView im = new ImageView(new Image(defaultCategories[i][0]));
            Text name = new Text(defaultCategories[i][1]);
            Text info = new Text(defaultCategories[i][2]);
            rows[i] = new HBox(30);

            im.setFitHeight(IMAGE_HEIGHT);
            im.setFitWidth(IMAGE_WIDTH);
            im.setPreserveRatio(true);

            name.setFont(new Font(Gui.BODY_FONT, 30));

            info.setFont(new Font(Gui.BODY_FONT, 25));
            
            rows[i].getChildren().addAll(im, name, info);
        }

        return rows;
    }

    private static TableView<String[]> createMaterialTable() {
        TableView<String[]> materialTable = new TableView<>();
        ObservableList<String[]> data = FXCollections.observableArrayList();
        String[][] defaultMaterials = Loader.load_csv("src/data/text/materials.csv", Gui.L);

        for(int i = 0; i < defaultMaterials[0].length; i++) {
            final int columnIndex = i;

            TableColumn<String[], String> column = new TableColumn<>(defaultMaterials[0][i]);

            column.setCellValueFactory(cellData -> 
                javafx.beans.binding.Bindings.createStringBinding(
                    () -> cellData.getValue()[columnIndex]));

            materialTable.getColumns().add(column);
        }

        for(int j = 1; j < defaultMaterials.length; j++) {
            data.add(defaultMaterials[j]);
        }

        materialTable.setItems(data);

        return materialTable;
    }
    

}
