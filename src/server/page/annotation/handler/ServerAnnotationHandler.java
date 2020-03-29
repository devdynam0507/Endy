package server.page.annotation.handler;

import java.lang.reflect.Method;

import framework.example.Index;
import server.html.Html;
import server.html.HtmlLoader;
import server.http.HttpProtocol;
import server.page.annotation.PageUrl;
import server.page.annotation.RequestHandler;
import server.request.HttpRequestPacket;
import server.page.AbstractPage;
import java.net.Socket;

public class ServerAnnotationHandler {
    
    public static void handle(HttpRequestPacket packet, Socket client) {
        String ref = packet.getReferrer();
        HttpProtocol method = HttpProtocol.valueOf(packet.getMethod());
        
        try {
            Class clazz = Index.class;
            PageUrl url = (PageUrl) clazz.getAnnotation(PageUrl.class);
            AbstractPage obj = (AbstractPage) clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            Html html = HtmlLoader.parse(url.html());
            
            if(url.location().equals(ref)) {
                for(Method m : methods) {
                    if(m.isAnnotationPresent(RequestHandler.class)) {
                        RequestHandler handler = (RequestHandler) m.getAnnotation(RequestHandler.class);

                        if(handler.protocol() == method) {
                            m.invoke(obj, packet);
                        }
                    }
                }
                
                obj.response(client, html);
            } else {
                //TODO run index
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
