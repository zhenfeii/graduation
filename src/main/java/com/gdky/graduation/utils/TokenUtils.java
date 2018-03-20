package com.gdky.graduation.utils;

import com.gdky.graduation.Config.Constants;
import com.gdky.graduation.entity.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    private String secret = Constants.TOKEN_SECRET;
    private static Integer expiration = Constants.EXPIRATION;


    public String generateToken(CustomUserDetails customUserDetails, String random) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", customUserDetails.getUsername());
        claims.put("created", generateCurrentDate());
        claims.put("password", customUserDetails.getPassword());
        claims.put("dlxx", random);
        return this.generateToken(claims);
    }


    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 30000);
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(this.secret)
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public boolean validateToken(String token, CustomUserDetails customUserDetails) {
        String username = getUsernameFromToken(token);
        String password = getPasswordFromToken(token);
        String dlxx = getDlxxFromToken(token);


        if (customUserDetails.getDlxx() == null || dlxx == null) {
            return false;
        } else {

            //用户名，密码，有效性都通过，才return true
            boolean rs = (username.equals(customUserDetails.getUsername())
                    && !(isTokenExpired(token))
                    && password.equals(customUserDetails.getPassword()));
            return true;
        }
    }

    public String getUsernameFromToken(String authToken) {
        String username;
        try {
            Claims claims = getClaimsFromToken(authToken);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public String getPasswordFromToken(String token) {
        String password;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            password = (String) claims.get("password");
        } catch (Exception e) {
            password = null;
        }
        return password;
    }

    public String getDlxxFromToken(String token) {
        String dlxx;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            dlxx = (String) claims.get("dlxx");
        } catch (Exception e) {
            dlxx = null;
        }
        return dlxx;
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;

    }
}