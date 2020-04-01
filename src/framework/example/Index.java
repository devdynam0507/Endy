package framework.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import server.http.HttpProtocol;
import server.model.ModelLoader;
import server.model.ModelLoader.ModelInfo;
import server.mysql.MySql;
import server.page.AbstractPage;
import server.page.annotation.PageUrl;
import server.page.annotation.RequestHandler;
import server.request.HttpRequestPacket;
import server.response.HttpResponse.ResponseContext;

@PageUrl(location = "/index", html = "index.html")
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
    
        ModelInfo info = ModelLoader.getModelInfo(ModelExample.class);
        ResultSet rs = MySql.select(info, "SELECT * FROM {table}");
        
        try{ 
            while(rs.next()) {
                System.out.println("name: " + rs.getString("name"));
                System.out.println("age: " + rs.getInt("age"));
                System.out.println("school: " + rs.getString("school"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
        Post request example
    */
    @RequestHandler(protocol = HttpProtocol.POST)
    public void post(HttpRequestPacket packet) {
        System.out.println("Call post method");
    }
    
}
