package net.endy.register;

import net.endy.server.model.AbstractModel;

public class ModelRegister extends Register<String, Class<? extends AbstractModel>> {
    
    private static ModelRegister modelRegister;
    
    public static synchronized ModelRegister getRegister() {
        if(modelRegister == null) {
            modelRegister = new ModelRegister();
        }
        
        return modelRegister;
    }
    
}
