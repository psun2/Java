package user;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserFindServlet")
public class UserFindServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String userID = request.getParameter("userID");
		userID = URLDecoder.decode(userID, "UTF-8");

		if (userID == null || userID.equals("")) { // 사용자의 아이디 값을 받지 못할 때
			response.getWriter().write("-1");
		} else if (new UserDAO().registerCheck(userID) == 0) {
			// 해당사용자가 존재 할때
			try {
				response.getWriter().write(find(userID));
			} catch (Exception e) { // find 함수를 통해 반환 하지 못 할 때,,,,
				// TODO Auto-generated catch block
				e.printStackTrace();
				response.getWriter().write("-1");
			}
		} else {
			// 사용자의 값은 받았으나, 사용자가 존재 하지 않을때
			response.getWriter().write("-1");
		}

	}

	public String find(String userID) throws Exception {
		StringBuffer result = new StringBuffer("");
		result.append("{\"userProfile\":\"" + new UserDAO().getProfile(userID) + "\"}");
		System.out.println(result);
		System.out.println(result.toString());
		return result.toString();
	}

}