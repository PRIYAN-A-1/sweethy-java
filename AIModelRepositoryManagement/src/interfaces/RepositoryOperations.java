package interfaces;

import model.AIModel;
import exceptions.ModelNotFoundException;
import exceptions.DuplicateModelException;
import exceptions.InvalidModelException;
import java.util.List;

public interface RepositoryOperations {
    void addModel(AIModel model) throws DuplicateModelException, InvalidModelException;
    List<AIModel> viewModels();
    List<AIModel> searchModel(String keyword);
    void updateModel(AIModel model) throws ModelNotFoundException, InvalidModelException;
    void deleteModel(String modelId) throws ModelNotFoundException;
}
