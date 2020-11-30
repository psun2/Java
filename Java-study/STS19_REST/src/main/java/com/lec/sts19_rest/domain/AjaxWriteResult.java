package com.lec.sts19_rest.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import lombok.Data;

@Data
public class AjaxWriteResult {
	
	private int count; // 데이터 개수
	private String status; // 처리 결과
	private String message; // 결과 메시지
	private String timeStamp; // 처리 날짜

	public void setTimeStamp(Timestamp timestamp) {
		this.timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
	}
}
