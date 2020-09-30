package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {

	DataSource dataSource = null;

	public UserDAO() {

		try {
			InitialContext initContext = new InitialContext();
			Context context = (Context) initContext.lookup("java:/comp/env");
			this.dataSource = (DataSource) context.lookup("jdbc/223. 강의평가 웹사이트 oracle_ver");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 로그인 함수
	public int login(String userID, String userPassword) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT userPassword FROM MEMBER WHERE userID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(userPassword))
					return 1; // 로그인 성공
				else
					return 0; // 비밀번호 오류
			}
			return -1; // 아이디 없음
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
		return -2; // 데이터 베이스 오류
	}

	// 회원가입 함수
	public int join(UserDTO user) {

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "INSERT INTO MEMBER (userID, userPassword, userEmail, userEmailHash, userEmailChecked) VALUES (?, ?, ?, ?, 0)";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUserID());
			psmt.setString(2, user.getUserPassword());
			psmt.setString(3, user.getUserEmail());
			psmt.setString(4, user.getUserEmailHash());
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

	// 회원의 이메일 반환
	public String getUserEmail(String userID) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT userEmail FROM MEMBER WHERE userID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();

			if (rs.next())
				return rs.getString(1);
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
		return null; // 데이터 베이스 오류
	}

	// 이메일 인증여부 확인
	public int getUserEmailChecked(String userID) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT userEmailChecked FROM MEMBER WHERE userID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();

			if (rs.next())
				return rs.getInt(1);
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
		return -1; // 데이터 베이스 오류
	}

	// 이메일 인증 함수
	public int setUserEmailChecked(String userID) {

		Connection conn = null;
		PreparedStatement psmt = null;

		String sql = "UPDATE MEMBER SET userEmailChecked = 1 WHERE userID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
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
