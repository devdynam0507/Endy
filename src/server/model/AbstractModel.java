package server.model;

import server.model.ModelLoader.ModelInfo;

public abstract class AbstractModel { 

    public ModelInfo getModelInfo(Class<? extends AbstractModel> modelClass) {
        return ModelLoader.getModelInfo(modelClass);
    }
    
}
