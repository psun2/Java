package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ChatDAO {

	DataSource dataSource;

	public ChatDAO() {
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/216. UserChat oracle ver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 크로스 사이트 스크립트 방어 문자열 변환하는 함수
	String crossSiteScriptingDefence(String word) { // replaceAll 순서에 막대한 영향을 끼침
		return word.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n",
				"<br />");
	}

	// 날짜 변환 함수
	String conversionDate(String date, int start, int end) {
		return date.substring(start, end);
	}

	// chatID로 대화 내역을 가져오는 함수
	public ArrayList<ChatDTO> getChatListByID(String fromID, String toID, String chatID) {

		ArrayList<ChatDTO> chatList = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
//		String sql = "SELECT * FROM CHAT WHERE ((fromID  = ? AND toID = ?) OR (fromID = ? AND toID = ?)) AND chatID = ? ORDER BY chatTimeStamp";
		String sql = "SELECT chat.*, TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS time FROM CHAT WHERE ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?)) AND chatID > ? ORDER BY time";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fromID);
			psmt.setString(2, toID);
			psmt.setString(3, toID);
			psmt.setString(4, fromID);
			psmt.setInt(5, Integer.parseInt(chatID));
			rs = psmt.executeQuery();

			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {
				ChatDTO chat = new ChatDTO();

				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(crossSiteScriptingDefence(rs.getString("fromID")));
				chat.setToID(crossSiteScriptingDefence(rs.getString("toID")));
				chat.setChatContent(crossSiteScriptingDefence(rs.getString("chatContent")));

				// String chatTimeString = rs.getString("chatTime");
				// String chatTimeStampString = rs.getString("chatTimeStamp");
				String time = rs.getString("time");

				// int chatTime = Integer.parseInt(conversionDate(chatTimeString, 11, 13));
				// int chatTimeStamp = Integer.parseInt(conversionDate(chatTimeStampString, 9,
				// 11));
				int integerTime = Integer.parseInt(conversionDate(time, 11, 13));

				String timeType = "오전";
				if (integerTime > 12) {
					timeType = "오후";
					integerTime -= 12;
				}

//				chat.setChatTime(conversionDate(chatTimeString, 11, 13) + " " + timeType + " " + chatTime + ":"
//						+ conversionDate(chatTimeString, 13, 16));
//				chat.setChatTimeStamp(conversionDate(chatTimeStampString, 0, 9) + " " + timeType + " " + chatTimeStamp
//						+ ":" + conversionDate(chatTimeStampString, 11, 16));
				chat.setTime(conversionDate(time, 0, 10) + " " + timeType + " " + integerTime
						+ conversionDate(time, 13, 16));

				System.out.println(chat);
				chatList.add(chat);
			}

			return chatList;

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
		return chatList; // 데이터베이스 오류
	}

	// 대화 내역중 최근 몇개까지만 가져오는 list 함수
	public ArrayList<ChatDTO> getChatListByRecent(String fromID, String toID, int number) {

		ArrayList<ChatDTO> chatList = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		// String sql = "SELECT * FROM CHAT WHERE ((fromID = ? AND toID = ?) OR (fromID
		// = ? AND toID = ?)) AND chatID = (SELECT MAX(chatID) - ? FROM CHAT) ORDER BY
		// chatTimeStamp";
		String sql = "SELECT chat.*, TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS time FROM CHAT WHERE ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?)) AND chatID > (SELECT MAX(chatID) - ? FROM CHAT WHERE (fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?)) ORDER BY time";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fromID);
			psmt.setString(2, toID);
			psmt.setString(3, toID);
			psmt.setString(4, fromID);
			psmt.setInt(5, number);
			psmt.setString(6, fromID);
			psmt.setString(7, toID);
			psmt.setString(8, toID);
			psmt.setString(9, fromID);
			rs = psmt.executeQuery();

			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {
				ChatDTO chat = new ChatDTO();

				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(crossSiteScriptingDefence(rs.getString("fromID")));
				chat.setToID(crossSiteScriptingDefence(rs.getString("toID")));
				chat.setChatContent(crossSiteScriptingDefence(rs.getString("chatContent")));

				// String chatTimeString = rs.getString("chatTime");
				// String chatTimeStampString = rs.getString("chatTimeStamp");
				String time = rs.getString("time");

				// int chatTime = Integer.parseInt(conversionDate(chatTimeString, 11, 13));
				// int chatTimeStamp = Integer.parseInt(conversionDate(chatTimeStampString, 9,
				// 11));
				int integerTime = Integer.parseInt(conversionDate(time, 11, 13));

				String timeType = "오전";
				if (integerTime > 12) {
					timeType = "오후";
					integerTime -= 12;
				}

//				chat.setChatTime(conversionDate(chatTimeString, 11, 13) + " " + timeType + " " + chatTime + ":"
//						+ conversionDate(chatTimeString, 13, 16));
//				chat.setChatTimeStamp(conversionDate(chatTimeStampString, 0, 9) + " " + timeType + " " + chatTimeStamp
//						+ ":" + conversionDate(chatTimeStampString, 11, 16));
				chat.setTime(conversionDate(time, 0, 10) + " " + timeType + " " + integerTime
						+ conversionDate(time, 13, 16));

				System.out.println(chat);
				chatList.add(chat);
			}

			return chatList;

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
		return chatList; // 데이터베이스 오류
	}

	// 채팅 전송
	public int submit(String fromID, String toID, String chatContent) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO CHAT (chatID, fromID, toID, chatContent, chatTime, chatTimeStamp, chatRead) values (CHAT_chatID.nextval, ?, ?, ?, SYSDATE, SYSDATE, 0)";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fromID);
			psmt.setString(2, toID);
			psmt.setString(3, chatContent);
			return psmt.executeUpdate(); // 성공적으로 전송

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
		return -1; // 데이터베이스 오류
	}

	// 읽음처리 함수
	public int readChat(String fromID, String toID) {

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "UPDATE CHAT SET chatRead = 1 WHERE (fromID = ? AND toID = ?)";

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, toID);
			psmt.setString(2, fromID);
			return psmt.executeUpdate(); // 성공적으로 읽음처리
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

	// 읽음처리가 안된 메시지 갯수 가져오는 함수
	public int getAllUnreadChat(String userID) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(chatID) FROM CHAT WHERE toID = ? AND chatRead = 0";

		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();

			if (rs.next())
				return rs.getInt("COUNT(chatID)"); // 읽음 처리가 안된 갯수 반환
			return 0; // 모두 읽음
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
		return -1; // 데이터베이스 오류
	}
	
	// 대화 상대별 안 읽은 메시지 출력 함수
	public int getUnreadChat(String fromID, String toID) {
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(chatID) FROM CHAT WHERE fromID = ? AND toID = ? AND chatRead = 0";
		
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fromID);
			psmt.setString(2, toID);
			rs = psmt.executeQuery();
			
			if (rs.next())
				return rs.getInt("COUNT(chatID)"); // 읽음 처리가 안된 갯수 반환
			return 0; // 모두 읽음
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
		return -1; // 데이터베이스 오류
	}

	// 메시지함에 출력될 데이터 반환 함수
	public ArrayList<ChatDTO> getBox(String userID) {
		ArrayList<ChatDTO> chatList = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT chat.*, TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS time FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE fromID = ? OR toID = ? GROUP BY fromID, toID)";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			psmt.setString(2, userID);
			rs = psmt.executeQuery();

			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {
				ChatDTO chat = new ChatDTO();

				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(crossSiteScriptingDefence(rs.getString("fromID")));
				chat.setToID(crossSiteScriptingDefence(rs.getString("toID")));
				chat.setChatContent(crossSiteScriptingDefence(rs.getString("chatContent")));

				String time = rs.getString("time");

				int integerTime = Integer.parseInt(conversionDate(time, 11, 13));

				String timeType = "오전";
				if (integerTime > 12) {
					timeType = "오후";
					integerTime -= 12;
				}

				chat.setTime(conversionDate(time, 0, 10) + " " + timeType + " " + integerTime
						+ conversionDate(time, 13, 16));

				System.out.println(chat);
				chatList.add(chat);
			}

			// 겹치는 데이터 파기
			for (int i = 0; i < chatList.size(); i++) {

				ChatDTO temp = chatList.get(i);

				for (int j = 0; j < chatList.size(); j++) {

					ChatDTO temp2 = chatList.get(j);

					if (temp.getFromID().equals(temp2.getToID()) && temp.getToID().equals(temp2.getFromID())) {
						if (temp.getChatID() < temp2.getChatID()) {
							chatList.remove(temp);
							i--;
							break;
						} else {
							chatList.remove(temp2);
							j--;
						}

					}

				}

			}

			return chatList;

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
		return chatList; // 데이터베이스 오류
	}

	// 메시지함에 출력될 데이터 반환 함수 (MyCreate 내가 생성 쿼리문)
	public ArrayList<ChatDTO> getBox2(String userID) {
		ArrayList<ChatDTO> chatList = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT chat.*, TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS time FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE fromID = ? OR toID = ?)";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {
				ChatDTO chat = new ChatDTO();

				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(crossSiteScriptingDefence(rs.getString("fromID")));
				chat.setToID(crossSiteScriptingDefence(rs.getString("toID")));
				chat.setChatContent(crossSiteScriptingDefence(rs.getString("chatContent")));

				String time = rs.getString("time");

				int integerTime = Integer.parseInt(conversionDate(time, 11, 13));

				String timeType = "오전";
				if (integerTime > 12) {
					timeType = "오후";
					integerTime -= 12;
				}

				chat.setTime(conversionDate(time, 0, 10) + " " + timeType + " " + integerTime
						+ conversionDate(time, 13, 16));

				System.out.println(chat);
				chatList.add(chat);
			}

			return chatList;

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
		return chatList; // 데이터베이스 오류
	}

}