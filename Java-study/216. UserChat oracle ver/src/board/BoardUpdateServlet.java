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

@WebServlet("/BoardUpdateServlet")
public class BoardUpdateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		MultipartRequest multi = null;

		String saveDirectory = session.getServletContext().getRealPath("/upload");
		int fileMaxSize = 10 * 1021 * 1024;

		try {
			multi = new MultipartRequest(request, saveDirectory, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "파일 크기는 10MB를 넘을 수 없습니다.");
			response.sendRedirect("boardView.jsp");
			return;
		}

		String userID = URLDecoder.decode(multi.getParameter("userID"), "UTF-8");

		if (userID == null || userID.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("login.jsp");
			return;
		}

		String boardID = URLDecoder.decode(multi.getParameter("boardID"), "UTF-8");

		if (boardID == null || boardID.equals("")) { // boardID 를 전달받지 못햇다면...
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("boardView.jsp");
			return;
		}

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO board = boardDAO.getBoard(boardID);

		if (!userID.equals(board.getUserID())) { // 현재 세션에 로그인된 사용자와 글 수정을 하는 글 의 사용자가 다른경우
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("boardView.jsp");
			return;
		}

		String boardTitle = URLDecoder.decode(multi.getParameter("boardTitle"), "UTF-8");
		String boardContent = URLDecoder.decode(multi.getParameter("boardContent"), "UTF-8");

		if (boardTitle == null || boardTitle.equals("") || boardContent == null || boardContent.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "내용을 모두 채워주세요.");
			response.sendRedirect("boardView.jsp");
			return;
		}

		File file = multi.getFile("boardFile");
		String boardFile = "";
		String boardRealFile = "";

		if (file != null) {

			boardFile = multi.getOriginalFileName("boardFile");
			boardRealFile = file.getName();

			File prevFile = new File(saveDirectory, boardDAO.getRealFile(boardID));

			if (prevFile.exists())
				prevFile.delete();
		} else { // 혹시 기존의 파일이 존재 하는 경우
			boardFile = boardDAO.getFile(boardID);
			boardRealFile = boardDAO.getRealFile(boardID);
		}

		boardDAO.update(boardID, boardTitle, boardContent, boardFile, boardRealFile);
		session.setAttribute("messageType", "성공 메시지");
		session.setAttribute("messageContent", "성공적으로 게시물이 수정되었습니다.");
		response.sendRedirect("boardShow.jsp?boardID=" + boardID);
		return;

	}

}
