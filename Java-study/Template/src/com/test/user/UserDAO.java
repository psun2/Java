package com.test.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.util.DataUtil;

public class UserDAO implements UserRepository {
	
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public int write(String a, String b, String c) {
		final String SQL = "INSERT INTO test_write (wr_uid, wr_subject, wr_content, wr_name, wr_regdate) VALUES (test_write_seq.nextval, ?, ?, ?, SYSDATE)";

		try {
			conn = DataUtil.getConnection();
			psmt = conn.prepareStatement(SQL);
			psmt.setNString(1, a);
			psmt.setNString(2, b);
			psmt.setNString(3, c);
			return psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("I cannot write.");
		} finally {
			close();
		}
		return -1;
	}

	@Override
	public void close() {
		try {
			if (rs != null && !rs.isClosed())
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ResultSet cannot be returned.");
		}

		try {
			if (psmt != null && !psmt.isClosed())
				psmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("PreparedStatement cannot be returned.");
		}

		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Connection cannot be returned.");
		}
	}
}
