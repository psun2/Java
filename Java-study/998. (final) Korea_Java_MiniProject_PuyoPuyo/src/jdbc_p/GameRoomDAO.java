package jdbc_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import ddong.InitData;

public class GameRoomDAO {

	Connection con;
	Statement stmt;
	ResultSet rs;

	String url = "jdbc:oracle:thin:@" + InitData.ip + ":1521:xe";
//   String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = InitData.id;
	String pw = InitData.pw;

	String sql;

	public GameRoomDAO() {

		try {
			con = DriverManager.getConnection(url, id, pw);
			stmt = con.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close() {

		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
			}
	}

	public ArrayList<GameRoomDTO> list() {

		ArrayList<GameRoomDTO> res = new ArrayList<GameRoomDTO>();

		sql = "select * from gameroom";

		try {

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				GameRoomDTO dto = new GameRoomDTO();

				dto.no = rs.getInt("no");
				dto.user1 = rs.getString("user1");
				dto.user2 = rs.getString("user2");

				res.add(dto);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return res;
	}

	// --------------------------------------------------------
	// public GameRoomDTO detail : ���ϴ� ������ ã���� ����Ѵ�
	public GameRoomDTO detailroom(Integer no) { // �游�鶧 user1�ڸ��� null�� �� ���� �����

		GameRoomDTO dto = null;

		sql = "select * from gameroom where no = '" + no + "'";

		try {

			rs = stmt.executeQuery(sql); // �������� ã�ƺ���

			if (rs.next()) { // �������� ã�� �����͸� DTO���� get�ؿ´�

				dto = new GameRoomDTO();

				dto.user1 = rs.getString("user1");
				dto.user2 = rs.getString("user2");
				dto.no = rs.getInt("no");
			} // if�� ��

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return dto;
		// ã�� ������ ���� ��ȯ���ش�
	}

	// --------------------------------------------------------
	// == public int modifyUser1 =======
	public int modifyUser1(GameRoomDTO dto) { // user1�� ���� ����� �߰�

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ������ ������ �������ִ� ������
		sql = "update gameroom set user1 = '" + dto.user1 + "' where  no = '" + dto.no + "'";

		try {

			res = stmt.executeUpdate(sql); // ������ ���� ������Ʈ

		} catch (Exception e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return res;
		// ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
	} // == public int modifyUser1 �� =======

	// --------------------------------------------------------
	// == public int modifyUser2 =======
	public int modifyUser2(GameRoomDTO dto) { // user2�� �濡 �����ϸ� �߰�

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ������ ������ �������ִ� ������
		sql = "update gameroom set user2 = '" + dto.user2 + "' where  no = '" + dto.no + "'";

		try {

			res = stmt.executeUpdate(sql); // ������ ���� ������Ʈ

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return res;
		// ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
	} // == public int modifyUser2 �� =======
		// --------------------------------------------------------

	// --------------------------------------------------------
	// == public int modifyUser3 =======

	public int modifyUser4(String col, String id, Integer roomNum) { // user2�� �濡 �����ϸ� �߰�

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ������ ������ �������ִ� ������
		sql = "update gameroom set " + col + " = " + null + " where " + col + " = '" + id + "' and no = " + roomNum;

		try {

			res = stmt.executeUpdate(sql); // ������ ���� ������Ʈ

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return res;
		// ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
	} // == public int modifyUser2 �� =======
		// --------------------------------------------------------

	public int modifyUser5(String col, String id) {

		int res = 0;

		sql = "update gameroom set " + col + " = " + null + " where " + col + " = '" + id + "'";
		// System.out.println(sql);

		try {

			res = stmt.executeUpdate(sql); // ������ ���� ������Ʈ

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return res;

	}

	public int modifyUser6(String col, Integer roomNum) {

		int res = 0;

		sql = "update gameroom set " + col + " = 'temp' where no = " + roomNum;
		// System.out.println(sql);

		try {

			res = stmt.executeUpdate(sql); // ������ ���� ������Ʈ

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return res;
	}

	// --------------------------------------------------------
	// == public int delete : java���� ������ ������ DB���� ���� =======
	public int reset(GameRoomDTO dto) { // user1�� ���� ������ ���� ���ش�

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		sql = "update gameroom set user1 = " + dto.user1 + ", user2 = " + dto.user2 + " where  no = '" + dto.no + "'";

		try {

			res = stmt.executeUpdate(sql); // ������ ������ ������Ʈ

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}

		return res;
		// ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
	} // == public int delete �� =======

	public GameRoomDTO roomdPepleetail(String id) {
		GameRoomDTO dto = new GameRoomDTO();
		sql = "select * from gameroom where user1 = '" + id + "'" + " or user2='" + id + "'";
		try {

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				dto.user1 = rs.getString("user1");
				dto.user2 = rs.getString("user2");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return dto;
	}

}