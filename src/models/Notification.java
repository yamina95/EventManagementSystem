package models;

import java.time.LocalDateTime;

public class Notification {
    private String message;
    private String recipientId;
    private LocalDateTime timestamp;
    private boolean isRead;

    public Notification(String message, String recipientId) {
        this.message = message;
        this.recipientId = recipientId;
        this.timestamp = LocalDateTime.now();
        this.isRead = false;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isRead() {
        return isRead;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public void showNotification() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "[" + (isRead ? "READ" : "NEW") + "] "
                + message + " (" + timestamp + ")";
    }
}