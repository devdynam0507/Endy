package project;

import server.EndyHttpServer;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        EndyHttpServer server = new EndyHttpServer((short) 8080);
        server.start();
    }

}
