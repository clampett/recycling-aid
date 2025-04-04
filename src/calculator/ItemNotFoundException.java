package src.calculator;

import src.gui.Gui_Calculator;

/**
 * {@link src.calculator.ItemNotFoundException ItemNotFoundException} is a custom exception for when the user attempts to add a new item to the 
 * {@link src.calculator.Impact_Calculator Impact_Calculator} allItems {@code HashMap}.
 * <p>
 * This exception is only called by the {@link src.calculator.Impact_Calculator#checkItemList(String) checkItemList()} method.
 * When thrown, the exception makes the neccessary controls visible to add a new item, specifically
 * using {@link src.gui.Gui_Calculator#showAddNew() showAddNew()}.
 * </p>
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/4/2025
 * @see src.calculator.Impact_Calculator Impact_Calculator
 * @see src.gui.Gui_Calculator Gui_Calculator
 */
public class ItemNotFoundException extends Exception {
    private String item;

    /**
     * Simple constructor for {@link src.calculator.ItemNotFoundException ItemNotFoundException}.
     * <p>
     * Sets item to given String & runs method to show controls to add a new item, using
     * {@link src.gui.Gui_Calculator#showAddNew() showAddNew()}.
     * 
     * @param item String item not present in List 
     */
    public ItemNotFoundException(String item) {
        this.item = item;
        Gui_Calculator.showAddNew();
    }

    /**
     * Returns item not present in allItems {@code HashMap}.
     */
    public String getItem() {
        return this.item;
    }

    /**
     * Custom toString() to show display an error.
     */
    @Override
    public String toString() {
        return "Could NOT find " + item + " in allItems HashMap";
    }
}