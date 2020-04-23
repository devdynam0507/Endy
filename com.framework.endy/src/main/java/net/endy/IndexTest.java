package net.endy;

import net.endy.server.http.HttpProtocol;
import net.endy.server.page.AbstractPage;
import net.endy.server.page.annotation.PageUrl;
import net.endy.server.page.annotation.RequestHandler;
import net.endy.server.request.HttpRequestPacket;
import net.endy.server.response.HttpResponse;

@PageUrl(location = "/index", html = "index.html", response_type = HttpResponse.Type.Render)
public class IndexTest extends AbstractPage {
    
    /**
        Get request example
    */
    @RequestHandler(protocol = HttpProtocol.GET)
    public void get(HttpRequestPacket packet) {    
        System.out.println("Call get");
        
        setStatusCode(200);
        context
            .setContext("test", "test_data")
            .setContext("test2", "adwjj");
    }
    
    /**
        Post request example
    */
    @RequestHandler(protocol = HttpProtocol.POST)
    public void post(HttpRequestPacket packet) {
        System.out.println("Call post method");
    }
}
