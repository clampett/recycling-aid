package src.customExceptions;

public class failedCenterException extends Exception{
    private static final String message = "Failed to send material to center."; //describes what went wrong
    private static final String errorCode = "05"; //error code specific to this exception

    public failedCenterException() {
        super(message);
        System.err.println("Error: " + errorCode());
    }

    //@return 
    // returns the error code of the exception
    public String errorCode(){
        return errorCode;
    }
}

