package utils;

import models.Event;
import models.Notification;
import models.Room;
import models.Student;
import exceptions.AlreadyRegisteredException;
import exceptions.EventFullException;
import exceptions.RoomAlreadyBookedException;
import exceptions.StudentNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<Event> events;

    public EventManager() {
        events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void registerStudent(Event event, Student student)
            throws AlreadyRegisteredException, EventFullException {
        event.registerStudent(student);
    }

    public void unregisterStudent(Event event, Student student)
            throws StudentNotFoundException {
        event.unregisterStudent(student);
    }

    public Notification createNotification(Event event, Student student) {
        return new Notification(
                student.getName() + " registered successfully for " + event.getTitle(),
                student.getId()
        );
    }

    public void bookRoom(Event newEvent, Room room) throws RoomAlreadyBookedException {
        for (Event event : events) {
            if (event.getRoom() != null
                    && event.getRoom().getRoomId() == room.getRoomId()
                    && event.getDate().equals(newEvent.getDate())) {
                throw new RoomAlreadyBookedException(room.getRoomName());
            }
        }
        newEvent.setRoom(room);
    }
}

