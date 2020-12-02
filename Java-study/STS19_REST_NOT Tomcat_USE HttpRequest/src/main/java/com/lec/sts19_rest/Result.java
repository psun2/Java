package com.lec.sts19_rest;

import lombok.Data;

@Data
public class Result {
	
	private String status;
	private String message;
	private UserDTO dto;

}
