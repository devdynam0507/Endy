package net.endy.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import net.endy.server.http.HttpParser;
import net.endy.server.request.HttpRequestPacket;
import net.endy.server.page.annotation.handler.ServerAnnotationHandler;

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
                    HttpRequestPacket request = HttpParser.parse(client);
                    System.out.println(client.getInetAddress().getHostAddress() + "/HTTP/" + request.getMethod() + " url: " + request.getHost() + request.getReferrer());

                    ServerAnnotationHandler.handle(request, client);
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
}
