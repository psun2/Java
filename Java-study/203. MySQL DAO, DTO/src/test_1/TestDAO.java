package test_1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utill.DatabaseUtill;

public class TestDAO {

	Connection con;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;

	public TestDAO() {
		// TODO Auto-generated constructor stub
		this.con = DatabaseUtill.getConnection();
		System.out.println(con);
	}

	public ArrayList<TestDTO> list() {

		ArrayList<TestDTO> result = new ArrayList<TestDTO>();

		this.sql = "SELECT * FROM TEST_1";
		System.out.println(sql);

		try {
			this.psmt = con.prepareStatement(sql);
			this.rs = psmt.executeQuery();
			while (rs.next()) {

				TestDTO dto = new TestDTO();
				dto.id = rs.getInt("ID");
				dto.sid = rs.getString("SID");
				result.add(dto);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return result;

	}

	public int insert(TestDTO dto) {

		int result = -1;

		this.sql = "INSERT INTO TEST_1 (SID) VALUES (?)";
		System.out.println(sql);

		try {

			this.psmt = con.prepareStatement(sql);

			psmt.setString(1, dto.sid);

			result = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return result;
	}

	public int listInsert(ArrayList<TestDTO> list) {

		int result = -1;
		this.sql = "INSERT INTO TEST_1 (SID) VALUES (?)";
		System.out.println(sql);

		try {
			con.setAutoCommit(false);

			for (TestDTO dto : list) {
				this.psmt = con.prepareStatement(sql);
				psmt.setString(1, dto.sid);
				result += psmt.executeUpdate();
			}

			con.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			close();
		} finally {
			close();
		}

		return result;
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
