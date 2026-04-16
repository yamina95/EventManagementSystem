package utils;
import models.Event;
import models.Student;
import models.Notification;
import exceptions.AlreadyRegisteredException;
import exceptions.EventFullException;
import exceptions.RoomAlreadyBookedException;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private List<Event> events;
    public EventManager(){
        events = new ArrayList<>();
    }
}
 public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void registerStudent(Event event, Student student)
            throws AlreadyRegisteredException, EventFullException {

        if (event.getParticipants().contains(student)) {
            throw new AlreadyRegisteredException();
        }

        if (event.getParticipants().size() >= event.getCapacity()) {
            throw new EventFullException();
        }

        event.getParticipants().add(student);
    }

    public void unregisterStudent(Event event, Student student) {
        event.getParticipants().remove(student);
    }

    public Notification createNotification(Event event, Student student) {
        return new Notification(student.getName() + " registered successfully for " + event.getTitle());
    }

    public void bookRoom(Event newEvent, Room room) throws RoomAlreadyBookedException {
        for (Event event : events) {
            if (event.getRoom().getRoomId() == room.getRoomId()
                    && event.getDate().equals(newEvent.getDate())) {
                throw new RoomAlreadyBookedException();
            }
        }
        newEvent.setRoom(room);
    }

