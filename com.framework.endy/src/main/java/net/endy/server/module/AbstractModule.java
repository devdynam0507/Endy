package net.endy.server.module;

import net.endy.register.ModelRegister;
import net.endy.register.PageRegister;
import net.endy.server.model.AbstractModel;
import net.endy.server.page.AbstractPage;

public abstract class AbstractModule {

    private String version;
    
    public AbstractModule(String version) {
        
    }
        
    public void registerModel(String model, Class<? extends AbstractModel> modelClass) {
        ModelRegister.getRegister().register(model, modelClass);
    }
    
    public void registerPage(String ref, Class<? extends AbstractPage> viewClass) {
        PageRegister.getRegister().register(ref, viewClass);
    }
    
    abstract public void load();
    
}
