package com.lec.security.domain;

import java.util.Date;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import lombok.Data;

@Data
public class TokenVO {

	private String username;
	private String series;
	private String tokenValue;
	private Date lastUsed;
	
	public TokenVO(PersistentRememberMeToken token) {
		this.username = token.getUsername();
		this.series = token.getSeries();
		this.tokenValue = token.getTokenValue();
	}	
	
}
