package member;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberFindServlet")
public class MemberFindServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String userID = URLDecoder.decode(request.getParameter("userID"), "UTF-8");

		if (userID == null || userID.equals(""))
			response.getWriter().write("-1"); // 유저 아이디가 없는 경우
		else if (new MemberDAO().registerCheck(userID) == 0) { // 이미 존재하는 회원 이라면

			try {
				response.getWriter().write(find(userID));
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().write("-1"); // 오류 발생
			}

		} else
			response.getWriter().write("-1"); // 유저 아이디가 없는 경우

	}

	public String find(String userID) throws Exception {
		StringBuffer result = new StringBuffer("");
		result.append("{\"userProfile\":\"" + new MemberDAO().getProfile(userID) + "\"}");
		return result.toString();
	}

}
