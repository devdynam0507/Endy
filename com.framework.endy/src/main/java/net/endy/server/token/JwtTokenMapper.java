package net.endy.server.token;

import java.util.Map;

public interface JwtTokenMapper {
    
    Map<String, Object> makeClaims();
    
}
