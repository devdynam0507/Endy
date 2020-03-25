package server.response;

import java.net.Socket;

public interface IHttpResponse {
    
    public void response(String pageName, Socket client); // server response method
    
}
