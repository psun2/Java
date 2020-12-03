package com.lec.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AjaxBoardListJSON {
	int count;  // 데이터 개수
	String status;  // 처리 결과
	
	@JsonIgnore
	String memo;   // 제외할 데이터  
	
	@JsonProperty("data")  // 매핑할 Json Property 이름과 Java 클래스의 필드이름이 다른경우
	List<WriteDTO> list;  // 글 데이터
	
	
	public List<WriteDTO> getList() {
		return list;
	}
	public void setList(List<WriteDTO> list) {
		this.list = list;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
