package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
		String sql = "SELECT BOARD.*, TO_CHAR(boardDate, 'YYYY/MM/DD HH24:MI:SS') AS createDate FROM Board WHERE boardID = ?";

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

				String temp = rs.getString("createDate");

				int integerTime = Integer.parseInt(temp.substring(11, 13));

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

	// 게시글을 가져오기 위한 함수
	public ArrayList<BoardDTO> getList() {
		ArrayList<BoardDTO> boardList = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT BOARD.*, TO_CHAR(boardDate, 'YYYY/MM/DD HH24:MI:SS') AS createDate FROM BOARD ORDER BY boardGroup DESC, boardSequence ASC";
		// boardGroup 의 존재이유 : 일반적으로 댓글은 부모글의 하위에 생성 그러므로 그룹으로 묶어 줄수 있도록 합니다.
		// boardSequence 를 통해 구룹핑이된 글들을 순서대로 출력 할 수 있도록 합니다.
		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			boardList = new ArrayList<BoardDTO>();

			while (rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setUserID(rs.getString("userID"));
				board.setBoardID(rs.getInt("boardID"));
				board.setBoardTitle(rs.getString("boardTitle").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll("<", "&gt;").replaceAll("\n", "<br />"));
				board.setBoardContent(rs.getString("boardContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll("<", "&gt;").replaceAll("\n", "<br />"));

				String temp = rs.getString("createDate");
				// 2020/09/26 15:20:55
				int integerTime = Integer.parseInt(temp.substring(11, 13));

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
				System.out.println(rs.getString("boardDate"));
				board.setDateStr(rs.getString("boardDate"));

				boardList.add(board);
			}
			return boardList;

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
		return boardList;
	}

	// 조회수 증가 메소드
	public int hit(String boardID) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "UPDATE BOARD SET boardHit = boardHit + 1 WHERE boardID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(boardID));
			return psmt.executeUpdate(); // 조회수 증가

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
		return -1; // 데이터 베이스 오류
	}

	// 실제 파일명
	public String getFile(String boardID) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT boardFile FROM BOARD WHERE boardID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, boardID);
			rs = psmt.executeQuery();

			if (rs.next())
				return rs.getString("boardFile");

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

		return "-1"; // 데이터 베이스 오류
	}

	// 서버의 물리적 공간에 있는 실제 서버에서 다운로드 시켜주어야할 파일 이름
	public String getRealFile(String boardID) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT boardRealFile FROM BOARD WHERE boardID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, boardID);
			rs = psmt.executeQuery();

			if (rs.next())
				return rs.getString("boardRealFile");

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

		return "-1"; // 데이터 베이스 오류
	}
}
