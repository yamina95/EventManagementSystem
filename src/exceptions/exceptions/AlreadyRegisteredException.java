package EventManagementSystem.src.exceptions.exceptions;

public class AlreadyRegisteredException extends Exception {
    public AlreadyRegisteredException(String studentName , String eventName ){
        super("Student '" +studentName + "'is already registered for '" +eventName + "'.");
    }
    
}
