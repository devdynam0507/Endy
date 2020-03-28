package server.page;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import server.response.IHttpResponse;
import server.response.HttpResponse.ResponseContext;

public abstract class AbstractPage implements IHttpResponse {
    
    private String html =  "<H1> Endy <H1> <hr> So.. simple framework!";
    protected ResponseContext context;
    
    public AbstractPage(String html) {
        this.html = html;
        this.context = new ResponseContext();
        //TODO html parse
    }
    
    @Override
    public void response(Socket client) {
        try {
            ResponseContext context = response();
        
            PrintWriter out = new PrintWriter(client.getOutputStream());

            /* Headers */
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html; charset=utf-8");
            out.println("Server: Endy");
            out.println("");
            out.println(html);
            
            out.close();            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    //TODO Request Callback
    public abstract ResponseContext response();
    
}
