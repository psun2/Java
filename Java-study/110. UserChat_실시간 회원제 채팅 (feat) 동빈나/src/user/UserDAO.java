package user;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {

	DataSource dataSource; // 커네션 풀을 사용 하기 위한 DataSource 변수

	public UserDAO() {
		// TODO Auto-generated constructor stub
		try {
			InitialContext initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/110. UserChat_실시간 회원제 채팅 (feat) 동빈나");
			System.out.println(dataSource);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public int login(String userID, String userPassword) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM USER WHERE userID = ?";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("userPassword").equals(userPassword)) {
					return 1; // 로그인 성공
				}
				return 2; // 비밀번호 오류
			} else {
				return 0; // 존재 하지 않는 사용자
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}

	public int registerCheck(String userID) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM USER WHERE userID = ?";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();

			if (rs.next() || userID.equals("")) {
				// 이미 아이디가 존재하거나 아이디가 공백인 경우 => 가입 알 수 없습니다.
				return 0; // 이미 존재하는 회원
			} else {
				return 1; // 가입 가능한 회원 아이디
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}

	public int register(String userID, String userPassword, String userName, String userAge, String userGender,
			String userEmail, String userProfile) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			psmt.setString(2, userPassword);
			psmt.setString(3, userName);
			psmt.setInt(4, Integer.parseInt(userAge));
			psmt.setString(5, userGender);
			psmt.setString(6, userEmail);
			psmt.setString(7, userProfile);
			return psmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}

	// 한명의 유저 정보를 가져옴
	public UserDTO getUser(String userID) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		UserDTO user = null;

		String sql = "SELECT * FROM USER WHERE userID = ?";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();

			if (rs.next()) {
				user = new UserDTO();
				user.setUserID(userID);
				user.setUserPassword(rs.getString("userPassword"));
				user.setUserName(rs.getString("userName"));
				user.setUserAge(rs.getInt("userAge"));
				user.setUserGender(rs.getString("userGender"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserProfile(rs.getString("userProfile"));
			}

			return user;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return user; // 데이터베이스 오류
	}

	// 회원정보 수정
	public int update(String userID, String userPassword, String userName, String userAge, String userGender,
			String userEmail) {
		Connection con = null;
		PreparedStatement psmt = null;

		System.out.println(userID);
		System.out.println(userPassword);
		System.out.println(userName);
		System.out.println(userAge);
		System.out.println(userGender);
		System.out.println(userEmail);

		String sql = "UPDATE USER SET userPassword = ?, userName = ?, userAge = ?, userGender = ?, userEmail = ? WHERE userID = ?";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userPassword);
			psmt.setString(2, userName);
			psmt.setInt(3, Integer.parseInt(userAge));
			psmt.setString(4, userGender);
			psmt.setString(5, userEmail);
			psmt.setString(6, userID);
			return psmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}

	// 프로필 사진 변경 및 등록
	public int profile(String userID, String userProfile) {
		Connection con = null;
		PreparedStatement psmt = null;

		String sql = "UPDATE USER SET userProfile = ? WHERE userID = ?";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userProfile);
			psmt.setString(2, userID);
			return psmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return -1; // 데이터베이스 오류
	}

	// 프로필 사진의 경로를 가지고 옵니다.
	public String getProfile(String userID) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT userProfile FROM USER WHERE userID = ?";

		try {
			con = dataSource.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			rs = psmt.executeQuery();

			String URL = "http://localhost:8080/";
			URL += "110.%20UserChat_실시간%20회원제%20채팅%20(feat)%20동빈나";
//			URL += URLEncoder.encode("110. UserChat_실시간 회원제 채팅 (feat) 동빈나", "UTF-8");

			if (rs.next()) {
				// 사용자의 프로필이 공백인 경우 즉, 가입후 프로필 사진을 업데이트를 안 한 경우
				if (rs.getString("userProfile").equals("")) {
					URL += "/images/yellow-48.png";
					// return "http://localhost:8080/110. UserChat_실시간 회원제 채팅 (feat)
					// 동빈나/images/yellow-48.png";
					return URL;
				}

				// 공백이 아닌 경우 즉 사용자가 프로필 파일을 업로드 한 경우
				URL += "/upload/" + rs.getString("userProfile");
				return URL;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
				if (psmt != null && !psmt.isClosed())
					psmt.close();
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return "https://placeimg.com/64/64/any"; // 데이터베이스 오류
	}

}
