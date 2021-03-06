package net.endy.server.page.annotation.handler;

import java.lang.reflect.Method;

import net.endy.register.PageRegister;
import net.endy.server.html.Html;
import net.endy.server.html.HtmlLoader;
import net.endy.server.http.HttpProtocol;
import net.endy.server.page.annotation.PageUrl;
import net.endy.server.page.annotation.RequestHandler;
import net.endy.server.request.HttpRequestPacket;
import net.endy.server.page.AbstractPage;
import java.net.Socket;

import net.endy.server.response.HttpResponse;

public class ServerAnnotationHandler {
    
    public static void handle(HttpRequestPacket packet, Socket client) {
        String ref = packet.getReferrer();
        HttpProtocol method = HttpProtocol.valueOf(packet.getMethod());
        
        try {
            Class<? extends AbstractPage> clazz = PageRegister.registeredPage.get(ref);
            PageUrl url = (PageUrl) clazz.getAnnotation(PageUrl.class);
            AbstractPage obj = (AbstractPage) clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            Html html = HtmlLoader.getHtmlResource(url.html());
            HttpResponse.Type responseType = url.response_type();
            
            if(url.location().equals(ref)) {
                for(Method m : methods) {
                    if(m.isAnnotationPresent(RequestHandler.class)) {
                        RequestHandler handler = (RequestHandler) m.getAnnotation(RequestHandler.class);

                        if(handler.protocol() == method) {
                            m.invoke(obj, packet);
                        }
                    }
                }
                
                obj.response(client, html, responseType);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
}
