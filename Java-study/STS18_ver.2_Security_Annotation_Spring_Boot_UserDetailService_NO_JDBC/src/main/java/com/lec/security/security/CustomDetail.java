package com.lec.security.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomDetail implements UserDetails {

    Map<String, String> user;

    public CustomDetail(Map<String, String> user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();

//        auth.add(new GrantedAuthority() {
//            @Override
//            public String getAuthority() {
//                return user.get("authority");
//            }
//        });

        auth.add(new SimpleGrantedAuthority(user.get("authority")));
        // 권한 설정이 담긴 String을 받습니다.
        // ROLE_ADMIN 등등...
        // 1 : N 관계라면 StringBuffer을 사용하여 문자열을합칠수 있도록 합니다.

        return auth;
    }

    @Override
    public String getPassword() {
        log.info("user : {}", user);
        log.info("user Password : {}", user.get("password"));
        return user.get("password");
    }

    @Override
    public String getUsername() {
        return user.get("username");
    }

    @Override
    public boolean isAccountNonExpired() {
        // 만료되지 않은 계정입니다.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠겨 있지 않음
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 만료되지 않은 자격 증명입니다.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 사용 가능
        return true;
    }
}
