package net.endy.server.url;

import java.util.HashMap;
import java.util.Map;

import net.endy.server.page.AbstractPage;

public class HttpUrl {
    
    private static Map<String, Class<AbstractPage>> urls = new HashMap<>();
    
    /**
        Url register
    */
    public static void register(String referrer, Class<AbstractPage> page) {
        urls.put(referrer, page);
    }
    
}
