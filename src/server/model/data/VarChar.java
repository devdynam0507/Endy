package server.model.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VarChar implements IModelData<String> {
    
    @Override
    public void setPrepareStatementElement(int idx, String object, PreparedStatement statement) throws SQLException {
        statement.setString(idx, object);
    }
    
}
