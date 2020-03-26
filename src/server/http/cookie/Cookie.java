package server.http.cookie;

import java.util.HashMap;
import java.util.Map;

public class Cookie {
    
    private Map<String, String> cookies = new HashMap<>();
    
    public Cookie() { 
        cookies = new HashMap<>();
    }
    
    public void setCookie(String cookieId, String cookieContent) {
        cookies.put(cookieId, cookieContent);
    }
    
}
