package src.recycling_types;


public class failedTrashException extends Exception {
    private static final String message = "Failed to recycle Material: " +
                                          "\u001B[46m" + "%s" + "\u001B[0m - " +
                                          "is not a \u001B[46m" + "%s" + "\u001B[0m";

    public failedTrashException(Material m, Class<?> inter) {
        super(String.format(message, m.getName(), inter.getSimpleName()));
    }

    public String getMessage(Material m, Class<?> inter) {
        return String.format(message, m.getName(), inter.getSimpleName());
    }
}