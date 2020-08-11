package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DataUtil;

public class UserDAO {

	Connection con;
	PreparedStatement psmt;
	ResultSet rs;

	// 로그인
	public int login(String userID, String userPassword) {

		String sql = "select userPassword from user where userID = ?";

		try {
			this.con = DataUtil.getConnection();
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			this.rs = psmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(userPassword))
					return 1; // 로그인 성공
				else
					return 0; // 비밀번호 틀림
			}

			return -1; // 아이디 없음

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return -2; // 데이터 베이스 오류

	}

	// 회원가입
	public int join(UserDTO user) {

		String sql = "insert into user values (?, ?, ?, ?, false)";
		// 이메일 인증은 처음에 되지 않았으므로, false 값 입력

		try {
			this.con = DataUtil.getConnection();
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, user.getUserID());
			psmt.setString(2, user.getUserPassword());
			psmt.setString(3, user.getUserEmail());
			psmt.setString(4, user.getUserEmailHash());

			return psmt.executeUpdate(); // 회원가입 성공
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return -1; // 회원가입 실패

	}

	// 사용자의 이메일을 반환하는 함수
	public String getUserEmail(String userID) {

		String sql = "select userEmail from user where userID = ?";

		try {
			this.con = DataUtil.getConnection();
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			this.rs = psmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return null; // 데이터베이스 오류

	}

	// 사용자가 현재 이메일 인증이 되었는지를 판가름 해주는 함수
	// 이메일 인증이 안됬을시 강의평가를 등록 할 수 없게 만들기 위해 이메일 인증이 필요
	public boolean getUserEmailChecked(String userID) {

		String sql = "select userEmailChecked from user where userID = ?";
		// 이메일 인증은 처음에 되지 않았으므로, false 값 입력

		try {
			this.con = DataUtil.getConnection();
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			this.rs = psmt.executeQuery();

			if (rs.next()) { // 사용자의 아이디가 존재하는 경우
				return rs.getBoolean(1); // 성공적인 경우 true 값 반환
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return false; // 데이터베이스 오류

	}

	// 특정한 사용자가 이메일 검증을 통해 이메일 인증이 완료가 될 수 있게 해주는 메소드
	public boolean setUserEmailChecked(String userID) {

		String sql = "update user set userEmailChecked = true where userID = ?";
		// 이메일 인증은 처음에 되지 않았으므로, false 값 입력

		try {
			this.con = DataUtil.getConnection();
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			psmt.executeUpdate();
			return true; // 이메일 인증 성공
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return false; // 데이터베이스 오류

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
