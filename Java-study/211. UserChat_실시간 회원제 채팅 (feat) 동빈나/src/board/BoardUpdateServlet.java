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

		System.out.println();
		System.out.println("BoardUpdateServlet 진입");
		System.out.println();

		MultipartRequest multi = null;
		String savePath = request.getRealPath("/upload").replaceAll("\\\\", "/");
		int fileMaxSize = 10 * 1024 * 1024; // 10MB

		// String boardID = URLDecoder.decode(request.getParameter("boardID"), "UTF-8");
//		String userIDDD = request.getParameter("userID");
//		System.out.println(userIDDD);
//		String boardID = request.getParameter("boardID");
		// System.out.println("URLDecoder.decode(request.getParameter(\"boardID\"),
		// \"UTF-8\")"
		// + URLDecoder.decode(request.getParameter("boardID"), "UTF-8"));

		try {
			// 파일을 직접적으로 업로드 하는 부분입니다.
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "파일 크기는 10MB를 넘을 수 없습니다.");
			response.sendRedirect("boardView.jsp");
			return;
		}

		String boardID = URLDecoder.decode(multi.getParameter("boardID"), "UTF-8");
		System.out.println("boardUpdateServlet boardID : " + boardID);

		// boardID가 넘어오지 않았을때
		if (boardID == null || boardID.equals("")) {
			request.getSession().setAttribute("messageType", "오류 메시지");
			request.getSession().setAttribute("messageContent", "접근할 수 없습니다.");
			response.sendRedirect("boardShow.jsp?boardID=" + boardID);
			return;
		}

		// 수정전 현재의 게시글을 가져 옵니다.
		BoardDAO boardDAO = new BoardDAO();
		BoardDTO board = boardDAO.getBoard(boardID);

		// 현재 로그인 되어있는 세션값과 글의 작성자가 다른 경우 즉, 다른 사람의 글을 수정 하려 할때
		String userID = URLDecoder.decode(multi.getParameter("userID"), "UTF-8");
		HttpSession session = request.getSession();
		if (!userID.equals(board.getUserID())) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "접근 권한이 없습니다.");
			response.sendRedirect("boardShow.jsp?boardId=" + boardID);
		}

		String boardTitle = URLDecoder.decode(multi.getParameter("boardTitle"), "UTF-8");
		String boardContent = URLDecoder.decode(multi.getParameter("boardContent"), "UTF-8");

		if (boardTitle == null || boardTitle.equals("") || boardContent == null || boardContent.equals("")) {
			session.setAttribute("messageType", "오류 메시지");
			session.setAttribute("messageContent", "내용을 모두 입력해 주세요.");
			response.sendRedirect("boardUpdate.jsp");
			return;
		}

		String boardFile = "";
		String boardRealFile = "";
		File file = multi.getFile("boardFile");

		if (file != null) {
			boardFile = multi.getOriginalFileName("boardFile"); // 오리지널 네임
			boardRealFile = file.getName(); // 파일명이 겹쳤을때 DefaultFileRenamePolicy 를 통한 파일명을 변경한 실제 서버에 저장되어있는 파일명

			// file을 등록하는데 현재 파일이 등록 되어 있는 경우
			String prev = board.getBoardRealFile();
			System.out.println("prev : " + prev);

			File prevFile = new File(savePath + "/" + prev);
			System.out.println("prevFile : " + prevFile.getPath());

			// 존재한다면 해당 파일을 지워 줍니다.
			if (prevFile.exists())
				prevFile.delete();
		} else {
			// boardFile = board.getBoardFile();
			// boardRealFile = board.getBoardRealFile();
			boardFile = boardDAO.getFile(boardID);
			boardRealFile = boardDAO.getRealFile(boardID);
		}

		boardDAO.update(boardID, boardTitle, boardContent, boardFile, boardRealFile);
		session.setAttribute("messageType", "성공 메시지");
		session.setAttribute("messageContent", "성공적으로 게시글이 수정되었습니다.");
		response.sendRedirect("boardView.jsp");
		return;

	}

}
