package net.endy.server.model;

import net.endy.server.model.ModelLoader.ModelInfo;

public abstract class AbstractModel { 

    public ModelInfo getModelInfo(Class<? extends AbstractModel> modelClass) {
        return ModelLoader.getModelInfo(modelClass);
    }
    
}
