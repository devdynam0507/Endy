package net.endy.server.html;

import java.util.List;
import java.util.ArrayList;

public class Html {
    
    private List<String> html;
    
    public Html(List<String> html) {
        this.html = html;
    }
    
    public String toHtml() {
        StringBuilder builder = new StringBuilder();
        
        for(String line : html) {
            builder.append(line);
        }
        
        return builder.toString();
    }
    
}
