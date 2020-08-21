package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DataUtil;

public class UserDAO {

	Connection con;
	PreparedStatement psmt;
	ResultSet rs;

	public UserDAO() {
		// TODO Auto-generated constructor stub
		this.con = new DataUtil().getConnection();
	}

	public int login(String userID, String userPassword) {

		String sql = "SELECT userPassword FROM USER WHERE userID = ?";

		try {

			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			this.rs = psmt.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(userPassword))
					return 1; // 로그인 성공
				else
					return 0; // 비밀번호 불일치
			}
			return -1; // 존재하지 않는 사용자 (아이디가 없음)
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return -2; // 데이터베이스 오류
	}

	public int join(UserDTO user) {

		String sql = "INSET INTO USER VALUES (?, ?, ?, ?, ?)";

		try {

			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, user.getUserID());
			psmt.setString(2, user.getUserPassword());
			psmt.setString(3, user.getUserName());
			psmt.setString(4, user.getUserGender());
			psmt.setString(5, user.getUserEmail());
			return psmt.executeUpdate(); // 회원가입 완료
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return -1; // 데이터베이스 오류
	}

	void close() {

		try {
			if (this.rs != null && !this.rs.isClosed())
				rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			if (this.psmt != null && !this.psmt.isClosed())
				psmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			if (this.con != null && !this.con.isClosed())
				con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
