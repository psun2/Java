<%@page import="evaluation.EvaluationDAO"%>
<%@page import="evaluation.EvaluationDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="evaluation.EvaluationDAO"%>
<%@page import="evaluation.EvaluationDTO"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		System.out.println("진입");
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	String userID = null;
	if (session.getAttribute("userID") != null)
		userID = (String) session.getAttribute("userID");

	if (userID == null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('로그인을 해주세요.');");
		script.println("location.href='userLogin.jsp'");
		script.println("</script>");
		script.close();
		return;
	}

	String lectureName = null;
	String professorName = null;
	int lectureYear = -1;
	String semesterDevied = null;
	String lectureDevied = null;
	String evaluationTitle = null;
	String evaluationContent = null;
	String totalScore = null;
	String creditScore = null;
	String comfortableScore = null;
	String lectureScore = null;

	if (request.getParameter("lectureName") != null)
		lectureName = URLDecoder.decode(request.getParameter("lectureName"), "UTF-8");
	System.out.println(lectureName);

	if (request.getParameter("professorName") != null)
		professorName = URLDecoder.decode(request.getParameter("professorName"), "UTF-8");
	System.out.println(professorName);

	if (request.getParameter("lectureYear") != null)
		lectureYear = Integer.parseInt(URLDecoder.decode(request.getParameter("lectureYear"), "UTF-8"));
	System.out.println(lectureYear);

	if (request.getParameter("semesterDevied") != null)
		semesterDevied = URLDecoder.decode(request.getParameter("semesterDevied"), "UTF-8");
	System.out.println(semesterDevied);

	if (request.getParameter("lectureDevied") != null)
		lectureDevied = URLDecoder.decode(request.getParameter("lectureDevied"), "UTF-8");
	System.out.println(lectureDevied);

	if (request.getParameter("evaluationTitle") != null)
		evaluationTitle = URLDecoder.decode(request.getParameter("evaluationTitle"), "UTF-8");
	System.out.println(evaluationTitle);

	if (request.getParameter("evaluationContent") != null)
		evaluationContent = URLDecoder.decode(request.getParameter("evaluationContent"), "UTF-8");
	System.out.println(evaluationContent);

	if (request.getParameter("totalScore") != null)
		totalScore = URLDecoder.decode(request.getParameter("totalScore"), "UTF-8");
	System.out.println(totalScore);

	if (request.getParameter("creditScore") != null)
		creditScore = URLDecoder.decode(request.getParameter("creditScore"), "UTF-8");
	System.out.println(creditScore);

	if (request.getParameter("comfortableScore") != null)
		comfortableScore = URLDecoder.decode(request.getParameter("comfortableScore"), "UTF-8");
	System.out.println(comfortableScore);

	if (request.getParameter("lectureScore") != null)
		lectureScore = URLDecoder.decode(request.getParameter("lectureScore"), "UTF-8");
	System.out.println(lectureScore);

	if (userID == null || userID.equals("") || lectureName == null || lectureName.equals("") || professorName == null
			|| professorName.equals("") || lectureYear == -1 || semesterDevied == null || semesterDevied.equals("")
			|| lectureDevied == null || lectureDevied.equals("") || evaluationTitle == null || evaluationTitle.equals("")
			|| evaluationContent == null || evaluationContent.equals("") || totalScore == null || totalScore.equals("")
			|| creditScore == null || creditScore.equals("") || comfortableScore == null || comfortableScore.equals("")
			|| lectureScore == null || lectureScore.equals("")) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('입력이 안된 사항이 있습니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}

	EvaluationDTO evaluation = new EvaluationDTO();
	evaluation.setUserID(userID);
	evaluation.setLectureName(lectureName);
	evaluation.setProfessorName(professorName);
	evaluation.setLectureYear(lectureYear);
	evaluation.setSemesterDevide(semesterDevied);
	evaluation.setLectureDevide(lectureDevied);
	evaluation.setEvaluationTitle(evaluationTitle);
	evaluation.setEvaluationContent(evaluationContent);
	evaluation.setTotlaScore(totalScore);
	evaluation.setCreditScore(creditScore);
	evaluation.setComportableScore(comfortableScore);
	evaluation.setLectureScore(lectureScore);
	
	System.out.println(evaluation);

	EvaluationDAO evaluationDAO = new EvaluationDAO();

	int result = evaluationDAO.write(evaluation);
	System.out.println(result);

	if (result == 1) { // 회원 가입 성공
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('강의평가가 완료되었습니다.');");
		script.println("location.href='index.jsp'");
		script.println("</script>");
		script.close();
	} else { // 회원가입 실패
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('데이터베이스 오류입니다.');");
		script.println("history.back();");
		script.println("</script>");
		script.close();
		return;
	}
	%>
</body>
</html>