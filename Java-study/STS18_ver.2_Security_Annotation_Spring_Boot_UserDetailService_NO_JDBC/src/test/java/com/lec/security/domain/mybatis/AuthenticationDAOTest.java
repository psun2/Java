package com.lec.security.domain.mybatis;

import com.lec.security.domain.AuthenticationDTO;
import jdk.nashorn.internal.runtime.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Logger
class AuthenticationDAOTest {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MemberDAOTest.class);
    private static DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;

    private long start;
    private long end;

    @BeforeAll
    static void 디비커넥션생성() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        driverManagerDataSource.setUsername("scott");
        driverManagerDataSource.setPassword("tiger");
        dataSource = driverManagerDataSource;

        JdbcTemplate jt = new JdbcTemplate();
        jt.setDataSource(dataSource);
        jdbcTemplate = jt;
    }

    @BeforeEach
    void 시작시간() {
        start = System.currentTimeMillis();
        log.info("시작시간: {}ms", start);
    }

    @AfterEach
    void 끝시간() {
        end = System.currentTimeMillis();
        log.info("경과시간: {}ms", end - start);
    }

    @Test
    void 디비자료생성() {
        final String sql = "INSERT" +
                " INTO authentication (memberId, auth)" +
                " VALUES (?, ?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement psmt = con.prepareStatement(sql);
                psmt.setString(1, "tjdddjs90");
                psmt.setString(2, "ROLE_ADMIN");
                return psmt;
            }
        });

    }

    @Test
    void 디비자료생성2() {
        final String sql = "INSERT" +
                " INTO authentication (memberId)" +
                " VALUES (#{username}";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement psmt = con.prepareStatement(sql);
                psmt.setString(1, "tjdddjs91");
                return psmt;
            }
        });

    }

    @Test
    void 디비셀렉() {
        final String sql = "SELECT memberId as username, auth as authority" +
                " FROM authentication" +
                "WHERE memberId = ?";

        List<AuthenticationDTO> list = jdbcTemplate.query(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement psmt = con.prepareStatement(sql);
                psmt.setString(1, "tjdddjs90");
                return psmt;
            }
        }, new BeanPropertyRowMapper<AuthenticationDTO>(AuthenticationDTO.class));
    }

}