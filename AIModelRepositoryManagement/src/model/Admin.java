package model;

public class Admin extends User {
    private static final long serialVersionUID = 1L;
    
    public Admin(String userId, String username, String password) {
        super(userId, username, password);
    }
    
    @Override
    public void displayDetails() {
        System.out.println("Admin User: " + getUsername() + " (ID: " + getUserId() + ")");
    }
    
    @Override
    public String toString() {
        return "ADMIN," + super.toString();
    }
}
