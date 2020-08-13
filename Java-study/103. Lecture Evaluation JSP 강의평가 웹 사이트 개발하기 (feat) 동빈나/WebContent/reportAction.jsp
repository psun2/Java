<%@page import="javax.mail.internet.InternetAddress"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 메일을 전송하기 위하여 기본적으로 javax.mail.* 라이브러리를 임포트 -->
<%@ page import="javax.mail.Transport"%>
<%@ page import="javax.mail.Message"%>
<%@ page import="javax.mail.Address"%>
<%@ page import="javax.mail.internet.InternetAddress"%>
<%@ page import="javax.mail.internet.MimeMessage"%>
<%@ page import="javax.mail.Session"%>
<%@ page import="javax.mail.Authenticator"%>

<!-- 만든 Gmail 라이브러리 사용 -->
<%@ page import="util.Gmail"%>

<!-- java.util.Properties : 속성 정의 라이브러리 -->
<%@ page import="java.util.Properties"%>

<%@ page import="user.UserDAO"%>
<%@ page import="util.SHA256"%>

<!-- java.io.PrintWriter : 특정한 스트립트 구문을 실행 할 때 사용 -->
<%@ page import="java.io.PrintWriter"%>


<%
	UserDAO userDAO = new UserDAO();
String userID = null; // 초기에 기본적으로 사용자의 아이디는 null 값을 가지고 있음
if (session.getAttribute("userID") != null) {
	// 현재 사용자가 로그인 한 상태 
	// 즉, 세션값이 유효한 상태 일때
	userID = (String) session.getAttribute("userID");
}

if (userID == null) { // 사용자가 로그인 하지 않은 경우
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('로그인을 해주세요.');");
	// 경고 메세지 후 로그인 페이지로 이동
	script.println("location.href = 'userLogin.jsp'");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.
}

// 인코딩 셋팅
request.setCharacterEncoding("UTF-8");
String reportTitle = null;
String reportContent = null;

if (request.getParameter("reportTitle") != null) {
	reportTitle = request.getParameter("reportTitle");
}

System.out.println();
System.out.println("getParameter : " + request.getParameter("reportTitle"));
// 세션을 등록 한 적이 없어 null
System.out.println("getAttribute : " + session.getAttribute("reportTitle"));
System.out.println();

if (request.getParameter("reportContent") != null) {
	reportContent = request.getParameter("reportContent");
}
System.out.println();
System.out.println("getParameter : " + request.getParameter("reportContent"));
//세션을 등록 한 적이 없어 null
System.out.println("getAttribute : " + session.getAttribute("reportContent"));
System.out.println();

if (reportTitle == null || reportContent == null) {
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('입력이 안된 사항이 있습니다.');");
	script.println("history.back();");
	script.println("</script>");
	script.close();
	return;
}

// host는 웹페이지의 주소를 그대로 넣어 주시면 됩니다.
String host = "http://localhost:8080/103._Lecture_Evaluation/";

// 보내는 사람
// 자신의 이메일 아이디를 넣어 주시면 됩니다.
// String from = "자신의 구글 이메일 계정";
String from = "tjddjs90test@gmail.com";
System.out.println("userEmail : " + from);
// TDDO: 사용자자의 이메일로 나에게 보내려면 Gmail클래스의 내용을 변동을 해야 하는데 나는 사용자의 이메일 비밀번호를 모른다........

// 받는 사람
// String to = userDAO.getUserEmail(userID);
String to = "tjddjs90test@gmail.com";

// 제목
String subject = "(Javax.mail 라이브러리 TEST)강의평가 사이트에서 신고된 메일 입니다.";

// 내용
String content = "신고자 : " + userID + "<br/> 제목 : " + reportTitle + "<br/>신고내용 : " + reportContent + "";

// 실제로 smtp에 접속하기위한 정보를 넣어 줍니다.
Properties p = new Properties();

// 구글 smtp 서버를 이용하기 위해서
p.put("mail.smtp.host", "smtp.googlemail.com"); // 서버 호스트
p.put("mail.smtp.port", "465"); // port
p.put("mail.smtp.starttls.enable", "true"); // 사용가능여부
p.put("mail.smtp.auth", "true"); // 인증
p.put("mail.smtp.debug", "true"); // 디버그
p.put("mail.smtp.socketFactory.port", "465");
p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
p.put("mail.smtp.socketFactory.fallback", "false");
p.put("mail.smtp.user", from);

try { // 실제로 메일을 보내는 과정
	Authenticator auth = new Gmail();
	// TDDO: 사용자자의 이메일로 나에게 보내려면 Gmail클래스의 내용을 변동을 해야 하는데 나는 사용자의 이메일 비밀번호를 모른다........
	System.out.println("auth : " + auth);
	Session ses = Session.getInstance(p, auth);
	ses.setDebug(true);
	MimeMessage msg = new MimeMessage(ses);
	msg.setSubject(subject);
	Address fromAddr = new InternetAddress(from);
	msg.setFrom(fromAddr);
	// TDDO: 사용자자의 이메일로 나에게 보내려면 Gmail클래스의 내용을 변동을 해야 하는데 나는 사용자의 이메일 비밀번호를 모른다........
	System.out.println("fromAddr : " + fromAddr);
	Address toAddr = new InternetAddress(to);
	msg.addRecipient(Message.RecipientType.TO, toAddr);
	msg.setContent(content, "text/html;charset=UTF-8");
	Transport.send(msg);
} catch (Exception e) { // 메일 전송시 오류가 발생한 경우 
	e.printStackTrace();
	PrintWriter script = response.getWriter();
	script.println("<script>");
	script.println("alert('오류가 발생했습니다.');");
	script.println("history.back();");
	script.println("</script>");
	script.close();
	return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.
}

// 오류가 발생하지 않은 경우
PrintWriter script = response.getWriter();
script.println("<script>");
script.println("alert('정상적으로 신고 되었습니다.');");
script.println("history.back();");
script.println("</script>");
script.close();
return; // 오류가 발생한 경우 jsp페이지의 작동을 바로 종료 할 수 있도록 합니다.
%>