package net.endy.server.response;

import java.net.Socket;

import net.endy.server.html.Html;

public interface IHttpResponse {
    
    public void response(Socket client, Html html); // server response method
    
}
