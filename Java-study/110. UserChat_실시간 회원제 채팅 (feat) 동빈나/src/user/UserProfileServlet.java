package user;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		MultipartRequest multi = null;

		// 파일의 최대크기
		int fileMaxSize = 10 * 1024 * 1024;

		// 저장경로
		String savePath = request.getRealPath("/upload").replaceAll("\\\\", "/");
		// 실제 저장 경로
		// D:\Java\Java-study\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\110. UserChat_실시간 회원제 채팅 (feat) 동빈나

		try {
			// DefaultFileRenamePolicy : 기본적으로 파일의 이름이 겹치거나 한다면, DefaultFileRenamePolicy 객체를
			// 통한 파일이름을 변경 합니다.
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "파일 크기는 10MB를 넘을 수 없습니다.");
			response.sendRedirect("profileUpdate.jsp");
			return;
		}
		// 에러 발생이 안되었을시 사용자의 아이디 값을 가져 옵니다.
		// MultipartRequest 를 생성시 인자로 request 를 넘겨주어 getparameter을 통해 입력 값을 가져 올 수 있는 거라
		// 생각 됩니다.
		String userID = multi.getParameter("userID");

		// 세션을 가져옵니다.
		// why? 다른 사람의 프로필 사진을 바꿀 수 없게 합니다.
		HttpSession session = request.getSession();
		if (!userID.equals((String) session.getAttribute("userID"))) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근 할 수 없습니다.");
			response.sendRedirect("index.jsp");
			return;
		}

		// 성공적으로 프로필을 변경 하려고 하는 경우
		String fileName = "";
		
		// form 에서 name 값을 가져옴 value
		File file = multi.getFile("userProfile");
		System.out.println(file);
		
		// 파일이 존재 하는 경우
		if (file != null) {

			// 확장자 처리
			String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);

			// 아래와 같은 확장자 명을 가질때만 파일을 업로드 할 수 있게 합니다.
			if (ext.equals("jpg") || ext.equals("png") || ext.equals("gif")) {
				String prev = new UserDAO().getUser(userID).getUserProfile();
				File prevFile = new File(savePath + "/" + prev);
				System.out.println("prev(데이터베이스에 등록 되어 있는 사용자의 이전 프로필 사진명) : " + prev);
				System.out.println("prevFile(경로 + 사용자의 이전 프로필 사진명) : " + prevFile);
				System.out.println("prevFile.exists() : " + prevFile.exists());

				// 기존에 프로필 파일이 존재 하는 경우 지워 주워 주고 새로운 프로필 사진으로 업데이트 합니다.
				// exist : 존재 하다
				if (prevFile.exists()) {
					prevFile.delete();
				}

				fileName = file.getName();
			} else {

				if (file.exists()) {
					file.delete();
				}

				session.setAttribute("messageType", "오류 메시지");
				session.setAttribute("messageContent", "이미지 파일만 업로드 가능합니다.");
				response.sendRedirect("profileUpdate.jsp");
				return;
			}
		}
		// 파일 업로드가 성공되어 아래까지 코드가 흐를때,

		// 데이터베이스 업데이트
		new UserDAO().profile(userID, fileName);

		session.setAttribute("messageType", "성공 메시지");
		session.setAttribute("messageContent", "성공적으로 프로필이 변경되었습니다.");
		response.sendRedirect("index.jsp");
		return;

	}

}
