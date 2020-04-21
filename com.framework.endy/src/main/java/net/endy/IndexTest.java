package net.endy;

import net.endy.server.http.HttpProtocol;
import net.endy.server.page.AbstractPage;
import net.endy.server.page.annotation.PageUrl;
import net.endy.server.page.annotation.RequestHandler;
import net.endy.server.request.HttpRequestPacket;
import net.endy.server.response.HttpResponse.ResponseContext;
import net.endy.server.response.HttpResponse;
import net.endy.server.response.HttpResponse.Type;

@PageUrl(location = "/index", html = "index.html", response_type = HttpResponse.Type.Render)
public class IndexTest extends AbstractPage {

    @Override
    public ResponseContext response() {
        context.setContext("test", "test_data");
        
        return context;
    }
    
    /**
        Get request example
    */
    @RequestHandler(protocol = HttpProtocol.GET)
    public void get(HttpRequestPacket packet) {    
        System.out.println("Call get");
    }
    
    /**
        Post request example
    */
    @RequestHandler(protocol = HttpProtocol.POST)
    public void post(HttpRequestPacket packet) {
        System.out.println("Call post method");
    }
}
