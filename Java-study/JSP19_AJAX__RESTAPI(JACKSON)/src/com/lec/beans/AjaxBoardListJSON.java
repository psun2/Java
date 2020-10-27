package com.lec.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AjaxBoardListJSON {
	int count; // 데이터 개수
	String status; // 처리 결과

	@JsonIgnore // 변환시 제외할 데이터
	String memo;

	@JsonProperty("data") // response 시 맵핑 할 Json Property 이름과 클래스의 필드 명이 다를 경우
	List<WriteDTO> list; // 글 데이터

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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<WriteDTO> getList() {
		return list;
	}

	public void setList(List<WriteDTO> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "AjaxBoardListJSON [count=" + count + ", status=" + status + ", memo=" + memo + ", list=" + list + "]";
	}

}
