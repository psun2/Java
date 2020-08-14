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
	}

	// 채팅메시지를 담고 있어 채팅을 뿌려주는 역할
	public ArrayList<ChatDTO> getChatList(String nowTime) {

		ArrayList<ChatDTO> chatList = null;
		this.sql = "SELECT * FROM CAHT WHERE CHATTIME > ? ORDER BY CHATTIME";

		try {
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, nowTime);
			this.rs = psmt.executeQuery();

			chatList = new ArrayList<ChatDTO>();

			while (rs.next()) {
				ChatDTO chatDTO = new ChatDTO();
				chatDTO.setChatName(rs.getString("chatName"));
				chatDTO.setChatContent(rs.getString("chatContent"));
				chatDTO.setChatTime(rs.getDate("chatTime"));
				chatList.add(chatDTO);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return chatList;

	}

	public int submit(String chatName, String chatContent) {

		this.sql = "INSET INTO CHAT VALUES (?, ?, now())";
		// now() : 데이터 베이스에 현재시각을 삽입 하겠다는 의미입니다.

		try {
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, chatName);
			psmt.setString(2, chatContent);
			return psmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}
		return -1; // 데이터베이스 오류
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
