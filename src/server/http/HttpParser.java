package server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import server.request.HttpRequestPacket;
import server.request.HttpRequestPacket.HttpRequestBuilder;

public class HttpParser {
    
    public static HttpRequestPacket parse(Socket client) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        HttpRequestPacket.HttpRequestBuilder builder = HttpRequestPacket.HttpRequestBuilder.builder();
        String line = null;
        
        while((line = reader.readLine()) != null && line.length() != 0) {
            String[] packet = parse(line);
            parse(builder, packet);
        }
        
        return null;
    }
    
    private static void parse(HttpRequestPacket.HttpRequestBuilder builder, String[] packet) {
        String packetId = packet[0];
        String packetContent = packet[1];
        
        switch(packetId) {
            case "GET":
            case "POST":
                builder.setMethod(packetContent);
                break;
            case "Host":
                builder.setHost(packetContent);
                break;
            case "Referer":
                //TODO need fix
                builder.setReferrer(parseReferrer(packetContent));
                System.out.println("ref: " + parseReferrer(packetContent));
        }
    }
    
    private static String parseReferrer(String ref) {
        System.out.println(ref);
        String split = ref.split("://")[1];
        
        return split.substring(split.indexOf('/'), split.length());
    }
    
    private static String[] parse(String line) {
        String[] split = line.split(":");    
        
        if(split.length == 1 && line.contains("GET") || line.contains("POST")) {
            String packetId = null;
            
            packetId = line.split("/")[0];
            split = new String[] { "method", packetId };
        }
        
        return split;
    }
    
}
