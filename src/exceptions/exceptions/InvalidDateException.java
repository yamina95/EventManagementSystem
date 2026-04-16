package exceptions;

public class InvalidDateException extends Exception {
    public InvalidDateException(String date) {
        super("Invalid date provided: '" + date + "'. Please use format yyyy-MM-dd and a future date.");
    }
}