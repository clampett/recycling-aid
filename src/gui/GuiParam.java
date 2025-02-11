package src.gui;

public enum GuiParam {
    //App Preferences
    APP_HEIGHT(750),
    APP_WIDTH(500),
    APP_CSS("-fx-background-color: #00b400;"),

    //Button Preferences
    BUTTON_HEIGHT(50),
    BUTTON_WIDTH(75),
    BACK_BUTTON_HEIGHT(30),
    BACK_BUTTON_WIDTH(50),
    BUTTON_CSS("-fx-background-color: #FFFFFF;"
                + "-fx-font-size: 15;"
                + "-fx-border-color: #000000;"
                + "-fx-border-width: 3;");

    private double doubVal;
    private String strVal;

    GuiParam(String strVal) {
        this.strVal = strVal;
    }

    GuiParam(double doubVal) {
        this.doubVal = doubVal;
    }

    public double getD() {
        return doubVal;
    }

    public String getS() {
        return strVal;
    }
};