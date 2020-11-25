package com.lec.sts13_jdbc.beans;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO implements DTO{

	private long uid;
	private String name;
	private String subject;
	private String content;
	private long viewcnt;
	private Timestamp regDate; // java.sql
	
}
