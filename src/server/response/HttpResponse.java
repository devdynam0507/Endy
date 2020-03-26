package server.response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.endy.html.HTMLStorage;

import server.response.IHttpResponse;

public class HttpResponse implements IHttpResponse {
    
    @Override
    public void response(String pageName, Socket client) {
        try {
            PrintWriter out = new PrintWriter(client.getOutputStream());
            String[] html = HTMLStorage.getPageHtml(pageName);

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
    
}
