package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataUtil {

	public static Connection getConnection() {

		System.out.println("getConnection() 함수 실행");

		Connection con = null;

		String dbURL = "jdbc:mysql://localhost:3306/REGISTER";
		String dbID = "root";
		String dbPassword = "root";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, dbID, dbPassword);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return con;

	}

}
