package net.endy.server.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import net.endy.server.html.Html;

public class HttpResponse {
    
    public enum Type {
        Render, Restful
    }
    
    public static class ResponseContext {
        
        private Map<String, String> context;
        
        public ResponseContext() {
            context = new HashMap<>();
        }
        
        public ResponseContext setContext(String contextName, String contextObject) {
            context.put(contextName, contextObject);
            
            return this;
        }
        
        public String getContext(String contextName) {
            return context.get(contextName);
        }
        
        public String toJson() {
            JSONObject object = new JSONObject();
            
            for(Map.Entry<String, String> entry : context.entrySet()) {
                object.put(entry.getKey(), entry.getValue());
            }
            
            return object.toJSONString();
        }
        
    }
    
}
