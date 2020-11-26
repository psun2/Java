package com.lec.sts13_jdbc.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WriteDTO  {

	private long uid;
	private String subject;
	private String content;
	private String name;
	private long viewcnt;
	private Timestamp regDate; // java.sql
	
}
