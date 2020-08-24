package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserRegisterCheckServlet")
public class UserRegisterCheckServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 사용자에게 아이디 중복체크 결과를 반환 할 수 있도록 합니다.

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// 요청
		String userID = request.getParameter("userID");

		// 사용자에대한 응답
		response.getWriter().write(new UserDAO().registerCheck(userID) + "");
		// + "" : new userDAO().registerCheck(userID) 의 반환값인 int형을 문자열로 바꾸어주기위해 빈 문자열을
		// 더해 줍니다.-

	}

}
