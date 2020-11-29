package com.lec.generatedkey.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Component
public class JdbcTemplateBoardDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String sql = " INSERT INTO" +
            " test_write (wr_uid, wr_subject, wr_content, wr_name, wr_regdate)" +
            " VALUES" +
            " (test_write_seq.nextval, ?, ?, ?, SYSDATE)";

    public int write(final BoardDTO dto) { // 객체의 불변성을 위한 final 상수화
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int result = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                String[] generatedKey = {"wr_uid"};
                PreparedStatement psmt = con.prepareStatement(sql, generatedKey);
                psmt.setString(1, dto.getSubject());
                psmt.setString(2, dto.getContent());
                psmt.setString(3, dto.getName());
                return psmt;
            }
        }, generatedKeyHolder);
        log.info("jdbcTemplate: 생성된 uid 는 {}", generatedKeyHolder.getKeyList());
        return result;
    }


}
