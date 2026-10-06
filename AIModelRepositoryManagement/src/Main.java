import exceptions.DuplicateModelException;
import exceptions.InvalidModelException;
import exceptions.ModelNotFoundException;
import exceptions.UserNotFoundException;
import model.AIModel;
import model.Admin;
import model.Developer;
import model.ModelStatus;
import model.ModelType;
import model.ModelVersion;
import model.User;
import service.ModelService;
import service.ReportService;
import service.UserService;
import service.VersionService;
import utils.DateUtil;
import utils.InputValidator;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static UserService userService = new UserService();
    private static ModelService modelService = new ModelService();
    private static VersionService versionService = new VersionService();
    private static ReportService reportService = new ReportService(userService, modelService, versionService);
    private static Scanner sc = new Scanner(System.in);
    private static User loggedInUser = null;

    public static void main(String[] args) {
        addSampleData();
        
        while (true) {
            System.out.println("========================================");
            System.out.println("     AI MODEL REPOSITORY MANAGEMENT     ");
            System.out.println("========================================");
            System.out.println("1. Admin Login");
            System.out.println("2. Developer Login");
            System.out.println("3. Register Developer");
            System.out.println("4. View Repository");
            System.out.println("5. Search AI Model");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1":
                    loginUser("Admin");
                    if (loggedInUser instanceof Admin) adminMenu();
                    break;
                case "2":
                    loginUser("Developer");
                    if (loggedInUser instanceof Developer) developerMenu();
                    break;
                case "3":
                    registerDeveloper();
                    break;
                case "4":
                    viewAllModels();
                    break;
                case "5":
                    searchModel();
                    break;
                case "6":
                    System.out.println("Exiting... Data saved.");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error: Invalid choice.");
            }
        }
    }

    private static void loginUser(String expectedRole) {
        System.out.println("\n--- " + expectedRole + " Login ---");
        String username = InputValidator.getNonEmptyString(sc, "Enter Username: ");
        String password = InputValidator.getNonEmptyString(sc, "Enter Password: ");
        
        try {
            User user = userService.login(username, password);
            if ((expectedRole.equals("Admin") && user instanceof Admin) || 
                (expectedRole.equals("Developer") && user instanceof Developer)) {
                loggedInUser = user;
                System.out.println("✓ Login successful.");
                user.displayDetails();
            } else {
                System.out.println("✗ Error: Role mismatch.");
                loggedInUser = null;
            }
        } catch (UserNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    private static void registerDeveloper() {
        System.out.println("\n--- Register Developer ---");
        String id = InputValidator.getNonEmptyString(sc, "Enter Developer ID: ");
        String username = InputValidator.getNonEmptyString(sc, "Enter Username: ");
        String password = InputValidator.getNonEmptyString(sc, "Enter Password: ");
        userService.registerDeveloper(id, username, password);
    }

    private static void viewAllModels() {
        System.out.println("\n--- All Models ---");
        List<AIModel> models = modelService.viewModels();
        if (models.isEmpty()) {
            System.out.println("No models available.");
            return;
        }
        displayModelsTable(models);
    }

    private static void displayModelsTable(List<AIModel> models) {
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-25s | %-15s | %-15s | %-6s | %-10s%n", 
                "Model ID", "Name", "Framework", "Type", "Acc(%)", "Status");
        System.out.println("--------------------------------------------------------------------------------------");
        for (AIModel m : models) m.displaySummary();
        System.out.println("--------------------------------------------------------------------------------------");
    }

    private static void searchModel() {
        String keyword = InputValidator.getNonEmptyString(sc, "Enter search keyword (name, framework, id, etc.): ");
        List<AIModel> results = modelService.searchModel(keyword);
        if (results.isEmpty()) {
            System.out.println("✗ No models found for keyword: " + keyword);
        } else {
            System.out.println("✓ Search Results:");
            displayModelsTable(results);
        }
    }

    // --- ADMIN MENU ---
    private static void adminMenu() {
        while (loggedInUser != null) {
            System.out.println("\n========================================");
            System.out.println("             ADMIN MENU                 ");
            System.out.println("========================================");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Delete User");
            System.out.println("4. Add AI Model");
            System.out.println("5. View AI Models");
            System.out.println("6. Update AI Model");
            System.out.println("7. Delete AI Model");
            System.out.println("8. Manage Model Versions");
            System.out.println("9. Generate Reports");
            System.out.println("10. Logout");
            System.out.print("Enter your choice: ");
            
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1": registerDeveloper(); break;
                    case "2": userService.viewUsers(); break;
                    case "3": 
                        String id = InputValidator.getNonEmptyString(sc, "Enter User ID to delete: ");
                        userService.deleteUser(id); 
                        break;
                    case "4": addModel(loggedInUser.getUserId()); break;
                    case "5": viewAllModels(); break;
                    case "6": updateModel(true); break;
                    case "7": deleteModel(); break;
                    case "8": manageModelVersions(true); break;
                    case "9": reportService.generateReport(); break;
                    case "10": 
                        loggedInUser = null; 
                        System.out.println("✓ Logged out.");
                        break;
                    default: System.out.println("Error: Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }
    }

    // --- DEVELOPER MENU ---
    private static void developerMenu() {
        while (loggedInUser != null) {
            System.out.println("\n========================================");
            System.out.println("           DEVELOPER MENU               ");
            System.out.println("========================================");
            System.out.println("1. Add AI Model");
            System.out.println("2. View My Models");
            System.out.println("3. Search AI Model");
            System.out.println("4. Update My Model");
            System.out.println("5. Add Model Version");
            System.out.println("6. View Version History");
            System.out.println("7. View Model Details");
            System.out.println("8. Logout");
            System.out.print("Enter your choice: ");
            
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1": addModel(loggedInUser.getUserId()); break;
                    case "2": 
                        List<AIModel> myModels = modelService.viewModelsByDeveloper(loggedInUser.getUserId());
                        if (myModels.isEmpty()) System.out.println("You have not added any models.");
                        else displayModelsTable(myModels);
                        break;
                    case "3": searchModel(); break;
                    case "4": updateModel(false); break;
                    case "5": addModelVersion(false); break;
                    case "6": manageModelVersions(false); break;
                    case "7": 
                        String id = InputValidator.getNonEmptyString(sc, "Enter Model ID: ");
                        AIModel m = modelService.getModelById(id);
                        System.out.println("Model Name: " + m.getModelName() + " | Type: " + m.getModelType());
                        System.out.println("Desc: " + m.getDescription());
                        break;
                    case "8": 
                        loggedInUser = null; 
                        System.out.println("✓ Logged out.");
                        break;
                    default: System.out.println("Error: Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("✗ Error: " + e.getMessage());
            }
        }
    }

    private static void addModel(String devId) throws DuplicateModelException, InvalidModelException {
        System.out.println("\n--- Add AI Model ---");
        String id = InputValidator.getNonEmptyString(sc, "Model ID: ");
        String name = InputValidator.getNonEmptyString(sc, "Model Name: ");
        String desc = InputValidator.getNonEmptyString(sc, "Description: ");
        
        System.out.println("Select Model Type: (1) ML (2) DL (3) CV (4) NLP");
        String t = sc.nextLine();
        ModelType type = ModelType.MACHINE_LEARNING;
        if (t.equals("2")) type = ModelType.DEEP_LEARNING;
        else if (t.equals("3")) type = ModelType.COMPUTER_VISION;
        else if (t.equals("4")) type = ModelType.NATURAL_LANGUAGE_PROCESSING;
        
        String frame = InputValidator.getNonEmptyString(sc, "Framework (e.g., TensorFlow): ");
        String lang = InputValidator.getNonEmptyString(sc, "Language (e.g., Python): ");
        String dataset = InputValidator.getNonEmptyString(sc, "Dataset Name: ");
        double acc = InputValidator.getValidAccuracy(sc, "Accuracy (0-100): ");
        String ver = InputValidator.getNonEmptyString(sc, "Version (e.g., 1.0): ");
        
        AIModel m = new AIModel(id, name, desc, type, frame, lang, dataset, acc, devId, ver, DateUtil.getCurrentDate(), ModelStatus.ACTIVE);
        modelService.addModel(m);
        System.out.println("✓ Model added successfully.");
        
        ModelVersion initialVer = new ModelVersion("V-" + id + "-" + ver, ver, id, acc, "Initial Version", DateUtil.getCurrentDate(), ModelStatus.ACTIVE);
        versionService.addVersion(initialVer);
    }

    private static void updateModel(boolean isAdmin) throws ModelNotFoundException, InvalidModelException {
        String id = InputValidator.getNonEmptyString(sc, "Enter Model ID to update: ");
        AIModel m = modelService.getModelById(id);
        
        if (!isAdmin && !m.getDeveloperId().equals(loggedInUser.getUserId())) {
            System.out.println("✗ Error: You can only update your own models.");
            return;
        }
        
        double acc = InputValidator.getValidAccuracy(sc, "New Accuracy (0-100): ");
        System.out.println("Select Status: (1) ACTIVE (2) DEPRECATED (3) ARCHIVED");
        String s = sc.nextLine();
        ModelStatus status = ModelStatus.ACTIVE;
        if (s.equals("2")) status = ModelStatus.DEPRECATED;
        else if (s.equals("3")) status = ModelStatus.ARCHIVED;
        
        m.setAccuracy(acc);
        m.setStatus(status);
        modelService.updateModel(m);
        System.out.println("✓ Model updated successfully.");
    }

    private static void deleteModel() {
        String id = InputValidator.getNonEmptyString(sc, "Enter Model ID to delete: ");
        try {
            modelService.deleteModel(id);
            System.out.println("✓ Model deleted successfully.");
        } catch (ModelNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    private static void manageModelVersions(boolean isAdmin) {
        String id = InputValidator.getNonEmptyString(sc, "Enter Model ID: ");
        try {
            AIModel m = modelService.getModelById(id);
            if (!isAdmin && !m.getDeveloperId().equals(loggedInUser.getUserId())) {
                System.out.println("✗ Error: You can only view/manage versions for your own models.");
                return;
            }
            List<ModelVersion> versions = versionService.getVersionsForModel(id);
            System.out.println("--- Version History for " + m.getModelName() + " ---");
            for (ModelVersion v : versions) {
                v.displayVersion();
            }
        } catch (ModelNotFoundException e) {
            System.out.println("✗ " + e.getMessage());
        }
    }

    private static void addModelVersion(boolean isAdmin) {
        String id = InputValidator.getNonEmptyString(sc, "Enter Model ID to add version: ");
        try {
            AIModel m = modelService.getModelById(id);
            if (!isAdmin && !m.getDeveloperId().equals(loggedInUser.getUserId())) {
                System.out.println("✗ Error: You can only add versions to your own models.");
                return;
            }
            String ver = InputValidator.getNonEmptyString(sc, "New Version (e.g., 1.1): ");
            double acc = InputValidator.getValidAccuracy(sc, "Accuracy (0-100): ");
            String changes = InputValidator.getNonEmptyString(sc, "Changes: ");
            
            ModelVersion newVer = new ModelVersion("V-" + id + "-" + ver, ver, id, acc, changes, DateUtil.getCurrentDate(), ModelStatus.ACTIVE);
            versionService.addVersion(newVer);
            
            m.setVersion(ver);
            m.setAccuracy(acc);
            modelService.updateModel(m);
            System.out.println("✓ Version added and model updated successfully.");
        } catch (Exception e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    private static void addSampleData() {
        if (modelService.viewModels().isEmpty()) {
            try {
                userService.registerDeveloper("DEV001", "devuser", "dev123");
                AIModel sample = new AIModel("AI001", "Image Classification Model", "Classifies standard images", 
                        ModelType.COMPUTER_VISION, "TensorFlow", "Python", "CIFAR-10", 94.5, "DEV001", "1.0", DateUtil.getCurrentDate(), ModelStatus.ACTIVE);
                modelService.addModel(sample);
                versionService.addVersion(new ModelVersion("V-AI001-1.0", "1.0", "AI001", 94.5, "Initial", DateUtil.getCurrentDate(), ModelStatus.ACTIVE));
            } catch (Exception e) {
                // Ignore errors during sample data creation
            }
        }
    }
}
