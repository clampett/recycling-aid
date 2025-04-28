package src.recycling_types;

/**
 * {@link failedTrashException} is the main {@code Exception} for the {@link src.gui.Gui_Game Gui_Game}.
 * It provides a detailed error message to display.
 * 
 * @author Andrew Casey, Saadat Emilbekova, Dylan Jablonski, Jason Mele & Will Zakroff
 * @version 4/28/2025
 */
public class failedTrashException extends Exception {
    private static final String message = "Failed to recycle Material: " +
                                          "\u001B[46m" + "%s" + "\u001B[0m - " +
                                          "is not a \u001B[46m" + "%s" + "\u001B[0m";

    public failedTrashException(Material m, Class<?> inter) {
        super(String.format(message, m.getName(), inter.getSimpleName()));
    }

    /**
     * Overriden getMaterial() to display a custom error message.
     * 
     * @param m {@link Material} that failed to be recycled.
     * @param inter {@link Material Material's} interface
     * @return Custom error message
     */
    public String getMessage(Material m, Class<?> inter) {
        return String.format(message, m.getName(), inter.getSimpleName());
    }
}