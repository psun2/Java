package com.lec.beans;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import common.D;

// DAO : Data Access Object
// DB에 접속하여 트랜잭션을 수행하는(전담하는) 객체

// DAO 객체가 생성될때 Connection 도 생성된다.
public class WriteDAO {
	Connection conn;
	PreparedStatement psmt;
	Statement stmt;
	ResultSet rs;

	public WriteDAO() {
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("WriteDAO 생성, 데이터베이스 연결~!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end 생성자

	// DB 자원 반납.
	public void close() throws SQLException {
		if (rs != null && !rs.isClosed())
			rs.close();
		if (stmt != null && !stmt.isClosed())
			stmt.close();
		if (psmt != null && !psmt.isClosed())
			psmt.close();
		if (conn != null && !conn.isClosed())
			conn.close();
	} // end close()

	// 새글 작성 <-- DTO
	public int insert(WriteDTO dto) throws SQLException {
		int cnt = 0;

		// TODO
		String subject = dto.getSubject();
		String content = dto.getContent();
		String name = dto.getName();
		cnt = this.insert(subject, content, name);

		return cnt;

	} // end insert(DTO)

	// 새글 작성 <-- 제목, 내용, 작성자
	public int insert(String subject, String content, String name) throws SQLException {
		int cnt = 0;

		// TODO
		try {
			psmt = conn.prepareStatement(D.SQL_WRITE_INSERT);
			psmt.setString(1, subject);
			psmt.setString(2, content);
			psmt.setString(3, name);
			cnt = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return cnt;
	} // end insert()

	// ResultSet => DTO배열로 리턴
	public WriteDTO[] createArray(ResultSet rs) throws SQLException {
		WriteDTO[] arr = null;
		List<WriteDTO> list = new ArrayList<WriteDTO>();

		// TODO
		while (rs.next()) {
			int uid = rs.getInt("wr_uid");
			String subject = rs.getString("wr_subject");
			String content = rs.getString("wr_content");
			if (content == null)
				content = "";
			String name = rs.getString("wr_name").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
			int viewcnt = rs.getInt("wr_viewcnt");

			Date d = rs.getDate("wr_regdate"); // 년, 월, 일
			Time t = rs.getTime("wr_regdate"); // 시, 분, 초

			String regDate = "";
			if (d != null) {
				regDate = new SimpleDateFormat("yyyy-mm-dd").format(d) + " "
						+ new SimpleDateFormat("hh:ss:mm").format(t);
			}

			WriteDTO dto = new WriteDTO(uid, subject, content, name, viewcnt);
			dto.setRegDate(regDate);
			list.add(dto);

		} // end while

		int size = list.size();

		if (size == 0)
			return null;
		arr = new WriteDTO[size];
		list.toArray(arr); // 리스트 -> 배열 변환

		return arr;
	} // createArray()

	// 전체 글 SELECT
	public WriteDTO[] select() throws SQLException {
		WriteDTO[] arr = null;

		// TODO
		try {
			psmt = conn.prepareStatement(D.SQL_WRITE_SELECT);
			rs = psmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}

		return arr;
	} // end select()

	// 특정 uid 의 글만 SELECT
	public WriteDTO[] selectByUid(int uid) throws SQLException {
		WriteDTO[] arr = null;

		try {
			psmt = conn.prepareStatement(D.SQL_WRITE_SELECT_BY_UID);
			psmt.setInt(1, uid);
			rs = psmt.executeQuery();
			arr = createArray(rs); // 배열 만드는 코드를 빼서 모듈화 함
		} finally {
			close();
		} // end try

		return arr;
	} // selectByUid()

	// 특정 uid 의 글 내용 읽기 + 조회수 증가
	public WriteDTO[] readByUid(int uid) throws SQLException {

		int cnt = 0;
		WriteDTO[] arr = null;

		try {
			// 트랜잭션 처리
			conn.setAutoCommit(false);
			psmt = conn.prepareStatement(D.SQL_WRITE_INC_VIEWCNT);
			psmt.setInt(1, uid);
			cnt = psmt.executeUpdate();

			psmt.close();
			psmt = conn.prepareStatement(D.SQL_WRITE_SELECT_BY_UID);
			psmt.setInt(1, uid);
			rs = psmt.executeQuery();

			arr = createArray(rs);

			if (cnt == 1)

				conn.commit(); // 트랜잭션 성공!
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback(); // 트랜잭션 실패 하면 rollback()
		} finally {
			close();
		} // end try

		return arr;
	} // end readByUid()

	// 특정 uid 글 수정(제목, 내용)
	public int update(int uid, String subject, String content) throws SQLException {
		int cnt = 0;

		try {
			psmt = conn.prepareStatement(D.SQL_WRITE_UPDATE);
			psmt.setString(1, subject);
			psmt.setString(2, content);
			psmt.setInt(3, uid);
			cnt = psmt.executeUpdate();
		} finally {
			close();
		} // end try

		return cnt;

	} // end update()

	// 특정 uid 글 삭제
	public int deletByUid(int uid) throws SQLException {
		int cnt = 0;

		try {
			psmt = conn.prepareStatement(D.SQL_WRITE_DELETE_BY_UID);
			psmt.setInt(1, uid);
			cnt = psmt.executeUpdate();
		} finally {
			close();
		} // end try

		return cnt;
	} // end deletByUid()

} // end DAO