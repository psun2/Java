<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>Model 및 request 사용</h1> 
<div>
    작성자: ${dto.name} <br />
    글제목: ${dto.subject} <br />
    글내용: ${dto.content} <br />
</div>


 <hr />
 
<h1>Command Object 사용 (주의점: 클래스이지만 앞글자가 소문자로 시작)</h1> 
 <!-- 주의점은 소문자로 시작합니다 -->
 <div>
	 작성자: ${writeDTO.name} <br />
	 글제목: ${writeDTO.subject} <br />
	 글내용: ${writeDTO.content} <br />
	 uid: ${writeDTO.uid} <br />
	 조회수: ${writeDTO.viewcnt} <br />
	 등록일: ${writeDTO.regDate} <br />
 </div>
 
 <hr />
 
<h1>@ModelAttribute("DTO") WriteDTO dto 사용</h1> 
  <div>
	 작성자: ${DTO.name} <br />
	 글제목: ${DTO.subject} <br />
	 글내용: ${DTO.content} <br />
	 uid: ${DTO.uid} <br />
	 조회수: ${DTO.viewcnt} <br />
	 등록일: ${DTO.regDate} <br />
 </div>
 
 <br />
 
 <button onclick="history.back()">뒤로가기</button>