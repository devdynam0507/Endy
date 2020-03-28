package server.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import server.response.IHttpResponse;

public class HttpResponse implements IHttpResponse {
    
    @Override
    public void response(Socket client) {
        try {
            PrintWriter out = new PrintWriter(client.getOutputStream());
            String[] html = new String[] { "<H1> Endy <H1> <hr> So.. simple framework!" };
            
            /* Headers */
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html; charset=utf-8");
            out.println("Server: Endy");
            out.println("");

            /* Body */
            for(String element : html) {
                out.println(element);
            }
            
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
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
        
    }
    
}
