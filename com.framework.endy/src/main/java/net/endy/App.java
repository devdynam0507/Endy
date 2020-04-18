package net.endy;

import net.endy.server.EndyHttpServer;
import net.endy.server.mysql.MySql;
import net.endy.server.token.JwtTokenService;
/**
 * Hello world!
 *
 */
public class App {
    
    public static void main( String[] args ) throws Exception {
        MySql.load("dev", "root", "dy050700");
        //ModelLoader.load(ModelExample.class);

        EndyHttpServer server = new EndyHttpServer((short) 8080);
        server.start();   
        
        String token = JwtTokenService.create("dynam0507", 10); //유효시간 10분
        System.out.println(token);
        System.out.println(JwtTokenService.isTokenValid(token));
    }
    
    public static void handleCommand(String... args) throws Exception {
        if(args != null ) {
            
           if(args[0].equalsIgnoreCase("-start")) {
                MySql.load("dev", "root", "dy050700");
                //ModelLoader.load(ModelExample.class);
                

                EndyHttpServer server = new EndyHttpServer((short) 8080);
                server.start();     
              
            }
        }
    }
}
