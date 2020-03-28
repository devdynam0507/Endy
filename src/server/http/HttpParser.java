package server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import server.http.cookie.Cookie;
import server.request.HttpRequestPacket;

public class HttpParser {
    
    public static HttpRequestPacket parse(Socket client) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        HttpRequestPacket.HttpRequestBuilder builder = HttpRequestPacket.HttpRequestBuilder.builder();
        String line = null;
        
        while((line = reader.readLine()) != null && line.length() != 0) {
            String[] packet = parse(line);
            parse(builder, packet);
        }
        
        return builder.build();
    }
    
    private static void parse(HttpRequestPacket.HttpRequestBuilder builder, String[] packet) {
        String packetId = packet[0];
        String packetContent = packet[1];
        
        switch(packetId) {
            case "Method":
                builder.setMethod(packetContent.trim());
                break;
            case "Host":
                builder.setHost(packetContent);
                break;
            case "Referer":
                parseParam(packetContent, builder);                
                builder.setReferrer(parseReferrer(packetContent));
                break;
            case "Cookie":
                builder.setCookie(parseCookie(packetContent));
                break;
        }
    }
    
    private static Cookie parseCookie(String packetContent) {
        String[] split = packetContent.split(";");
        Cookie cookie = new Cookie();
        
        for(String cookieContent : split) {
            String[] cookieSplit = cookieContent.split("=");
            
            cookie.setCookie(cookieSplit[0].trim(), cookieSplit[1]); //cookieId, cookieContent
        }
        
        return cookie;
    }
    
    private static String parseReferrer(String ref) {
        int last = ref.indexOf("?");
        
        if(last == -1) {
            last = ref.length();
        }
        
        return ref.substring(ref.indexOf('/'), last);
    }
    
    private static void parseParam(String ref, HttpRequestPacket.HttpRequestBuilder builder) {
        int paramStart = ref.indexOf('?');
        
        if(paramStart != -1) {
            String[] parseParam = ref.split("\\?")[1].split("&");
            
            for(String param : parseParam) {
                String[] paramSplit = param.split("=");
                
                builder.setParameter(paramSplit[0], paramSplit[1]);
            }
        }
    }
    
    private static String[] parse(String line) {
        String[] split = line.split(":");    
        
        if(split.length == 1 && line.contains("GET") || line.contains("POST")) {
            String packetId = null;
            
            packetId = line.split("/")[0];
            split = new String[] { "Method", packetId };
        } else if(line.contains("Referer")) {
            split = new String[] { "Referer", line.substring(17, line.length())};
        }
        
        return split;
    }
    
}
