package server.handler;

import java.net.Socket;

public class HttpClientConnectHandler extends EndyHttpAsyncEvent<Socket> {
    
    public static HttpClientConnectHandler instance(Socket client) { return new HttpClientConnectHandler(client); }
    
    private HttpClientConnectHandler(Socket client) {
        super(client);
    }
    
    public void handle(Socket client) {
        BufferedReader reader = new BufferedReader(new InputStream(client.getInputStreamReader());
        System.out.println(reader.readLine());
    }
    
}
