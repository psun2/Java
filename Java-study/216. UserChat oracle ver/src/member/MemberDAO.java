package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	DataSource dataSource = null;

	public MemberDAO() {
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/216. UserChat oracle ver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 로그인
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
				if (rs.getString("userPassword").equals(userPassword))
					return 1; // 로그인 성공
				else
					return 2; // 비밀번호 오류
			} else
				return 0; // 존재하지 않는 회원

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

	// 중복확인
	public int registerCheck(String userID) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM MEMBER WHERE userID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();
			if (rs.next() || userID.equals(""))
				return 0; // 이미 존재하는 회원
			else
				return 1; // 가입 가능

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

	// 회원가입
	public int register(String userID, String userPassword, String userName, String userAge, String userGender,
			String userEmail, String userProfile) {

		Connection conn = null;
		PreparedStatement psmt = null;

		String sql = "INSERT INTO MEMBER (userID, userPassword, userName, userAge, userGender, userEmail, userProfile) VALUES(?, ?, ?, ?, ?, ?, ?)";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			psmt.setString(2, userPassword);
			psmt.setString(3, userName);
			psmt.setInt(4, Integer.parseInt(userAge));
			psmt.setString(5, userGender);
			psmt.setString(6, userEmail);
			psmt.setString(7, userProfile);
			return psmt.executeUpdate(); // 회원가입 성공

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
		return -1; // 데이터베이스 오류
	}

	// 한명의 회원을 가져오는함수
	public MemberDTO getUser(String userID) {
		MemberDTO member = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE userID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();

			if (rs.next()) {
				member = new MemberDTO();
				member.setUserID(rs.getString("userID"));
				member.setUserName(rs.getString("userName"));
				member.setUerAge(Integer.parseInt(rs.getString("userAge")));
				member.setUserGender(rs.getString("userGender"));
				member.setUserEmail(rs.getString("userEmail"));
				member.setUserProfile(rs.getString("userProfile"));
			}

			return member;

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
		return member; // 데이터베이스 오류
	}

	// 회원정보 수정함수
	public int update(String userID, String userPassword, String userName, String userAge, String userGender,
			String userEmail) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "UPDATE MEMBER SET userPassword = ?, userName = ?, userAge = ?, userGender = ?, userEmail = ? WHERE userID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userPassword);
			psmt.setString(2, userName);
			psmt.setInt(3, Integer.parseInt(userAge));
			psmt.setString(4, userGender);
			psmt.setString(5, userEmail);
			psmt.setString(6, userID);

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
		return -1; // 데이터베이스 오류
	}

	public int profile(String userID, String fileName) {

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "UPDATE MEMBER SET userProfile = ? WHERE userID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fileName);
			psmt.setString(2, userID);
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

		return -1; // 데이터베이스 오류
	}

	// 사용자의 프로필 주소 반환 (채팅창에서 사용하기 위한)
	public String getProfile(String userID) {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT userProfile FROM MEMBER WHERE userID = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();

			if (rs.next()) {
				String userProfile = rs.getString("userProfile");
				System.out.println(userProfile);
//				if (userProfile.equals(""))
				if (userProfile == null)
					return "http://localhost:8080/216. UserChat oracle ver/images/yellow-48.png";
//					return "http://localhost:8080/216.%20UserChat%20oracle%20ver/WebContent/images/yellow-48.png";

				return "http://localhost:8080/216. UserChat oracle ver/upload/" + userProfile;
			}

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

		return "http://localhost:8080/216. UserChat oracle ver/images/yellow-48.png"; // 데이터 베이스 오류
	}

}
