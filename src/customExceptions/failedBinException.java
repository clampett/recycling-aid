package src.customExceptions;

public class failedBinException extends Exception{
    private static final String message = "Failed to bin material."; //describes what went wrong
    private static final String errorCode = "04"; //error code specific to this exception

    public failedBinException() {
        super(message);
        System.err.println("Error: " + errorCode());
    }

    //@return 
    // returns the error code of the exception
    public String errorCode(){
        return errorCode;
    }
}