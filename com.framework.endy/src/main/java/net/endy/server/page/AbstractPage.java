package net.endy.server.page;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import net.endy.server.response.IHttpResponse;
import net.endy.server.response.HttpResponse;
import net.endy.server.response.HttpResponse.ResponseContext;
import net.endy.server.html.Html;

public abstract class AbstractPage implements IHttpResponse {
    
    protected ResponseContext context = new ResponseContext();
    private int statusCode = 200;
    
    @Override
    public void response(Socket client, Html html, HttpResponse.Type type) {
        try {
            PrintWriter out = new PrintWriter(client.getOutputStream());
            
            out.println("HTTP/1.1 " + statusCode + " OK");
            out.println("Server: Endy");
            
            type.callback(out, html, context);
            
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    protected void setStatusCode(int statusCode) { this.statusCode = statusCode; }
    
}
