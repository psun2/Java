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
			dataSource = (DataSource) envContext.lookup("jdbc/211. UserChat_실시간 회원제 채팅 (feat) 동빈나");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// 게시글 등록 함수
	public int write(String userID, String boardTitle, String boardContent, String boardFile, String boardRealFile) {

		Connection conn = null;
		PreparedStatement psmt = null;

		String sql = "INSERT INTO BOARD SELECT ?, IFNULL((SELECT MAX(boardID) + 1 FROM BOARD), 1), ?, ?, NOW(), 0, ?, ?, IFNULL((SELECT MAX(boardGroup) + 1 FROM BOARD), 0), 0, 0, 1";
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
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
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
			psmt.setString(1, boardID);
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

				System.out.println("DB 반환 날짜 : " + rs.getString("boardDate"));

				String src = "오전";
				int hour = Integer.parseInt(rs.getString("boardDate").substring(11, 13));
				if (hour > 12) {
					src = "오후";
					hour -= 12;
				}
				String date = rs.getString("boardDate").substring(0, 11) + "(" + src + ") " + hour
						+ rs.getString("boardDate").substring(13, 19);

				board.setBoardDate(date);
//				board.setBoardDate(rs.getString("boardDate").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
//						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;").substring(0, 10));
//				board.setBoardDate(rs.getString("boardDate").substring(0, 11));
				board.setBoardHit(rs.getInt("boardHit"));
				board.setBoardFile(rs.getString("boardFile").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;"));
				board.setBoardRealFile(rs.getString("boardRealFile").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;"));
				board.setBoardGroup(rs.getInt("boardGroup"));
				board.setBoardSequence(rs.getInt("boardSequence"));
				board.setBoardLevel(rs.getInt("boardLevel"));
				board.setBoardAvailable(rs.getInt("boardAvailable"));
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

	// 게시글 목록 반환
	public ArrayList<BoardDTO> getList(String pageNumber) {
		// 마지막 강의로 인한 함수 변경(게시판의 페이징 처리)

		ArrayList<BoardDTO> boardList = null;

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		// 마지막 강의로 인한 함수 변경(게시판의 페이징 처리)
		// String sql = "SELECT * FROM BOARD ORDER BY boardGroup DESC, boardSequence
		// ASC";
		String sql = "SELECT * FROM BOARD WHERE boardGroup > (SELECT MAX(boardGroup) FROM BOARD) - ? AND boardGroup <= (SELECT MAX(boardGroup) FROM BOARD) - ? ORDER BY boardGroup DESC, boardSequence ASC";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);

			// 마지막 강의로 인한 함수 변경(게시판의 페이징 처리)
			psmt.setInt(1, Integer.parseInt(pageNumber) * 10);
			psmt.setInt(2, (Integer.parseInt(pageNumber) - 1) * 10);

			rs = psmt.executeQuery();

			boardList = new ArrayList<BoardDTO>();
			while (rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setUserID(rs.getString("userID").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;"));
				board.setBoardID(rs.getInt("boardID"));
				board.setBoardTitle(rs.getString("boardTitle").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;"));
				board.setBoardContent(rs.getString("boardContent").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;"));

				System.out.println("DB 반환 날짜 : " + rs.getString("boardDate"));

				String src = "오전";
				int hour = Integer.parseInt(rs.getString("boardDate").substring(11, 13));
				if (hour > 12) {
					src = "오후";
					hour -= 12;
				}
				String date = rs.getString("boardDate").substring(0, 11) + "(" + src + ") " + hour
						+ rs.getString("boardDate").substring(13, 19);

				board.setBoardDate(date);

//				board.setBoardDate(rs.getString("boardDate").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
//						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;").substring(0, 10));

				System.out.println("날짜가 이상해요 : " + rs.getString("boardDate").replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;").substring(0, 10));
				System.out.println("날짜가 이상해요 : " + rs.getString("boardDate").substring(0, 11));

				board.setBoardHit(rs.getInt("boardHit"));
				board.setBoardFile(rs.getString("boardFile").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;"));
				board.setBoardRealFile(rs.getString("boardRealFile").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>").replaceAll(" ", "&nbsp;"));
				board.setBoardGroup(rs.getInt("boardGroup"));
				board.setBoardSequence(rs.getInt("boardSequence"));
				board.setBoardLevel(rs.getInt("boardLevel"));
				board.setBoardAvailable(rs.getInt("boardAvailable"));

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
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

		return boardList; // 오류

	}

	// 다음 페이지의 존재 여부를 알려주는 함수
	public boolean nextPage(String pageNumber) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BOARD WHERE boardGroup >= ?";

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(pageNumber) * 10);
			rs = psmt.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
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
		return false; // 데이터베이스 오류
	}

	// 페이지 네이션
	public int targetPage(String pageNumber) {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(boardGroup) FROM BOARD WHERE boardGroup > ?";

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, (Integer.parseInt(pageNumber) - 1) * 10);
			rs = psmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("COUNT(boardGroup)") / 10;
			}
		} catch (Exception e) {
			// TODO: handle exception
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
		return 0; // 데이터베이스 오류
	}

	// 조회수 증가
	public int hit(String boardID) {

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "UPDATE BOARD SET boardHit = boardHit + 1 WHERE boardID = ?";

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, boardID);
			return psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		return -1; // 데이터베이스 오류
	}

	// 파일 다운로드
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
			if (rs.next()) {
				return rs.getString("boardFile");
			}
		} catch (Exception e) {
			// TODO: handle exception
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
		return ""; // 데이터베이스 오류
	}

	// 실제 서버에 저장되 어있는 파일 이름
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
			if (rs.next()) {
				return rs.getString("boardRealFile");
			}
		} catch (Exception e) {
			// TODO: handle exception
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
		return ""; // 데이터베이스 오류
	}

	// 게시글 수정 함수
	public int update(String boardID, String boardTitle, String boardContent, String boardFile, String boardRealFile) {

		Connection conn = null;
		PreparedStatement psmt = null;

		System.out.println("update 함수 : " + boardTitle);
		System.out.println("update 함수 : " + boardContent);
		System.out.println("update 함수 : " + boardFile);
		System.out.println("update 함수 : " + boardRealFile);
		System.out.println("update 함수 : " + boardID);

		String sql = "UPDATE BOARD SET boardTitle = ?, boardContent = ?, boardFile = ?, boardRealFile = ? WHERE boardID = ?";

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, boardTitle);
			psmt.setString(2, boardContent);
			psmt.setString(3, boardFile);
			psmt.setString(4, boardRealFile);
			psmt.setInt(5, Integer.parseInt(boardID));

			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return -1; // 데이터베이스 오류
	}

	// 게시글 삭제
	public int delete(String boardID) {

		Connection conn = null;
		PreparedStatement psmt = null;

		// 데이터베이스에서 실제로 지우는 것이 아닌 available 의 값을 0으로 바꿉니다.
		// String sql = "DELETE FROM BOARD WHERE boardID = ?";
		String sql = "UPDATE BOARD SET boardAvailable = 0 WHERE boardID = ?";

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(boardID));

			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return -1; // 데이터베이스 오류
	}

	// 답변 작성
	public int reply(String userID, String boardTitle, String boardContent, String boardFile, String boardRealFile,
			BoardDTO parent) {

		Connection conn = null;
		PreparedStatement psmt = null;

		String sql = "INSERT INTO BOARD SELECT ?, IFNULL((SELECT MAX(boardID) + 1 FROM BOARD), 1), ?, ?, NOW(), 0, ?, ?, ?, ?, ?, 1";
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
			psmt.setInt(6, parent.getBoardGroup());
			psmt.setInt(7, parent.getBoardSequence() + 1);
			psmt.setInt(8, parent.getBoardLevel() + 1);

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

	// 답변 수정
	public int replyUpdate(BoardDTO parent) {

		Connection conn = null;
		PreparedStatement psmt = null;

		String sql = "UPDATE BOARD SET boardSequence = boardSequence + 1 WHERE boardGroup = ? AND boardSequence > ?";

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, parent.getBoardGroup());
			psmt.setInt(2, parent.getBoardSequence());

			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (conn != null && !conn.isClosed())
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return -1; // 데이터베이스 오류
	}
}
