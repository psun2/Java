package member;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/MemberProfileServlet")
public class MemberProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; carset=UTF-8");

		MultipartRequest multi = null;
//		JAVA 에서 request.getRealPath("/"); 는 deprecated되었다.
//		이유는 ServletContext에서도 있는걸 중복으로 가지고 있어서 그렇다고 한다.

		String basicSavePath = request.getRealPath("/upload").replace("\\\\", "/");
		System.out.println("basicSavePath : " + basicSavePath);
		String savePath = request.getSession().getServletContext().getRealPath("/upload");
		System.out.println("savePath : " + savePath);

		int fileMaxSize = 10 * 1024 * 1024;

		try {
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "파일 크기는 10MB를 넘을 수 없습니다.");
			response.sendRedirect("profileUpdate.jsp");
			return;
		}

		// 폼이 encType 이 enctype="multipart/form-data"
		// 이므로 request 로는 폼의 값을 긁어 들어 올 수 없으며
		// mutipart request를 사용 해야 합니다.
		String userID = multi.getParameter("userID");
		System.out.println(userID);
		// System.out.println(request.getParameter("userID")); //
		// enctype="multipart/form-data" 이 없을때
		// 작동 확인

		// 아이디 검증
		if (userID == null || userID.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "현재 로그인이 되어있지 않습니다.");
			response.sendRedirect("login.jsp");
			return;
		} else {
			userID = URLDecoder.decode(userID, "UTF-8");
		}

		// 세션에 로그인 되어있는 아이디 검증
		HttpSession session = request.getSession();
		String sessionID = (String) session.getAttribute("userID");
		if (!userID.equals(sessionID)) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("index.jsp");
			return;
		}

		String fileName = "";
		File file = multi.getFile("userProfile");

		if (file != null) {
			String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
			if (ext.equals("jpg") || ext.equals("png") || ext.equals("gif")) {
				String prev = new MemberDAO().getUser(userID).getUserProfile();
				File prevFile = new File(savePath + "/" + prev); // 기존에 사용하던 프로필 사진
				if (prevFile.exists()) // 이전 프로필 사진이 존재하는 경우에는
					prevFile.delete(); // 파일 삭제

				fileName = file.getName();
			} else { // 파일이 잘못된 경우 즉 확장자에 맞지 않는 경우
				if (file.exists())
					file.delete();
				session.setAttribute("messageType", "오류 메시지");
				session.setAttribute("messageContent", "이미지 파일만 업로드 가능 합니다.");
				response.sendRedirect("profileUpdate.jsp");
				return;
			}
		} else { // 파일이 없는 경우
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "파일을 다시 확인해 주세요.");
			response.sendRedirect("profileUpdate.jsp");
			return;
		}

		int result = new MemberDAO().profile(userID, fileName);

		if (result == 1) {
			session.setAttribute("messageType", "성공 메시지");
			session.setAttribute("messageContent", "프로필이 정상적으로 업데이트 되었습니다.");
			response.sendRedirect("index.jsp");
			return;
		} else {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "데이터베이스 오류.");
			response.sendRedirect("profileUpdate.jsp");
			return;
		}

	}

}
