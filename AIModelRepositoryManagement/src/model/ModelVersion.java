package model;

import java.io.Serializable;

public class ModelVersion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String versionId;
    private String versionNumber;
    private String modelId;
    private double accuracy;
    private String changes;
    private String createdDate;
    private ModelStatus status;

    public ModelVersion(String versionId, String versionNumber, String modelId, double accuracy, String changes, String createdDate, ModelStatus status) {
        this.versionId = versionId;
        this.versionNumber = versionNumber;
        this.modelId = modelId;
        this.accuracy = accuracy;
        this.changes = changes;
        this.createdDate = createdDate;
        this.status = status;
    }

    public String getVersionId() { return versionId; }
    public void setVersionId(String versionId) { this.versionId = versionId; }
    public String getVersionNumber() { return versionNumber; }
    public void setVersionNumber(String versionNumber) { this.versionNumber = versionNumber; }
    public String getModelId() { return modelId; }
    public void setModelId(String modelId) { this.modelId = modelId; }
    public double getAccuracy() { return accuracy; }
    public void setAccuracy(double accuracy) { this.accuracy = accuracy; }
    public String getChanges() { return changes; }
    public void setChanges(String changes) { this.changes = changes; }
    public String getCreatedDate() { return createdDate; }
    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }
    public ModelStatus getStatus() { return status; }
    public void setStatus(ModelStatus status) { this.status = status; }

    @Override
    public String toString() {
        return versionId + "," + versionNumber + "," + modelId + "," + accuracy + "," + changes + "," + createdDate + "," + status;
    }
    
    public void displayVersion() {
        System.out.printf("Version: %-5s | Accuracy: %-5.2f%% | Date: %-12s | Status: %-10s | Changes: %s%n",
                versionNumber, accuracy, createdDate, status, changes);
    }
}
