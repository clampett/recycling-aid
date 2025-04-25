package src.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.*;
import src.calculator.Impact_Calculator;
import src.calculator.ItemNotFoundException;

public class Gui_Lookup {
    private static Impact_Calculator ic = new Impact_Calculator();
    private static String infoSuccess = "Successfully added: %s";
    private static Button notFound = new Button("Add to Database");
    private static Button yesMaterialButton = new Button("Yes"); 
    private static Button noMaterialButton = new Button("No");
    private static Button cleanButton = new Button("Clean"); 
    private static Button soiledButton = new Button("Soiled");
    private static Button cardboardButton = new Button("Cardboard"); 
    private static Button electronicButton = new Button("Electronic");
    private static Button yesBatteryButton = new Button("Yes"); 
    private static Button noBatteryButton = new Button("No");
    private static Button fabricButton = new Button("Fabric");
    private static Button yesHarmDyeButton = new Button("Yes"); 
    private static Button noHarmDyeButton = new Button("No");
    private static Button foodWasteButton = new Button("Food Waste");
    private static Button glassButton = new Button("Glass");
    private static Button yesBrokenButton = new Button("Yes"); 
    private static Button noBrokenButton = new Button("No");
    private static Button metalButton = new Button("Metal");
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
    private static Button paperButton = new Button("Paper");
    private static Button yesInkButton = new Button("Yes"); 
    private static Button noInkButton = new Button("No");
    private static Button plasticButton = new Button("Plastic");
    private static Button p1Button = new Button("1");
    private static Button p2Button = new Button("2");
    private static Button p3Button = new Button("3");
    private static Button p4Button = new Button("4");
    private static Button p5Button = new Button("5");
    private static Button p6Button = new Button("6");
    private static Button p7Button = new Button("7");
    private static TextField field = new TextField();
    private static Button checkButton = new Button("Check For Item");
    private static Button woodButton = new Button("Wood");
    private static Button yesCoMButton = new Button("Yes"); 
    private static Button noCoMButton = new Button("No");
    private static Button exitButton = new Button("Exit Lookup");
    private static Button restartButton = new Button("Restart Lookup");
    private static Button newLookupButton = new Button("Lookup New Item");
    private static Text questionText = new Text("Do you know the material of your item?");
    private static VBox v = new VBox();
    private static Text titleText = new Text("Recycling Lookup!");
    protected static Scene setUpLookupScene(Stage mainStage) {
        Gui.L.info("Setting up game scene");

        // Create text for lookup
        titleText.setFont(new Font("Impact", 30));
        titleText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
        titleText.setTranslateX(300);
        titleText.setTranslateY(50);

        questionText.setFont(new Font("Impact", 23));
        questionText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
        questionText.setTranslateX(300);
        questionText.setTranslateY(150);

        field.setFont(Font.font(Gui.BODY_FONT, 20));
        field.setMaxWidth(Gui.APP_WIDTH);

        v.setPrefSize(1100,700);
        v.setStyle(Gui.APP_CSS);
        v.autosize();
        v.getChildren().add(titleText);
        v.getChildren().add(questionText);
        v.getChildren().addAll(exitButton, restartButton, yesMaterialButton, noMaterialButton);

        yesMaterialButton.setTranslateX(400);
        yesMaterialButton.setTranslateY(200);
        yesMaterialButton.setPrefHeight(30);
        yesMaterialButton.setPrefWidth(100);
        noMaterialButton.setTranslateX(630);
        noMaterialButton.setTranslateY(170);
        noMaterialButton.setPrefHeight(30);
        noMaterialButton.setPrefWidth(100);
        exitButton.setTranslateX(1000);
        exitButton.setTranslateY(20);
        exitButton.setPrefHeight(30);
        exitButton.setPrefWidth(100);
        restartButton.setTranslateX(0);
        restartButton.setTranslateY(0);
        restartButton.setPrefHeight(30);
        restartButton.setPrefWidth(100);

        cardboardButton.setTranslateX(0);
        cardboardButton.setTranslateY(200);
        cardboardButton.setPrefHeight(30);
        cardboardButton.setPrefWidth(100);
        electronicButton.setTranslateX(125);
        electronicButton.setTranslateY(170);
        electronicButton.setPrefHeight(30);
        electronicButton.setPrefWidth(100);
        fabricButton.setTranslateX(251);
        fabricButton.setTranslateY(140);
        fabricButton.setPrefHeight(30);
        fabricButton.setPrefWidth(100);
        foodWasteButton.setTranslateX(376);
        foodWasteButton.setTranslateY(110);
        foodWasteButton.setPrefHeight(30);
        foodWasteButton.setPrefWidth(100);
        glassButton.setTranslateX(502);
        glassButton.setTranslateY(80);
        glassButton.setPrefHeight(30);
        glassButton.setPrefWidth(100);
        metalButton.setTranslateX(627);
        metalButton.setTranslateY(50);
        metalButton.setPrefHeight(30);
        metalButton.setPrefWidth(100);
        paperButton.setTranslateX(753);
        paperButton.setTranslateY(20);
        paperButton.setPrefHeight(30);
        paperButton.setPrefWidth(100);
        plasticButton.setTranslateX(878);
        plasticButton.setTranslateY(-10);
        plasticButton.setPrefHeight(30);
        plasticButton.setPrefWidth(100);
        woodButton.setTranslateX(1000);
        woodButton.setTranslateY(-40);
        woodButton.setPrefHeight(30);
        woodButton.setPrefWidth(100);

        cleanButton.setTranslateX(400);
        cleanButton.setTranslateY(200);
        cleanButton.setPrefHeight(30);
        cleanButton.setPrefWidth(100);
        soiledButton.setTranslateX(630);
        soiledButton.setTranslateY(170);
        soiledButton.setPrefHeight(30);
        soiledButton.setPrefWidth(100);

        yesBatteryButton.setTranslateX(400);
        yesBatteryButton.setTranslateY(200);
        yesBatteryButton.setPrefHeight(30);
        yesBatteryButton.setPrefWidth(100);
        noBatteryButton.setTranslateX(630);
        noBatteryButton.setTranslateY(170);
        noBatteryButton.setPrefHeight(30);
        noBatteryButton.setPrefWidth(100);

        yesHarmDyeButton.setTranslateX(400);
        yesHarmDyeButton.setTranslateY(200);
        yesHarmDyeButton.setPrefHeight(30);
        yesHarmDyeButton.setPrefWidth(100);
        noHarmDyeButton.setTranslateX(630);
        noHarmDyeButton.setTranslateY(170);
        noHarmDyeButton.setPrefHeight(30);
        noHarmDyeButton.setPrefWidth(100);

        yesBrokenButton.setTranslateX(400);
        yesBrokenButton.setTranslateY(200);
        yesBrokenButton.setPrefHeight(30);
        yesBrokenButton.setPrefWidth(100);
        noBrokenButton.setTranslateX(630);
        noBrokenButton.setTranslateY(170);
        noBrokenButton.setPrefHeight(30);
        noBrokenButton.setPrefWidth(100);

        aluminumButton.setTranslateX(0);
        aluminumButton.setTranslateY(200);
        aluminumButton.setPrefHeight(30);
        aluminumButton.setPrefWidth(110);
        brassButton.setTranslateX(110);
        brassButton.setTranslateY(170);
        brassButton.setPrefHeight(30);
        brassButton.setPrefWidth(110);
        bronzeButton.setTranslateX(220);
        bronzeButton.setTranslateY(140);
        bronzeButton.setPrefHeight(30);
        bronzeButton.setPrefWidth(110);
        copperButton.setTranslateX(330);
        copperButton.setTranslateY(110);
        copperButton.setPrefHeight(30);
        copperButton.setPrefWidth(110);
        leadButton.setTranslateX(440);
        leadButton.setTranslateY(80);
        leadButton.setPrefHeight(30);
        leadButton.setPrefWidth(110);
        ironButton.setTranslateX(550);
        ironButton.setTranslateY(50);
        ironButton.setPrefHeight(30);
        ironButton.setPrefWidth(110);
        nickelButton.setTranslateX(660);
        nickelButton.setTranslateY(20);
        nickelButton.setPrefHeight(30);
        nickelButton.setPrefWidth(110);
        steelButton.setTranslateX(770);
        steelButton.setTranslateY(-10);
        steelButton.setPrefHeight(30);
        steelButton.setPrefWidth(110);
        tinButton.setTranslateX(880);
        tinButton.setTranslateY(-40);
        tinButton.setPrefHeight(30);
        tinButton.setPrefWidth(110);
        titaniumButton.setTranslateX(990);
        titaniumButton.setTranslateY(-70);
        titaniumButton.setPrefHeight(30);
        titaniumButton.setPrefWidth(110);

        yesInkButton.setTranslateX(400);
        yesInkButton.setTranslateY(200);
        yesInkButton.setPrefHeight(30);
        yesInkButton.setPrefWidth(100);
        noInkButton.setTranslateX(630);
        noInkButton.setTranslateY(170);
        noInkButton.setPrefHeight(30);
        noInkButton.setPrefWidth(100);

        p1Button.setTranslateX(125);
        p1Button.setTranslateY(170);
        p1Button.setPrefHeight(30);
        p1Button.setPrefWidth(100);
        p2Button.setTranslateX(251);
        p2Button.setTranslateY(140);
        p2Button.setPrefHeight(30);
        p2Button.setPrefWidth(100);
        p3Button.setTranslateX(376);
        p3Button.setTranslateY(110);
        p3Button.setPrefHeight(30);
        p3Button.setPrefWidth(100);
        p4Button.setTranslateX(502);
        p4Button.setTranslateY(80);
        p4Button.setPrefHeight(30);
        p4Button.setPrefWidth(100);
        p5Button.setTranslateX(627);
        p5Button.setTranslateY(50);
        p5Button.setPrefHeight(30);
        p5Button.setPrefWidth(100);
        p6Button.setTranslateX(753);
        p6Button.setTranslateY(20);
        p6Button.setPrefHeight(30);
        p6Button.setPrefWidth(100);
        p7Button.setTranslateX(878);
        p7Button.setTranslateY(-10);
        p7Button.setPrefHeight(30);
        p7Button.setPrefWidth(100);

        yesCoMButton.setTranslateX(400);
        yesCoMButton.setTranslateY(200);
        yesCoMButton.setPrefHeight(30);
        yesCoMButton.setPrefWidth(100);
        noCoMButton.setTranslateX(630);
        noCoMButton.setTranslateY(170);
        noCoMButton.setPrefHeight(30);
        noCoMButton.setPrefWidth(100);

        newLookupButton.setTranslateX(400);
        newLookupButton.setTranslateY(200);
        newLookupButton.setPrefHeight(90);
        newLookupButton.setPrefWidth(300);

        field.setTranslateX(300);
        field.setTranslateY(200);
        checkButton.setTranslateX(500);
        checkButton.setTranslateY(220);
        checkButton.setPrefHeight(30);
        checkButton.setPrefWidth(100);
        notFound.setTranslateX(500);
        notFound.setTranslateY(250);
        notFound.setPrefHeight(30);
        notFound.setPrefWidth(100);

        yesMaterialButton.setOnAction(e -> {
            Gui.L.info("Answered yes to material question");
            questionText.setText("What is the material?");
            v.getChildren().removeAll(yesMaterialButton, noMaterialButton);
            v.getChildren().addAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
        });
        noMaterialButton.setOnAction(e -> {
            Gui.L.info("Answered no to material question");
            questionText.setText("What is your item?");
            v.getChildren().removeAll(yesMaterialButton, noMaterialButton);
            v.getChildren().addAll(field, checkButton);
        });

        cardboardButton.setOnAction(e -> {
            Gui.L.info("Cardboard button pressed");
            questionText.setText("Is the cardboard clean or soiled?");
            v.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            v.getChildren().addAll(cleanButton, soiledButton);
        });

        cleanButton.setOnAction(e -> {
            Gui.L.info("Clean button pressed");
            questionText.setText("The item is recyclable at a bin or a center");
            v.getChildren().removeAll(cleanButton, soiledButton);
            v.getChildren().add(newLookupButton);
        });

        soiledButton.setOnAction(e -> {
            Gui.L.info("Soiled button pressed");
            questionText.setText("The item is disposable in a trash can");
            v.getChildren().removeAll(cleanButton, soiledButton);
            v.getChildren().add(newLookupButton);
        });

        electronicButton.setOnAction(e -> {
            Gui.L.info("Electronic button pressed");
            questionText.setText("Does the electronic have a battery?");
            v.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            v.getChildren().addAll(yesBatteryButton, noBatteryButton);
        });

        yesBatteryButton.setOnAction(e -> {
            Gui.L.info("Yes Battery button pressed");
            questionText.setText("The item is donatable at a donation center");
            v.getChildren().removeAll(yesBatteryButton, noBatteryButton);
            v.getChildren().add(newLookupButton);
        });

        noBatteryButton.setOnAction(e -> {
            Gui.L.info("No Battery button pressed");
            questionText.setText("The item is disposable in a trash can, recyclable at a center,\n and donatable at a donation center");
            v.getChildren().removeAll(yesBatteryButton, noBatteryButton);
            v.getChildren().add(newLookupButton);
        });

        fabricButton.setOnAction(e -> {
            Gui.L.info("Fabric button pressed");
            questionText.setText("Does the fabric contain harmful dyes?");
            v.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            v.getChildren().addAll(yesHarmDyeButton, noHarmDyeButton);
        });

        yesHarmDyeButton.setOnAction(e -> {
            Gui.L.info("Yes Harmful Dye button pressed");
            questionText.setText("The item is disposable in a trash can");
            v.getChildren().removeAll(yesHarmDyeButton, noHarmDyeButton);
            v.getChildren().add(newLookupButton);
        });

        noHarmDyeButton.setOnAction(e -> {
            Gui.L.info("No Harmful Dye button pressed");
            questionText.setText("The item is disposable in a trash can and recyclable at a center");
            v.getChildren().removeAll(yesHarmDyeButton, noHarmDyeButton);
            v.getChildren().add(newLookupButton);
        });

        foodWasteButton.setOnAction(e -> {
            Gui.L.info("Food Waste button pressed");
            questionText.setText("The item is compostable");
            v.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
        });

        glassButton.setOnAction(e -> {
            Gui.L.info("Glass button pressed");
            questionText.setText("Is the glass broken?");
            v.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            v.getChildren().addAll(yesBrokenButton, noBrokenButton);
        });

        yesBrokenButton.setOnAction(e -> {
            Gui.L.info("Glass Broken button pressed");
            questionText.setText("The item is disposable in a trash can, but should be marked");
            v.getChildren().removeAll(yesBrokenButton, noBrokenButton);
            v.getChildren().add(newLookupButton);
        });

        noBrokenButton.setOnAction(e -> {
            Gui.L.info("No Glass Broken button pressed");
            questionText.setText("The item is recyclable at a center");
            v.getChildren().removeAll(yesBrokenButton, noBrokenButton);
            v.getChildren().add(newLookupButton);
        });

        metalButton.setOnAction(e -> {
            Gui.L.info("Metal button pressed");
            questionText.setText("What type of metal is it?");
            v.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            v.getChildren().addAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        aluminumButton.setOnAction(e -> {
            Gui.L.info("Aluminum button pressed");
            questionText.setText("The item is disposable at a trash can or recyclable at a center");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        brassButton.setOnAction(e -> {
            Gui.L.info("Brass button pressed");
            questionText.setText("The item is disposable at a trash can or recyclable at a center");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        bronzeButton.setOnAction(e -> {
            Gui.L.info("Bronze button pressed");
            questionText.setText("The item is disposable at a trash can or recyclable at a center");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        copperButton.setOnAction(e -> {
            Gui.L.info("Copper button pressed");
            questionText.setText("The item is disposable at a trash can or recyclable at a center");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        leadButton.setOnAction(e -> {
            Gui.L.info("Lead button pressed");
            questionText.setText("The item is recyclable at a center");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        ironButton.setOnAction(e -> {
            Gui.L.info("Iron button pressed");
            questionText.setText("The item is recyclable at a center");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        nickelButton.setOnAction(e -> {
            Gui.L.info("Nickel button pressed");
            questionText.setText("The item is recyclable at a center");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        steelButton.setOnAction(e -> {
            Gui.L.info("Steel button pressed");
            questionText.setText("The item is disposable at a trash can or recyclable at a center");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        tinButton.setOnAction(e -> {
            Gui.L.info("Tin button pressed");
            questionText.setText("The item is disposable at a trash can or recyclable at a center");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        titaniumButton.setOnAction(e -> {
            Gui.L.info("Titanium button pressed");
            questionText.setText("The item is recyclable at a center");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton);
        });

        paperButton.setOnAction(e -> {
            Gui.L.info("Paper button pressed");
            questionText.setText("Does the paper have ink on it?");
            v.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            v.getChildren().addAll(yesInkButton, noInkButton);
        });

        yesInkButton.setOnAction(e -> {
            Gui.L.info("Ink button pressed");
            questionText.setText("The item is disposable in a trash can and recyclable at a bin");
            v.getChildren().removeAll(yesInkButton, noInkButton);
            v.getChildren().add(newLookupButton);
        });

        noBrokenButton.setOnAction(e -> {
            Gui.L.info("No Ink button pressed");
            questionText.setText("The item is recyclable at a bin, disposable in a trash can, \n and compostable");
            v.getChildren().removeAll(yesInkButton, noInkButton);
            v.getChildren().add(newLookupButton);
        });

        plasticButton.setOnAction(e -> {
            Gui.L.info("Plastic button pressed");
            questionText.setText("What number is on the plastic?");
            v.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            v.getChildren().addAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p1Button.setOnAction(e -> {
            Gui.L.info("Plastic 1 button pressed");
            questionText.setText("The item is recyclable at a bin");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p2Button.setOnAction(e -> {
            Gui.L.info("Plastic 2 button pressed");
            questionText.setText("The item is recyclable at a bin");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p3Button.setOnAction(e -> {
            Gui.L.info("Plastic 3 button pressed");
            questionText.setText("The item is disposable at a trash can");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p4Button.setOnAction(e -> {
            Gui.L.info("Plastic 4 button pressed");
            questionText.setText("The item is recyclable at a center");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p5Button.setOnAction(e -> {
            Gui.L.info("Plastic 5 button pressed");
            questionText.setText("The item is recyclable at a bin");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p6Button.setOnAction(e -> {
            Gui.L.info("Plastic 6 button pressed");
            questionText.setText("The item is disposable at a trash can");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        p7Button.setOnAction(e -> {
            Gui.L.info("Plastic 7 button pressed");
            questionText.setText("The item is disposable at a trash can");
            v.getChildren().add(newLookupButton);
            v.getChildren().removeAll(p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button);
        });

        woodButton.setOnAction(e -> {
            Gui.L.info("Wood button pressed");
            questionText.setText("Is the wood manufactored or contaminated in any way?");
            v.getChildren().removeAll(cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
            v.getChildren().addAll(yesCoMButton, noCoMButton);
        });

        yesCoMButton.setOnAction(e -> {
            Gui.L.info("Contaminated or Manufactored button pressed");
            questionText.setText("The item is disposable in a trash can");
            v.getChildren().removeAll(yesCoMButton, noCoMButton);
            v.getChildren().add(newLookupButton);
        });

        noCoMButton.setOnAction(e -> {
            Gui.L.info("Not Contaminated or Manufactored button pressed");
            questionText.setText("The item is compostable");
            v.getChildren().removeAll(yesCoMButton, noCoMButton);
            v.getChildren().add(newLookupButton);
        });

        exitButton.setOnAction(e -> {
            Gui.L.info("Exit button pressed");
            mainStage.setScene(Gui.titleScene); // Close the application 
        });

        restartButton.setOnAction(e -> {
            Gui.L.info("Restart button pressed");
            restart(); 
        });

        newLookupButton.setOnAction(e -> {
            Gui.L.info("New Lookup button pressed");
            restart(); 
        });
        
        Scene lookupScene = new Scene(v);
        return lookupScene;
    }

    private static void restart(){
        Gui.L.info("Restarting Lookup...");
        v.getChildren().removeAll(checkButton, notFound, field, newLookupButton, yesCoMButton, noCoMButton, p1Button, p2Button, p3Button, p4Button, p5Button, p6Button, p7Button, aluminumButton, brassButton, bronzeButton, copperButton, leadButton, ironButton, nickelButton, steelButton, tinButton, titaniumButton, yesInkButton, noInkButton, yesBatteryButton, noBatteryButton, cleanButton, soiledButton, exitButton, restartButton, yesMaterialButton, noMaterialButton, titleText, questionText, cardboardButton, electronicButton, fabricButton, foodWasteButton, glassButton, metalButton, paperButton, plasticButton, woodButton);
        v.getChildren().add(titleText);
        v.getChildren().add(questionText);
        v.getChildren().addAll(exitButton, restartButton, yesMaterialButton, noMaterialButton);
        questionText.setText("Do you know the material of your item?");

        
    }
}