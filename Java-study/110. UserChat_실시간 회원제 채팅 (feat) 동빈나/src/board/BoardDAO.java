package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	DataSource dataSource;

	public BoardDAO() {
		// TODO Auto-generated constructor stub
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/110. UserChat_실시간 회원제 채팅 (feat) 동빈나");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 게시글 등록 함수
	public int write(String userID, String boardTitle, String boardContent, String boardFile, String boardRealFile) {

		Connection conn = null;
		PreparedStatement psmt = null;

		String sql = "INSERT INTO BOARD SELECT ?, IFNULL((SELECT MAX(boardID) + 1 FROM BOARD), 1), ?, ?, NOW(), 0, ?, ?, IFNULL((SELECT MAX(boardGroup) + 1 FROM BOARD), 0), 0, 0";
		// (SELECT MAX(boardID) + 1 FROM boardID), 1) : max로 가장 큰 값의 id 값을 뽑아와 + 1 씩 증가
		// 만약 존재 하지 않는다면 default = 1

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			psmt.setString(2, boardTitle);
			psmt.setString(3, boardContent);
			psmt.setString(4, boardFile);
			psmt.setString(5, boardRealFile);

			return psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 등록중 sql 문 오류 발생");
			e.printStackTrace();
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {

		}

		return -1; // 데이터베이스 오류
	}

	// 하나의 정보를 가져오는 select 함수
	public BoardDTO getBoard(String boardID) {

		BoardDTO board = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM BOARD WHERE boardID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				board = new BoardDTO();
				board.setUserID(rs.getString("userID").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;"));
				board.setBoardID(rs.getInt("boardID"));
				board.setBoardTitle(rs.getString("boardTitle").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;"));
				board.setBoardContent(rs.getString("boardContent").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;"));
				board.setBoardDate(rs.getString("boardDate").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;").substring(0, 11));
				board.setBoardHit(rs.getInt("boardHit"));
				board.setBoardFile(rs.getString("boardFile").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;"));
				board.setBoardRealFile(rs.getString("boardRealFile").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;"));
				board.setBoardGroup(rs.getInt("boardGroup"));
				board.setBoardSequence(rs.getInt("boardSequence"));
				board.setBoardLevel(rs.getInt("boardLevel"));
			}

			return board;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		return board; // 오류

	}

}
