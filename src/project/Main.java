package project;

import framework.example.ModelExample;
import framework.project.AbstractProjectFileManager;
import server.EndyHttpServer;
import server.model.ModelLoader;
import server.mysql.*;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        handleCommand(args);
    }
    
    public static void handleCommand(String[] args) throws Exception {
        if(args != null ) {
            int len = args.length;

            if(len == 1 && args[0].equalsIgnoreCase("-makeproject")) {
                AbstractProjectFileManager.makeSourceDirectories();
            }else if(args[0].equalsIgnoreCase("-start")) {
                MySql.load("dev", "root", "dy050700");
                ModelLoader.load(ModelExample.class);

                EndyHttpServer server = new EndyHttpServer((short) 8080);
                server.start();                
            }
        }
    }
    
}
