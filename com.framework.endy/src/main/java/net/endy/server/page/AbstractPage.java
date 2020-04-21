package net.endy.server.page;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import net.endy.server.response.IHttpResponse;
import net.endy.server.response.HttpResponse;
import net.endy.server.response.HttpResponse.Type;
import net.endy.server.response.HttpResponse.ResponseContext;
import net.endy.server.html.Html;

public abstract class AbstractPage implements IHttpResponse {
    
    protected ResponseContext context = new ResponseContext();
    
    @Override
    public void response(Socket client, Html html, HttpResponse.Type type) {
        try {
            ResponseContext context = response();
            PrintWriter out = new PrintWriter(client.getOutputStream());
            
            handle(type, out, html);
                                
            out.close();            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    private void handle(HttpResponse.Type handleType, PrintWriter out, Html html) throws IOException {
        out.println("HTTP/1.1 200 OK");
        out.println("Server: Endy");
        
        switch(handleType) {
            case HttpResponse.Type.Render:
                handleRender(out, html);
                break;
            case HttpResponse.Type.Restful:
                handleRestful(out);
                break;
        }
    }
    
    private void handleRender(PrintWriter writer, Html html) throws IOException {
        out.println("Content-Type: text/html; charset=utf-8");
        out.println("");
        out.println(html.toHtml());
    }
    
    private void handleRestful(PrintWriter writer) throws IOException {
        out.println("Content-Type: application/json; charset=utf-8");
        out.println("");
        out.println(context.toJson());
    }
    
    //TODO Request Callback
    public abstract ResponseContext response();
    
}
