package src.customExceptions;

public class failedDisposeException extends Exception {
    private static final String message = "Failed to dispose material."; //describes what went wrong
    private static final String errorCode = "03"; //error code specific to this exception

    public failedDisposeException() {
        super(message);
        System.err.println("Error: " + errorCode());
    }

    //@return 
    // returns the error code of the exception
    public String errorCode(){
        return errorCode;
    }
}
