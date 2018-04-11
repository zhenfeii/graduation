package com.gdky.restful.utils;

import com.gdky.restful.security.CustomUserDetails;
import com.gdky.restful.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    private String secret = "c2VjcmV0IGZvciBnZHpz";

    //seconds
    //86400 seconds = 24 hours
    private Integer expiration = 86400;


//    @Value("${spring.profiles.active}")
//    private String active;

    @Resource
    private AuthService authService;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getClaimsFromToken(token);
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


    public Integer getUserIdFromToken(String token) {
        Integer userId;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            userId = (Integer) claims.get("userId");
        } catch (Exception e) {
            userId = null;
        }
        return userId;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            created = new Date((Long) claims.get("created"));
        } catch (Exception e) {
            created = null;
        }
        return created;
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

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            audience = (String) claims.get("audience");
        } catch (Exception e) {
            audience = null;
        }
        return audience;
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

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 30000);
    }

//    验证token 是否过期
    private Boolean isTokenExpired(String token) {
        final Date expiration = this.getExpirationDateFromToken(token);
        return expiration.before(this.generateCurrentDate());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created,
                                                     Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public String generateToken(CustomUserDetails userDetails, String random) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", this.generateCurrentDate());
        claims.put("password", userDetails.getPassword());
        claims.put("dlxx", random);
        return this.generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = this.getCreatedDateFromToken(token);
        return (!(this.isCreatedBeforeLastPasswordReset(created,
                lastPasswordReset)) && !(this.isTokenExpired(token)));
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            claims.put("created", this.generateCurrentDate());
            refreshedToken = this.generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    //验证token  是否有效
    public Boolean validateToken(String token, UserDetails userDetails) {
        CustomUserDetails user = (CustomUserDetails) userDetails;
        final String username = this.getUsernameFromToken(token);
        final String password = this.getPasswordFromToken(token);
        final String dlxx = this.getDlxxFromToken(token);

        // 验证 用户、密码是否正确，token 是否过期
        if(username.equals(user.getUsername()) && password.equals(user.getPassword()) && this.isTokenExpired(token)){
            return true;
        }else {
            return false;
        }

    }

    public static void main(String[] args) {
        TokenUtils tokenUtils = new TokenUtils();
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwicGFzc3dvcmQiOiIxZTJiMDdiOGU1ZGMwZDg5YzQwMjdmYzVmZTdjYWFjMiIsImNyZWF0ZWQiOjE1MjMyODU5ODg3OTQsImRseHgiOiIxNzN0MmJuOTN2IiwiZXhwIjoxNTIxNTgzMDIxfQ.A5uXDgvGulfUYgPRLtsOy_T26WDiSDVXh4_HoRNpp3kAtQRdmSQyMRQt7WGEwbhdwWSRDSMO3Vt-VQDCFqo_Ag";

        Boolean dlxx = tokenUtils.isTokenExpired(token);
    }
}
