package com.lec.sts13_jdbc.domain;

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
import lombok.Data;

@Component
@Data
public class WriteDAOImpl implements WriteDAO {
	// Impl => implements 구현한 구현체라는 의미

	@Autowired
	JdbcTemplate template;

	public WriteDAOImpl() {
		super();
		System.out.println("writeDAOImple() 생성");
	}

	// 전체 SELECT
	@Override
	public List<WriteDTO> select() {
		return template.query(C.SQL_WRITE_SELECT, new BeanPropertyRowMapper<WriteDTO>(WriteDTO.class));
	}

	// final + ??
	// final + 변수 => 더이상 변경 X (상수)
	// final + 메소드 => 더이상 오버라이딩 X
	// final + 클래스 => 더이상 상속 X
	@Override
	public int insert(final WriteDTO dto) {

		// 1. update() + PreparedStatementSetter() 사용
//		return template.update(C.SQL_WRITE_INSERT, new PreparedStatementSetter() {
//			// PreparedStatementSetter : 익명 클래스는 같은 스코프의 지역변수를 사용 할  수 없습니다.
//			// 그러므로 받아온 dto 를 final 해주어 상수로 변경합니다.
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setString(1, dto.getSubject());
//				ps.setString(2, dto.getContent());
//				ps.setString(3, dto.getName());
//			}
//		});

		// 2. update() + PreparedStatementCreate() 사용
		return template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(C.SQL_WRITE_INSERT);
				psmt.setString(1, dto.getSubject());
				psmt.setString(2, dto.getContent());
				psmt.setString(3, dto.getName());
				return psmt;
			}
		});
	} // end insert()

	// 글을 읽어 조회수 증가
	@Override
	public List<WriteDTO> readByUid(long uid) {

		// 조회수 증가
		template.update(C.SQL_WRITE_INC_VIEWCNT, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, uid);
			}
		});

		// list 반환
		return template.query(C.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, uid);
			}
		}, new BeanPropertyRowMapper<WriteDTO>(WriteDTO.class));

	} // end readByUid()

	// 글 읽기(update.do) => 업데이트에 꽃아줄때 조회수가 증가하면 안되기 때문...
	@Override
	public List<WriteDTO> selectByUid(long uid) {

		return template.query(C.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, uid);
			}
		}, new BeanPropertyRowMapper<WriteDTO>(WriteDTO.class));

	} // end selectByUid();

	@Override
	public int update(WriteDTO dto) {

		return template.update(C.SQL_WRITE_UPDATE, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getContent());
				ps.setLong(3, dto.getUid());
			}
		});
	} // end update()

	@Override
	public int deleteByUid(long uid) {
		return template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement psmt = con.prepareStatement(C.SQL_WRITE_DELETE_BY_UID);
				psmt.setLong(1, uid);
				return psmt;
			}
		});
	} // end deleteByUid()

}
