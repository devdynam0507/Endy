package server.handler;

import java.net.Socket;

import server.response.HttpResponse;
import server.response.IHttpResponse;

public class HttpClientConnectHandler extends EndyHttpAsyncEvent<Socket> {
    
    public static HttpClientConnectHandler instance(Socket client) { return new HttpClientConnectHandler(client); }
    
    private HttpClientConnectHandler(Socket client) {
        super(client);
    }
    
    public void handle(Socket client) throws Exception {
        IHttpResponse res = new HttpResponse();
        res.response(client);
    }
    
}
