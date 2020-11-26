package com.lec.sts13_jdbc.beans;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO implements DTO{

	 @NotEmpty(message="name을 입력해주세요") // 유효성 검사를 이렇게도 가능
	private long uid;
	private String name;
	private String subject;
	@Size(min = 6, max=10, message = "길이가 알맞지 않습니다")
	private String content;
	private long viewcnt;
	private Timestamp regDate; // java.sql
	
}
