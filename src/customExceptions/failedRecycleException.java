package src.customExceptions;

public class failedRecycleException extends Exception {
    private static final String message = "Failed to recycle material."; //describes what went wrong
    private static final String errorCode = "01"; //error code specific to this exception

    public failedRecycleException() {
        super(message);
        System.err.println(errorCode());
    }

    //@return 
    // returns the error code of the exception
    public String errorCode(){
        return errorCode;
    }
}