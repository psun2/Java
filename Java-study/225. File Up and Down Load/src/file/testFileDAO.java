package file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import util.DatabaseUtil;

public class testFileDAO {

	public static void testUtil() {

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM FileUpload";

			conn = DatabaseUtil.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			System.out.println("DB접속 성공");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println("DB접속 실패");
	}

	public static void testContext() {

		DataSource dataSource = null;

		try {
			InitialContext initContext = new InitialContext();
			Context context = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) context.lookup("jdbc/225. File Up and Down Load");
		} catch (Exception e) {
			e.printStackTrace();
		}

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM FileUpload";

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			System.out.println("DB접속 성공");
			return;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println("DB접속 실패");
	}
	
	public static void main(String[] args) {
	
		testFileDAO.testUtil();

	}

}
