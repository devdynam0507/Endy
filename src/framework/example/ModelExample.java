package framework.example;

import server.model.AbstractModel;
import server.model.annotation.Columm;
import server.model.annotation.Model;
import server.model.type.ModelDataType;

@Model(db_name = "dev", table_name = "test")
public class ModelExample extends AbstractModel {

    @Columm(name = "name", type = ModelDataType.VARCHAR, length = 100) 
    public String name;
    
    @Columm(name = "age", type = ModelDataType.INTEGER, length = 0) 
    public int age;
    
    @Columm(name = "school", type = ModelDataType.VARCHAR, length = 100) 
    public String school;
    
}
