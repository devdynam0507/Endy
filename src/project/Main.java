package project;

import framework.example.ModelExample;
import framework.project.AbstractProjectFileManager;
import server.EndyHttpServer;
import server.model.ModelLoader;
import server.mysql.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import server.html.template.*;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //test();    
        
        handleCommand(new String[]{"-start"});
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
    
    public static void test() {
        Pattern pattern = Pattern.compile("[\\{]([^}]*)[\\}]");
        Matcher m = pattern.matcher("<h1> { if a > 10 } { var:test } </h1>");
                
        while(m.find()) {
            System.out.println(m.group());
        }
    }
    
}
