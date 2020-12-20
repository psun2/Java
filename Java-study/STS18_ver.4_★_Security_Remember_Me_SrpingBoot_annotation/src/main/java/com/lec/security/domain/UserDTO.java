package com.lec.security.domain;

import lombok.Data;

@Data
public class UserDTO {

	private String usernmae;
	private String password;
	private String enabled;
	private String authority;
	
}
