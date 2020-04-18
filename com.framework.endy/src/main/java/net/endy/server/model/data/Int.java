package net.endy.server.model.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Int implements IModelData<Integer> {
    
    @Override
    public void setPrepareStatementElement(int idx, Integer object, PreparedStatement statement) throws SQLException {
        statement.setInt(idx, object);
    }
    
}
