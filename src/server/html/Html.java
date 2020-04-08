package server.html;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import server.html.template.HtmlInjectTemplate;

public class Html {
    
    private List<String> html;
    private Map<Integer, String[]> templateLines; //Inject template cache
    
    public Html(List<String> html) {
        this.html = html;
        this.templateLines = new HashMap<>();
        
        parseInjectTemplates();
    }
    
    private void parseInjectTemplates() {
        int size = html.size();
        
        for(int i = 0; i < size; i++) {
            String line = html.get(i);
            Matcher m = HtmlInjectTemplate.pattern.matcher(line);
            
            while(m.find()) {
                line = m.group().trim();
                templateLines.put(i, HtmlInjectTemplate.parseTemplateTag(line));
            }
        }
    }
    
    public void replaceTag(int line, String tag, String element) {
        String htmlLine = html.get(line).replace(tag, element);
        html.set(line, htmlLine);
    }
    
    public String toHtml() {
        StringBuilder builder = new StringBuilder();
        
        for(String line : html) {
            builder.append(line);
        }
        
        return builder.toString();
    }
    
}
