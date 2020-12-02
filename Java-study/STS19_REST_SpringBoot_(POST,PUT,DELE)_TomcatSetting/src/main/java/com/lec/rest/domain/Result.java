package com.lec.rest.domain;

import lombok.Data;

@Data
public class Result {
	
	private String status;
	private String message;
	private UserDTO dto;

}
