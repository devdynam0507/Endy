package net.endy.server.response;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import net.endy.server.html.Html;

public class HttpResponse {
    
    public enum Type {
        
        Render((out, html, context) -> {
            out.println("Content-Type: text/html; charset=utf-8");
            out.println("");
            out.println(html.toHtml());            
        }),
        Restful((out, html, context) -> {
            out.println("Content-Type: application/json; charset=utf-8");
            out.println("");
            out.println(context.toJson());
        });
        
        private HttpResponseCallback callbackObject;
        
        Type(HttpResponseCallback callback) {
            this.callbackObject = callback;
        }
        
        public void callback(PrintWriter out, Html html, ResponseContext context) {
            callbackObject.callback(out, html, context);
        }
        
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
