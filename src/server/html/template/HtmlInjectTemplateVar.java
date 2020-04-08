package server.html.template;

import server.html.Html;
import server.response.HttpResponse.ResponseContext;

public class HtmlInjectTemplateVar extends HtmlInjectTemplate {
    
    @Override
    public void compile(ResponseContext context, Html html, String[] templateTag, String tag, int line) {
        String element = context.getContext(templateTag[1]);
        html.replaceTag(line, tag, element);
    }
    
}
