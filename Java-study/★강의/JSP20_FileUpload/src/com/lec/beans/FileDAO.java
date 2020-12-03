package com.lec.beans;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import common.D;

public class FileDAO {
	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	// DAO 객체가 생성될때 Connection 도 생성된다
	public FileDAO(){
		try {
			Class.forName(D.DRIVER);
			conn = DriverManager.getConnection(D.URL, D.USERID, D.USERPW);
			System.out.println("WriteDAO생성, 데이터베이스 연결!!");
		} catch (Exception e) {			
			e.printStackTrace();
		} // end try
	}// 생성자
	
	// DB 자원 반납 메소드, 만들어놓으면 편함..
	public void close() throws SQLException{
		if(rs != null) rs.close();
		if(pstmt != null) pstmt.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	} // end close();

	
	// 특정 글(wr_uid) 에 첨부파일(들) 추가 INSERT
	// 글 update, insert 시 사용됨.
	public int insert(int wrUid,
			List<String> originalFileNames,  // 원본파일명(들)
			List<String> fileSystemNames  // 저장된 파일명(들)			
			) throws SQLException {
		int cnt = 0;
		
		try {			
			// 첨부파일 정보 추가(저장)하기
			pstmt = conn.prepareStatement(D.SQL_FILE_INSERT);
			for(int i = 0; i < originalFileNames.size(); i++) {
				pstmt.setString(1, originalFileNames.get(i));
				pstmt.setString(2, fileSystemNames.get(i));
				pstmt.setInt(3, wrUid);
				cnt += pstmt.executeUpdate();
			}
		} finally {
			close();
		} // end try
		
		
		return cnt;
	} // end insert()


	// 첨부파일 읽어 들이기 ★
	//---------------------------------------------------------------
	// ResultSet --> DTO배열로 리턴
	public FileDTO [] createArray(ResultSet rs) throws SQLException {
		FileDTO [] arr = null;
		
		ArrayList<FileDTO> list = new ArrayList<FileDTO>();
		
		while(rs.next()){
			int uid = rs.getInt("bf_uid");
			String source = rs.getString("bf_source");
			String file = rs.getString("bf_file");

			FileDTO dto = new FileDTO(uid, source, file);
			list.add(dto);
		}//end while		
		
		arr = new FileDTO[list.size()];  // 리스트에 담긴 DTO 의 개수만큰의 배열 생성
		list.toArray(arr);  // 리스트에 저장된 데이터를 배열에 복사
		
		return arr;
	} // end createArray()


	// 특정 글 (wr_uid) 의 첨부파일(들) SELECT
	public FileDTO [] selectFilesByWrUid(int wrUid) throws SQLException{
		FileDTO [] arr = null;
		
		try{
			// 트랜잭션 처리
			pstmt = conn.prepareStatement(D.SQL_FILE_SELECT);
			pstmt.setInt(1, wrUid);
			rs = pstmt.executeQuery();
			
			arr = createArray(rs);
		}finally{
			close();
		} // end try	
		
		return arr;		
	} // end selectFilesByWrUid()


	// 특정 파일 (bf_uid) 의 첨부파일 한개 SELECT
	public FileDTO [] selectByUid(int uid) throws SQLException{
		FileDTO arr [] = null;
		
		try{
			// 트랜잭션 처리
			pstmt = conn.prepareStatement(D.SQL_FILE_SELECT_BY_UID);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();
			
			arr = createArray(rs);
		}finally{
			close();
		} // end try	
		
		return arr;
	} // end selectByUid()
	
	
	// 물리적인 파일(들) 삭제
	public void deleteFiles(FileDTO [] arr, HttpServletRequest request) {
		if(arr == null || arr.length == 0 || request == null) return;
		
		// 물리적인 경로
		ServletContext context = request.getServletContext();
		String saveDirectory = context.getRealPath("upload");
		
		for(FileDTO dto : arr) {
			
			File f = new File(saveDirectory, dto.getFile());  // 물리적인 삭제 대상
			System.out.println("삭제시도--> " + f.getAbsolutePath());
			
			if(f.exists()) {
				if(f.delete()) {  // 삭제!
					System.out.println("삭제 성공!");
				} else {
					System.out.println("삭제 실패!");
				}
			} else {
				System.out.println("파일이 존재하지 않습니다.");
			}
			
		} // end for
		
	} // end deleteFiles()
	
	// 특정 글 (wr_uid) 의 첨부파일(들) 삭제
	//1) 물리적인 파일(들) 삭제, 2) DB 삭제
	public int deleteByWrUid(int wrUid, HttpServletRequest request) throws SQLException{
		int cnt = 0;
		
		FileDTO [] arr = null;
		
		try {
			// 1) 물리적인 파일(들) 삭제
			pstmt = conn.prepareStatement(D.SQL_FILE_SELECT);
			pstmt.setInt(1, wrUid);
			rs = pstmt.executeQuery();
			arr = createArray(rs);
			
			deleteFiles(arr, request); // 파일 삭제
			
			// 2) test_file 테이블 에서 삭제
			pstmt.close();
			
			pstmt = conn.prepareStatement(D.SQL_FILE_DELETE_BY_WRUID);
			pstmt.setInt(1, wrUid);
			cnt = pstmt.executeUpdate();
			System.out.println("첨부파일 " + cnt + "개 삭제");
			
			
		} finally {
			close();
		}
		
		return cnt;
	} // end deleteByWrUid()
	
	
	// 복수개의 bf_uid 의 파일(들) 제거
	public int deleteByUid(int [] uids, HttpServletRequest request) throws SQLException{
		if(uids == null || uids.length == 0) return 0;
		
		int cnt = 0;
		
		// 101, 204, 319 번 파일을 읽어오려면
		// SELECT * FROM test_file WHERE bf_uid = 101 OR bf_uid = 204 OR bf_uid = 319
		// SELECT * FROM test_file WHERE bf_uid IN (101, 204, 319)
		
		// 101, 204, 319 번 파일을 지우려면? 
		// DELETE FROM test_file WHERE bf_uid = 101 OR bf_uid = 204 OR bf_uid = 319
		// DELETE FROM test_file WHERE bf_uid IN (101, 204, 319)

		try {
			// 1. 물리적인 파일(들) 삭제
			StringBuffer sql = new StringBuffer("SELECT * FROM test_file WHERE bf_uid IN (");
			for(int uid : uids) {
				sql.append(uid + ",");
			}
			sql.deleteCharAt(sql.lastIndexOf(","));  // 맨 끝의 콤마 삭제
			sql.append(")");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql.toString());
			
			FileDTO [] arr = createArray(rs);
			deleteFiles(arr, request);  // 파일 삭제
			
			
			
			// 2. test_file 테이블 에서 삭제
			sql = new StringBuffer("DELETE FROM test_file WHERE bf_uid IN (");
			for(int uid : uids) {
				sql.append(uid + ",");
			}
			sql.deleteCharAt(sql.lastIndexOf(","));  // 맨 끝의 콤마 삭제
			sql.append(")");
			
			System.out.println("파일삭제: " +  sql);
			
			cnt = stmt.executeUpdate(sql.toString());
			
			System.out.println(cnt + " 개 삭제");	
			
		} finally {
			close();
		}
		
		return cnt;
	} // end deleteByUid()

	

} // end DAO


















