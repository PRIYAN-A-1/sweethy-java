package service;

import exceptions.DuplicateModelException;
import exceptions.InvalidModelException;
import exceptions.ModelNotFoundException;
import interfaces.RepositoryOperations;
import model.AIModel;
import utils.FileManager;

import java.util.ArrayList;
import java.util.List;

public class ModelService implements RepositoryOperations {
    private List<AIModel> models;

    public ModelService() {
        models = FileManager.loadModels();
    }

    @Override
    public void addModel(AIModel model) throws DuplicateModelException, InvalidModelException {
        for (AIModel m : models) {
            if (m.getModelId().equals(model.getModelId())) {
                throw new DuplicateModelException("Model ID " + model.getModelId() + " already exists.");
            }
        }
        if (model.getModelName() == null || model.getModelName().trim().isEmpty()) {
            throw new InvalidModelException("Model name cannot be empty.");
        }
        models.add(model);
        saveChanges();
    }

    @Override
    public List<AIModel> viewModels() {
        return models;
    }

    public List<AIModel> viewModelsByDeveloper(String devId) {
        List<AIModel> devModels = new ArrayList<>();
        for (AIModel m : models) {
            if (m.getDeveloperId().equals(devId)) {
                devModels.add(m);
            }
        }
        return devModels;
    }

    @Override
    public List<AIModel> searchModel(String keyword) {
        List<AIModel> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();
        for (AIModel m : models) {
            if (m.getModelName().toLowerCase().contains(lowerKeyword) ||
                m.getFramework().toLowerCase().contains(lowerKeyword) ||
                m.getModelId().toLowerCase().contains(lowerKeyword) ||
                m.getModelType().name().toLowerCase().contains(lowerKeyword) ||
                m.getLanguage().toLowerCase().contains(lowerKeyword)) {
                results.add(m);
            }
        }
        return results;
    }

    @Override
    public void updateModel(AIModel model) throws ModelNotFoundException, InvalidModelException {
        boolean found = false;
        for (int i = 0; i < models.size(); i++) {
            if (models.get(i).getModelId().equals(model.getModelId())) {
                models.set(i, model);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new ModelNotFoundException("Model ID " + model.getModelId() + " not found.");
        }
        saveChanges();
    }

    @Override
    public void deleteModel(String modelId) throws ModelNotFoundException {
        boolean removed = models.removeIf(m -> m.getModelId().equals(modelId));
        if (!removed) {
            throw new ModelNotFoundException("Model ID " + modelId + " not found.");
        }
        saveChanges();
    }

    public AIModel getModelById(String modelId) throws ModelNotFoundException {
        for (AIModel m : models) {
            if (m.getModelId().equals(modelId)) {
                return m;
            }
        }
        throw new ModelNotFoundException("Model ID " + modelId + " not found.");
    }

    public void saveChanges() {
        FileManager.saveModels(models);
    }
}
