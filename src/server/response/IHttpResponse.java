package server.response;

import java.net.Socket;

import server.html.Html;

public interface IHttpResponse {
    
    public void response(Socket client, Html html); // server response method
    
}
