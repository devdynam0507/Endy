package net.endy.server.page;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import net.endy.server.response.IHttpResponse;
import net.endy.server.response.HttpResponse.ResponseContext;
import net.endy.server.html.Html;

public abstract class AbstractPage implements IHttpResponse {
    
    protected ResponseContext context = new ResponseContext();
    
    @Override
    public void response(Socket client, Html html) {
        try {
            ResponseContext context = response();
            PrintWriter out = new PrintWriter(client.getOutputStream());

            /* Headers */
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html; charset=utf-8");
            out.println("Server: Endy");
            out.println("");
            out.println(html.toHtml());
            
            out.close();            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    //TODO Request Callback
    public abstract ResponseContext response();
    
}
