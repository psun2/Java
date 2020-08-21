package bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DataUtil;

public class BbsDAO {

	// 현재의 시간을 가져오는 함수
	public String getDate() {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT NOW()";

		try {
			con = DataUtil.getConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next())
				return rs.getString(1); // 현재의 날짜 반환

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return ""; // 데이터베이스 오류
	}

	// 게시글의 아이디를 가져옴으로써, 다음 글의 번호를 알 수 있습니다.
	public int getNext() {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		String sql = "SELECT bbsID FROM BBS ORDER BY bbsID DESC";

		try {

			con = DataUtil.getConnection();
			psmt = con.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next())
				return rs.getInt(1) + 1; // 다음 게시글 번호를 반환

			// 만약 첫번째 게시물일 경우에는 가져올 게시글의 번호가 없기때문에
			return 1; // 무조건 1을 반환 (1번 게시글 부터 시작하기 때문..)

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

		return -1; // 데이터베이스 오류
	}

	// 실제로 글을 작성해서 db에 추가하는 함수
	public int write(String bbsTitle, String userID, String bbsContent) {

		Connection con = null;
		PreparedStatement psmt = null;

		String sql = "INSERT INTO BBS VALUES (?, ?, ?, ?, ?, ?)";

		try {

			con = DataUtil.getConnection();
			psmt = con.prepareStatement(sql);
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

			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return -1;
	}

	// 글목록을 가져 오는 함수
	public ArrayList<BbsDTO> getList(int pageNumber) {

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1 ORDER BY bbsID DESC LIMIT 10";

		ArrayList<BbsDTO> list = null;

		try {

			con = DataUtil.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = psmt.executeQuery();

			list = new ArrayList<BbsDTO>();

			while (rs.next()) {
				BbsDTO bbs = new BbsDTO();
				bbs.setBbsID(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsDate(rs.getString(4));
				bbs.setBbsContent(rs.getString(5));
				bbs.setBbsAvailable(rs.getInt(6));

				list.add(bbs);
			}

			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return list;
	}

	// 다음페이지의 존재여부를 반환하는 함수 입니다. (페이징 처리)
	public boolean nextPage(int pageNumber) {
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM BBS WHERE bbsID < ? AND bbsAvailable = 1";

		try {

			con = DataUtil.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, getNext() - (pageNumber - 1) * 10);
			rs = psmt.executeQuery();

			// 해당 결과가 하나라도 존재 한다면....
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed())
					rs.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (psmt != null && !psmt.isClosed())
					psmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				if (con != null && !con.isClosed())
					con.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return false;
	}

}
