package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import oracle.net.aso.a;

public class BoardDAO {

	DataSource dataSource;

	public BoardDAO() {
		// TODO Auto-generated constructor stub

		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/216. UserChat oracle ver");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 게시글 등록
	public int write(String userID, String boardTitle, String boardContent, String boardFile, String boardRealFile) {

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "INSERT INTO BOARD "
				+ "(userID, boardID, boardTitle, boardContent,boardDate, boardHit, boardFile, boardRealFile, boardGroup, boardSequence, boardLevel) "
				+ "VALUES(?, board_boardID.NEXTVAL, ?, ?, sysdate, 0, ?, ?, board_boardGroup.NEXTVAL, 0, 0)";

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
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}

	// 특정 게시글을 불러오기
	public BoardDTO getBoard(String boardID) {
		BoardDTO board = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT BOARD.*, TO_CHAR(boardDate) AS createDATE FROM Board WHERE boardID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, boardID);

			rs = psmt.executeQuery();

			if (rs.next()) {
				board = new BoardDTO();
				board.setUserID(rs.getString("userID"));
				board.setBoardID(rs.getInt("boardID"));
				board.setBoardTitle(rs.getString("boardTitle").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll("<", "&gt;").replaceAll("\n", "<br />"));
				board.setBoardContent(rs.getString("boardContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll("<", "&gt;").replaceAll("\n", "<br />"));

				String temp = rs.getString("createDATE");

				int integerTime = Integer.parseInt(temp);

				String timeType = "오전";
				if (integerTime > 12) {
					timeType = "오후";
					integerTime -= 12;
				}

				board.setBoardDate(temp.substring(0, 10) + " " + timeType + " " + integerTime + temp.substring(13, 16));
				board.setBoardHit(rs.getInt("boardHit"));
				board.setBoardFile(rs.getString("boardFile"));
				board.setBoardRealFile(rs.getString("boardRealFile"));
				board.setBoardGroup(rs.getInt("boardGroup"));
				board.setBoardSequence(rs.getInt("boardSequence"));
				board.setBoardLevel(rs.getInt("boardLevel"));
				board.setDateStr(rs.getString("boardDate"));

				System.out.println(board);

				return board;
			}

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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return board;
	}
}
