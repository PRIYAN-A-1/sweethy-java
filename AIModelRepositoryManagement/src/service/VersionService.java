package service;

import model.ModelVersion;
import utils.FileManager;

import java.util.ArrayList;
import java.util.List;

public class VersionService {
    private List<ModelVersion> versions;

    public VersionService() {
        versions = FileManager.loadVersions();
    }

    public void addVersion(ModelVersion version) {
        versions.add(version);
        FileManager.saveVersions(versions);
    }

    public List<ModelVersion> getVersionsForModel(String modelId) {
        List<ModelVersion> result = new ArrayList<>();
        for (ModelVersion v : versions) {
            if (v.getModelId().equals(modelId)) {
                result.add(v);
            }
        }
        return result;
    }
    
    public List<ModelVersion> getAllVersions() {
        return versions;
    }
}
