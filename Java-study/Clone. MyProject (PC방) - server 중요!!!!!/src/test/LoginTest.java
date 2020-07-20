package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginTest {

	public static void main(String[] args) {

		boolean result = loginTest("admin", "0000");

		System.out.println("로그인 결과 : " + result);

		System.out.println();

		System.out.println("로그인 결과 : " + loginTest("admin", "1004"));

	}

	public static boolean loginTest(String id, String password) {

		boolean flag = false;

//		String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbUrl = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String dbUser = "hr";
		String dbPassword = "hr";
		String sql = null;

		String tempId = null;
		String tempPassWord = null;

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
			stmt = con.createStatement();

			sql = "select * from pcmember";
			System.out.println(sql);

			rs = stmt.executeQuery(sql);

			if (rs.next()) {

				tempId = rs.getString("id");
				if (tempId.equals(id)) {

					System.out.println("받아온 아이디 : " + tempId);
					System.out.println("사용자가 입력한 아이디 : " + id);

					// 컬럼명이 password 인 값을 불러 온다
					tempPassWord = rs.getString("password");

					// 사용자가 입력한 값과 db의 값이 같다면
					if (tempPassWord.equals(password)) {
						System.out.println("받아온 비밀번호 : " + tempPassWord);
						System.out.println("사용자가 입력한 비밀번호 : " + password);
						flag = true;
					}

					// Error
					// 이런 문법을 사용 하려 했지만 next 함수가 일회용 ...
					// 딱 한번 읽을 값이 있나 없나만 반환
//					if (rs.next()) { // 다음 읽어올 문자열이 있다면...
//						
//						System.out.println("진입");
//
//						// 컬럼명이 password 인 값을 불러 온다
//						tempPassWord = rs.getString("password");
//
//						// 사용자가 입력한 값과 db의 값이 같다면
//						if (tempPassWord.equals(password)) {
//							System.out.println("받아온 비밀번호 : " + tempPassWord);
//							System.out.println("사용자가 입력한 비밀번호 : " + password);
//							flag = true;
//						}
//
//					}
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			// 자원반납
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (stmt != null && !stmt.isClosed())
					stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return flag;

	}

}

//select * from pcmember
//받아온 아이디 : admin
//사용자가 입력한 아이디 : admin
//받아온 비밀번호 : 0000
//사용자가 입력한 비밀번호 : 0000
//로그인 결과 : true
//
//select * from pcmember
//받아온 아이디 : admin
//사용자가 입력한 아이디 : admin
//로그인 결과 : false
