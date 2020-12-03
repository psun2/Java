package com.les.sts13_jdbc.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WriteDTO {
	private int uid;
	private String subject;
	private String content;
	private String name;
	private int viewCnt;
	private Timestamp regDate;
	
	
}
