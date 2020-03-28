package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server.handler.EndyHttpAsyncEvent;
import server.handler.HttpClientConnectHandler;
import server.http.HttpParser;
import server.request.HttpRequestPacket;
import server.page.annotation.handler.ServerAnnotationHandler;

public class EndyHttpServer extends Thread {
    
    private static ServerSocket socket; // Http Server Socket
    private static boolean isAlive = false; // Server is alive
    
    private short port;
    
    public EndyHttpServer(short port) {
        this.port = port;
    }
    
    @Override
    public void run() {
        if(socket == null) {
            try {
                socket = new ServerSocket(port);
                isAlive = true;
                
                System.out.println("Endy framework to be started!");
                System.out.println("Listening client...");
                System.out.println("IP: " + socket.getInetAddress().getHostName() + ":" + port);
                
                while(isAlive) {
                    Socket client = socket.accept();
                    System.out.println(client.getInetAddress().getHostAddress() + "/HTTP");
                    
                    HttpRequestPacket request = HttpParser.parse(client);
                    
                    ServerAnnotationHandler.handleTest(request, client);
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
