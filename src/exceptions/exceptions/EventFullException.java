package exceptions;

public class EventFullException extends Exception {
    public EventFullException(String eventName, int maxCapacity) {
        super("Cannot register: event '" + eventName + "' is full (max " + maxCapacity + " students).");
    }
}
