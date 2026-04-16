package utils;

import models.Student;
import models.Admin;
import models.Room;
import models.Event;
import models.Notification;

import java.util.List;

import exceptions.AlreadyRegisteredException;
import exceptions.EventFullException;
import exceptions.RoomAlreadyBookedException;

public class TestDay2 {
    public static void main(String[] args) {
        EventManager manager = new EventManager();

        Admin admin = new Admin(1, "Yamina", "yamina@enscs.dz");

        Room room1 = new Room(101, "Conference Hall", 50);

        Event event1 = new Event(1, "Cyber Security Workshop", "2026-05-01", 2, room1);
        admin.createEvent(event1);

        manager.addEvent(event1);

        Student s1 = new Student(1, "hamza", "hamza@enscs.dz");
        Student s2 = new Student(2, "habibou", "habibou@enscs.dz");
        Student s3 = new Student(3, "babya", "babya@enscs.dz");

        try {
            manager.registerStudent(event1, s1);
            Notification n1 = manager.createNotification(event1, s1);
            n1.showNotification();

            manager.registerStudent(event1, s2);
            Notification n2 = manager.createNotification(event1, s2);
            n2.showNotification();

            manager.registerStudent(event1, s3);
        } catch (AlreadyRegisteredException | EventFullException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nRegistered students:");
        for (Student s : event1.getParticipants()) {
            System.out.println("- " + s.getName());
        }

        try {
            Event event2 = new Event(2, "AI Seminar", "2026-05-01", 30, room1);
            manager.bookRoom(event2, room1);
        } catch (RoomAlreadyBookedException e) {
            System.out.println("\nRoom booking error: " + e.getMessage());
        }
        NotificationService notifService = new NotificationService();

try {
    event.registerStudent(student);
    notifService.addNotification(
        "You registered for " + event.getTitle(),
        student.getId()
    );
    System.out.println("Registration successful!");
} catch (EventFullException e) {
    System.out.println(e.getMessage());
}

// Check notifications
List<Notification> notifs =
    notifService.getNotificationsFor(student.getId());
notifs.forEach(System.out::println);
    }
}
