package com.lec.jdbc_refactoring.domain;

import common.C;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class WriteDAOImpl implements WriteDAO {

    @Autowired
    @Qualifier("loggerDAO")
    Logger logger;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<WriteDTO> readAll() {
        return jdbcTemplate.query(C.SQL_WRITE_SELECT, new BeanPropertyRowMapper<WriteDTO>(WriteDTO.class));
    }

    @Override
    public int create(final WriteDTO dto) { // 객체의 불변성이 허물어져 final 키워드를 붙여 상수화 합니다.
        return jdbcTemplate.update(C.SQL_WRITE_INSERT, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, dto.getSubject());
                ps.setString(2, dto.getContent());
                ps.setString(3, dto.getName());
            }
        });
    }

    @Override
    public List<WriteDTO> readyAndViewcntIncrease(final long uid) {  // 객체의 불변성이 허물어져 final 키워드를 붙여 상수화 합니다.

        int cnt = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement psmt = con.prepareStatement(C.SQL_WRITE_INC_VIEWCNT);
                psmt.setLong(1, uid);
                return psmt;
            }
        });

        logger.info("viewCnt 증가: {}", cnt);

        return jdbcTemplate.query(C.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, uid);
            }
        }, new BeanPropertyRowMapper<WriteDTO>(WriteDTO.class));
    }

    @Override
    public List<WriteDTO> readByUid(final long uid) {  // 객체의 불변성이 허물어져 final 키워드를 붙여 상수화 합니다.
        return jdbcTemplate.query(C.SQL_WRITE_SELECT_BY_UID, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, uid);
            }
        }, new BeanPropertyRowMapper<WriteDTO>(WriteDTO.class));
    }

    @Override
    public int update(final WriteDTO dto) {  // 객체의 불변성이 허물어져 final 키워드를 붙여 상수화 합니다.
        return jdbcTemplate.update(C.SQL_WRITE_UPDATE, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, dto.getSubject());
                ps.setString(2, dto.getContent());
                ps.setLong(3, dto.getUid());
            }
        });
    }

    @Override
    public int delete(final long uid) {  // 객체의 불변성이 허물어져 final 키워드를 붙여 상수화 합니다.
        return jdbcTemplate.update(C.SQL_WRITE_DELETE_BY_UID, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setLong(1, uid);
            }
        });
    }
}
