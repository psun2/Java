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

@WebServlet("/BoardReplyServlet")
public class BoardReplyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		MultipartRequest multi = null;
		String saveDirectory = request.getSession().getServletContext().getRealPath("/upload");
		int fileMaxSize = 10 * 1024 * 1024;

		try {
			multi = new MultipartRequest(request, saveDirectory, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "파일 크기는 10MB를 넘을 수 없습니다.");
			response.sendRedirect("boardWrite.jsp");
			return;
		}

		System.out.println(multi);
		// 폼이 encType 이 enctype="multipart/form-data"
		// 이므로 request 로는 폼의 값을 긁어 들어 올 수 없으며
		// mutipart request를 사용 해야 합니다.
		String userID = URLDecoder.decode(multi.getParameter("userID"), "UTF-8");
		// System.out.println(request.getParameter("userID")); //
		// enctype="multipart/form-data" 이 없을때
		// 작동 확인

		// 아이디 검증
		if (userID == null || userID.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "현재 로그인이 되어있지 않습니다.");
			response.sendRedirect("login.jsp");
			return;
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

		String boardID = URLDecoder.decode(multi.getParameter("boardID"), "UTF-8");
		if (boardID == null || boardID.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("index.jsp");
			return;
		}

		String boardTitle = URLDecoder.decode(multi.getParameter("boardTitle"), "UTF-8");
		String boardContent = URLDecoder.decode(multi.getParameter("boardContent"), "UTF-8");
		if (boardTitle == null || boardTitle.equals("") || boardContent == null || boardContent.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "내용을 모두 채워주세요.");
			response.sendRedirect("boardWrite.jsp");
			return;
		}

		String boardFile = "";
		String boardRealFile = "";

		File file = multi.getFile("boardFile");

//		String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);

		// 기본적으로 파일 확장자 제한은 존재 하지 않도록 설정 물론 각종 보안상의 이슈가 됨....
//		if(ext.equals("jpg")|| ext.equals("png") || ext.equals("gif")) {
//			
//		}

		if (file != null) {
			boardFile = multi.getOriginalFileName("boardFile");
			boardRealFile = file.getName();
		}

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO parent = boardDAO.getBoard(boardID); // 같은 구룹 내에서도 시퀀스를 내림 차순으로 정렬 함으로써, 전에 있던 글들은
		// 모두 sequence 에 1 씩을 더하여 최신 답변보다 아래 보 일 수 있도록 합니다.
		boardDAO.replyUpdate(parent);
		boardDAO.reply(userID, boardTitle, boardContent, boardFile, boardRealFile, parent);
		session.setAttribute("messageType", "성공 메시지");
		session.setAttribute("messageContent", "성공적으로 답변이 작성되었습니다.");
		response.sendRedirect("boardView.jsp");
		return;
	}

}
