package project;

import server.EndyHttpServer;
import server.mysql.*;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        //TODO parse properties file
        MySql.load("dev", "root", "dy050700");
        
        EndyHttpServer server = new EndyHttpServer((short) 8080);
        server.start();
    }
    
}
