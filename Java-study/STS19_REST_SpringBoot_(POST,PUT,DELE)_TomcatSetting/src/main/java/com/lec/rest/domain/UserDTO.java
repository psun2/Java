package com.lec.rest.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


// 이 Entity로 RequestBody 로 자동 주입을 받을경우
// @JsonProperty 를 이용해서 이름을 바꾸면
// 클라이언트 측에서 들어오는 이름또한 
// @JsonProperty 와 동일해야 합니다.
@Data
public class UserDTO {

	private long uid;
	
	@JsonProperty("id")
	private String userId;
	
	@JsonProperty("password")
	private String userPassword;

}
