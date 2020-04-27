package net.endy.register;

import java.util.Collection;

import net.endy.server.module.AbstractModule;

public class ModuleRegister extends Register<String, AbstractModule> {
    
    private static ModuleRegister moduleRegister;
    
    public static synchronized ModuleRegister getRegister() {
        if(moduleRegister == null) {
            moduleRegister = new ModuleRegister();
        }
        
        return moduleRegister;
    }
    
    public void load() {
        Collection<AbstractModule> collections = values();
        
        for(AbstractModule module : collections) {
            module.load();
        } 
    }
    
}
