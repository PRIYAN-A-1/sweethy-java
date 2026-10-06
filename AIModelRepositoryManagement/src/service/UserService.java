package service;

import exceptions.UserNotFoundException;
import model.User;
import model.Admin;
import model.Developer;
import utils.FileManager;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private Map<String, User> users;

    public UserService() {
        users = FileManager.loadUsers();
        if (users.isEmpty()) {
            users.put("ADM001", new Admin("ADM001", "admin", "admin123"));
            FileManager.saveUsers(users);
        }
    }

    public User login(String username, String password) throws UserNotFoundException {
        for (User user : users.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserNotFoundException("Invalid credentials. Please try again.");
    }

    public void registerDeveloper(String userId, String username, String password) {
        if (users.containsKey(userId)) {
            System.out.println("User ID already exists.");
            return;
        }
        Developer dev = new Developer(userId, username, password);
        users.put(userId, dev);
        FileManager.saveUsers(users);
        System.out.println("✓ Developer registered successfully.");
    }

    public void viewUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }
        System.out.println("========================================");
        System.out.printf("%-10s | %-15s | %-10s%n", "User ID", "Username", "Role");
        System.out.println("----------------------------------------");
        for (User user : users.values()) {
            String role = (user instanceof Admin) ? "Admin" : "Developer";
            System.out.printf("%-10s | %-15s | %-10s%n", user.getUserId(), user.getUsername(), role);
        }
        System.out.println("========================================");
    }

    public void deleteUser(String userId) throws UserNotFoundException {
        if (!users.containsKey(userId)) {
            throw new UserNotFoundException("User ID not found.");
        }
        if (users.get(userId) instanceof Admin) {
            System.out.println("Error: Cannot delete an Admin user.");
            return;
        }
        users.remove(userId);
        FileManager.saveUsers(users);
        System.out.println("✓ User deleted successfully.");
    }
    
    public Map<String, User> getUsers() {
        return users;
    }
}
