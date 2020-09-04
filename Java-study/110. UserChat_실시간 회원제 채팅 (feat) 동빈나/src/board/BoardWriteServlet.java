package board;

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

@WebServlet("/BoardWriteServlet")
public class BoardWriteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		MultipartRequest multi = null;
		String savePath = request.getRealPath("/upload").replaceAll("\\\\", "/");
		int fileMaxSize = 10 * 1024 * 1024;

		try {
			// 파일을 직접적으로 업로드 하는 부분입니다.
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "파일 크기는 10MB를 넘을 수 없습니다.");
			response.sendRedirect("boardWrite.jsp");
			return;
		}

		// 세션 즉 로그인 되어있는 아이디와 접근하는 아이디가 다른 경우!
		String userID = URLDecoder.decode(multi.getParameter("userID"), "UTF-8");
		HttpSession session = request.getSession();
		if (!userID.equals((String) session.getAttribute("userID"))) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("index.jsp");
			return;
		}

		String boardTitle = URLDecoder.decode(multi.getParameter("boardTitle"), "UTF-8");
		String boardContent = URLDecoder.decode(multi.getParameter("boardContent"), "UTF-8");

		if (boardTitle == null || boardTitle.equals("") || boardContent == null || boardContent.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messgeeContent", "내용을 모두 채 주세요.");
			response.sendRedirect("boardWrite.jsp");
			return;
		}

		String boardFile = "";
		String boardRealFile = "";

		File file = multi.getFile("boardFile");

		// 보안 관련 이슈들은 이번 강의에서 다루지 않습니다.
		if (file != null) {
			String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
			System.out.println("파일의 확장자 : " + ext);

			boardFile = multi.getOriginalFileName("boardFile");
			boardRealFile = file.getName();

			System.out.println("boardFile : " + boardFile);
			System.out.println("boardRealFile : " + boardRealFile);
		}

		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.write(userID, boardTitle, boardContent, boardFile, boardRealFile);

		if (result == 1) {

			session.setAttribute("messageType", "성공 메시지");
			session.setAttribute("messageContent", "성공적으로 게시물이 작성되었습니다.");
			response.sendRedirect("boardView.jsp");
			return;

		} else if (result == -1) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "데이터베이스 오류가 발생했습니다.");
			response.sendRedirect("boardView.jsp");
			return;
		}
	}

}
