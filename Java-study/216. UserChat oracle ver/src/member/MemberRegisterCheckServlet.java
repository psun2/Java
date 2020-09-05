package member;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO 아이디 중복체크 서블릿

@WebServlet("/MemberRegisterCheckServlet")
public class MemberRegisterCheckServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String userID = URLDecoder.decode(request.getParameter("userID"), "UTF-8");

		// join.jsp 에 반환 (즉, 사용자에게 반환)
		response.getWriter().write(new MemberDAO().registerCheck(userID) + "");
	}

}
