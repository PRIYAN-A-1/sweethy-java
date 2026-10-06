package utils;

import model.AIModel;
import model.ModelStatus;
import model.ModelType;
import model.ModelVersion;
import model.User;
import model.Admin;
import model.Developer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {
    private static final String DATA_DIR = "data/";
    private static final String USERS_FILE = DATA_DIR + "users.txt";
    private static final String MODELS_FILE = DATA_DIR + "models.txt";
    private static final String VERSIONS_FILE = DATA_DIR + "versions.txt";

    static {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static void saveUsers(Map<String, User> users) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users.values()) {
                bw.write(user.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users data: " + e.getMessage());
        }
    }

    public static Map<String, User> loadUsers() {
        Map<String, User> users = new HashMap<>();
        File file = new File(USERS_FILE);
        if (!file.exists()) return users;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String role = parts[0];
                    String id = parts[1];
                    String username = parts[2];
                    String password = parts[3];
                    if (role.equals("ADMIN")) {
                        users.put(id, new Admin(id, username, password));
                    } else if (role.equals("DEVELOPER")) {
                        users.put(id, new Developer(id, username, password));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading users data: " + e.getMessage());
        }
        return users;
    }

    public static void saveModels(List<AIModel> models) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(MODELS_FILE))) {
            for (AIModel model : models) {
                bw.write(model.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving models data: " + e.getMessage());
        }
    }

    public static List<AIModel> loadModels() {
        List<AIModel> models = new ArrayList<>();
        File file = new File(MODELS_FILE);
        if (!file.exists()) return models;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 12) {
                    models.add(new AIModel(parts[0], parts[1], parts[2], ModelType.valueOf(parts[3]), 
                                           parts[4], parts[5], parts[6], Double.parseDouble(parts[7]), 
                                           parts[8], parts[9], parts[10], ModelStatus.valueOf(parts[11])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading models data: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Data format error in models file.");
        }
        return models;
    }

    public static void saveVersions(List<ModelVersion> versions) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(VERSIONS_FILE))) {
            for (ModelVersion version : versions) {
                bw.write(version.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving versions data: " + e.getMessage());
        }
    }

    public static List<ModelVersion> loadVersions() {
        List<ModelVersion> versions = new ArrayList<>();
        File file = new File(VERSIONS_FILE);
        if (!file.exists()) return versions;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    versions.add(new ModelVersion(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]), 
                                                  parts[4], parts[5], ModelStatus.valueOf(parts[6])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading versions data: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Data format error in versions file.");
        }
        return versions;
    }
}
