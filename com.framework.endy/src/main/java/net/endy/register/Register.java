package net.endy.register;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class Register<K, V> {
    
    private Map<K, V> registeredModuls = new HashMap<>();
    
    public Register<K, V> register(K key, V value) {
        registeredModuls.put(key, value);
        
        return this;
    }
    
    public V get(K key) { return (V) registeredModuls.get(key); }
    
    protected Collection<V> values() { return registeredModuls.values(); }
    
}
