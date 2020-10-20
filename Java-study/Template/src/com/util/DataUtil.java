package com.util;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DataUtil {
	
	private static DataSource dataSource;
	
	public static Connection getConnection() {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/Template");
			return dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("UnCatched Connection throw");
		}
		return null; // error
	}

}
