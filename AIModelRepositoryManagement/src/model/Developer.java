package model;

public class Developer extends User {
    private static final long serialVersionUID = 1L;
    
    public Developer(String userId, String username, String password) {
        super(userId, username, password);
    }
    
    @Override
    public void displayDetails() {
        System.out.println("Developer User: " + getUsername() + " (ID: " + getUserId() + ")");
    }
    
    @Override
    public String toString() {
        return "DEVELOPER," + super.toString();
    }
}
