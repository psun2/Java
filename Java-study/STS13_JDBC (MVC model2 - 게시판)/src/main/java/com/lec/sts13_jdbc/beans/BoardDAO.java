package com.lec.sts13_jdbc.beans;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import common.BoardQuery;
import lombok.Data;

@Component
@Data
public class BoardDAO implements DAO {

	@Autowired
	@Qualifier("template")
	JdbcTemplate template;

	public List<BoardDTO> selectAll() {

		return template.query(BoardQuery.SQL_WRITE_SELECT, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));

	}

	public int write(BoardDTO dto) {
		return template.update(BoardQuery.SQL_WRITE_INSERT, new PreparedStatementSetter() { // setter 사용

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, dto.getName());
				ps.setString(2, dto.getSubject());
				ps.setString(3, dto.getContent());
			}
		});
	}

	public List<BoardDTO> selectById(long uid) {
		return template.query(BoardQuery.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {
			// 이 방식으로 return 할때 PreparedStatementCreator 은 사용 불가능 합니다.
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, uid);
			}
		}, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
	}

	public int update(BoardDTO dto) {
		return template.update(BoardQuery.SQL_WRITE_UPDATE, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, dto.getSubject());
				ps.setString(2, dto.getContent());
				ps.setLong(3, dto.getUid());
			}
		});
	}

	public int delete(long uid) {
		return template.update(BoardQuery.SQL_WRITE_DELETE_BY_UID, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, uid);
			}
		});
	}

}
