package net.endy.server.model.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IModelData<T> {
    
    public abstract void setPrepareStatementElement(int idx, T object, PreparedStatement statement) throws SQLException;
    
}
