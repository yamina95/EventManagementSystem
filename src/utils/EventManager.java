package utils;

import exceptions.AlreadyRegisteredException;
import exceptions.EventFullException;
import exceptions.RoomAlreadyBookedException;
import exceptions.StudentNotFoundException;
import models.Event;
import models.Notification;
import models.Room;
import models.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventManager {
    private List<Event> events;

    public EventManager() {
        events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null.");
        }
        events.add(event);
    }

    public List<Event> getEvents() {
        return Collections.unmodifiableList(events);
    }

    public void registerStudent(Event event, Student student)
            throws AlreadyRegisteredException, EventFullException {
        if (event == null || student == null) {
            throw new IllegalArgumentException("Event and student cannot be null.");
        }
        event.registerStudent(student);
    }

    public void unregisterStudent(Event event, Student student)
            throws StudentNotFoundException {
        if (event == null || student == null) {
            throw new IllegalArgumentException("Event and student cannot be null.");
        }
        event.unregisterStudent(student);
    }

    public Notification createNotification(Event event, Student student) {
        return new Notification(
                student.getName() + " registered successfully for " + event.getTitle(),
                student.getId()
        );
    }

    public void bookRoom(Event newEvent, Room room) throws RoomAlreadyBookedException {
        if (newEvent == null || room == null) {
            throw new IllegalArgumentException("Event and room cannot be null.");
        }

        for (Event event : events) {
            if (event.getRoom() != null
                    && event.getRoom().getRoomId() == room.getRoomId()
                    && event.getDate().equals(newEvent.getDate())
                    && event.getId() != newEvent.getId()) {
                throw new RoomAlreadyBookedException(room.getRoomName());
            }
        }
        newEvent.setRoom(room);
    }
}

