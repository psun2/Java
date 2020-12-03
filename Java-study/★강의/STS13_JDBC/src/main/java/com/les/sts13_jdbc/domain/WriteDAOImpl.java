package com.les.sts13_jdbc.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import common.C;

@Component
public class WriteDAOImpl implements WriteDAO {
	JdbcTemplate template;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public WriteDAOImpl() {
		super();
		System.out.println("WriteDAOImple() 생성");
	}
	
	// 전체 SELECT
	@Override
	public List<WriteDTO> select(){
		return template.query(C.SQL_WRITE_SELECT, 
				new BeanPropertyRowMapper<WriteDTO>(WriteDTO.class));
	}
	
	// final + ??
	// final + 변수 => 더이상 변경 X (상수)
	// final + 메소드 => 더이상 오버라이딩 X
	// final + 클래스 => 더이상 상속 X

	@Override
	public int insert(final WriteDTO dto) {
		
		// 1. update() + PreapredStatementSetter() 사용
//		return
//		template.update(C.SQL_WRITE_INSERT, new PreparedStatementSetter() {
//			
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, dto.getSubject());
//				ps.setString(2, dto.getContent());
//				ps.setString(3, dto.getName());				
//			}
//		});
		
		// 2. update() + PreparedStatementCreate() 사용
		return
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(C.SQL_WRITE_INSERT);
				pstmt.setString(1, dto.getSubject());
				pstmt.setString(2, dto.getContent());
				pstmt.setString(3, dto.getName());
				return pstmt;
			}
		});
			
	} // end insert()
	
	@Override
	public List<WriteDTO> readByUid(final int uid){

		// 조회수 증가
		this.template.update(C.SQL_WRITE_INC_VIEWCNT, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, uid);
				
			}
		});
		
		// 글 읽어오기
		List<WriteDTO> list =
				template.query(C.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						ps.setInt(1, uid);
						
					}
				}, new BeanPropertyRowMapper<WriteDTO>(WriteDTO.class));
		
		
		return list; 
		
	}
	
	// 글 읽기 (update.do)
	@Override
	public List<WriteDTO> selectByUid(final int uid){
		return
			template.query(C.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, uid);
					
				}
			}, new BeanPropertyRowMapper<WriteDTO>(WriteDTO.class));
	}
	
	//  글 수정 
	@Override
	public int update(final WriteDTO dto) {
		return 
		template.update(C.SQL_WRITE_UPDATE, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getContent());
				ps.setInt(3, dto.getUid());
			}
		});
	}
	
	// 글삭제
	@Override
	public int deleteByUid(final int uid) {
		return
			template.update(C.SQL_WRITE_DELETE_BY_UID, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, uid);					
				}
			});
	}
	
	
}
















