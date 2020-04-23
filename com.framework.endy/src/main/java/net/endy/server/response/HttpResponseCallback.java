package net.endy.server.response;

import java.io.PrintWriter;

import net.endy.server.html.Html;
import net.endy.server.response.HttpResponse.ResponseContext;

public interface HttpResponseCallback {
    
    /**
         * @param out Response client object
         * @param html render html object
    */
    void callback(PrintWriter out, Html html, ResponseContext context);
    
}
