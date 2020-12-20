package com.lec.security.config.security.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean enabled;
    // private List<String> authority; // String 을 list 에 꽃을수 없습니다.
    // 1 : N 의 관계를 같는 테이블 이라면 따로 서비스에서 따른 로직을 실행해야 합니다.
    private String authority;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // private List<String> authority; // String 을 list 에 꽃을수 없습니다.
        // 1 : N 의 관계를 같는 테이블 이라면 따로 서비스에서 따른 로직을 실행해야 합니다.

        // Collection<GrantedAuthority> result = new ArrayList<>();
        // authority.forEach(auth -> result.add(new SimpleGrantedAuthority(auth)));
        // return result;

        Collection<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(authority));
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
