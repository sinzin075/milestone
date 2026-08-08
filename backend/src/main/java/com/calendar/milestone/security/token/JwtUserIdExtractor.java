package com.calendar.milestone.security.token;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class JwtUserIdExtractor {
    
    /**
     * JwtトークンからuserIdを取得する
     * @param jwt
     * @return
     */
    public int extract(Jwt jwt){
        return Integer.parseInt(jwt.getSubject());
    }
}
