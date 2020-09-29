package user;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import util.DatabaseUtil;

public class UserDAO {

	DataSource dataSource = null;

	public UserDAO() {
		// TODO Auto-generated constructor stub
		try {
			InitialContext initContext = new InitialContext();
			Context context = (Context) initContext.lookup("java:/comp/env");
			this.dataSource = (DataSource) context.lookup("jdbc/222. 강의평가 웹사이트 개발 Tutorial_oracle ver");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int join(String userID, String userPassword) {
		System.out.println("userID : " + userID);
		System.out.println("userPassword : " + userPassword);

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "INSERT INTO MEMBER (userID, userPassword) VALUES (?, ?)";
		try {

			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			psmt.setString(2, userPassword);
			return psmt.executeUpdate();

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

		return -1; // 데이터 베이스 오류
	}

	public int join2(String userID, String userPassword) {
		System.out.println("userID : " + userID);
		System.out.println("userPassword : " + userPassword);

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "INSERT INTO MEMBER (userID, userPassword) VALUES (?, ?)";
		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			psmt.setString(2, userPassword);
			return psmt.executeUpdate();

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

		return -1; // 데이터 베이스 오류
	}

}
