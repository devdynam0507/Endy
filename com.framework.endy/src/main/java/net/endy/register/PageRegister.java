package net.endy.register;

import java.util.HashMap;
import java.util.Map;

import net.endy.server.page.AbstractPage;

public class PageRegister extends Register<AbstractPage> {
    
    public static Map<String, Class<? extends AbstractPage>> registeredPage = new HashMap<>();
    
    
    
}
