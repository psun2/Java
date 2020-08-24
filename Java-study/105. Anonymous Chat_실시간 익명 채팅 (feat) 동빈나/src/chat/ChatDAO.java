package chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DataUtil;

public class ChatDAO {

	private Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;

	public ChatDAO() {
		// TODO Auto-generated constructor stub
		this.con = DataUtil.getConnection();
		System.out.println("null은 아니겟지 ? " + con);
	}

	public int submit(String chatName, String chatContent) {

		System.out.println(chatName);
		System.out.println(chatContent);

		// this.sql = "INSERT INTO CHAT VALUES (?, ?, now())";
		// now() : 데이터 베이스에 현재시각을 삽입 하겠다는 의미입니다.

		// 데이터 베이스 수정으로 인한 sql문장 수정
		this.sql = "INSERT INTO CHAT VALUES (null, ?, ?, now())";

		try {
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, chatName);
			psmt.setString(2, chatContent);
			System.out.println("submit 성공");
			return psmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}
		System.out.println("submit 실패");
		return -1; // 데이터베이스 오류
	}

	// 채팅메시지를 담고 있어 채팅을 뿌려주는 역할
	public ArrayList<ChatDTO> getChatList(String nowTime) {

		System.out.println("nowTime : " + nowTime);

		ArrayList<ChatDTO> chatList = null;
		// this.sql = "SELECT * FROM CHAT WHERE CHATTIME > ? ORDER BY CHATTIME DESC";
		this.sql = "SELECT * FROM CHAT WHERE CHATTIME > ? ORDER BY CHATTIME";

		try {
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, nowTime);
			this.rs = psmt.executeQuery();

			chatList = new ArrayList<ChatDTO>();

			while (rs.next()) {
				ChatDTO chatDTO = new ChatDTO();
				chatDTO.setChatID(rs.getInt("chatID"));
				chatDTO.setChatName(rs.getString("chatName"));
				chatDTO.setChatContent(rs.getString("chatContent").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br/>").replaceAll("\r", "<br/>").replaceAll(" ", "&nbsp;"));

				System.out.println("rs.getString(\"chatTime\") : " + rs.getString("chatTime"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if (chatTime >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				System.out.println("시간변환 : " + rs.getString("chatTime").substring(0, 11) + " " + timeType + " "
						+ chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				chatDTO.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":"
						+ rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chatDTO);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		System.out.println("chatList DAO : " + chatList);
		return chatList;

	}

	// 페이지 순에 따른 채팅 페이지 반환
	public ArrayList<ChatDTO> getChatListByRecent(int number) {

		ArrayList<ChatDTO> chatList = null;
		// 가장 최근으로 부터 몇개 만큼의 데이터를 가져오겠다는 sql문장
		// this.sql = "SELECT * FROM CHAT WHERE CHATID > (SELECT MAX(CHATID) -? FROM
		// CHAT) ORDER BY CHATTIME DESC";
		this.sql = "SELECT * FROM CHAT WHERE CHATID > (SELECT MAX(CHATID) -? FROM CHAT) ORDER BY CHATTIME";

		try {
			this.psmt = con.prepareStatement(sql);
			psmt.setInt(1, number);
			this.rs = psmt.executeQuery();

			chatList = new ArrayList<ChatDTO>();

			while (rs.next()) {
				ChatDTO chatDTO = new ChatDTO();
				chatDTO.setChatID(rs.getInt("chatID"));
				chatDTO.setChatName(rs.getString("chatName"));
				chatDTO.setChatContent(rs.getString("chatContent").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br/>").replaceAll("\r", "<br/>").replaceAll(" ", "&nbsp;"));

				System.out.println("rs.getString(\"chatTime\") : " + rs.getString("chatTime"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if (chatTime >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				System.out.println("시간변환 : " + rs.getString("chatTime").substring(0, 11) + " " + timeType + " "
						+ chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				chatDTO.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":"
						+ rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chatDTO);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		System.out.println("chatList DAO : " + chatList);
		return chatList;

	}

	// 특정한 chatID 를받아 받은 chatID보다 큰 메시지만 보기
	public ArrayList<ChatDTO> getChatListByRecent(String chatID) {

		ArrayList<ChatDTO> chatList = null;
		// 가장 최근으로 부터 몇개 만큼의 데이터를 가져오겠다는 sql문장
		// this.sql = "SELECT * FROM CHAT WHERE CHATID > (SELECT MAX(CHATID) -? FROM
		// CHAT) ORDER BY CHATTIME DESC";
		this.sql = "SELECT * FROM CHAT WHERE CHATID > ? ORDER BY CHATTIME";

		try {
			this.psmt = con.prepareStatement(sql);
			psmt.setInt(1, Integer.parseInt(chatID));
			this.rs = psmt.executeQuery();

			chatList = new ArrayList<ChatDTO>();

			while (rs.next()) {
				ChatDTO chatDTO = new ChatDTO();
				chatDTO.setChatID(rs.getInt("chatID"));
				chatDTO.setChatName(rs.getString("chatName"));
				chatDTO.setChatContent(rs.getString("chatContent").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
						.replaceAll("\n", "<br/>").replaceAll("\r", "<br/>").replaceAll(" ", "&nbsp;"));

				System.out.println("rs.getString(\"chatTime\") : " + rs.getString("chatTime"));
				int chatTime = Integer.parseInt(rs.getString("chatTime").substring(11, 13));
				String timeType = "오전";
				if (chatTime >= 12) {
					timeType = "오후";
					chatTime -= 12;
				}
				System.out.println("시간변환 : " + rs.getString("chatTime").substring(0, 11) + " " + timeType + " "
						+ chatTime + ":" + rs.getString("chatTime").substring(14, 16) + "");
				chatDTO.setChatTime(rs.getString("chatTime").substring(0, 11) + " " + timeType + " " + chatTime + ":"
						+ rs.getString("chatTime").substring(14, 16) + "");
				chatList.add(chatDTO);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		System.out.println("chatList DAO : " + chatList);
		return chatList;

	}

	void close() {

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			if (psmt != null && !psmt.isClosed())
				psmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			if (con != null && !con.isClosed())
				con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
