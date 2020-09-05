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

	// delete 를 실행시 url 이 아닌 바로 servlet을 호출하였으므로, get방식으로 넘어오게 되지만,
	// 이때 내부 적으로 doGet 메소드를 만들어 doPost에 request 와 response를 넘겨주는 방식으로 처리합니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println();
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// 유저 아이디를 바로 받지 말고 현재 서버가 가지고 있는 세션값을 이용하여 로그인 한 사용 자를 가지고 옵니다.
		// String userID = URLDecoder.decode(request.getParameter("userID"));
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");

		String boardID = URLDecoder.decode(request.getParameter("boardID"), "UTF-8");

		System.out.println("userID : " + userID);
		System.out.println("boardID : " + boardID);

		// boardID 가 없는 경우
		if (boardID == null || boardID.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("boardView.jsp");
			return;
		}

		// 로그인 하지 않은 경우
		if (userID == null || userID.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "현재 로그인이 되어있지 않습니다.");
			response.sendRedirect("boardShow.jsp?boardID=" + boardID);
			return;
		}

		BoardDAO boardDAO = new BoardDAO();
		BoardDTO board = boardDAO.getBoard(boardID);

		System.out.println("board.getUserID() : " + board.getUserID());

		// 로그인한 사용자와 게시글의 작성자가 다른경우
		if (!userID.equals(board.getUserID())) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("boardView.jsp");
			return;
		}

		// 업로드된 파일의 물리적 경로
		String savePath = request.getRealPath("/upload").replaceAll("\\\\", "/"); // else 로 뺏으나, 데이터 베이스 삭제가 먼저 이루어 지므로,
																					// 데이터베이스 삭제 보다 위에 선언, 정의 합니다.

		// 현재 서버에 업로드된 실제 파일명
		String prev = boardDAO.getRealFile(boardID); // else 로 뺏으나, 데이터 베이스 삭제가 먼저 이루어 지므로, 데이터베이스 삭제 보다 위에 선언, 정의 합니다.

		// 데이터베이스에서 파일 삭제
		int result = boardDAO.delete(boardID);

		if (result == -1) { // 삭제실패
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "데이터베이스 오류로 인한 게시글 삭제 실패");
			response.sendRedirect("boardView.jsp");
			return;
		} else { // 삭제 성공

			System.out.println("savePath : " + savePath);
			System.out.println("prev : " + prev);
			File prevFile = new File(savePath + "/" + prev);

			// 삭제할 파일이 존재 한다면 서버의 물리적 경로에서 파일 삭제
			if (prevFile.exists())
				prevFile.delete();

			session.setAttribute("messageType", "성공 메시지");
			session.setAttribute("messageContent", "게시글 삭제에 성공했습니다.");
			response.sendRedirect("boardView.jsp");
			return;
		}

	}

}
