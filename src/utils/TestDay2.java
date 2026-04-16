package utils;

import exceptions.AlreadyRegisteredException;
import exceptions.EventFullException;
import exceptions.InvalidDateException;
import exceptions.RoomAlreadyBookedException;
import models.*;

import java.util.List;

public class TestDay2 {
    public static void main(String[] args) {
        EventManager manager = new EventManager();
        NotificationService notifService = new NotificationService();

        try {
            Admin admin = new Admin(
                    "A1",
                    "Yamina",
                    "yamina@enscs.dz",
                    "HIGH",
                    "Student Affairs",
                    true
            );

            Room room1 = new Room(101, "Conference Hall", 50);

            Event event1 = new SeminarEvent(
                    1,
                    "Cyber Security Workshop",
                    "2026-05-20",
                    "Main Campus",
                    2,
                    room1,
                    "Cybersecurity Basics"
            );

            admin.approveEvent(event1);
            manager.addEvent(event1);

            Student s1 = new Student("1", "Hamza", "hamza@enscs.dz", "S001", "Computer Science");
            Student s2 = new Student("2", "Habibou", "habibou@enscs.dz", "S002", "AI");
            Student s3 = new Student("3", "Babya", "babya@enscs.dz", "S003", "Networks");

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

            notifService.addNotification(
                    "You registered for " + event1.getTitle(),
                    s1.getId()
            );

            System.out.println("\nRegistered students:");
            for (Student s : event1.getParticipants()) {
                System.out.println("- " + s.getName());
            }

            try {
                Event event2 = new ConferenceEvent(
                        2,
                        "AI Seminar",
                        "2026-05-20",
                        "Main Campus",
                        30,
                        room1,
                        "Dr. Ahmed"
                );
                manager.bookRoom(event2, room1);

            } catch (RoomAlreadyBookedException e) {
                System.out.println("\nRoom booking error: " + e.getMessage());
            }

            List<Notification> notifs = notifService.getNotificationsFor(s1.getId());
            System.out.println("\nNotifications for " + s1.getName() + ":");
            notifs.forEach(System.out::println);

        } catch (InvalidDateException e) {
            System.out.println("Date error: " + e.getMessage());
        }
    }
}
