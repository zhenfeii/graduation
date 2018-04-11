package com.gdky.restful.api;

import com.gdky.restful.entity.AuthRequest;
import com.gdky.restful.entity.AuthResponse;
import com.gdky.restful.security.CustomUserDetails;
import com.gdky.restful.service.AuthService;
import com.gdky.restful.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * Created by Administrator on 2018/4/8 0008.
 */
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthService authService;

    private TokenUtils tokenUtils = new TokenUtils();

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest, HttpServletRequest req) {


        //进行验证
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);


        // Reload password post-authentication so we can generate token
        CustomUserDetails userDetails = (CustomUserDetails) this.userDetailsService.loadUserByUsername(authRequest.getUsername());

        String random = getRandomString(10);
        String token = this.tokenUtils.generateToken(userDetails, random);

        AuthResponse authResponse = new AuthResponse(token);
        authResponse.setTokenHash(token);
        return ResponseEntity.ok(authResponse);
    }

    //生成任意字符串的通用方法
    private String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
