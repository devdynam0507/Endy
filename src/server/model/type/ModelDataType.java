package server.model.type;

import server.model.data.*;

public enum ModelDataType {
    
    INTEGER(new Int()), VARCHAR(new VarChar()), DATE(new DateWrapper());
    
    private IModelData data;
    
    private ModelDataType(IModelData data) {
        this.data = data;
    }
    
    public IModelData convertor() { return data; }
    
}
