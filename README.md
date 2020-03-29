# Endy Web Framework
간단한 서버 프로그래밍을 지원하는 Endy Web Framework 입니다.
기본적인 Http 통신 지식만 있으면 누구나 간단하게 서버 프로그래밍을 할 수 있습니다.

# Example JavaCode
```java
package framework.example;

import server.http.HttpProtocol;
import server.page.AbstractPage;
import server.page.annotation.PageUrl;
import server.page.annotation.RequestHandler;
import server.request.HttpRequestPacket;
import server.response.HttpResponse.ResponseContext;

@PageUrl(location = "/workspace/Endy", html = "index.html") //페이지 경로, 작성한 html 파일 
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
```

