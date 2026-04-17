package ui;

import exceptions.AlreadyRegisteredException;
import exceptions.EventFullException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import models.Event;
import models.Student;
import utils.NotificationService;

import java.util.List;
import java.util.stream.Collectors;

public class RegistrationPanel extends VBox {

    private ComboBox<Event> eventCombo;
    private Button registerBtn;
    private Label feedbackLabel;
    private Student currentStudent;
    private NotificationService notifService;
    private TableView<Event> myEventsTable;
    private List<Event> allEvents;

    public RegistrationPanel(List<Event> events,
                             Student student,
                             NotificationService notifService) {
        this.currentStudent = student;
        this.notifService = notifService;
        this.allEvents = events;

        setSpacing(12);
        setPadding(new Insets(20));

        Label title = new Label("Register for an Event");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        eventCombo = new ComboBox<>(FXCollections.observableArrayList(events));
        eventCombo.setPromptText("Select an event...");
        eventCombo.setPrefWidth(300);

        registerBtn = new Button("Register");
        registerBtn.setOnAction(e -> handleRegister());

        feedbackLabel = new Label("");
        feedbackLabel.setWrapText(true);

        Label myEventsLabel = new Label("My Registered Events");
        myEventsLabel.setStyle("-fx-font-weight: bold;");

        myEventsTable = new TableView<>();
        myEventsTable.setPrefHeight(200);

        TableColumn<Event, String> titleCol = new TableColumn<>("Event Title");
        titleCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getTitle()));
        titleCol.setPrefWidth(200);

        TableColumn<Event, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getDate()));
        dateCol.setPrefWidth(120);

        myEventsTable.getColumns().addAll(titleCol, dateCol);

        getChildren().addAll(
                title,
                eventCombo,
                registerBtn,
                feedbackLabel,
                myEventsLabel,
                myEventsTable
        );

        refreshMyEvents();
    }

    private void handleRegister() {
        Event selectedEvent = eventCombo.getValue();

        if (selectedEvent == null) {
            feedbackLabel.setText("Please select an event first.");
            return;
        }

        try {
            selectedEvent.registerStudent(currentStudent);

            notifService.addNotification(
                    "You registered for '" + selectedEvent.getTitle() + "'.",
                    currentStudent.getId()
            );

            feedbackLabel.setText("Registration successful!");
            refreshMyEvents();

        } catch (AlreadyRegisteredException | EventFullException ex) {
            feedbackLabel.setText(ex.getMessage());
        }
    }

    private void refreshMyEvents() {
        List<Event> mine = allEvents.stream()
                .filter(e -> e.getRegisteredStudents().contains(currentStudent))
                .collect(Collectors.toList());

        myEventsTable.setItems(FXCollections.observableArrayList(mine));
    }
}