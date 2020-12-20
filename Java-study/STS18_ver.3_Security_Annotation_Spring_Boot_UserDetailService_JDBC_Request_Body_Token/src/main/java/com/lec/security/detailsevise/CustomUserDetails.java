package com.lec.security.detailsevise;

import com.lec.security.domain.AuthenticationDAO;
import com.lec.security.domain.AuthenticationDTO;
import com.lec.security.domain.UserDAO;
import com.lec.security.domain.UserDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private String username;

    private UserDAO userDAO;

    private AuthenticationDAO authenticationDAO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<AuthenticationDTO> list = authenticationDAO.findByuid(this.username);

        log.info("UserDetails password : {}", list);

        List<GrantedAuthority> result = new ArrayList<>();

        // 방법1
//        list.forEach(auth -> {
//            result.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return auth.getUsername();
//                }
//            });
//        });

        // 방법2
//        list.forEach(auth -> {
//            result.add(() -> {
//                return auth.getUsername();
//            });
//        });

        // 방법 3
        list.forEach(auth -> {
            result.add(new SimpleGrantedAuthority(auth.getAuthority()));
        });

        return result;
    }

    @Override
    public String getPassword() {
        UserDTO user = userDAO.findByUid(this.username);
        String password = user.getPassword();
        log.info("UserDetails password : {}", password);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return userDAO.findByUid(this.username).getPassword();
    }

    @Override
    public String getUsername() {
        UserDTO user = userDAO.findByUid(this.username);
        String username = user.getUsername();
        log.info("UserDetails username : {}", username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return userDAO.findByUid(this.username).getUsername();
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
        return true;
    }
}
