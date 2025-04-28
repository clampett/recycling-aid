package src.gui;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Date;
import java.util.List;
import java.lang.reflect.Field;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import src.recycling_types.Material;
import src.recycling_types.failedTrashException;
import src.recycling_types.categories.*;
import src.recycling_types.materials.*;

/**
 * {@link Gui_Game} is apart of Recycling Aid's {@link Gui}.
 * It creates and handles all fo the {@code Scenes} and logic apart of the 
 * * game portion of the project. The game itself works like this:
 * <ol>
 *      <li>A random {@link src.recycling_types.Material Material} is generated, with random fields.
 *      <li>The user selects what the category that they think the random Material is apart of.
 *      <li>Repeat until the user selects out.
 * </ol>
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/28/2025
 */
public class Gui_Game {
    // Create buttons as instances
    // Accessible throughout the class
    private static Button startButton = Gui.createBackButton(); // Starts the game
    private static Button exitButton = Gui.createBackButton(); // Closes the program
    
    private static Button donatableButton = new Button("Donateable"); // Donateable button
    private static Button disposableButton = new Button("Disposeable"); // Disposeable button
    private static Button compostableButton = new Button("Compostable"); // Compostable button
    private static Button centerableButton = new Button("Centerable"); // Centerable button
    private static Button binnableButton = new Button("Binnable"); // Binnable button
    private static Text materialText = new Text("START GAME"); // Placeholder text
    private static Text successStatusText = new Text();

    private static ArrayList<Button> recycleButtons = new ArrayList<>(Arrays.asList(donatableButton, disposableButton, compostableButton, centerableButton, binnableButton)); // List of all recycle buttons
    private static Material currentMaterial = null; // Current material to be recycled
    private static Class<? extends Material> currentMaterialClass = null;
    private static Field[] currentMaterialFields = null;

    /**
     * Sets up the game {@code Scene}. When the user selected the start button,
     * the game starts by generating random {@link src.recycling_types.Material Materials}
     * and activating the category buttons.
     * 
     * @param mainStage the main stage from {@link Gui}
     * @return Game {@code Scene}
     */
    protected static Scene setUpGameScene(Stage mainStage) {
        Gui.L.info("Setting up game scene");

        // Create text for gameScene
        Text titleText = new Text("Recycling game!");
        titleText.setFont(new Font("Impact", 30));
        titleText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");

        Text materialTextI = new Text("MATERIAL: ");
        materialTextI.setFont(new Font("Impact", 23));
        materialTextI.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");

        // Create text that presents the material and its fields
        materialText.setFont(new Font(Gui.BODY_FONT, 18));
     
        // Create text that shows whether a material was succefully recycled when failedException is thrown
        Text successStatusI = new Text("Recycle Success Status: ");
        successStatusI.setFont(new Font("Impact", 23));
        successStatusI.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");

        successStatusText.setFont(new Font(Gui.BODY_FONT, 18));


        // Set button Icons and their sizes
        ImageView donateableIcon = new ImageView("data/images/donatable.png");
        donateableIcon.setFitWidth(64);
        donateableIcon.setFitHeight(64);
        donatableButton.setGraphic(donateableIcon);
        
        ImageView disposeableIcon = new ImageView("data/images/disposable.png");
        disposeableIcon.setFitWidth(64);
        disposeableIcon.setFitHeight(64);
        disposableButton.setGraphic(disposeableIcon);
        
        ImageView compostableIcon = new ImageView("data/images/compostable.png");
        compostableIcon.setFitWidth(64);
        compostableIcon.setFitHeight(64);
        compostableButton.setGraphic(compostableIcon);
        
        ImageView centerableIcon = new ImageView("data/images/centerable.png");
        centerableIcon.setFitWidth(64);
        centerableIcon.setFitHeight(64);
        centerableButton.setGraphic(centerableIcon);
        
        ImageView binnableIcon = new ImageView("data/images/binnable.png");
        binnableIcon.setFitWidth(64);
        binnableIcon.setFitHeight(64);
        binnableButton.setGraphic(binnableIcon);


        startButton.setText("Start Game");
        exitButton.setText("Exit Game");
        startButton.setMinWidth(Gui.BIGGER_BUTTON_WIDTH);
        exitButton.setMinWidth(Gui.BIGGER_BUTTON_WIDTH);

        recycleButtons.forEach(b -> {
                                b.setStyle(Gui.BUTTON_CSS);
                                b.setDisable(true);
        });

        // Give buttons functionality
        startButton.setOnAction(e -> {
            Gui.L.info("Start game button pressed");
            // Call the startGame method
            startGame();
        });
        exitButton.setOnAction(e -> {
            Gui.L.info("Exit game button pressed");
            mainStage.setScene(Gui.titleScene); // Close the application
            endGame(); // Call the endGame method
            
        });

        donatableButton.setOnAction(e -> {
            Gui.L.info("Donateable button pressed");
            // Logic for donateable button goes here

            if(currentMaterial instanceof Donatable){
                try{
                    if(((Donatable)currentMaterial).attemptDonate(currentMaterial)) // Attempt to donate the material
                    {
                    Gui.L.info("Material donated successfully: " + currentMaterial.getClass().getSimpleName());
                    successStatusText.setText("Donated " + currentMaterial.getClass().getSimpleName()); // Update the success status text

                    //After successfully donating the material, we need to get a new random material
                    currentMaterial = getRandomMaterial(); // Get a new random material
                    randomizeMaterialFields(currentMaterial); // Randomize the material fields
                    currentMaterialClass = currentMaterial.getClass(); // Get the class of the current material
                    
                    }
                } catch (failedTrashException ex){
                    Gui.L.warning("Failed to donate material: " + ex.getMessage(currentMaterial, Donatable.class)); // Log the error message
                    successStatusText.setText("Failed to donate " + currentMaterial.getClass().getSimpleName());
                }
            } else {
                Gui.L.warning("Material is not donateable: " + currentMaterial.getClass().getSimpleName()); // Log the error message
                successStatusText.setText("Material is not donateable");
            }
            
        });
        
        disposableButton.setOnAction(e -> {
            Gui.L.info("Disposeable button pressed");
            // Logic for disposeable button goes here
            if(currentMaterial instanceof Disposable){
                try{
                    if(((Disposable)currentMaterial).attemptDispose(currentMaterial)) // Attempt to dispose the material
                    {
                    Gui.L.info("Material disposed successfully: " + currentMaterial.getClass().getSimpleName());
                    successStatusText.setText("Disposed " + currentMaterial.getClass().getSimpleName()); // Update the success status text

                    //After successfully disposing the material, we need to get a new random material
                    currentMaterial = getRandomMaterial(); // Get a new random material
                    randomizeMaterialFields(currentMaterial); // Randomize the material fields
                    currentMaterialClass = currentMaterial.getClass(); // Get the class of the current material
                    
                    }
                } catch(failedTrashException ex){
                    Gui.L.warning("Failed to dispose material: " + ex.getMessage(currentMaterial, Disposable.class)); // Log the error message
                    successStatusText.setText("Failed to dispose " + currentMaterial.getClass().getSimpleName()); // Update the success status text
                }
            }
            else{
                Gui.L.warning("Material is not disposable: " + currentMaterial.getClass().getSimpleName()); // Log the error message
                successStatusText.setText("Material is not disposable");
            }
            
        });

        compostableButton.setOnAction(e -> {
            Gui.L.info("Compostable button pressed");
            // Logic for compostable button goes here
            if(currentMaterial instanceof Compostable){
                try{
                    if(((Compostable)currentMaterial).attemptCompost(currentMaterial)) // Attempt to compost the material
                    {
                    Gui.L.info("Material composted successfully: " + currentMaterial.getClass().getSimpleName());
                    successStatusText.setText("Composted " + currentMaterial.getClass().getSimpleName()); // Update the success status text
                    
                    //After successfully composting the material, we need to get a new random material
                    currentMaterial = getRandomMaterial(); // Get a new random material
                    randomizeMaterialFields(currentMaterial); // Randomize the material fields
                    currentMaterialClass = currentMaterial.getClass(); // Get the class of the current material
                    
                    }
                } catch(failedTrashException ex){
                    Gui.L.warning("Failed to compost material: " + ex.getMessage(currentMaterial, Compostable.class)); // Log the error message
                    successStatusText.setText("Failed to compost " + currentMaterial.getClass().getSimpleName()); // Update the success status text
                }
            } else{
                Gui.L.warning("Material is not compostable: " + currentMaterial.getClass().getSimpleName()); // Log the error message
                successStatusText.setText("Material is not compostable");
            }
        });

        centerableButton.setOnAction(e -> {
            Gui.L.info("Centerable button pressed");
            // Logic for centerable button goes here 
            if(currentMaterial instanceof Centerable){
                try{
                    if(((Centerable)currentMaterial).attemptCenter(currentMaterial)) // Attempt to center the material
                    {
                    Gui.L.info("Material centered successfully: " + currentMaterial.getClass().getSimpleName());
                    successStatusText.setText("Centered " + currentMaterial.getClass().getSimpleName()); // Update the success status text
                    
                    //After successfully centering the material, we need to get a new random material
                    currentMaterial = getRandomMaterial(); // Get a new random material
                    randomizeMaterialFields(currentMaterial); // Randomize the material fields
                    currentMaterialClass = currentMaterial.getClass(); // Get the class of the current material
                    
                    }

                } catch(failedTrashException ex){
                    Gui.L.warning("Failed to center material: " + ex.getMessage(currentMaterial, Centerable.class)); // Log the error message
                    successStatusText.setText("Failed to center " + currentMaterial.getClass().getSimpleName()); // Update the success status text
                }
            } else{
                Gui.L.warning("Material is not centerable: " + currentMaterial.getClass().getSimpleName()); // Log the error message
                successStatusText.setText("Material is not centerable"); // Update the success status text
            }
            
        });

        binnableButton.setOnAction(e -> {
            Gui.L.info("Binnable button pressed");
            // Logic for binnable button goes here
            if(currentMaterial instanceof Binnable){
                try{
                    if(((Binnable)currentMaterial).attemptBin(currentMaterial))// Attempt to bin the material
                    {
                    Gui.L.info("Material binned successfully: " + currentMaterial.getClass().getSimpleName());
                    successStatusText.setText("Binned " + currentMaterial.getClass().getSimpleName()); // Update the success status text

                    //After successfully binning the material, we need to get a new random material
                    currentMaterial = getRandomMaterial(); // Get a new random material
                    randomizeMaterialFields(currentMaterial); // Randomize the material fields
                    currentMaterialClass = currentMaterial.getClass(); // Get the class of the current material
                    
                    }
                } catch(failedTrashException ex){
                    Gui.L.warning("Failed to bin material: " + ex.getMessage(currentMaterial, Binnable.class)); // Log the error message
                    successStatusText.setText("Failed to bin " + currentMaterial.getClass().getSimpleName()); // Update the success status text
                }
            
            }else{
                    Gui.L.warning("Material is not binnable: " + currentMaterial.getClass().getSimpleName()); // Log the error message
                    successStatusText.setText("Material is not binnable"); // Update the success status text
            }
            
        });
        
        //Layout Managers
        HBox title = new HBox(titleText);
        title.setAlignment(Pos.CENTER);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox controls = new HBox(startButton, spacer, exitButton);

        VBox recycleControls = new VBox(10);
        recycleControls.getChildren().addAll(recycleButtons);

        HBox materialTextBox = new HBox(5, materialTextI, materialText);
        HBox statusTextBox = new HBox(5, successStatusI, successStatusText);

        VBox texts = new VBox(20, materialTextBox, statusTextBox);

        HBox middle = new HBox(10, recycleControls, texts);


        // Create the game scene
        VBox v = new VBox(10);

        // Set the size of the Vbox
        v.setPrefSize(1100,700);
        v.setStyle(Gui.APP_CSS);
        v.autosize();

        // Add buttons to the Vbox
        v.getChildren().addAll(title, controls, middle);

        Scene gameScene = new Scene(v);
        return gameScene;
    }

    /**
     * Starts up game by generating random {@link src.recycling_types.Material Materials}
     * and activating the category buttons.
     */
    private static void startGame(){
        Gui.L.info("Starting game...");

        currentMaterial = getRandomMaterial(); // Get a random material
        randomizeMaterialFields(currentMaterial); // Randomize the material fields
        currentMaterialClass = currentMaterial.getClass(); // Get the class of the current material
        
        Gui.L.info("Recieved material: " + currentMaterial.getClass().getSimpleName()); // Log the material name
        
        // Disable and enable buttons based on the game state
        startButton.setDisable(true); // Disable the start button
        recycleButtons.forEach(b-> b.setDisable(false)); // Enable all recycle buttons
    }

    /**
     * Ends game by setting everything to null.
     */
    private static void endGame(){
        Gui.L.info("Ending game...");
        currentMaterial = null; // Set the current material to null

        // Disable and enable buttons based on the game state
        startButton.setDisable(false); // Disable the start button
        recycleButtons.forEach(b -> b.setDisable(true)); // Disable all recycle buttons
        materialText.setText("START GAME"); // Reset the material text
        successStatusText.setText("");
    }

    /**
     * Randomizes the fields of the material based on its type
     * Each randomly selected material for the game has basic, pre selected values for their fields,
     * so this method is meant to authenticate the material and set the fields to random values
     * 
     * @param m the material to randomize
     */
    private static void randomizeMaterialFields(Material m){
        switch(m.getClass().getSimpleName()){
            case "Plastic":
            // Randomize plastic fields
            int randomPlastic = (int)(Math.random() *7 +1);
            ((Plastic) m).setPlasticType(randomPlastic);
            Gui.L.info("Randomized plastic fields: " + ((Plastic) m).getPlasticType()); // Log the randomized plastic fields
            materialText.setText("Material: " + m.getClass().getSimpleName() + ", Plastic Type: " + ((Plastic) m).getPlasticType()); // Update the material text
            break;
            case "Paper":
                // Randomize paper fields
                int randomPaperNum = (int) (Math.random()*2 + 1); // Randomize between 1 and 2
                if(randomPaperNum == 1){
                    ((Paper) m).setHasInk(true); // Set whether the paper has ink or not
                } else {
                    ((Paper) m).setHasInk(false); // Set whether the paper has ink or not
                }
                Gui.L.info("Randomized paper fields: " + ((Paper) m).hasInk()); // Log the randomized paper fields
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", Has ink:" + ((Paper) m).hasInk()); // Update the material text
                break;

            case "Metal":
                String[] metalTypes = {"Al", "CuZn", "CuSn", "Cu", "Pb", "Fe", "Ni", "FeC", "Sn", "Ti"}; // List of metal types
                // Randomize metal fields
                int randomIndex = (int)(Math.random() *9); // Randomize between 1 and 10
                ((Metal) m).setMetalType(metalTypes[randomIndex]); // Set a random metal type
                Gui.L.info("Randomized metal fields: " + ((Metal) m).getMetalType()); // Log the randomized metal fields
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", Metal Type: " + ((Metal) m).getMetalType()); // Update the material text
                break;
            case "Glass":
                // Randomize glass fields
                int randomGlassNum = (int) (Math.random()*2 + 1); // Randomize between 1 and 2
                if(randomGlassNum == 1){
                    ((Glass) m).setTempered(false); // Set tempered to false
                } else {
                    ((Glass) m).setTempered(true); // Set tempered to true
                }
                Gui.L.info("Randomized glass fields: " + ((Glass) m).getIsTempered()); // Log the randomized glass fields
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", Tempered: " + ((Glass) m).getIsTempered()); // Update the material text
                break;
            case "Wood":
                int randomWoodContamNum = (int) (Math.random()*2 + 1); // Randomize between 1 and 2
                int randomWoodManNum = (int) (Math.random()*2 + 1); // Randomize between 1 and 2

                if(randomWoodManNum == 1){
                    ((Wood) m).setManufactured(false); // Set manufactured to false
                } else {
                    ((Wood) m).setManufactured(true); // Set manufactured to true
                }

                if(randomWoodContamNum == 1){
                    ((Wood) m).setContaminated(false); // Set contaminated to false
                } else {
                    ((Wood) m).setContaminated(true); // Set contaminated to true
                }
                Gui.L.info("Randomized wood fields: " + ((Wood) m).getManufactured() + ((Wood) m).getContaminated()); // Log the randomized wood fields
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", " + " Manufactured: " + ((Wood) m).getManufactured() + ", Contaminated: " + ((Wood) m).getContaminated()); // Update the material text

                break;
            case "Food_Waste":
                int decompTime = (int) (Math.random()*14 + 1); // Randomize between 1 and 14 days
                ((Food_Waste) m).setDecompositionTime(decompTime); // Set the decomposition time
                int randomFoodNum = (int) (Math.random()*2 + 1); // Randomize between 1 and 2
                if(randomFoodNum == 1){
                    ((Food_Waste) m).setOrganic(false); // Set organic to false
                } else {
                    ((Food_Waste) m).setOrganic(true); // Set organic to true
                }
                Gui.L.info("Randomized food waste fields: " + ((Food_Waste) m).getIsOrganic() + ", " + ((Food_Waste) m).getDecompositionTime()); // Log the randomized food waste fields
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", " + "Organic: " + ((Food_Waste) m).getIsOrganic()); // Update the material text
                break;
            case "Fabric":
                int randomFabricIsDyed = (int)(Math.random() * 2 + 1); // Randomize between 1 and 2
                int randomFabricIsHarmfulDyed = (int)(Math.random() * 2 + 1); // Randomize between 1 and 2
                
                if(randomFabricIsDyed == 1){
                    ((Fabric) m).setDyed(false); // Set dyed to false
                } else {
                    ((Fabric) m).setDyed(true); // Set dyed to true
                }

                if(randomFabricIsHarmfulDyed == 1){
                    ((Fabric) m).setHarmfulDyed(false); // Set harmful dyed to false
                } else {
                    ((Fabric) m).setHarmfulDyed(true); // Set harmful dyed to true
                }

                Gui.L.info("Randomized fabric fields: " + ((Fabric) m).isDyed() + ", " + ((Fabric) m).isHarmfulDyed()); // Log the randomized fabric fields
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", Harmful Dyes: " + ((Fabric) m).isHarmfulDyed()); // Update the material text
                break;
            case "Electronic":
                int randomHasBatery = (int)(Math.random() * 2 + 1); // Randomize between 1 and 2
                
                if(randomHasBatery == 1){
                    ((Electronic) m).setBatteryStatus(false); // Set has battery to false
                } else {
                    ((Electronic) m).setBatteryStatus(true); // Set has battery to true
                }

                Gui.L.info("Randomized electronic fields: " + ((Electronic) m).getHasBattery()); // Log the randomized electronic fields
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", " + "Has Battery: " + ((Electronic) m).getHasBattery()); // Update the material text
                break;
            case "Cardboard":
                int randomCardboardSoiled = (int)(Math.random() * 2 + 1); // Randomize between 1 and 2

                if(randomCardboardSoiled == 1){
                    ((Cardboard) m).setSoiled(false); // Set soiled to false
                } else {
                    ((Cardboard) m).setSoiled(true); // Set soiled to true
                }

                Gui.L.info("Randomized cardboard fields: " + ((Cardboard) m).getIsSoiled()); // Log the randomized cardboard fields
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", " + "Soiled: " + ((Cardboard) m).getIsSoiled()); // Update the material text
                break;
            default:
                Gui.L.warning("Unknown material type: " + m.getClass().getSimpleName());
        }
    }
    

    /**
     * Returns a random material from the list of materials
     * 
     * @return a random material
     */
    private static Material getRandomMaterial(){
        // Create arrayList of all Materials
        List<Class<? extends Material>> materialClasses = Material.createAllMaterials();

        Date d = new Date(); // Get the current date and time
        Random r = new Random(d.getTime());
        int materialsLength = materialClasses.size(); // Get the length of the materials array
        int randomIndex = r.nextInt(materialsLength); // Get a random index from the materials array
        
        Class<? extends Material> randomMaterialClass = materialClasses.get(randomIndex); // Get the random material class

        Gui.L.info("Random material class selected: " + randomMaterialClass.getSimpleName()); // Log the selected material class
        try{
            // Logic to display the material and ask the user to recycle it goes here
            Constructor<?> constructor = randomMaterialClass.getDeclaredConstructor(); // Get the constructor of the random material class
            Material newMaterial = (Material) constructor.newInstance(); // Create an instance of the random material class
            Gui.L.info("Material successfully created: " + newMaterial.getClass().getSimpleName()); // Log the material name
            return newMaterial; // Return the current material
        }
        catch (Exception e) {
            Gui.L.severe("Error creating material: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
            return null; // Return null if there was an error
        }
    }
}