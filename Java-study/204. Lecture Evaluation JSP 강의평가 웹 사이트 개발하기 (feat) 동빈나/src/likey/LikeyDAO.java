package likey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DataUtil;

public class LikeyDAO {

	Connection con;
	PreparedStatement psmt;
	ResultSet rs;

	public int like(String userID, String evaluationID, String userIP) {

		// likey 테이블의 userID 와 evaluationID 는 primary key 로 구성되어 있으며,
		// 다중으로 primary 키를 등록한 경우 둘중 하나라도 unique 하면 테이블에 등록 됩니다.
		// ex) 'admin', 34
		// ex) 'admin', 33
		// primary가 걸린 userID가 겹쳐도 evaluationID 가 겹치지 않으므로 insert에 문제가 나지 않습니다.

		String sql = "insert into likey values (?, ?, ?)";
		// 이메일 인증은 처음에 되지 않았으므로, false 값 입력

		try {
			this.con = DataUtil.getConnection();
			this.psmt = con.prepareStatement(sql);
			psmt.setString(1, userID);
			psmt.setInt(2, Integer.parseInt(evaluationID));
			psmt.setString(3, userIP);

			return psmt.executeUpdate(); // 추천 성공
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return -1; // 추천 중복 오류
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
