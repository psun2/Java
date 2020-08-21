package bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DataUtil;

public class BbsDAO_failed {

	// 실패한 DAO
	// write 함수에서 getNext 와 getDate 로 인하여
	// preparedstatement 가 계속 열리고 닫히고 해서 Error 발생

	Connection con;
	PreparedStatement psmt;
	ResultSet rs;

	public BbsDAO_failed() {
		// TODO Auto-generated constructor stub
		this.con = DataUtil.getConnection();
	}

	// 현재의 시간을 가져오는 함수
	public String getDate() {

		String sql = "SELECT NOW()";

		try {

			this.psmt = con.prepareStatement(sql);
			this.rs = psmt.executeQuery();
			if (rs.next())
				return rs.getString(1); // 현재의 날짜 반환

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}

		return ""; // 데이터베이스 오류
	}

	// 게시글의 아이디를 가져옴으로써, 다음 글의 번호를 알 수 있습니다.
	public int getNext() {

		String sql = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";

		try {

			this.psmt = con.prepareStatement(sql);
			this.rs = psmt.executeQuery();
			if (rs.next())
				return rs.getInt(1) + 1; // 다음 게시글 번호를 반환

			// 만약 첫번째 게시물일 경우에는 가져올 게시글의 번호가 없기때문에
			return 1; // 무조건 1을 반환 (1번 게시글 부터 시작하기 때문..)

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}

		return -1; // 데이터베이스 오류
	}

	// 실제로 글을 작성해서 db에 추가하는 함수
	public int write(String bbsTitle, String userID, String bbsContent) {

		System.out.println(bbsTitle);
		System.out.println(userID);
		System.out.println(bbsContent);

		String sql = "INSERT INTO BBS VALUES (?, ?, ?, ?, ?, ?)";

		try {

			this.psmt = con.prepareStatement(sql);
			psmt.setInt(1, getNext());
			psmt.setString(2, bbsTitle);
			psmt.setString(3, userID);
			psmt.setString(4, getDate());
			psmt.setString(5, bbsContent);
			psmt.setInt(6, 1); // 처음엔 글이 작성된 상태이고, 보여야 하므로, 1을 임의로 삽입
			return psmt.executeUpdate(); // 글쓰기 성공
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}
		return -1;
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
