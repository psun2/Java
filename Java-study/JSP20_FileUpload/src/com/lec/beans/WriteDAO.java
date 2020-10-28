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

import common.D;

// DAO : Data Access Object
// DB에 접속하여 트랜잭션을 수행하는(전담하는) 객체

public class WriteDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	// DAO 객체가 생성될때 Conneciton 도 생성된다.
	public WriteDAO() {
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
//			System.out.println("WriteDAO 생성, 데이터베이스 연결!~");
		} catch(Exception e) {
			e.printStackTrace();
		} // end try		
	} // end 생성자
	
	// DB 자원 반납.
	public void close() throws SQLException {
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close(); 
	} // end close()
	
	
	// 새글 작성 <-- DTO
	public int insert(WriteDTO dto) throws SQLException {
		int cnt = 0;
		
		String subject = dto.getSubject();
		String content = dto.getContent();
		String name = dto.getName();
		cnt = this.insert(subject, content, name);
		
		return cnt;
	} // end insert(DTO)
	
	// 새글 작성 <-- 제목, 내용, 작성자
	public int insert(String subject, String content, String name) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_INSERT);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, name);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		}
		
		return cnt;
	} // end insert()
	
	
	// ResultSet => DTO배열로 리턴
	public WriteDTO [] createArray(ResultSet rs) throws SQLException {
		WriteDTO [] arr = null;
		ArrayList<WriteDTO> list = new ArrayList<WriteDTO>();
		
		while(rs.next()) {
			int uid = rs.getInt("wr_uid");
			String subject = rs.getString("wr_subject");
			String content = rs.getString("wr_content");
			if(content == null) content = "";
			String name = rs.getString("wr_name");
			int viewCnt = rs.getInt("wr_viewcnt");
			
			Date d = rs.getDate("wr_regdate");  // 년, 월, 일
			Time t = rs.getTime("wr_regdate");  // 시, 분, 초
			
			String regDate = "";
			if(d != null) {
				regDate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " "
						+ new SimpleDateFormat("hh:mm:ss").format(t);
			}
			
			WriteDTO dto = new WriteDTO(uid, subject, content, name, viewCnt);
			dto.setRegDate(regDate);
			
			list.add(dto);
		} // end while
		
		int size = list.size();
		
		if(size == 0) return null;
		arr = new WriteDTO[size];
		list.toArray(arr);  // 리스트 -> 배열 변환
		
		return arr;
	} // end createArray()
	
	
	// 전체 글 SELECT
	public WriteDTO [] select() throws SQLException {
		WriteDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_SELECT);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		}
		
		return arr;
		
	} // end select()
	
	
	// 특정 uid 의 글 만 SELECT
	public WriteDTO [] selectByUid(int uid) throws SQLException{
		WriteDTO [] arr = null;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_SELECT_BY_UID);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
		} finally {
			close();
		} // end try
		
		return arr;
	} // end selectByUid()
	
	
	// 특정 uid 의 글 내용 읽기 + 조회수 증가
	public WriteDTO [] readByUid(int uid) throws SQLException{
		
		int cnt = 0;
		WriteDTO [] arr = null;
		
		try {
			// 트랜잭션 처리
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(D.SQL_WRITE_INC_VIEWCNT);
			pstmt.setInt(1, uid);
			cnt = pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = conn.prepareStatement(D.SQL_WRITE_SELECT_BY_UID);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
			
			arr = createArray(rs);
			
			conn.commit();  // 트랜잭션 성공!
		} catch (SQLException e) {
			conn.rollback();  // 트랜잭션 실패하면 rollback()
			throw e;
		} finally {
			close();
		} // end try		
		
		
		return arr;
	} // end readByUid()
	
	
	// 특정 uid 글 수정 (제목, 내용)
	public int update(int uid, String subject, String content) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_UPDATE);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, uid);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		} // end try
		
		return cnt;
	} // end update()
	
	// 특정 uid 글 삭제
	public int deleteByUid(int uid) throws SQLException {
		int cnt = 0;
		
		try {
			pstmt = conn.prepareStatement(D.SQL_WRITE_DELETE_BY_UID);
			pstmt.setInt(1, uid);
			cnt = pstmt.executeUpdate();
		} finally {
			close();
		} // end try
		
		return cnt;
	}

} // end DAO



























