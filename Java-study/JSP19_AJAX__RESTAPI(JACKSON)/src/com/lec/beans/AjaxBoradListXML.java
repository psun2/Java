package com.lec.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "WriteList")
public class AjaxBoradListXML {
	int count;
	String status;

	@JsonIgnore // 변환시 제외
	String memo;

	@JacksonXmlElementWrapper(useWrapping = false) // 전체적으로 감싸는 태그를 없애 줍니다.
	@JacksonXmlProperty(localName = "Data")
	List<WriteDTO> list; // 데이터

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
		return "AjaxBoradListXML [count=" + count + ", status=" + status + ", memo=" + memo + ", list=" + list + "]";
	}

}
