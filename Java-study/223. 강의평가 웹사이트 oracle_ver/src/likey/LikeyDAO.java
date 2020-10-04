package likey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LikeyDAO {

	DataSource dataSource = null;

	public LikeyDAO() {

		try {

			InitialContext initContext = new InitialContext();
			Context context = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) context.lookup("jdbc/223. 강의평가 웹사이트 oracle_ver");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int like(String userID, String evaluationID, String userIP) {

		Connection conn = null;
		PreparedStatement psmt = null;
		String sql = "INSERT INTO LIKEY (userID, evalueationID, userIP) VALUES (?, ?, ?)";

		try {

			conn = dataSource.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userID);
			psmt.setInt(2, Integer.parseInt(evaluationID));
			psmt.setString(3, userIP);

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
		return -1; // 추천 중복 오류
	}

}
