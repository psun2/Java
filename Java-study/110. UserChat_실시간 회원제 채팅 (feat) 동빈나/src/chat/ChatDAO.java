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
			dataSource = (DataSource) envContext.lookup("jdbc/110. UserChat_실시간 회원제 채팅 (feat) 동빈나");
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
				chat.setChatTime(rs.getNString(5).substring(0, 11) + " " + timeType + " " + chatTime + ":"
						+ rs.getNString(5).substring(14, 16) + "");

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
	public ArrayList<ChatDTO> getChatListByResect(String fromID, String toID, int number) {

		ArrayList<ChatDTO> chatList = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		// 본인이 보냈거나, 받았을때 두 경우에 어떠한 경우에라도 해당이 된다면
		String sql = "SELECT * FROM CHAT WHERE  ((fromID = ? AND toID = ?) OR (fromID = ? AND toID = ? )) AND chatID > (SELECT MAX(chatID) - ? FROM CHAT) ORDER BY chatTime";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, fromID);
			psmt.setString(2, toID);
			psmt.setString(3, toID);
			psmt.setString(4, fromID);
			psmt.setInt(5, number);
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
				chat.setChatTime(rs.getNString(5).substring(0, 11) + " " + timeType + " " + chatTime + ":"
						+ rs.getNString(5).substring(14, 16) + "");

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

	// 채팅을 보내는 메소드 입니다. (채팅 전송기능)
	public int submit(String fromID, String toID, String chatContent) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		// 본인이 보냈거나, 받았을때 두 경우에 어떠한 경우에라도 해당이 된다면
		String sql = "INSERT INTO CHAT VALUES (NULL, ?, ?, ?, NOW())";
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

}
