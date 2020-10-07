package file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
		String sql = "INSERT INTO FILEUPLOAD (fileName, fileRealName, downloadCount) VALUES (?, ?, 0)";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, fileName);
			psmt.setString(2, fileRealName);
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

	
	
	// 다운로드 횟수 증가
	public int hit(String fileRealName) {
		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "UPDATE FILEUPLOAD SET downloadCount = downloadCount + 1 WHERE fileRealName = ?";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
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
	
	public ArrayList<FileDTO> getList() {

		ArrayList<FileDTO> fileList = null;
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM FILEUPLOAD";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			fileList = new ArrayList<FileDTO>();
			while (rs.next()) {
				FileDTO file = new FileDTO();

				if (rs.getString(1) == null || rs.getString(1).equals("") || rs.getString(2) == null
						|| rs.getString(2).equals(""))
					continue;

				file.setFileName(rs.getString(1));
				file.setFileRealName(rs.getString(2));
				file.setDownloadCount(rs.getInt(3));

				fileList.add(file);
			}
			

			return fileList;

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
		return null; // 데이터베이스 오류
	}
}
