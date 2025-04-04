package src.gui;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.util.ArrayList;
import java.util.List;

import src.calculator.*;
import src.recycling_types.Material;

/**
 * {@link Gui_Calculator} is apart of the Recycling Aid's {@link Gui}.
 * It creates and handles all of the {@code Scenes} that allow the user to determine their impact score.
 * Gui_Calculator heavily utilizes {@link src.calculator.Impact_Calculator Impact_Calculator} to determine score.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 3/20/2025
 * @see src.calculator.Impact_Calculator Impact_Calculator
 */
public class Gui_Calculator {
    private static Scene addScene;
    private static Impact_Calculator ic = new Impact_Calculator();
    private static List<String> currentItems = new ArrayList<>(16);

    private static Text info, result;
    private static Button notFound, calc;

    /**
     * Sets up the impact calculation {@code Scene}. The user inputs an item that
     * they are throwing out:
     * <ul>
     *      <li>If that item is found, it is added to the {@code List}
     *      <li>If NOT, then the user is given the chance to add this item to the list
     *      <ul><li>Custom items are saved inbetween sessions via serialization</li></ul>
     * </ul>
     * 
     * Actual calculations and serialization/deserialization is handled by
     * {@link src.calculator.Impact_Calculator}.
     * 
     * @see src.calculator.Impact_Calculator Impact_Calculator
     * @param mainStage the main stage from {@link Gui}
     * @return Calculator {@code Scene} for calculating user impact
     */
    protected static Scene setUpCalculatorScene(Stage mainStage) {
        String infoSuccess = "Successfully added: %s";

        //Label
        Text title = new Text("Calculate Your Impact");
        Text explanation = new Text("To calculate your Impact Score™, input all items that you are throwing away this week.");
        info = new Text();
        result = new Text();

        info.setVisible(false);
        result.setVisible(false);

        title.setFont(Font.font(Gui.TITLE_FONT, 45));
        explanation.setFont(Font.font(Gui.BODY_FONT, 20));
        info.setFont(Font.font(Gui.BODY_FONT, 20));
        result.setFont(Font.font(Gui.BODY_FONT, 20));

        //Text Field
        TextField field = new TextField();

        field.setFont(Font.font(Gui.BODY_FONT, 20));
        field.setPrefWidth(20);

        //Buttons
        Button back = Gui.createBackButton();
        Button add = new Button("Add to List");
        notFound = new Button("Add to Database");
        calc = new Button("Calculate");

        add.setStyle(Gui.BUTTON_CSS);
        notFound.setStyle(Gui.BUTTON_CSS);
        calc.setStyle(Gui.BUTTON_CSS);

        notFound.setVisible(false);
        calc.setVisible(false);

        back.setOnAction(e -> {
            info.setVisible(false);
            result.setVisible(false);
            notFound.setVisible(false);
            calc.setVisible(false);
            currentItems.clear();

            mainStage.setScene(Gui.titleScene);
        });

        add.setOnAction(e -> {
            String input = field.getText().toLowerCase();

            try {
                if (ic.checkItemList(input)) {
                    notFound.setVisible(false);
                    calc.setVisible(true);
    
                    currentItems.add(input);
    
                    info.setText(String.format(infoSuccess, input));
                    info.setVisible(true);
    
                    field.clear();
                }
            } catch(ItemNotFoundException itemNot) {
                Gui.L.info(itemNot.toString());
            }
        });

        notFound.setOnAction(e -> {
            if(addScene == null)
                setUpAddScene(mainStage);
            currentItems.add(field.getText().toLowerCase());

            info.setText("");
            info.setVisible(false);
            notFound.setVisible(false);

            mainStage.setScene(addScene);
        });

        calc.setOnAction(e -> {
            double r = ic.getImpactScore(ic.getMaterials(currentItems));
            result.setText(String.format("Score: %.2f", r));
            result.setVisible(true);
        });


        //Layout
        Region leftSpacer = new Region();
        Region rightSpacer = new Region();
        HBox.setHgrow(leftSpacer, Priority.ALWAYS);
        HBox.setHgrow(rightSpacer, Priority.ALWAYS);

        HBox top = new HBox(back, leftSpacer, title, rightSpacer);

        Region expLSpacer = new Region();
        Region expRSpacer = new Region();
        HBox.setHgrow(expLSpacer, Priority.ALWAYS);
        HBox.setHgrow(expRSpacer, Priority.ALWAYS);

        HBox exp = new HBox(expLSpacer, explanation, expRSpacer);

        VBox input = new VBox(10, field, add, info, notFound, calc, result);

        VBox main = new VBox(top, exp, input);

        main.setPrefSize(Gui.APP_WIDTH, Gui.APP_HEIGHT);
        main.setStyle(Gui.APP_CSS);
        main.autosize();

        Scene calculatorScene = new Scene(main);
        return calculatorScene;
    }

    /**
     * Sets the add new item {@code Scene}. The user can visit this {@code Scene} 
     * if they attempt to add an item not found in the the {@link src.recycling_types.Material}
     * possibleItems {@code Sets}. They choose what Material the item is, and this new 
     * item is added. 
     * 
     * @param mainStage the main stage from {@link Gui}
     */
    private static void setUpAddScene(Stage mainStage) {
        //Labels
        Text title = new Text("Add To Database");

        title.setFont(Font.font(Gui.TITLE_FONT, 45));

        //Radiobuttons
        ToggleGroup matGroup = new ToggleGroup();
        RadioButton[] materialSelectBtns = new RadioButton[Material.ALL_MATERIALS.size()];

        for(int i = 0; i < materialSelectBtns.length; i++) {
            materialSelectBtns[i] = new RadioButton(Material.ALL_MATERIALS.get(i).getSimpleName().replaceAll("_", " "));
            materialSelectBtns[i].setToggleGroup(matGroup);
            materialSelectBtns[i].setFont(Font.font(Gui.BODY_FONT, 16));
        }

        //Buttons
        Button confirm = new Button("Confirm");

        confirm.setStyle(Gui.BUTTON_CSS);

        confirm.setOnAction(e -> {
            RadioButton selected = (RadioButton)matGroup.getSelectedToggle();

            if(selected != null) {
                String mat = selected.getText();
                ic.getMaterialToAdd(mat).addToPossibleItems(currentItems.get(currentItems.size() - 1));
                Gui.L.info(String.format("Added %s to list of possibleItems", mat));

                ic.reCollate();
                ic.reSerialize();
                ic.reloadMaterials();
                mainStage.setScene(Gui.calculatorScene);
            }
        });

        //Layout
        HBox top = new HBox(title);

        VBox btns = new VBox(20, materialSelectBtns);
        btns.getChildren().add(confirm);

        VBox main = new VBox(top, btns);

        main.setPrefSize(Gui.APP_WIDTH, Gui.APP_HEIGHT);
        main.setStyle(Gui.APP_CSS);
        main.autosize();

        addScene = new Scene(main);
    }

    /**
     * Shows the necessary information to add a new item to the database.
     * 
     * @see src.calculator.ItemNotFoundException ItemNotFoundException
     */
    public static void showAddNew() {
        info.setText("Sorry, but we could not find that item.\nWould you like to add that item to our database?");
        info.setVisible(true);
        notFound.setVisible(true);
    }
}