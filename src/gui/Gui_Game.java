package src.gui;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Date;
import java.lang.reflect.Field;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import src.customExceptions.failedBinException;
import src.customExceptions.failedCenterException;
import src.customExceptions.failedCompostException;
import src.customExceptions.failedDisposeException;
import src.customExceptions.failedDonateException;
import src.recycling_types.Material;
import src.recycling_types.categories.Binnable;
import src.recycling_types.categories.Centerable;
import src.recycling_types.categories.Compostable;
import src.recycling_types.categories.Disposable;
import src.recycling_types.categories.Donatable;
import src.recycling_types.materials.Cardboard;
import src.recycling_types.materials.Electronic;
import src.recycling_types.materials.Fabric;
import src.recycling_types.materials.Food_Waste;
import src.recycling_types.materials.Glass;
import src.recycling_types.materials.Metal;
import src.recycling_types.materials.Paper;
import src.recycling_types.materials.Plastic;
import src.recycling_types.materials.Wood;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;




public class Gui_Game {


    // Create buttons as instances
    // Accessible throughout the class
    private static Button startButton = new Button("Start Game"); // Starts the game
    private static Button exitButton = new Button("Exit Game"); // Closes the program
    
    private static Button donatableButton = new Button("Donateable"); // Donateable button
    private static Button disposableButton = new Button("Disposeable"); // Disposeable button
    private static Button compostableButton = new Button("Compostable"); // Compostable button
    private static Button centerableButton = new Button("Centerable"); // Centerable button
    private static Button binnableButton = new Button("Binnable"); // Binnable button
    private static Text materialText = new Text("Material: START GAME"); // Placeholder text


    private static ArrayList<Button> recycleButtons = new ArrayList<>(Arrays.asList(donatableButton, disposableButton, compostableButton, centerableButton, binnableButton)); // List of all recycle buttons
    private static Material currentMaterial = null; // Current material to be recycled
    private static Class<? extends Material> currentMaterialClass = null;
    private static Field[] currentMaterialFields = null;

    protected static Scene setUpGameScene(Stage mainStage) {
        Gui.L.info("Setting up game scene");

        // Create text for gameScene
        Text titleText = new Text("Recycling game!");
        titleText.setFont(new Font("Impact", 30));
        titleText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
        titleText.setTranslateX(300);
        titleText.setTranslateY(50);

        // Create text that presents the material and its fields
        materialText.setFont(new Font("Impact", 23));

        materialText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
        materialText.setTranslateX(300);
        materialText.setTranslateY(150);
        
        // Create the game scene
        VBox v = new VBox();

        // Set the size of the Vbox
        v.setPrefSize(1100,700);
        v.setStyle(Gui.APP_CSS);
        v.autosize();
        v.getChildren().add(titleText);
        v.getChildren().add(materialText); // Add the material text to the vbox
        
        // Create text that shows whether a material was succefully recycled when failedException is thrown
        Text successStatusText = new Text("Recycle Success Status: ");
        successStatusText.setFont(new Font("Impact", 23));
        successStatusText.setStyle("-fx-fill: white; -fx-stroke: black; -fx-stroke-width: 1;");
        successStatusText.setTranslateX(300);
        successStatusText.setTranslateY(350);
        v.getChildren().add(successStatusText); // Add text to vbox

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

        // Set button locations and space them out
        startButton.setTranslateX(0);
        startButton.setTranslateY(20);
        exitButton.setTranslateX(1030);
        exitButton.setTranslateY(0);

        disposableButton.setTranslateY(10);
        compostableButton.setTranslateY(20);
        centerableButton.setTranslateY(30);
        binnableButton.setTranslateY(40);

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
                    ((Donatable)currentMaterial).attemptDonate(currentMaterial); // Attempt to donate the material
                    Gui.L.info("Material donated successfully: " + currentMaterial.getClass().getSimpleName());
                    successStatusText.setText("Recycle Success Status: Donated " + currentMaterial.getClass().getSimpleName()); // Update the success status text

                    //After successfully donating the material, we need to get a new random material
                    currentMaterial = getRandomMaterial(); // Get a new random material
                    randomizeMaterialFields(currentMaterial); // Randomize the material fields
                    currentMaterialClass = currentMaterial.getClass(); // Get the class of the current material
                    currentMaterialFields = currentMaterialClass.getDeclaredFields(); // Get the fields of the current material class
                    
                } catch (failedDonateException ex){
                    Gui.L.warning("Failed to donate material: " + ex.getMessage()); // Log the error message
                    successStatusText.setText("Recycle Success Status: Failed to donate " + currentMaterial.getClass().getSimpleName());
                }
            } else {
                Gui.L.warning("Material is not donateable: " + currentMaterial.getClass().getSimpleName()); // Log the error message
                successStatusText.setText("Recycle Success Status: Material is not donateable");
            }
            
        });

        disposableButton.setOnAction(e -> {
            Gui.L.info("Disposeable button pressed");
            // Logic for disposeable button goes here
            if(currentMaterial instanceof Disposable){
                try{
                    ((Disposable)currentMaterial).attemptDispose(currentMaterial); // Attempt to dispose the material
                    Gui.L.info("Material disposed successfully: " + currentMaterial.getClass().getSimpleName());
                    successStatusText.setText("Recycle Success Status: Disposed " + currentMaterial.getClass().getSimpleName()); // Update the success status text

                    //After successfully disposing the material, we need to get a new random material
                    currentMaterial = getRandomMaterial(); // Get a new random material
                    randomizeMaterialFields(currentMaterial); // Randomize the material fields
                    currentMaterialClass = currentMaterial.getClass(); // Get the class of the current material
                    currentMaterialFields = currentMaterialClass.getDeclaredFields(); // Get the fields of the current material class

                } catch(failedDisposeException ex){
                    Gui.L.warning("Failed to dispose material: " + ex.getMessage()); // Log the error message
                }
            }
            else{
                Gui.L.warning("Material is not disposable: " + currentMaterial.getClass().getSimpleName()); // Log the error message
                successStatusText.setText("Recycle Success Status: Material is not disposable");
            }
            
        });

        compostableButton.setOnAction(e -> {
            Gui.L.info("Compostable button pressed");
            // Logic for compostable button goes here
            if(currentMaterial instanceof Compostable){
                try{
                    ((Compostable)currentMaterial).attemptCompost(currentMaterial); // Attempt to compost the material
                    Gui.L.info("Material composted successfully: " + currentMaterial.getClass().getSimpleName());
                    successStatusText.setText("Recycle Success Status: Composted " + currentMaterial.getClass().getSimpleName()); // Update the success status text
                    
                    //After successfully composting the material, we need to get a new random material
                    currentMaterial = getRandomMaterial(); // Get a new random material
                    randomizeMaterialFields(currentMaterial); // Randomize the material fields
                    currentMaterialClass = currentMaterial.getClass(); // Get the class of the current material
                    currentMaterialFields = currentMaterialClass.getDeclaredFields(); // Get the fields of the current material class
                } catch(failedCompostException ex){
                    Gui.L.warning("Failed to compost material: " + ex.getMessage()); // Log the error message
                }
            } else{
                Gui.L.warning("Material is not compostable: " + currentMaterial.getClass().getSimpleName()); // Log the error message
                successStatusText.setText("Recycle Success Status: Material is not compostable");
            }
        });

        centerableButton.setOnAction(e -> {
            Gui.L.info("Centerable button pressed");
            // Logic for centerable button goes here 
            if(currentMaterial instanceof Centerable){
                try{
                    ((Centerable)currentMaterial).attemptCenter(currentMaterial); // Attempt to center the material
                    Gui.L.info("Material centered successfully: " + currentMaterial.getClass().getSimpleName());
                    successStatusText.setText("Recycle Success Status: Centered " + currentMaterial.getClass().getSimpleName()); // Update the success status text
                    
                    //After successfully centering the material, we need to get a new random material
                    currentMaterial = getRandomMaterial(); // Get a new random material
                    randomizeMaterialFields(currentMaterial); // Randomize the material fields
                    currentMaterialClass = currentMaterial.getClass(); // Get the class of the current material
                    currentMaterialFields = currentMaterialClass.getDeclaredFields(); // Get the fields of the current material class

                } catch(failedCenterException ex){
                    Gui.L.warning("Failed to center material: " + ex.getMessage()); // Log the error message
                    successStatusText.setText("Recycle Success Status: Failed to center " + currentMaterial.getClass().getSimpleName()); // Update the success status text
                }
            } else{
                Gui.L.warning("Material is not centerable: " + currentMaterial.getClass().getSimpleName()); // Log the error message
                successStatusText.setText("Recycle Success Status: Material is not centerable"); // Update the success status text
            }
            
        });

        binnableButton.setOnAction(e -> {
            Gui.L.info("Binnable button pressed");
            // Logic for binnable button goes here
            if(currentMaterial instanceof Binnable){
                try{
                    ((Binnable)currentMaterial).attemptBin(currentMaterial); // Attempt to bin the material
                    Gui.L.info("Material binned successfully: " + currentMaterial.getClass().getSimpleName());
                    successStatusText.setText("Recycle Success Status: Binned " + currentMaterial.getClass().getSimpleName()); // Update the success status text

                    //After successfully binning the material, we need to get a new random material
                    currentMaterial = getRandomMaterial(); // Get a new random material
                    randomizeMaterialFields(currentMaterial); // Randomize the material fields
                    currentMaterialClass = currentMaterial.getClass(); // Get the class of the current material
                    currentMaterialFields = currentMaterialClass.getDeclaredFields(); // Get the fields of the current material class
                } catch(failedBinException ex){
                    Gui.L.warning("Failed to bin material: " + ex.getMessage()); // Log the error message
                    successStatusText.setText("Recycle Success Status: Failed to bin " + currentMaterial.getClass().getSimpleName()); // Update the success status text
                }
            
            }else{
                    Gui.L.warning("Material is not binnable: " + currentMaterial.getClass().getSimpleName()); // Log the error message
                    successStatusText.setText("Recycle Success Status: Material is not binnable"); // Update the success status text
            }
            
        });

        // Add buttons to the Vbox
        v.getChildren().addAll(startButton, exitButton, donatableButton, disposableButton, compostableButton, centerableButton, binnableButton);

        // Disable buttons until the game starts
        recycleButtons.forEach(b -> b.setDisable(true)); // Disable all recycle buttons

        Scene gameScene = new Scene(v);
        return gameScene;
    }


    private static void startGame(){
        Gui.L.info("Starting game...");
        // Logic to start the game goes here
        currentMaterial = getRandomMaterial(); // Get a random material
        randomizeMaterialFields(currentMaterial); // Randomize the material fields
        currentMaterialClass = currentMaterial.getClass(); // Get the class of the current material
        currentMaterialFields = currentMaterialClass.getDeclaredFields(); // Get the fields of the current material class

        Gui.L.info("Recieved material: " + currentMaterial.getClass().getSimpleName()); // Log the material name
        
        // Disable and enable buttons based on the game state
        startButton.setDisable(true); // Disable the start button
        recycleButtons.forEach(b-> b.setDisable(false)); // Enable all recycle buttons
    }

    private static void endGame(){
        Gui.L.info("Ending game...");
        currentMaterial = null; // Set the current material to null

        // Disable and enable buttons based on the game state
        startButton.setDisable(false); // Disable the start button
        recycleButtons.forEach(b -> b.setDisable(true)); // Disable all recycle buttons
        materialText.setText("Material: START GAME"); // Reset the material text
    }

    /*
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
                ((Plastic) m).setPlasticNum(new Random().nextInt(7) + 1); // Set a random plastic number between 1 and 7
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
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", Properties: " + ((Paper) m).hasInk()); // Update the material text
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
                Gui.L.info("Randomized glass fields: " + ((Glass) m).glassIsTempered()); // Log the randomized glass fields
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", Properties: " + ((Glass) m).glassIsTempered()); // Update the material text
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
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", Properties : " + " Manufactured: " + ((Wood) m).getManufactured() + ", Contaminated: " + ((Wood) m).getContaminated()); // Update the material text

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
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", Properties: " + "Organic: " + ((Food_Waste) m).getIsOrganic() + ", Decomposition Time: " + ((Food_Waste) m).getDecompositionTime() + " days"); // Update the material text
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
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", Properties: " + "Dyed: " + ((Fabric) m).isDyed() + ", Harmful Dyes: " + ((Fabric) m).isHarmfulDyed()); // Update the material text
                break;
            case "Electronic":
                int randomHasBatery = (int)(Math.random() * 2 + 1); // Randomize between 1 and 2
                
                if(randomHasBatery == 1){
                    ((Electronic) m).setBatteryStatus(false); // Set has battery to false
                } else {
                    ((Electronic) m).setBatteryStatus(true); // Set has battery to true
                }

                Gui.L.info("Randomized electronic fields: " + ((Electronic) m).getHasBattery()); // Log the randomized electronic fields
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", Properties: " + "Has Battery: " + ((Electronic) m).getHasBattery()); // Update the material text
                break;
            case "Cardboard":
                int randomCardboardSoiled = (int)(Math.random() * 2 + 1); // Randomize between 1 and 2

                if(randomCardboardSoiled == 1){
                    ((Cardboard) m).setSoiled(false); // Set soiled to false
                } else {
                    ((Cardboard) m).setSoiled(true); // Set soiled to true
                }

                Gui.L.info("Randomized cardboard fields: " + ((Cardboard) m).getIsSoiled()); // Log the randomized cardboard fields
                materialText.setText("Material: " + m.getClass().getSimpleName() + ", Properties: " + "Soiled: " + ((Cardboard) m).getIsSoiled()); // Update the material text
                break;
            default:
                Gui.L.warning("Unknown material type: " + m.getClass().getSimpleName());
        }
    }
    

    /*
     * Returns a random material from the list of materials
     * 
     * @return a random material
     */
    private static Material getRandomMaterial(){
        // Logic to get a random material goes here

        // First we need to get a collection of all Material subclasses
        // This is done by getting all declared classes of the Material class
        
        
        // Create arrayList of all Materials
        ArrayList<Class<? extends Material>> materialClasses = new ArrayList<>(); // Create an array list to store the material classes
        materialClasses.add(Cardboard.class); // Add the Cardboard class to the array list
        materialClasses.add(Electronic.class); // Add the Electronic class to the array list
        materialClasses.add(Fabric.class); // Add the Fabric class to the array list
        materialClasses.add(Food_Waste.class); // Add the Food_Waste class to the array list
        materialClasses.add(Glass.class); // Add the Glass class to the array list
        materialClasses.add(Metal.class); // Add the Metal class to the array list
        materialClasses.add(Paper.class); // Add the Paper class to the array list
        materialClasses.add(Plastic.class); // Add the Plastic class to the array list
        materialClasses.add(Wood.class); // Add the Wood class to the array list

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
