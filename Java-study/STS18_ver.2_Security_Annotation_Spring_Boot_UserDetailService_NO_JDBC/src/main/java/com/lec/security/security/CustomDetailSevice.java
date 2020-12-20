package com.lec.security.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomDetailSevice implements UserDetailsService {

    private final static Map<String, String> user = new HashMap<String, String>(); // JDBC 로 교체 !!

    public CustomDetailSevice() {
        user.put("username", "admin00");
        user.put("password", "{noop}1234");
        user.put("authority", "ROLE_ADMIN"); // ROLE_ADMIN 또는 ADMIN 둘다 활용 가능
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserService : {}", username);


        if (user.size() == 0) {
            throw new UsernameNotFoundException(username);
        }


        return new CustomDetail(this.user);
    }
}
