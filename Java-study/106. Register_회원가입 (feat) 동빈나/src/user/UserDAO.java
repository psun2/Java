package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection con;
	private PreparedStatement psmt;
	private ResultSet rs;

	public UserDAO() {

		this.con = util.DataUtil.getConnection();
		System.out.println("또 con 이 null 이야 ? : " + con);

	}

	// 중복체크
	public int registerCheck(String userID) {
		String sql = "SELECT * FROM USER WHERE userID = ?";
		System.out.println(sql);

		try {
			this.psmt = this.con.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();
			if(userID.equals(""))
				return 2; //공백 안받음
			if (rs.next())
				return 0; // 이미 존재하는 회원
			else
				return 1; // 가입 가능한 회원 아이디
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}
		return -1; // 데이터 베이스 오류
	}

	// 회원 등록 함수
	public int register(UserDTO user) {
		System.out.println(user);

		String sql = "INSERT INTO USER VALUES(?, ?, ?, ?, ?, ?)";
		System.out.println(sql);

		try {
			this.psmt = this.con.prepareStatement(sql);
			psmt.setString(1, user.getUserID());
			psmt.setString(2, user.getUserPassword());
			psmt.setString(3, user.getUserName());
			psmt.setInt(4, user.getUserAge());
			psmt.setString(5, user.getUserGender());
			psmt.setString(6, user.getUserEmail());
			return psmt.executeUpdate(); // 정상적회원가입 1반환
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}
		return -1; // 데이터 베이스 오류
	}

	void close() {

		try {
			if (this.rs != null && !rs.isClosed())
				rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			if (this.psmt != null && !psmt.isClosed())
				psmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			if (this.con != null && !con.isClosed())
				con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
