package server.page.annotation.handler;

import java.lang.reflect.Method;

import framework.example.Index;
import server.http.HttpProtocol;
import server.page.annotation.PageUrl;
import server.page.annotation.RequestHandler;
import server.request.HttpRequestPacket;
import server.page.AbstractPage;
import java.net.Socket;

public class ServerAnnotationHandler {
    
    public static void handleTest(HttpRequestPacket packet, Socket client) {
        String ref = packet.getReferrer();
        System.out.println(packet.getMethod());
        HttpProtocol method = HttpProtocol.valueOf(packet.getMethod());
        
        try {
            Class clazz = Index.class;
            PageUrl url = (PageUrl) clazz.getAnnotation(PageUrl.class);
            AbstractPage obj = (AbstractPage) clazz.newInstance();

            Method[] methods = clazz.getDeclaredMethods();
            
            if(url.location().equals(ref)) {
                for(Method m : methods) {
                    if(m.isAnnotationPresent(RequestHandler.class)) {
                        RequestHandler handler = (RequestHandler) m.getAnnotation(RequestHandler.class);

                        if(handler.protocol() == method) {
                            //run
                            m.invoke(obj, packet);
                        }
                    }
                }
                
                obj.response(client);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
