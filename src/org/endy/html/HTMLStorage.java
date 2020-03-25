package org.endy.html;

import java.util.HashMap;
import java.util.Map;

public class HTMLStorage {

    private static Map<String, String[]> htmlMap = new HashMap<>();

    static {
        htmlMap.put("index.html", new String[] { "<H1> endy framework... </H1>" });
    }
    
    public static void push(String pageName, String... html) {
        htmlMap.put(pageName, html);
    }
    
    public static String[] getPageHtml(String pageName) {
        String[] html = htmlMap.get(pageName);
        
        if(html == null || html.length < 1) {
            html = new String[] { "<h1> Page html not found </h1>" };
        }
        
        
        return html;
    }


}
