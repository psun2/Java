<%@page
	import="jdk.internal.org.objectweb.asm.commons.TryCatchBlockSorter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="evaluation.EvaluationDTO"%>
<%@ page import="evaluation.EvaluationDAO"%>
<%@ page import="util.SHA256"%>

<!-- java.io.PrintWriter : 특정한 스트립트 구문을 실행 할 때 사용 -->
<%@ page import="java.io.PrintWriter"%>

<%
	request.setCharacterEncoding("UTF-8");

String userID = null;

// 로그인 상태라면
if (session.getAttribute("userID") != null) {
	userID = (String) session.getAttribute("userID");
}

// 기본적으로 사용자는 로그인이 되어있는 상태 이여야 강의평가를 등록 할 수 있음
if (userID == null) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('로그인을 해주세요.');");
	script.println("location.href = 'userLogin.jsp';");
	script.println("</script>");
	script.close();
	return;
}

int lectureYear = 0;
String lectureName = null, professorName = null, semesterDivide = null, lectureDivide = null, evaluationTitle = null,
		evaluationContent = null, totalScore = null, creditScore = null, comfortableScore = null, lectureScore = null;

// 사용자로부터 입력을 받는 곳
if (request.getParameter("lectureName") != null) {
	lectureName = request.getParameter("lectureName");
}

	System.out.println(lectureName);
	
if (request.getParameter("professorName") != null) {
	professorName = request.getParameter("professorName");
}

System.out.println(professorName);

if (request.getParameter("lectureYear") != null) {

	try {
		// letctureYear 같은 경우 int 형이기 때문에 형변환을 해주어야 합니다.
		lectureYear = Integer.parseInt(request.getParameter("lectureYear"));
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("강의 년도 데이터 오류");
	}
}

System.out.println(lectureYear);

if (request.getParameter("semesterDivide") != null) {
	semesterDivide = request.getParameter("semesterDivide");
}

System.out.println(semesterDivide);

if (request.getParameter("lectureDivide") != null) {
	lectureDivide = request.getParameter("lectureDivide");
}

System.out.println(lectureDivide);

if (request.getParameter("evaluationTitle") != null) {
	evaluationTitle = request.getParameter("evaluationTitle");
}

System.out.println(evaluationTitle);

if (request.getParameter("evaluationContent") != null) {
	evaluationContent = request.getParameter("evaluationContent");
}

System.out.println(evaluationContent);

if (request.getParameter("totalScore") != null) {
	totalScore = request.getParameter("totalScore");
}

System.out.println(totalScore);

if (request.getParameter("creditScore") != null) {
	creditScore = request.getParameter("creditScore");
}

System.out.println(creditScore);

if (request.getParameter("comfortableScore") != null) {
	comfortableScore = request.getParameter("comfortableScore");
}

System.out.println(comfortableScore);

if (request.getParameter("lectureScore") != null) {
	lectureScore = request.getParameter("lectureScore");
}


System.out.println(lectureScore);

// 사용자가 정상적으로 입력하지 않은 경우
if (userID == null || lectureName == null || professorName == null || lectureYear == 0 || semesterDivide == null
		|| lectureDivide == null || evaluationTitle == null || evaluationContent == null || totalScore == null
		|| creditScore == null || comfortableScore == null || lectureScore == null || evaluationTitle.equals("")
		|| evaluationContent.equals("")) { // 제목과 내용이 공백일 경우도 있음....
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('입력이 안 된 사항이 있습니다.');");
	// 입력이 되지 않아 다시 뒤쪽으로 돌려 보내는 history.back() 함수 사용
	script.println("history.back();");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.
}

// 정상입력 한경우 => 회원가입
EvaluationDAO evaluationDAO = new EvaluationDAO();
int result = evaluationDAO.write(new EvaluationDTO(0, userID, lectureName, professorName, lectureYear, semesterDivide,
		lectureDivide, evaluationTitle, evaluationContent, totalScore, creditScore, comfortableScore, lectureScore, 0));

if (result == -1) { // DAO 의 join함수에서 회원가입 실패시 -1을 반환

	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('강의 평가 등록을 실패했습니다.');");
	// 입력이 되지 않아 다시 뒤쪽으로 돌려 보내는 history.back() 함수 사용
	script.println("history.back();");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.

} else { // 성공시 1을 반환

	PrintWriter script = response.getWriter();
	script.println("<script>");
	// 로그인 성공시 특정페이지로 이동
	// 즉 사용자가 회원가입이 성공되고 mail 인증을 받을 수 있도록 바로 인증 페이지로 넘어 갑니다.
	script.println("location.href='index.jsp'");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.

}
%>