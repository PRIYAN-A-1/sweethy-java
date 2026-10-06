package service;

import model.AIModel;
import model.ModelType;
import model.ModelVersion;
import model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    private UserService userService;
    private ModelService modelService;
    private VersionService versionService;

    public ReportService(UserService userService, ModelService modelService, VersionService versionService) {
        this.userService = userService;
        this.modelService = modelService;
        this.versionService = versionService;
    }

    public void generateReport() {
        System.out.println("\n========================================");
        System.out.println("          REPOSITORY REPORT");
        System.out.println("========================================");
        
        List<AIModel> models = modelService.viewModels();
        Map<String, User> users = userService.getUsers();
        List<ModelVersion> versions = versionService.getAllVersions();
        
        System.out.println("### Repository Summary");
        System.out.println("Total Users      : " + users.size());
        System.out.println("Total AI Models  : " + models.size());
        System.out.println("Total Versions   : " + versions.size());
        
        long activeCount = models.stream().filter(m -> m.getStatus().name().equals("ACTIVE")).count();
        long archivedCount = models.stream().filter(m -> m.getStatus().name().equals("ARCHIVED")).count();
        
        System.out.println("Active Models    : " + activeCount);
        System.out.println("Archived Models  : " + archivedCount);
        System.out.println();
        
        System.out.println("### Framework Report");
        Map<String, Integer> frameworkCounts = new HashMap<>();
        for (AIModel m : models) {
            frameworkCounts.put(m.getFramework(), frameworkCounts.getOrDefault(m.getFramework(), 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : frameworkCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " Models");
        }
        System.out.println();
        
        System.out.println("### Model Type Report");
        Map<ModelType, Integer> typeCounts = new HashMap<>();
        for (AIModel m : models) {
            typeCounts.put(m.getModelType(), typeCounts.getOrDefault(m.getModelType(), 0) + 1);
        }
        for (Map.Entry<ModelType, Integer> entry : typeCounts.entrySet()) {
            System.out.println(entry.getKey().getDisplayName() + ": " + entry.getValue());
        }
        System.out.println("========================================\n");
    }
}
