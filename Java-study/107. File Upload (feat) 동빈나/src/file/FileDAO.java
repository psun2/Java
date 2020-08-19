package file;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FileDAO {

	private Connection con;
	PreparedStatement psmt;
	ResultSet rs;

	public FileDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/FILE";
			String dbUser = "root";
			String dbPassword = "root";
			Class.forName("com.mysql.jdbc.Driver");
			this.con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int upload(String fileName, String fileRealName) {

		String sql = "INSERT INTO FILE VALUES(?, ?)";

		try {
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, fileName);
			psmt.setString(2, fileRealName);
			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return -1;
	}

	void close() {
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (psmt != null && !psmt.isClosed())
				psmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (con != null && !con.isClosed())
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
