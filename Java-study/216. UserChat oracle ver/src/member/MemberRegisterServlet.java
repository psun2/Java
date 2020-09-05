package member;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO 회원가입 서블릿

@WebServlet("/MemberRegisterServlet")
public class MemberRegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String userID = URLDecoder.decode(request.getParameter("userID"), "UTF-8");
		String userPassword1 = URLDecoder.decode(request.getParameter("userPassword1"), "UTF-8");
		String userPassword2 = URLDecoder.decode(request.getParameter("userPassword2"), "UTF-8");
		String userName = URLDecoder.decode(request.getParameter("userName"), "UTF-8");
		String userAge = URLDecoder.decode(request.getParameter("userAge"), "UTF-8");
		String userGender = URLDecoder.decode(request.getParameter("userGender"), "UTF-8");
		String userEmail = URLDecoder.decode(request.getParameter("userEmail"), "UTF-8");

		// userProfile 값이 없으므로, Decode 시킬시 Decode Exception이 발생합니다.
		String userProfile = request.getParameter("userProfile");

		// userProfile : 강제성이 없으므로, 체크하는부분에서 빼주도록 합니다.
		String[] userInfo = { userID, userPassword1, userPassword2, userName, userAge, userGender, userEmail };

		// join.jsp 의 빈 값이 있나 확인
		for (String user : userInfo) {
			if (user == null || user.equals("")) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");

				// 세션을 사용하여 등록하여야 페이지가 바뀌어도 그 세션값이 남아있게 되므로 getSession을 통해 set을 할 수 있도록합니다.
				// 아래와 같으면 session 값이 넘어가지 않아... 모달이 뜨지 않습니다.
//				request.setAttribute("messageType", "오류 메시지");
//				request.setAttribute("messageContent", "모든 내용을 입력하세요.");
				response.sendRedirect("join.jsp");
				return;
			}
		}

		// join.jsp 의 비밀번호와 비밀번호 확인이 값이 같은지 확인
		if (!userPassword1.equals(userPassword2)) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "비밀번호가 서로 다릅니다.");

			// 세션을 사용하여 등록하여야 페이지가 바뀌어도 그 세션값이 남아있게 되므로 getSession을 통해 set을 할 수 있도록합니다.
			// 아래와 같으면 session 값이 넘어가지 않아... 모달이 뜨지 않습니다.
//			request.setAttribute("messageType", "오류 메시지");
//			request.setAttribute("messageContent", "비밀번호가 서로 다릅니다.");
			response.sendRedirect("join.jsp");
			return;
		}

		// 실제로 DataBase에 등록하는 과정
		int result = new MemberDAO().register(userID, userPassword1, userName, userAge, userGender, userEmail,
				userProfile);

		if (result == 1) { // 회원가입 성공
			request.getSession().setAttribute("messageType", "성공 메시지");
			request.getSession().setAttribute("messageContent", "회원가입에 성공했습니다.");

			// 세션을 사용하여 등록하여야 페이지가 바뀌어도 그 세션값이 남아있게 되므로 getSession을 통해 set을 할 수 있도록합니다.
			// 아래와 같으면 session 값이 넘어가지 않아... 모달이 뜨지 않습니다.
//			request.setAttribute("messageType", "성공 메시지");
//			request.setAttribute("messageContent", "회원가입에 성공했습니다.");
			response.sendRedirect("index.jsp");
			return;
		} else { // 회원가입 실패
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "회원가입에 실패했습니다.");

			// 세션을 사용하여 등록하여야 페이지가 바뀌어도 그 세션값이 남아있게 되므로 getSession을 통해 set을 할 수 있도록합니다.
			// 아래와 같으면 session 값이 넘어가지 않아... 모달이 뜨지 않습니다.
//			request.setAttribute("messageType", "오류 메시지");
//			request.setAttribute("messageContent", "회원가입에 실패했습니다.");
			response.sendRedirect("join.jsp");
			return;
		}
	}

}
