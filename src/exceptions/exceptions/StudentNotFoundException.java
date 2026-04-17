package exceptions;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String studentName, String eventName) {
        super("Student '" + studentName + "' is not registered for '" + eventName + "'.");
    }
}