package server.model.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class DateWrapper implements IModelData<Date> {
    
    @Override
    public void setPrepareStatementElement(int idx, Date object, PreparedStatement statement) throws SQLException {
        statement.setDate(idx, new java.sql.Date(object.getTime()));
    }
    
}
