package member;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MemberUpdateServlet")
public class MemberUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String userID = URLDecoder.decode(request.getParameter("userID"), "UTF-8");

		HttpSession session = null;
		if (userID != null) {
			session = request.getSession();
			// 현재 세션에 로그인 되어있는 사용자와 servlet에 접근하는 사용자가 일치하는지 여부를 위해 session 객체를 생성합니다
			userID = URLDecoder.decode(request.getParameter("userID"), "UTF-8");

			if (!userID.equals((String) session.getAttribute("userID"))) {
				session.setAttribute("messageType", "오류 메시지");
				session.setAttribute("messageContent", "접근 할 수 없습니다.");
				response.sendRedirect("index.jsp");
				return;
			}
		}

		String userPassword1 = URLDecoder.decode(request.getParameter("userPassword1"), "UTF-8");
		String userPassword2 = URLDecoder.decode(request.getParameter("userPassword2"), "UTF-8");
		String userName = URLDecoder.decode(request.getParameter("userName"), "UTF-8");
		String userAge = URLDecoder.decode(request.getParameter("userAge"), "UTF-8");
		String userGender = URLDecoder.decode(request.getParameter("userGender"), "UTF-8");
		String userEmail = URLDecoder.decode(request.getParameter("userEmail"), "UTF-8");

		if (userPassword1 == null || userPassword1.equals("") || userPassword2 == null || userPassword2.equals("")
				|| userName == null || userName.equals("") || userAge == null || userAge.equals("")
				|| userGender == null || userGender.equals("") || userEmail == null || userEmail.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "모든 내용을 입력하세요.");
			response.sendRedirect("update.jsp");
			return;
		}

		if (!userPassword1.equals(userPassword2)) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "비밀번호가 서로 일치하지 않습니다.");
			response.sendRedirect("update.jsp");
			return;
		}

		int result = new MemberDAO().update(userID, userPassword1, userName, userAge, userGender, userEmail);

		if (result == 1) {
			session.setAttribute("messageType", "성공 메시지");
			session.setAttribute("messageContent", "회원정보수정에 성공했습니다.");
			response.sendRedirect("index.jsp");
			return;
		} else {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "데이터베이스 오류가 발생했습니다.");
			response.sendRedirect("update.jsp");
			return;
		}

	}

}
