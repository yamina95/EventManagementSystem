package ui;

import exceptions.StudentNotFoundException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import models.Event;
import models.Student;
import utils.NotificationService;

import java.util.List;

public class StudentRosterPanel extends VBox {

    private ComboBox<Event> eventPicker;
    private TableView<Student> rosterTable;
    private Label countLabel;
    private NotificationService notifService;

    public StudentRosterPanel(List<Event> events,
                              NotificationService notifService) {
        this.notifService = notifService;

        setSpacing(12);
        setPadding(new Insets(20));

        Label title = new Label("Student Roster (Admin View)");
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        eventPicker = new ComboBox<>(FXCollections.observableArrayList(events));
        eventPicker.setPromptText("Select an event...");
        eventPicker.setPrefWidth(300);

        eventPicker.valueProperty().addListener(
                (obs, oldVal, newVal) -> refreshRoster(newVal)
        );

        countLabel = new Label("0 students registered");

        rosterTable = new TableView<>();
        rosterTable.setPrefHeight(220);
        buildTableColumns();

        Button removeBtn = new Button("Remove Selected");
        removeBtn.setOnAction(e -> handleRemove());

        getChildren().addAll(title, eventPicker, countLabel, rosterTable, removeBtn);
    }

    private void buildTableColumns() {
        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getName()));
        nameCol.setPrefWidth(160);

        TableColumn<Student, String> idCol = new TableColumn<>("Student ID");
        idCol.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getStudentId()));
        idCol.setPrefWidth(100);

        TableColumn<Student, String> majorCol = new TableColumn<>("Major");
        majorCol.setCellValueFactory(d ->
                new SimpleStringProperty(d.getValue().getMajor()));
        majorCol.setPrefWidth(140);

        rosterTable.getColumns().addAll(nameCol, idCol, majorCol);
    }

    private void refreshRoster(Event event) {
        if (event == null) {
            rosterTable.setItems(FXCollections.emptyObservableList());
            countLabel.setText("0 students registered");
            return;
        }

        List<Student> students = event.getRegisteredStudents();

        rosterTable.setItems(FXCollections.observableArrayList(students));
        countLabel.setText(students.size() + " / " + event.getMaxCapacity() + " registered");
    }

    private void handleRemove() {
        Event selectedEvent = eventPicker.getValue();
        Student selectedStudent = rosterTable.getSelectionModel().getSelectedItem();

        if (selectedEvent == null || selectedStudent == null) {
            showAlert("Please select both an event and a student.");
            return;
        }

        try {
            selectedEvent.unregisterStudent(selectedStudent);

            notifService.addNotification(
                    "Your registration for '" + selectedEvent.getTitle() + "' was cancelled by an admin.",
                    selectedStudent.getId()
            );

            refreshRoster(selectedEvent);

        } catch (StudentNotFoundException ex) {
            showAlert(ex.getMessage());
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Action Failed");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}