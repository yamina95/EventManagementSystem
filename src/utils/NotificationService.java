package utils;

import models.Notification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NotificationService {
    private List<Notification> notifications = new ArrayList<>();

    public void addNotification(String message, String recipientId) {
        notifications.add(new Notification(message, recipientId));
    }

    public List<Notification> getNotificationsFor(String recipientId) {
        return notifications.stream()
                .filter(n -> n.getRecipientId().equals(recipientId))
                .collect(Collectors.toList());
    }

    public void markAllReadFor(String recipientId) {
        getNotificationsFor(recipientId).forEach(Notification::markAsRead);
    }

    public long countUnreadFor(String recipientId) {
        return getNotificationsFor(recipientId)
                .stream()
                .filter(n -> !n.isRead())
                .count();
    }
}