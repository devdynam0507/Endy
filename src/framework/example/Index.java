package framework.example;

import server.http.HttpProtocol;
import server.page.AbstractPage;
import server.page.annotation.PageUrl;
import server.page.annotation.RequestHandler;
import server.request.HttpRequestPacket;
import server.response.HttpResponse.ResponseContext;

@PageUrl(location = "/a", html = "index.html")
public class Index extends AbstractPage {
    
    /**
        Response example
    */
    @Override
    public ResponseContext response() {
        context.setContext("db", "3306").setContext("coin", "1000");  
        
        return context;
    }
    
    /**
        Get request example
    */
    @RequestHandler(protocol = HttpProtocol.GET)
    public void get(HttpRequestPacket packet) {
        System.out.println("Call get method");
    }
    
    /**
        Post request example
    */
    @RequestHandler(protocol = HttpProtocol.POST)
    public void post(HttpRequestPacket packet) {
        System.out.println("Call post method");
    }
    
}
