package models;

import exceptions.AlreadyRegisteredException;
import exceptions.EventFullException;
import exceptions.InvalidDateException;
import exceptions.StudentNotFoundException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Event {
    protected int id;
    protected String title;
    protected String date;
    protected String location;
    protected int maxCapacity;
    protected Room room;
    protected List<Student> registeredStudents;

    public Event(int id, String title, String date, String location, int maxCapacity, Room room)
            throws InvalidDateException {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Event title cannot be empty.");
        }
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty.");
        }
        if (maxCapacity < 1) {
            throw new IllegalArgumentException("Max capacity must be at least 1.");
        }
        if (room != null && maxCapacity > room.getCapacity()) {
            throw new IllegalArgumentException("Event capacity cannot exceed room capacity.");
        }

        validateDate(date);

        this.id = id;
        this.title = title;
        this.date = date;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.room = room;
        this.registeredStudents = new ArrayList<>();
    }

    private void validateDate(String date) throws InvalidDateException {
        try {
            LocalDate parsedDate = LocalDate.parse(date);
            if (!parsedDate.isAfter(LocalDate.now())) {
                throw new InvalidDateException(date);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(date);
        }
    }

    public int getId() {
        return id;
    }

    public int getEventId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCapacity() {
        return maxCapacity;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        if (room != null && maxCapacity > room.getCapacity()) {
            throw new IllegalArgumentException("Event capacity cannot exceed room capacity.");
        }
        this.room = room;
    }

    public List<Student> getRegisteredStudents() {
        return Collections.unmodifiableList(registeredStudents);
    }

    public List<Student> getParticipants() {
        return getRegisteredStudents();
    }

    public boolean isFull() {
        return registeredStudents.size() >= maxCapacity;
    }

    public int getRegisteredCount() {
        return registeredStudents.size();
    }

    public void registerStudent(Student student)
            throws AlreadyRegisteredException, EventFullException {

        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (registeredStudents.contains(student)) {
            throw new AlreadyRegisteredException(student.getName(), title);
        }

        if (isFull()) {
            throw new EventFullException(title, maxCapacity);
        }

        registeredStudents.add(student);
    }

    public void unregisterStudent(Student student)
            throws StudentNotFoundException {

        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null.");
        }

        if (!registeredStudents.contains(student)) {
            throw new StudentNotFoundException(student.getName(), title);
        }

        registeredStudents.remove(student);
    }

    public abstract String getDetails();

    @Override
    public String toString() {
        return title + " - " + date;
    }
}