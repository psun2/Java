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

		System.out.println();
		System.out.println("BoardReplyServlet 진입");

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		MultipartRequest multi = null;

		String savePath = request.getRealPath("/upload").replaceAll("\\\\", "/");

		int fileMaxSize = 10 * 1024 * 1024; // 10MB

		HttpSession session = request.getSession();

		try {
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("boardView.jsp");
			return;
		}

		String boardID = URLDecoder.decode(multi.getParameter("boardID"), "UTF-8");
		System.out.println("boardID : " + boardID);

		if (boardID == null || boardID.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("boardView.jsp");
			return;
		}

		String userID = URLDecoder.decode(multi.getParameter("userID"), "UTF-8");

		// 글 작성자와 로그인된 아이디가 다른 경우
		if (!userID.equals(session.getAttribute("userID"))) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("boardView.jsp");
			return;
		}

		String boardTitle = URLDecoder.decode(multi.getParameter("boardTitle"), "UTF-8");
		String boardContent = URLDecoder.decode(multi.getParameter("boardContent"), "UTF-8");

		if (boardTitle == null || boardTitle.equals("") || boardContent == null || boardContent.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "내용을 모두 채 주세요.");
			response.sendRedirect("boardShow.jsp?boardID=" + boardID);
			return;
		}

		String boardFile = "";
		String boardRealFile = "";

		File file = multi.getFile("boardFile");

		if (file != null) {
			boardFile = multi.getOriginalFileName("boardFile");
			boardRealFile = file.getName();
		}

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO parent = boardDAO.getBoard(boardID);

		boardDAO.replyUpdate(parent);
		boardDAO.reply(userID, boardTitle, boardContent, boardFile, boardRealFile, parent);
		session.setAttribute("messageType", "성공 메시지");
		session.setAttribute("messageContent", "성공적으로 답변이 작성되었습니다.");
		response.sendRedirect("boardView.jsp");
		return;
	}

}
