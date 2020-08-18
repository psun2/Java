package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// doPost : 사용자가 어떠한 정보를 post 방식으로 전달했을때 처리하는 메소드
	// post 방식으로 처리한다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String userID = request.getParameter("userID");
		String userPassword1 = request.getParameter("userPassword1");
		String userPassword2 = request.getParameter("userPassword2");
		String userName = request.getParameter("userName");
		// input창의 값을 request 받으므로 String
		String userAge = request.getParameter("userAge");
		String userGender = request.getParameter("userGender");
		String userEmail = request.getParameter("userEmail");

		// 입력란이 공란인 경우
		if (userID == null || userID.equals("") || userPassword1 == null || userPassword1.equals("")
				|| userPassword2 == null || userPassword2.equals("") || userName == null || userName.equals("")
				|| userAge == null || userAge.equals("") || userGender == null || userGender.equals("")
				|| userEmail == null || userEmail.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
			response.sendRedirect("index.jsp");
			return;
		}

		// 사용자가 입력한 두개의 패스워드가 일치하지 않는 경우
		if (!userPassword1.equals(userPassword2)) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "비밀번호가 서로 일치하지 않습니다.");
			response.sendRedirect("index.jsp");
			return;
		}

		// 위의 과정을 거치면서 함수가 종료되지 않았다면, 모든 조건이 맞아 떨어지므로 회원가입을 진행합니다.
		UserDTO user = new UserDTO(userID, userPassword1, userName, userAge, userGender, userEmail);
		int result = new UserDAO().register(user);

		if (result == 1) { // 회원 가입 성공
			request.getSession().setAttribute("messageType", "성공 메시지");
			request.getSession().setAttribute("messageContent", "회원가입에 성공 했습니다.");
			response.sendRedirect("index.jsp");
		} else if (result == 0) { // 이미 존재하는 회원
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "이미존재 하는 회원입니다.");
			response.sendRedirect("index.jsp");
		} else if (result == -1) { // 데이터베이스 오류
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "데이터베이스 오류 입니다.");
			response.sendRedirect("index.jsp");
		}
	}

}
