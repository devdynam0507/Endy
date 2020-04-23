package net.endy.server.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import net.endy.server.http.cookie.Cookie;
import net.endy.server.request.HttpRequestPacket;

public class HttpParser {
    
    public static HttpRequestPacket parse(Socket client) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        HttpRequestPacket.HttpRequestBuilder builder = HttpRequestPacket.HttpRequestBuilder.builder();
        String line = null;
        StringBuilder body = new StringBuilder();
        
        while((line = reader.readLine()) != null && line.length() != 0) {
            String[] packet = parse(line);
            parse(builder, packet);
        }
        
        HttpRequestPacket packet = builder.build();
        
        while(reader.ready()) {
            body.append(reader.read());
        }

        System.out.println(body.toString());
        
        
        reader.close();
        
        return packet;
    }
    
    private static void parse(HttpRequestPacket.HttpRequestBuilder builder, String[] packet) {
        String packetId = packet[0];
        String packetContent = packet[1];
        
        switch(packetId) {
            case "Method":
                builder.setMethod(packetContent.trim());
                builder.setReferrer(packet[2]);
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
            String cookieElement = null;

            if(cookieSplit.length > 1) {
                cookieElement = cookieSplit[1];
            }

            cookie.setCookie(cookieSplit[0].trim(), cookieElement); //cookieId, cookieContent
            
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
        
        if(split.length == 1 && line.contains("HTTP/1.1")) {
            String packetId = null;
            String[] packetSplit = line.split(" ");
            
            packetId = packetSplit[0];
            split = new String[] { "Method", packetId, packetSplit[1] };
        } else if(line.contains("Referer")) {
            split = new String[] { "Referer", line.substring(17, line.length())};
        }
        
        return split;
    }
    
}
