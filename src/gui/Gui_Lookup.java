package src.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import src.recycling_types.Material;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.*;
import src.calculator.Impact_Calculator;
import src.calculator.ItemNotFoundException;

/**
 * The {@code Gui_Lookup} class is responsible for setting up and managing the GUI for the recycling lookup feature.
 * It provides an interactive interface for users to determine the recyclability of various items based on their material.
 * Users can also add new items to the database if they are not found.
 * 
 * This class uses JavaFX for the GUI components and interacts with the {@code Impact_Calculator} class to check
 * item recyclability and manage the database of materials.
 * Interactive material selection and recyclability guidance
 * Ability to add new items to the database
 *
 * {@code Impact_Calculator} for item lookup and database management</li>
 * {@code Material} for material definitions</li>
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/12/2025
 */
public class Gui_Lookup {
    /**
     * The {@code Impact_Calculator} instance used for checking item recyclability and managing the database.
     */
    private static Impact_Calculator ic = new Impact_Calculator();

    // Buttons for various user actions
    private static Button addToDataButton = new Button("Add to database");
    private static Button notFound = new Button("Add to Database");
    private static Button yesMaterialButton = new Button("Yes");
    private static Button noMaterialButton = new Button("No");
    private static Button cleanButton = new Button("Clean");
    private static Button soiledButton = new Button("Soiled");
    private static Button yesBatteryButton = new Button("Yes");
    private static Button noBatteryButton = new Button("No");
    private static Button yesHarmDyeButton = new Button("Yes");
    private static Button noHarmDyeButton = new Button("No");
    private static Button yesBrokenButton = new Button("Yes");
    private static Button noBrokenButton = new Button("No");
    private static Button aluminumButton = new Button("Aluminum");
    private static Button brassButton = new Button("Brass");
    private static Button bronzeButton = new Button("Bronze");
    private static Button copperButton = new Button("Copper");
    private static Button leadButton = new Button("Lead");
    private static Button ironButton = new Button("Iron");
    private static Button nickelButton = new Button("Nickel");
    private static Button steelButton = new Button("Steel");
    private static Button tinButton = new Button("Tin");
    private static Button titaniumButton = new Button("Titanium");
    private static Button yesInkButton = new Button("Yes");
    private static Button noInkButton = new Button("No");
    private static Button p1Button = new Button("1");
    private static Button p2Button = new Button("2");
    private static Button p3Button = new Button("3");
    private static Button p4Button = new Button("4");
    private static Button p5Button = new Button("5");
    private static Button p6Button = new Button("6");
    private static Button p7Button = new Button("7");
    private static TextField field = new TextField();
    private static Button yesCoMButton = new Button("Yes");
    private static Button noCoMButton = new Button("No");
    private static Button exitButton = new Button("Exit Lookup");
    private static Button restartButton = new Button("Restart Lookup");
    private static Button newLookupButton = new Button("Lookup New Item");

    // Text elements for displaying information
    private static Text questionText = new Text("Do you know the material of your item?");
    private static VBox v = new VBox();
    private static Text titleText = new Text("Recycling Lookup!");
    private static Text instructionsText = new Text("");

    // List to store current items being processed
    private static List<String> currentItems = new ArrayList<>(16);

    // Scenes for different stages of the lookup process
    private static Scene addScene;
    private static Scene lookupScene;

    /**
     * Sets up the lookup scene for the recycling aid application.
     * 
     * @param mainStage The primary stage of the application.
     * @return The {@code Scene} object representing the lookup scene.
     */
    protected static Scene setUpLookupScene(Stage mainStage) {
        Gui.L.info("Setting up lookup scene (clean version)");
        
        // --- Create Layout Root ---
        VBox lookupLayout = new VBox(20);
        lookupLayout.setAlignment(Pos.CENTER);
        lookupLayout.setPrefSize(Gui.APP_WIDTH, Gui.APP_HEIGHT);
        lookupLayout.setStyle(Gui.APP_CSS);
        
        // --- Title Text ---
        Text titleText = new Text("Recycling Lookup!");
        titleText.setFont(new Font("Impact", 30));
        titleText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
        
        // --- Question Text ---
        Text questionText = new Text("Do you know the material of your item?");
        questionText.setFont(new Font("Impact", 23));
        questionText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
        
        // --- Instructions Text ---
        Text instructionsText = new Text("");
        instructionsText.setFont(new Font("Impact", 20));
        instructionsText.setStyle("-fx-fill: white;");
        
        // --- Buttons ---
        Button yesMaterialButton = new Button("Yes");
        Button noMaterialButton = new Button("No");
        Button exitButton = new Button("Exit Lookup");
        Button restartButton = new Button("Restart Lookup");
        
        HBox materialQuestionButtons = new HBox(20, yesMaterialButton, noMaterialButton);
        materialQuestionButtons.setAlignment(Pos.CENTER);
        
        HBox bottomButtons = new HBox(20, exitButton, restartButton);
        bottomButtons.setAlignment(Pos.CENTER);
        
        // --- Field for "No" Option ---
        TextField itemField = new TextField();
        itemField.setPromptText("Enter your item");
        itemField.setMaxWidth(300);
        
        Button checkButton = new Button("Check Item");
        VBox unknownMaterial = new VBox(10, itemField, checkButton, addToDataButton);
        unknownMaterial.setAlignment(Pos.CENTER);
        unknownMaterial.setVisible(false);
        addToDataButton.setVisible(false);
        
        // --- Material Selection Layout ---
        Button cardboardButton = new Button("Cardboard");
        Button electronicButton = new Button("Electronic");
        Button fabricButton = new Button("Fabric");
        Button foodWasteButton = new Button("Food Waste");
        Button glassButton = new Button("Glass");
        Button metalButton = new Button("Metal");
        Button paperButton = new Button("Paper");
        Button plasticButton = new Button("Plastic");
        Button woodButton = new Button("Wood");

        
        
        VBox materialOptionsLayout = new VBox(10, cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
        materialOptionsLayout.setAlignment(Pos.CENTER);
        materialOptionsLayout.setVisible(false);
        
        // --- Actions ---
        yesMaterialButton.setOnAction(e -> {
            Gui.L.info("User knows material");
            materialQuestionButtons.setVisible(false);
            unknownMaterial.setVisible(false);
            materialOptionsLayout.setVisible(true);
            questionText.setText("Select the material:");
        });
        
        noMaterialButton.setOnAction(e -> {
            Gui.L.info("User does NOT know material");
            materialQuestionButtons.setVisible(false);
            materialOptionsLayout.setVisible(false);
            unknownMaterial.setVisible(true);
            questionText.setText("What is your item?");
        });

        addToDataButton.setOnAction(e -> {
            currentItems.add(field.getText().toLowerCase());
            setUpAddScene(mainStage);
            mainStage.setScene(addScene);
        });

        // Material Button Functionality

        cardboardButton.setOnAction(e -> {
            Gui.L.info("Cardboard button pressed");
            questionText.setText("Is the cardboard clean or soiled?");
            materialOptionsLayout.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            materialOptionsLayout.getChildren().addAll(cleanButton, soiledButton);
        });

        cleanButton.setOnAction(e -> {
            Gui.L.info("Clean button pressed");
            questionText.setText("The item is recyclable at a bin or a center");
            materialOptionsLayout.getChildren().removeAll(cleanButton, soiledButton);
            materialOptionsLayout.getChildren().add(newLookupButton);
        });

        soiledButton.setOnAction(e -> {
            Gui.L.info("Soiled button pressed");
            questionText.setText("The item is disposable in a trash can");
            materialOptionsLayout.getChildren().removeAll(cleanButton, soiledButton);
            materialOptionsLayout.getChildren().add(newLookupButton);
        });

        electronicButton.setOnAction(e -> {
            Gui.L.info("Electronic button pressed");
            questionText.setText("Does the electronic have a battery?");
            materialOptionsLayout.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            materialOptionsLayout.getChildren().addAll(yesBatteryButton, noBatteryButton);
        });

        yesBatteryButton.setOnAction(e -> {
            Gui.L.info("Yes Battery button pressed");
            questionText.setText("The item is donatable at a donation center");
            materialOptionsLayout.getChildren().removeAll(yesBatteryButton, noBatteryButton);
            materialOptionsLayout.getChildren().add(newLookupButton);
        });

        noBatteryButton.setOnAction(e -> {
            Gui.L.info("No Battery button pressed");
            questionText.setText("The item is disposable in a trash can, recyclable at a center,\n and donatable at a donation center");
            materialOptionsLayout.getChildren().removeAll(yesBatteryButton, noBatteryButton);
            materialOptionsLayout.getChildren().add(newLookupButton);
        });

        fabricButton.setOnAction(e -> {
            Gui.L.info("Fabric button pressed");
            questionText.setText("Does the fabric contain harmful dyes?");
            materialOptionsLayout.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            materialOptionsLayout.getChildren().addAll(yesHarmDyeButton, noHarmDyeButton);
        });

        yesHarmDyeButton.setOnAction(e -> {
            Gui.L.info("Yes Harmful Dye button pressed");
            questionText.setText("The item is disposable in a trash can");
            materialOptionsLayout.getChildren().removeAll(yesHarmDyeButton, noHarmDyeButton);
            materialOptionsLayout.getChildren().add(newLookupButton);
        });

        noHarmDyeButton.setOnAction(e -> {
            Gui.L.info("No Harmful Dye button pressed");
            questionText.setText("The item is disposable in a trash can and recyclable at a center");
            materialOptionsLayout.getChildren().removeAll(yesHarmDyeButton, noHarmDyeButton);
            materialOptionsLayout.getChildren().add(newLookupButton);
        });

        foodWasteButton.setOnAction(e -> {
            Gui.L.info("Food Waste button pressed");
            questionText.setText("The item is compostable");
            materialOptionsLayout.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
        });

        glassButton.setOnAction(e -> {
            Gui.L.info("Glass button pressed");
            questionText.setText("Is the glass broken?");
            materialOptionsLayout.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            materialOptionsLayout.getChildren().addAll(yesBrokenButton, noBrokenButton);
        });

        yesBrokenButton.setOnAction(e -> {
            Gui.L.info("Glass Broken button pressed");
            questionText.setText("The item is disposable in a trash can, but should be marked");
            materialOptionsLayout.getChildren().removeAll(yesBrokenButton, noBrokenButton);
            materialOptionsLayout.getChildren().add(newLookupButton);
        });

        noBrokenButton.setOnAction(e -> {
            Gui.L.info("No Glass Broken button pressed");
            questionText.setText("The item is recyclable at a center");
            materialOptionsLayout.getChildren().removeAll(yesBrokenButton, noBrokenButton);
            materialOptionsLayout.getChildren().add(newLookupButton);
        });

        metalButton.setOnAction(e -> {
            Gui.L.info("Metal button pressed");
            questionText.setText("What type of metal is it?");
            materialOptionsLayout.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            materialOptionsLayout.getChildren().addAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        aluminumButton.setOnAction(e -> {
            Gui.L.info("Aluminum button pressed");
            questionText.setText("The item is disposable at a trash can or recyclable at a center");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        brassButton.setOnAction(e -> {
            Gui.L.info("Brass button pressed");
            questionText.setText("The item is disposable at a trash can or recyclable at a center");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        bronzeButton.setOnAction(e -> {
            Gui.L.info("Bronze button pressed");
            questionText.setText("The item is disposable at a trash can or recyclable at a center");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        copperButton.setOnAction(e -> {
            Gui.L.info("Copper button pressed");
            questionText.setText("The item is disposable at a trash can or recyclable at a center");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        leadButton.setOnAction(e -> {
            Gui.L.info("Lead button pressed");
            questionText.setText("The item is recyclable at a center");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        ironButton.setOnAction(e -> {
            Gui.L.info("Iron button pressed");
            questionText.setText("The item is recyclable at a center");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        nickelButton.setOnAction(e -> {
            Gui.L.info("Nickel button pressed");
            questionText.setText("The item is recyclable at a center");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        steelButton.setOnAction(e -> {
            Gui.L.info("Steel button pressed");
            questionText.setText("The item is disposable at a trash can or recyclable at a center");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        tinButton.setOnAction(e -> {
            Gui.L.info("Tin button pressed");
            questionText.setText("The item is disposable at a trash can or recyclable at a center");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        titaniumButton.setOnAction(e -> {
            Gui.L.info("Titanium button pressed");
            questionText.setText("The item is recyclable at a center");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        paperButton.setOnAction(e -> {
            Gui.L.info("Paper button pressed");
            questionText.setText("Does the paper have ink on it?");
            materialOptionsLayout.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            materialOptionsLayout.getChildren().addAll(yesInkButton, noInkButton);
        });

        yesInkButton.setOnAction(e -> {
            Gui.L.info("Ink button pressed");
            questionText.setText("The item is disposable in a trash can and recyclable at a bin");
            materialOptionsLayout.getChildren().removeAll(yesInkButton, noInkButton);
            materialOptionsLayout.getChildren().add(newLookupButton);
        });

        noBrokenButton.setOnAction(e -> {
            Gui.L.info("No Ink button pressed");
            questionText.setText("The item is recyclable at a bin, disposable in a trash can, \n and compostable");
            materialOptionsLayout.getChildren().removeAll(yesInkButton, noInkButton);
            materialOptionsLayout.getChildren().add(newLookupButton);
        });

        plasticButton.setOnAction(e -> {
            Gui.L.info("Plastic button pressed");
            questionText.setText("What number is on the plastic?");
            materialOptionsLayout.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            materialOptionsLayout.getChildren().addAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p1Button.setOnAction(e -> {
            Gui.L.info("Plastic 1 button pressed");
            questionText.setText("The item is recyclable at a bin");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p2Button.setOnAction(e -> {
            Gui.L.info("Plastic 2 button pressed");
            questionText.setText("The item is recyclable at a bin");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p3Button.setOnAction(e -> {
            Gui.L.info("Plastic 3 button pressed");
            questionText.setText("The item is disposable at a trash can");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p4Button.setOnAction(e -> {
            Gui.L.info("Plastic 4 button pressed");
            questionText.setText("The item is recyclable at a center");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p5Button.setOnAction(e -> {
            Gui.L.info("Plastic 5 button pressed");
            questionText.setText("The item is recyclable at a bin");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p6Button.setOnAction(e -> {
            Gui.L.info("Plastic 6 button pressed");
            questionText.setText("The item is disposable at a trash can");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p7Button.setOnAction(e -> {
            Gui.L.info("Plastic 7 button pressed");
            questionText.setText("The item is disposable at a trash can");
            materialOptionsLayout.getChildren().add(newLookupButton);
            materialOptionsLayout.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        woodButton.setOnAction(e -> {
            Gui.L.info("Wood button pressed");
            questionText.setText("Is the wood manufactored or contaminated in any way?");
            materialOptionsLayout.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            materialOptionsLayout.getChildren().addAll(yesCoMButton, noCoMButton);
        });

        yesCoMButton.setOnAction(e -> {
            Gui.L.info("Contaminated or Manufactored button pressed");
            questionText.setText("The item is disposable in a trash can");
            materialOptionsLayout.getChildren().removeAll(yesCoMButton, noCoMButton);
            materialOptionsLayout.getChildren().add(newLookupButton);
        });

        noCoMButton.setOnAction(e -> {
            Gui.L.info("Not Contaminated or Manufactored button pressed");
            questionText.setText("The item is compostable");
            materialOptionsLayout.getChildren().removeAll(yesCoMButton, noCoMButton);
            materialOptionsLayout.getChildren().add(newLookupButton);
        });
        
        checkButton.setOnAction(e -> {
            String input = itemField.getText().toLowerCase();
            Gui.L.info("Checking item: " + input);
            
            try {
                    if (ic.checkItemList(input)){
                        src.recycling_types.Material m = ic.getAllItems().get(input);
                        Class<?>[] interfaceList = m.getClass().getInterfaces();
                        ArrayList<String> listOfInterfaces = new ArrayList<>();
    
                        for(Class<?> i : interfaceList){
                            listOfInterfaces.add(i.toString().replaceFirst("interface src.recycling_types.categories.", ""));
                        }
    
                        Gui.L.info("Item found in list");
                        instructionsText.setText("Found item! " + input + " is " + m.getClass().toString().substring(36) + 
                        "\nWhich means it's " + listOfInterfaces);
                        
                    }
    
                } catch(ItemNotFoundException itemNot) {
                    Gui.L.info(itemNot.getMessage());
                    String newItem = input;
                    instructionsText.setText("Item not found. Would you like to add " + newItem + " to the database?");
                    notFound.setVisible(true);
                    addToDataButton.setVisible(true);
    
                    
                }
        });
        
        exitButton.setOnAction(e -> {
            Gui.L.info("Exiting lookup");
            mainStage.setScene(Gui.titleScene);
        });
        
        restartButton.setOnAction(e -> {
            Gui.L.info("Restarting lookup");
            mainStage.setScene(setUpLookupScene(mainStage));
        });
        
        // --- Assemble Everything ---
        lookupLayout.getChildren().addAll(
            titleText,
            questionText,
            instructionsText,
            materialQuestionButtons,
            unknownMaterial,
            materialOptionsLayout,
            bottomButtons
        );
        
        lookupScene = new Scene(lookupLayout, 500, 750);  // Make sure it's initialized here
        return lookupScene;
        }
        
    /**
     * Restarts the lookup process by resetting the GUI components to their initial state.
     */
    private static void restart() {
        Gui.L.info("Restarting Lookup...");
        v.getChildren().clear(); // Clear all children to avoid duplicates
        v.getChildren().add(titleText);
        v.getChildren().add(questionText);
        v.getChildren().add(instructionsText);
        v.getChildren().addAll(exitButton, restartButton, yesMaterialButton, noMaterialButton);
        questionText.setText("Do you know the material of your item?");
    }

    /**
     * Sets up the scene for adding a new item to the database.
     * 
     * @param mainStage The primary stage of the application.
     */
    protected static void setUpAddScene(Stage mainStage) {
        // Labels
        Text title = new Text("Add To Database");
        title.setFont(Font.font(Gui.TITLE_FONT, 45));
    
        // Radiobuttons
        List<Class<? extends Material>> materials = Material.createAllMaterials();
        ToggleGroup matGroup = new ToggleGroup();
        RadioButton[] materialSelectBtns = new RadioButton[materials.size()];
    
        for (int i = 0; i < materialSelectBtns.length; i++) {
            materialSelectBtns[i] = new RadioButton(materials.get(i).getSimpleName().replaceAll("_", " "));
            materialSelectBtns[i].setToggleGroup(matGroup);
            materialSelectBtns[i].setFont(Font.font(Gui.BODY_FONT, 16));
        }
    
        // Buttons
        Button confirm = new Button("Confirm");
        confirm.setStyle(Gui.BUTTON_CSS);
    
        confirm.setOnAction(e -> {
            RadioButton selected = (RadioButton) matGroup.getSelectedToggle();
        
            if (selected != null) {
                String mat = selected.getText();
                ic.getMaterialToAdd(mat).addToPossibleItems(currentItems.get(currentItems.size() - 1));
                Gui.L.info(String.format("Added %s to list of possibleItems", mat));
        
                ic.reCollate();
                ic.reSerialize();
                ic.reloadMaterials();
                
                restart(); // Clear the scene for a new lookup
                setUpLookupScene(mainStage);
                mainStage.setScene(lookupScene);
            }
        });
    
        // Layout
        HBox top = new HBox(title);
        top.setAlignment(Pos.CENTER);
    
        VBox btns = new VBox(20, materialSelectBtns);
        btns.getChildren().add(confirm);
        btns.setAlignment(Pos.CENTER);
    
        VBox main = new VBox(top, new Separator(), btns);
    
        main.setPrefSize(Gui.APP_HEIGHT, Gui.APP_WIDTH);
        main.setStyle(Gui.APP_CSS);
        main.autosize();
    
        addScene = new Scene(main);
    }
    
    
}

