package src.customExceptions;

public class failedCompostException extends Exception{
    private static final String message = "Failed to compost material."; //describes what went wrong
    private static final String errorCode = "04"; //error code specific to this exception

    public failedCompostException() {
        super(message);
        System.err.println("Error: " + errorCode());
    }

    //@return 
    // returns the error code of the exception
    public String errorCode(){
        return errorCode;
    }
}