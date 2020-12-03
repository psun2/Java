package com.lec.sts19_rest.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty("viewcnt")
	private int viewCnt;
	@JsonProperty("regdate")
	private Timestamp regDate;
	
	public String getRegDate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(regDate);
	}
	
}













