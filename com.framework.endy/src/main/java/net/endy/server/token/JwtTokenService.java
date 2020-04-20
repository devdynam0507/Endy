package net.endy.server.token;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtTokenService {
    
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public static synchronized String create(JwtTokenMapper mapper, int expiredMinute) {
        Map<String, Object> payloads = mapper.makeClaims();
        payloads.put("exp", (long) (1000 * (expiredMinute * 60)));
                        
        String token = Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setHeaderParam("alg", "HS512")
            .setClaims(payloads)
            .signWith(key)
            .compact();
        
        return token;
    }
    
    public static boolean isTokenValid(String token) {        
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        System.out.println(claims.get("data", String.class));
        
        return !claims.get("exp", Date.class).before(new Date());
    }
    
    private static Date getExpiredTime(long millSec) {
        Date now = new Date();
        Date before = now;
        
        if(millSec > 0) {
            before = new Date(now.getTime() + millSec);
        }
        
        return before;
    }
    
}
