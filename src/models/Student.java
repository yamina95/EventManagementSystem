package models;

import interfaces.Registerable;

public class Student extends Person implements Registerable {
    private String studentId;
    private String major;

    public Student(String id, String name, String email, String studentId, String major) {
        super(id, name, email);

        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be empty.");
        }
        if (major == null || major.trim().isEmpty()) {
            throw new IllegalArgumentException("Major cannot be empty.");
        }

        this.studentId = studentId;
        this.major = major;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public void register() {
        System.out.println(name + " performed a register action.");
    }

    @Override
    public void unregister() {
        System.out.println(name + " performed an unregister action.");
    }

    @Override
    public String getDetails() {
        return "Student{id='" + id + "', name='" + name + "', email='" + email
                + "', studentId='" + studentId + "', major='" + major + "'}";
    }

    @Override
    public String toString() {
        return name + " (" + studentId + ")";
    }
}