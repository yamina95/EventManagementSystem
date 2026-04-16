package models;

import java.util.ArrayList;
import java.util.List;

import exceptions.exceptions.AlreadyRegisteredException;
import exceptions.exceptions.EventFullException;
import exceptions.exceptions.InvalidDateException;
import exceptions.exceptions.StudentNotFoundException;

public class Event {
    private int eventId;
    private String title;
    private String date;
    private int capacity;
    private Room room;
    private List<Student> participants;

    public Event(int eventId, String title, String date, int capacity, Room room) {
         throws InvalidDateException {

    if (title == null || title.trim().isEmpty()) {
        throw new IllegalArgumentException(
            "Event title cannot be empty.");
    }
    if (maxCapacity < 1) {
        throw new IllegalArgumentException(
            "Capacity must be at least 1.");
    }
    if (date == null || date.trim().isEmpty()) {
        throw new InvalidDateException(date);
    }

        this.eventId = eventId;
        this.title = title;
        this.date = date;
        this.capacity = capacity;
        this.room = room;
        this.participants = new ArrayList<>();
    }

    public int getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getCapacity() {
        return capacity;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<Student> getParticipants() {
        return participants;
    }
    public boolean isFull() {
     return registeredStudents.size() >= maxCapacity;
}
if (isFull()) {
    throw new EventFullException(title, maxCapacity);
}
   
    @Override
    public String toString() {
        return "Event{id=" + eventId + ", title='" + title + "', date='" + date +
                "', capacity=" + capacity + ", room=" + room + "}";
    }
}
