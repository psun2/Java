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
		return word.replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br />");
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
		String sql = "SELECT chat.*, TO_CHAR(chatTimeStamp, 'YYYY/MM/DD HH24:MI:SS') AS time FROM CHAT WHERE ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ?)) AND chatID > (SELECT MAX(chatID) - ? FROM CHAT) ORDER BY time";
		try {
			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fromID);
			psmt.setString(2, toID);
			psmt.setString(3, toID);
			psmt.setString(4, fromID);
			psmt.setInt(5, number);
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

	public int submit(String fromID, String toID, String chatContent) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO CHAT (chatID, fromID, toID, chatContent, chatTime, chatTimeStamp) values (chatID.nextval, ?, ?, ?, SYSDATE, SYSDATE)";

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
}