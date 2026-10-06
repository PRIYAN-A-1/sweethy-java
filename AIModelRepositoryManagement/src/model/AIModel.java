package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AIModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String modelId;
    private String modelName;
    private String description;
    private ModelType modelType;
    private String framework;
    private String language;
    private String datasetName;
    private double accuracy;
    private String developerId;
    private String version;
    private String createdDate;
    private ModelStatus status;

    public AIModel(String modelId, String modelName, String description, ModelType modelType, String framework, 
                   String language, String datasetName, double accuracy, String developerId, String version, 
                   String createdDate, ModelStatus status) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.description = description;
        this.modelType = modelType;
        this.framework = framework;
        this.language = language;
        this.datasetName = datasetName;
        this.accuracy = accuracy;
        this.developerId = developerId;
        this.version = version;
        this.createdDate = createdDate;
        this.status = status;
    }

    public String getModelId() { return modelId; }
    public void setModelId(String modelId) { this.modelId = modelId; }
    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public ModelType getModelType() { return modelType; }
    public void setModelType(ModelType modelType) { this.modelType = modelType; }
    public String getFramework() { return framework; }
    public void setFramework(String framework) { this.framework = framework; }
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public String getDatasetName() { return datasetName; }
    public void setDatasetName(String datasetName) { this.datasetName = datasetName; }
    public double getAccuracy() { return accuracy; }
    public void setAccuracy(double accuracy) { this.accuracy = accuracy; }
    public String getDeveloperId() { return developerId; }
    public void setDeveloperId(String developerId) { this.developerId = developerId; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getCreatedDate() { return createdDate; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }
    public ModelStatus getStatus() { return status; }
    public void setStatus(ModelStatus status) { this.status = status; }

    @Override
    public String toString() {
        return modelId + "," + modelName + "," + description + "," + modelType + "," + framework + "," + 
               language + "," + datasetName + "," + accuracy + "," + developerId + "," + version + "," + 
               createdDate + "," + status;
    }
    
    public void displaySummary() {
        System.out.printf("%-10s | %-25s | %-15s | %-15s | %-6.2f%% | %-10s%n", 
                modelId, modelName, framework, modelType.name(), accuracy, status.name());
    }
}
