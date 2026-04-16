package models;

public class Room {
    private int roomId;
    private String roomName;
    private int capacity;

    public Room(int roomId, String roomName, int capacity) {
        if (roomName == null || roomName.trim().isEmpty()) {
            throw new IllegalArgumentException("Room name cannot be empty.");
        }
        if (capacity < 1) {
            throw new IllegalArgumentException("Room capacity must be at least 1.");
        }

        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return roomName + " (capacity: " + capacity + ")";
    }
}
