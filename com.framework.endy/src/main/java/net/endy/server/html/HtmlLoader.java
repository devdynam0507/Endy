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

public class HtmlLoader {
    
    private static final String HTML_PARENT_PATH = "/resource/";
    
    public static Html parse(String fileName) {
        File file = getHtmlFile(fileName);
        
        return parse(file);
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
                 
                HtmlStorage.htmls.put(htmlName, html);
            }
        } catch(IOException e) {
             e.printStackTrace();
        }
         
         return html;
    }
    
    public static File getHtmlFile(final String fileName) {
        return new File(HTML_PARENT_PATH + fileName);
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
