package net.endy.register;

import java.util.HashMap;
import java.util.Map;

public abstract class Register<T> {
    
    private Map<T, Object> registeredModuls = new HashMap<>();
    
    public Register<T> register(T data, Object object) {
        registeredModuls.put(data, object);
        
        return this;
    }
    
    public <K> Object get(T data) { return (K) registeredModuls.get(data); }
    
}
