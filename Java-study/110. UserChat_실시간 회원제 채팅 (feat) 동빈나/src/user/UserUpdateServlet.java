package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// 실제로 회원 가입이 이뤄어짐

		// "userID" : input 태그의 name form 을 통해 넘어온 값 입니다.
		String userID = request.getParameter("userID");

		String userPassword1 = request.getParameter("userPassword1"); // 비밀번호
		String userPassword2 = request.getParameter("userPassword2"); // 비밀번호 체크
		String userName = request.getParameter("userName");
		String userAge = request.getParameter("userAge");
		String userGender = request.getParameter("userGender");
		String userEmail = request.getParameter("userEmail");

		// 프로필 사진은 사용자에 따라 등록 또는 미등록을 할 수 있으므로, 필수요소에서 빼도록 하겠습니다.
		String[] infos = { userID, userPassword1, userPassword2, userName, userAge, userGender, userEmail };

		for (String info : infos) {
			if (info == null || info.equals("")) {
				request.getSession().setAttribute("messageType", "오류 메시지");
				request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요.");
				// 설정한 세션 정보를 join.jsp로 보내줍니다.
				response.sendRedirect("update.jsp");
				System.out.println("null 보다 userRegister의 코드가 먼저 뜨잔아 ?");
				return;
			}
		}

		// 원 코드
		// 회원정보중 한 개라도 null 또는 공백인 경우
//		if (userID == null || userID.equals("") || userPassword1 == null || userPassword1.equals("")
//				|| userPassword2 == null || userPassword2.equals("") || userName == null || userName.equals("")
//				|| userAge == null || userAge.equals("") || userGender == null || userGender.equals("")
//				|| userEmail == null || userEmail.equals("") || userProfile == null || userProfile.equals("")) {
//			request.getSession().setAttribute("messageType", "오류 메시지");
//			request.getSession().setAttribute("messageContetn", "모든 내용을 입력하세요.");
//			// 설정한 세션 정보를 join.jsp로 보내줍니다.
//			response.sendRedirect("join.jsp");
//			return;
//		}

		// 반듯이 본인이여야 하기때문에 session값 검증이 필수 입니다.
		HttpSession session = request.getSession();
		if (!userID.equals((String) session.getAttribute("userID"))) {
			// 로그인 되어있는 아이디와 현재 서블릿으로 넘어온 아이디가 같은지 검증
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("update.jsp");
			return;
		}

		// 비밀번호 와 비밀번호 확인의 값이 다른 경우
		if (!userPassword1.equals(userPassword2)) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContetn", "비밀번호가 서로 다릅니다.");
			// 설정한 세션 정보를 join.jsp로 보내줍니다.
			response.sendRedirect("update.jsp");
			return;
		}

		// 모든 조건문을 거쳐 return 을 하지 않았으므로, 함수는 살아있는 상태고 회원가입이 가능한 상태 입니다.
		int result = new UserDAO().update(userID, userPassword1, userName, userAge, userGender, userEmail);

		// 원 코드
//		if(result == 1) {
//			request.getSession().setAttribute("messageType", "성공 메시지");
//			request.getSession().setAttribute("messageContetn", "회원가입에 성공했습니다.");
//			// 회원가입에 성공했으므로, 설정한 세션 정보를 index.jsp로 보내줍니다.
//			response.sendRedirect("index.jsp");
//			return;			
//		} else {
//			request.getSession().setAttribute("messageType", "오류 메시지");
//			request.getSession().setAttribute("messageContetn", "이미 존재하는 회원입니다..");
//			// 설정한 세션 정보를 join.jsp로 보내줍니다.
//			response.sendRedirect("join.jsp");
//			return;			
//		}
		System.out.println("result : " + result);
		switch (result) {
		case 1:
			// 회원가입 성공시 로그인 상태로 되어 있기 위해 session을 잡아줍니다.
			request.getSession().setAttribute("userID", userID);
			request.getSession().setAttribute("messageType", "성공 메시지");
			request.getSession().setAttribute("messageContent", "회원정보 수정에 성공했습니다.");
			// 회원가입에 성공했으므로, 설정한 세션 정보를 index.jsp로 보내줍니다.
			response.sendRedirect("index.jsp");
			break;
		default:
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "데이터베이스 오류가 발생했습니다.");
			// 설정한 세션 정보를 join.jsp로 보내줍니다.
			response.sendRedirect("update.jsp");
			break;
		}

	}

}
