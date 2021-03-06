package net.endy.server.request;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class HttpRequestBody {
    
    private String body;
    private String[] splitedBodyStr;
    
    public HttpRequestBody(String body) {
        this.body = body;
        
        if(body != null) {
            this.splitedBodyStr = body.split("&");            
        }
    }    
    
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        
        for(String element : splitedBodyStr) {
            String[] splitedElement = element.split("=");
            
            map.put(splitedElement[0], splitedElement[1]);
        }
        
        return map;
    }
    
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        
        json.putAll(toMap());
        
        return json;
    }
    
    @Override
    public String toString() { return body;}

}
