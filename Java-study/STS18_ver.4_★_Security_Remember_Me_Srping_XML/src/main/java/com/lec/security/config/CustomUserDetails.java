package com.lec.security.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lec.security.domain.DTO;
import com.lec.security.service.DAOService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetails implements UserDetails {

	private String username;
	private String password;
	private boolean enabled;
	private String authority;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new SimpleGrantedAuthority(authority));
		log.info("getAuthorities : {}", list);
		return list;

	}

	@Override
	public String getPassword() {
		log.info("getPassword : {}", password);
		return password;
	}

	@Override
	public String getUsername() {
		log.info("getUsername : {}", username);
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
		log.info("isEnabled : {}", enabled);
		return enabled;
	}
}
