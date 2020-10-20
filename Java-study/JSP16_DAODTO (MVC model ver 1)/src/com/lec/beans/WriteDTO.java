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
		System.out.println("WriteDTO() 객체 생성");
	}

	// 매개변수 받는 생성자
	public WriteDTO(int uid, String subject, String content, String name, int viewcnt) {
		super();
		this.uid = uid;
		this.subject = subject;
		this.content = content;
		this.name = name;
		this.viewcnt = viewcnt;
		System.out.printf("WriteDTO(%d, %s, %s, %s, %d) 객체 생성\n", uid, subject, content, name, viewcnt);
	}

	public int getUid() {
		System.out.println("getUid() 호출");
		return uid;
	}

	public void setUid(int uid) {
		System.out.println("setUid() 호출");
		this.uid = uid;
	}

	public String getSubject() {
		System.out.println("getSubject() 호출");
		return subject;
	}

	public void setSubject(String subject) {
		System.out.println("setSubject() 호출");
		this.subject = subject;
	}

	public String getContent() {
		System.out.println("getContent() 호출");
		return content;
	}

	public void setContent(String content) {
		System.out.println("setContent() 호출");
		this.content = content;
	}

	public String getName() {
		System.out.println("getName() 호출");
		return name;
	}

	public void setName(String name) {
		System.out.println("setName() 호출");
		this.name = name;
	}

	public int getViewcnt() {
		System.out.println("getViewcnt() 호출");
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		System.out.println("setViewcnt() 호출");
		this.viewcnt = viewcnt;
	}

	public String getRegDate() {
		System.out.println("getRegDate() 호출");
		return regDate;
	}

	public void setRegDate(String regDate) {
		System.out.println("setRegDate() 호출");
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "WriteDTO [uid=" + uid + ", subject=" + subject + ", content=" + content + ", name=" + name
				+ ", viewcnt=" + viewcnt + ", regDate=" + regDate + "]";
	}

}
