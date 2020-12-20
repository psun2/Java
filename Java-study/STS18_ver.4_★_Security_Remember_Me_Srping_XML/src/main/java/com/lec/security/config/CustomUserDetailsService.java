package com.lec.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.lec.security.service.DAOService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private DAOService daoService;
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
    	log.info("loadUserByUsername({}) 진입", username);
    	
        CustomUserDetails user = daoService.findByUserDetail(username);
        if(user==null) {
            throw new UsernameNotFoundException(username);
        }
        return user;

    }
}
