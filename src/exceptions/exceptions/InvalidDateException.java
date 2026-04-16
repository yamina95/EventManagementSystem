package EventManagementSystem.src.exceptions.exceptions;

public class InvalidDateException extends Exception {
    public InvalidDateException(String date) {
        super("Invalid date provided: '" + date + "'. Please use a future date.");
    }
}