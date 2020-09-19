package pps.model;

public class ReturnMessage {
    boolean result;
    String message;

    public ReturnMessage(boolean result, String message) {
        this.result = result;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean getResult() {
        return result;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
