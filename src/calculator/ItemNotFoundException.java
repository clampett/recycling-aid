package src.calculator;

import src.gui.Gui_Calculator;

public class ItemNotFoundException extends Exception {
    private String item;

    public ItemNotFoundException(String item) {
        this.item = item;
        Gui_Calculator.showAddNew();
    }

    public String getItem() {
        return this.item;
    }

    @Override
    public String toString() {
        return "Could NOT find " + item + " in allItems HashMap";
    }
}