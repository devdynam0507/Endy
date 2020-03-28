package server.response;

import java.net.Socket;

public interface IHttpResponse {
    
    public void response(Socket client); // server response method
    
}
