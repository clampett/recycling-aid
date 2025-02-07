package src.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import src.garbage_types.*;

public class InfoCLI {
    public List<Garbage> allTypes;

    public InfoCLI() {
        allTypes = new ArrayList<>(10);
        fillAllGarbageTypes(allTypes);
    }

    private void fillAllGarbageTypes(List<Garbage> toFill) {
        toFill.add(new Biodegradable());
        toFill.add(new Electronic());
        toFill.add(new Hazardous());

        for(int i = 1; i < 8; i++)
            toFill.add(new Plastic(i));
    }

    private void printList() {
        for(int i = 0; i < allTypes.size(); i++) {
            System.out.print(i + 1 + ". ");
            allTypes.get(i).printGarbageName();
        }
    }

    public void loopInfo() {
        boolean run = true;
        Scanner scan = new Scanner(System.in);

        while(run) {
            String selection = "";
            int selectionInt = -1;

            System.out.println("Types of Garbage: ");
            printList();

            System.out.print("\nSelect a number to learn more about the garbage type: ");

            selection = scan.nextLine().toLowerCase().trim();

            if(selection.equals("q")) 
                run = false;
            else{
                try {
                    selectionInt = Integer.parseInt(selection);

                    if(0 < selectionInt && selectionInt < 11) {
                        System.out.print("\033[H\033[2J");  
                        System.out.flush(); 
                        allTypes.get(--selectionInt).printRecyclingInfo();             
            
                        System.out.println("Press Enter");
                        scan.nextLine();

                    }
                    else {
                        System.out.print("\033[H\033[2J");  
                        System.out.flush(); 
                        System.out.println("Error - Invalid Number");
                    }
                } catch(NumberFormatException e) {
                    System.out.print("\033[H\033[2J");  
                    System.out.flush(); 
                    System.out.println("Error - Unkown Input");
                }
            }
        }
        scan.close();
    }
}