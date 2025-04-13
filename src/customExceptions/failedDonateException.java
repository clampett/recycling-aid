package src.customExceptions;

public class failedDonateException extends Exception {
    private static final String message = "Failed to donate material."; //describes what went wrong
    private static final String errorCode = "02"; //error code specific to this exception

    public failedDonateException() {
        super(message);
        System.err.println("Error: " + errorCode());
    }

    //@return 
    // returns the error code of the exception
    public String errorCode(){
        return errorCode;
    }
}