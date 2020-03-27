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
    
    public String getCookie(String cookieId) {
        String cookie = cookies.get(cookieId);
        
        if(cookie == null) {
            //TODO throws cookie not found exception
        }
        
        return cookie;
    }
    
    @Override
    public String toString() { return cookies.toString(); }
    
}
