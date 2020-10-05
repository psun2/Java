package file;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FileDAO {

	DataSource dataSource = null;

	public FileDAO() {
		try {
			InitialContext initContext = new InitialContext();
			Context context = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) context.lookup("jdbc/225. File Up and Down Load");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int upload(String fileName, String fileRealName) {

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "INSERT INTO FILEUPLOAD (fileName, fileRealName) VALUES (?, ?)";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fileName);
			psmt.setString(1, fileRealName);
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
}
