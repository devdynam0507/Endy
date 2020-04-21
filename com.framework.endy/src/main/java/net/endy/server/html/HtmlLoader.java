package net.endy.server.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.endy.AbstractProjectFileManager;

public class HtmlLoader {
        
    public static Html getHtmlResource(String fileName) {
        //TODO: ADD HTML NOT FOUND EXCEPTION      
        return HtmlStorage.getHtml(fileName);
    }
    
    public static Html parse(File htmlFile) {
        Html html = new Html(Arrays.asList("<h1> Endy Framework! </h1> <hr> Repository: <a href='https://github.com/devdynam0507/Endy'/>"));
         
        try {
            String htmlName = htmlFile.getName();
            
            if(HtmlStorage.htmls.containsKey(htmlName)) {
                html = HtmlStorage.getHtml(htmlName);
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(htmlFile));
                List<String> lines = new ArrayList<>();
                String line = null;
               
                while((line = reader.readLine()) != null) {
                    lines.add(line);
                }

                reader.close();
                html = new Html(lines);                 
            }
        } catch(IOException e) {
             e.printStackTrace();
        }
         
         return html;
    }
    
    public static void load() {
        File[] htmlFiles = AbstractProjectFileManager.getHtmlDirectory().listFiles((dir, name) -> name.endsWith("html"));
        
        for(File htmlFile : htmlFiles) {
            HtmlStorage.htmls.put(htmlFile.getName(), parse(htmlFile));
        }
    }
    
    
    public static class HtmlStorage { 
    
        protected static Map<String, Html> htmls;
        
        static {
            htmls = new HashMap<>();
        }
        
        private HtmlStorage() {}
        
        public static Html getHtml(String name) { return htmls.get(name); }
        
    }
    
}
