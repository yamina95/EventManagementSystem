package EventManagementSystem.src.exceptions.exceptions;

public class RoomAlreadyBookedException extends Exception{
    public RoomAlreadyBookedException(String roomName) {
        super("Room '"+roomName+ "'is already booked for this time . we're sorry :-)");

    }
}
