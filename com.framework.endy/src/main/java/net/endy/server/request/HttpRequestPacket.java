package net.endy.server.request;

import java.util.HashMap;
import java.util.Map;

import net.endy.server.http.cookie.Cookie;

public class HttpRequestPacket {
    
    private String host;
    private String method;
    private String referrer;
    private Cookie cookie;
    private Map<String, String> parameter;
    private Map<String, String> etc;
    private HttpRequestBody body;
    
    private HttpRequestPacket() {
        parameter = new HashMap<>();
        etc = new HashMap<>();
    }
    
    public String getHost() { return host; }
    public String getMethod() { return method; }
    public String getReferrer() { return referrer; }
    public Cookie getCookie() { return cookie; }
    
    public String getParameter(String paramName) { return parameter.get(paramName); }
    public String getEtc(String etcName) { return etc.get(etcName); }
    
    public HttpRequestBody getBody() { return body; }
    
    @Override
    public String toString() { return method; }
    
    public static class HttpRequestBuilder {
    
        private HttpRequestPacket request;
        
        private HttpRequestBuilder() {
            request = new HttpRequestPacket();
        }
        
        public static HttpRequestBuilder builder() {
            return new HttpRequestBuilder();
        }
        
        public HttpRequestBuilder setMethod(String method) {
            request.method = method;
            
            return this;
        }
        
        public HttpRequestBuilder setHost(String host) {
            request.host = host;
            
            return this;
        }
        
        public HttpRequestBuilder setReferrer(String referrer) {
            request.referrer = referrer;
            
            return this;
        }
        
        public HttpRequestBuilder setCookie(Cookie cookie) {
            request.cookie = cookie;
            
            return this;
        }
        
        public HttpRequestBuilder setParameter(String paramName, String param) {
            request.parameter.put(paramName, param);
            
            return this;
        }
        
        public HttpRequestBuilder setEtc(String etcName, String etcValue) {
            request.etc.put(etcName, etcValue);
            
            return this;
        }
        
        public HttpRequestBuilder setBody(String body) {
            request.body = new HttpRequestBody(body);
            
            return this;
        }
        
        public HttpRequestPacket build() { return request; }
        
    }
    
}
