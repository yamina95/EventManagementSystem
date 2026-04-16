package models;

public class Admin extends Person {
    private String accessLevel;
    private String department;
    private boolean canApproveEvents;

    public Admin(String id, String name, String email,
                 String accessLevel, String department, boolean canApproveEvents) {
        super(id, name, email);
        this.accessLevel = accessLevel;
        this.department = department;
        this.canApproveEvents = canApproveEvents;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public String getDepartment() {
        return department;
    }

    public boolean canApproveEvents() {
        return canApproveEvents;
    }

    public void approveEvent(Event event) {
        if (!canApproveEvents) {
            System.out.println("Admin " + name + " is not allowed to approve events.");
            return;
        }
        System.out.println("Admin " + name + " approved event: " + event.getTitle());
    }

    public void manageUsers() {
        System.out.println("Admin " + name + " is managing users.");
    }

    @Override
    public String getDetails() {
        return "Admin{id='" + id + "', name='" + name + "', email='" + email
                + "', accessLevel='" + accessLevel + "', department='" + department
                + "', canApproveEvents=" + canApproveEvents + "}";
    }

    @Override
    public String toString() {
        return name + " (" + accessLevel + ")";
    }
}