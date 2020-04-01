package project;

import framework.example.ModelExample;
import server.EndyHttpServer;
import server.model.ModelLoader;
import server.mysql.*;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //TODO parse properties file
        MySql.load("dev", "root", "dy050700");
        ModelLoader.load(ModelExample.class);
        
        EndyHttpServer server = new EndyHttpServer((short) 8080);
        server.start();
    }
    
}
