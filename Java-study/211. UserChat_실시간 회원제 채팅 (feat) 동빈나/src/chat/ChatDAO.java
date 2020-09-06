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
		// TODO Auto-generated constructor stub
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/211. UserChat_실시간 회원제 채팅 (feat) 동빈나");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public ArrayList<ChatDTO> getChatListByID(String fromID, String toID, String chatID) {

		ArrayList<ChatDTO> chatList = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		// 본인이 보냈거나, 받았을때 두 경우에 어떠한 경우에라도 해당이 된다면
		String sql = "SELECT * FROM CHAT WHERE  ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ? )) AND chatID > ? ORDER BY chatTime";

		try {

			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fromID);
			psmt.setString(2, toID);
			psmt.setString(3, toID);
			psmt.setString(4, fromID);
			psmt.setInt(5, Integer.parseInt(chatID));
			rs = psmt.executeQuery();

			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {

				ChatDTO chat = new ChatDTO();
				chat.setChatID(rs.getInt(1));
				chat.setFromID(rs.getString(2).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>"));
				chat.setToID(rs.getString(3).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString(4).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString(5).substring(11, 13));
				String timeType = "오전";
				if (chatTime > 12) {
					timeType = "오후";
					chatTime -= 12; // 24시간제라서 12를 빼줍니다.
				}
				chat.setChatTime(rs.getString(5).substring(0, 11) + " " + timeType + " " + chatTime + ":"
						+ rs.getString(5).substring(14, 16) + "");

//				chat.setChatID(rs.getInt("chatID"));
//				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
//						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
//				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
//						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
//				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
//						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
//				chat.setChatTime(rs.getString("chatTime").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
//						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
//				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
//				String timeType = "오전";
//				if (chatTime > 12) {
//					timeType = "오후";
//					chatTime -= 12; // 24시간제라서 12를 빼줍니다.
//				}
//				chat.setChatTime(rs.getString(5).substring(0, 11) + " " + timeType + " " + chatTime + ":"
//						+ rs.getNString(5).substring(14, 16) + "");

				chatList.add(chat);
				System.out.println(chat);

			}

			return chatList;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return chatList; // 리스트 반환
	}

	// 대화 내역 중 최근 대화 몇개만을 뽑아서 반환합니다.
	public ArrayList<ChatDTO> getChatListByRecent(String fromID, String toID, int number) {

		ArrayList<ChatDTO> chatList = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		// 본인이 보냈거나, 받았을때 두 경우에 어떠한 경우에라도 해당이 된다면
		// (기존) String sql = "SELECT * FROM CHAT WHERE ((fromID = ? AND toID = ?) OR
		// (fromID = ? AND toID = ? )) AND chatID > (SELECT MAX(chatID) - ? FROM CHAT)
		// ORDER BY chatTime";

		// 메시지 읽음 처리 구현
		// 추가된 부분 : WHERE (fromID = ? AND toID = ?) OR (fromID = ? AND toID = ? )
		// 메시지 100개를 가져올때 최근 100개가 아닌, 해당 사용자들끼리의 메시지를 가져오기위함 입니다.
		String sql = "SELECT * FROM CHAT WHERE  ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ? )) AND chatID > (SELECT MAX(chatID) - ? FROM CHAT WHERE (fromID = ? AND toID = ?) OR (fromID = ? AND toID = ? )) ORDER BY chatTime";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);

			// (기존)
//			psmt.setString(1, fromID);
//			psmt.setString(2, toID);
//			psmt.setString(3, toID);
//			psmt.setString(4, fromID);
//			psmt.setInt(5, number);

			// (메시지 읽음처리 구현 sql 문의 바뀜에 따라 setting 할 String이 늘었습니다.)
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
				chat.setChatID(rs.getInt(1));
				chat.setFromID(rs.getString(2).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>"));
				chat.setToID(rs.getString(3).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString(4).replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString(5).substring(11, 13));
				String timeType = "오전";
				if (chatTime > 12) {
					timeType = "오후";
					chatTime -= 12; // 24시간제라서 12를 빼줍니다.
				}
				chat.setChatTime(rs.getString(5).substring(0, 11) + " " + timeType + " " + chatTime + ":"
						+ rs.getString(5).substring(14, 16) + "");

//				chat.setChatID(rs.getInt("chatID"));
//				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
//						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
//				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
//						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
//				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
//						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
//				chat.setChatTime(rs.getString("chatTime").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
//						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
//				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
//				String timeType = "오전";
//				if (chatTime > 12) {
//					timeType = "오후";
//					chatTime -= 12; // 24시간제라서 12를 빼줍니다.
//				}
//				chat.setChatTime(rs.getString(5).substring(0, 11) + " " + timeType + " " + chatTime + ":"
//						+ rs.getString(5).substring(14, 16) + "");

				chatList.add(chat);
				System.out.println(chat);

			}

			return chatList;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return chatList; // 리스트 반환
	}

	// 메시지 함을 반환하는 함수
	// 특정한 사용자가 최근에 추고받은 모든 메시지 리스트를 출력
	public ArrayList<ChatDTO> getBox(String userID) {

		System.out.println("getBox 진입");
		System.out.println(userID);
		System.out.println("------------------------------");

		ArrayList<ChatDTO> chatList = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM CHAT WHERE chatID IN (SELECT MAX(chatID) FROM CHAT WHERE toID = ? OR fromID = ? GROUP BY fromID, toID )";
		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);

			psmt.setString(1, userID);
			psmt.setString(2, userID);

			rs = psmt.executeQuery();

			chatList = new ArrayList<ChatDTO>();
			while (rs.next()) {
				ChatDTO chat = new ChatDTO();

				chat.setChatID(rs.getInt("chatID"));
				chat.setFromID(rs.getString("fromID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setToID(rs.getString("toID").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setChatContent(rs.getString("chatContent").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				chat.setChatTime(rs.getString("chatTime").replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
						.replaceAll(">", "&gt;").replaceAll("\n", "<br>"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if (chatTime > 12) {
					timeType = "오후";
					chatTime -= 12; // 24시간제라서 12를 빼줍니다.
				}
				chat.setChatTime(rs.getString(5).substring(0, 11) + " " + timeType + " " + chatTime + ":"
						+ rs.getString(5).substring(14, 16) + "");

				chatList.add(chat);
				System.out.println(chat);

			}

			for (int i = 0; i < chatList.size(); i++) {
				ChatDTO x = chatList.get(i);
				for (int j = 0; j < chatList.size(); j++) {
					ChatDTO y = chatList.get(j);
					if (x.getFromID().equals(y.getToID()) && x.getToID().equals(y.getFromID())) {
						if (x.getChatID() < y.getChatID()) {
							chatList.remove(x);
							i--;
							break;
						} else {
							chatList.remove(y);
							j--;
						}
					}
				}
			}

			System.out.println("반환 : " + chatList);

			return chatList;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				System.out.println("finally반환 : " + chatList);
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		System.out.println("finally 밑 반환 : " + chatList);
		return chatList; // 리스트 반환
	}

	// 채팅을 보내는 메소드 입니다. (채팅 전송기능)
	public int submit(String fromID, String toID, String chatContent) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		// 본인이 보냈거나, 받았을때 두 경우에 어떠한 경우에라도 해당이 된다면
		String sql = "INSERT INTO CHAT VALUES (NULL, ?, ?, ?, NOW(), 0)";
		// 맨마지막의 0 : 첫 메시지 상태 일땐 읽지 않음 상태이므로, 0을 넣어줍니다.
		// 해당 유저가 읽었다면 1로 바꾸어 줍니다.
		System.out.println("dataSource : " + dataSource);
		try {

			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fromID);
			psmt.setString(2, toID);
			psmt.setString(3, chatContent);
			return psmt.executeUpdate(); // 전송성공

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return -1; // 리스트 반환
	}

	// 메시지 읽음처리 구현
	public int readChat(String fromID, String toID) {
		Connection con = null;
		PreparedStatement psmt = null;

		String sql = "UPDATE CHAT SET chatRead = 1 WHERE (fromID = ? AND toID = ?)";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, toID);
			psmt.setString(2, fromID);
			return psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}

	// 현재 모든 채팅중 읽지 않은 메시지의 갯수를 가져오는 함수
	public int getAllUnreadChat(String userID) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(chatID) FROM CHAT WHERE toID = ? AND chatRead = 0";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();

			if (rs.next()) // 읽지 않은 메시지가 있는 경우
				return rs.getInt("COUNT(chatID)");
			else // 읽지 않은 메시지가 없는 경우
				return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}

	public int getUnreadChat(String fromID, String toID) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(chatID) FROM CHAT WHERE fromID = ? AND toID = ? AND chatRead = 0";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fromID);
			psmt.setString(2, toID);
			rs = psmt.executeQuery();

			if (rs.next()) // 읽지 않은 메시지가 있는 경우
				return rs.getInt("COUNT(chatID)");
			else // 읽지 않은 메시지가 없는 경우
				return 0;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}

}
