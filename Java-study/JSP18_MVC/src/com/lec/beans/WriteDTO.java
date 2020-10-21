package com.lec.beans;

public class WriteDTO {

	private int uid; // wr_uid
	private String subject; // wr_subject
	private String content; // wr_content
	private String name; // wr_name
	private int viewcnt; // wr_viewcnt
	private String regDate; // wr_regdate

	// 웹 개발시...
	// 가급적, 다음 3가지는 이름을 일치 시켜주는게 좋습니다.
	// DB필드명 = 클래스 필드명 = form의 name명

	// 기본 생성자
	public WriteDTO() {
		// TODO Auto-generated constructor stub
	}

	// 매개변수 받는 생성자
	public WriteDTO(int uid, String subject, String content, String name, int viewcnt) {
		super();
		this.uid = uid;
		this.subject = subject;
		this.content = content;
		this.name = name;
		this.viewcnt = viewcnt;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "WriteDTO [uid=" + uid + ", subject=" + subject + ", content=" + content + ", name=" + name
				+ ", viewcnt=" + viewcnt + ", regDate=" + regDate + "]";
	}

}
