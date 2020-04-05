package server.html.template;

import server.response.*;

public interface IHtmlInjectTemplate {
    
    String compile(HttpResponse.ResponseContext context);
    
}
