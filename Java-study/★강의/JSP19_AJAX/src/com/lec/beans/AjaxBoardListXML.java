package com.lec.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "WriteList")
public class AjaxBoardListXML {
	int count;
	String status;
	
	@JsonIgnore
	String memo;  // 변환시 제외
	
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "Data")  // <Data>...</Data> 들로 변환된다
	List<WriteDTO> list;  // 데이터
	
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
