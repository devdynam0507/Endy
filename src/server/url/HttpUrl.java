package server.url;

import java.util.HashMap;
import java.util.Map;

import server.page.AbstractPage;

public class HttpUrl {
    
    private static Map<String, Class<? super AbstractPage>> urls = new HashMap<>();
    
    /**
        Url 등록
    */
    public static void register(String referrer, Class<? super AbstractPage> page) {
        
    }
    
}
