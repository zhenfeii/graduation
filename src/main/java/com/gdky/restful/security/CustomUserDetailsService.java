package com.gdky.restful.security;

import com.gdky.restful.entity.Role;
import com.gdky.restful.entity.User;
import com.gdky.restful.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/4/8 0008.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(CustomUserDetails.class);
    @Autowired
    private AuthService authService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authService.getUser(username);
        if (user == null) {
            log.warn("用户不存在");
            throw new UsernameNotFoundException("账户或密码错误！");
        }
        log.warn("获取用户");
        CustomUserDetails u = new CustomUserDetails(user);
        u.setAuthorities(this.getAuthorities(username));
        return u;
    }

    private List<? extends GrantedAuthority> getAuthorities(String username) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<Role> roles = authService.getRolesByUser(username);
        for (Role role : roles) {
            authorities.
                    add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return authorities;
    }
}
