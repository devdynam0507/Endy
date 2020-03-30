package framework.example;

import server.model.annotation.Columm;
import server.model.annotation.Model;

@Model(name = "endy")
public class ModelExample {
 
    @Columm(name="name") String name;
    @Columm(name="age") int age;
    @Columm(name="school") String school;
    
}
