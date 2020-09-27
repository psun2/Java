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

@WebServlet("/BoardDeleteServlet")
public class BoardDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		String boardID = URLDecoder.decode(request.getParameter("boardID"), "UTF-8");

		if (boardID == null || boardID.equals("")) { // 아이디 값이 없다면....
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("index.jsp");
			return;
		}

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO board = boardDAO.getBoard(boardID);

		if (!userID.equals(board.getUserID())) { // 로그인 된사람과 게시글 작성자가 틀리다면
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("index.jsp");
			return;
		}

		String saveDirectory = session.getServletContext().getRealPath("/upload");
		String prev = boardDAO.getFile(boardID);
		int result = boardDAO.delete(boardID);

		if (result == -1) { // 삭제 실패
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "데이터베이스 오류");
			response.sendRedirect("boardShow.jsp?" + boardID);
			return;
		} else { // 삭제 성공
			File prevFile = new File(saveDirectory, prev);
			if (prevFile.exists())
				prevFile.delete();
			session.setAttribute("messageType", "성공 메시지");
			session.setAttribute("messageContent", "게시글 삭제에 성공 했습니다.");
			response.sendRedirect("boardView.jsp");
			return;
		}

	}

}
