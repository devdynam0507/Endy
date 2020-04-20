package net.endy;

import net.endy.register.PageRegister;
import net.endy.server.EndyHttpServer;
import net.endy.server.mysql.MySql;
/**
 * Hello world!
 *
 */
public class App {
    
    public static void main(String[] args) throws Exception {
        register();
        
        handleCommand(args); 
    }
    
    public static void register() {
        PageRegister.registeredPage.put("/index", IndexTest.class);
    }
    
    public static void handleCommand(String... args) throws Exception {
        if(args != null ) {
            switch(args[0]) {
                case "-start":
                    MySql.load("dev", "root", "dy050700");

                    EndyHttpServer server = new EndyHttpServer((short) 8080);
                    server.start();
                    break;
                case "-makeproject":
                    AbstractProjectFileManager.makeSourceDirectories();
            }
            
        }
    }
}
