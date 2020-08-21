package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataUtil {

	public Connection getConnection() {

		Connection con = null;

		try {
			String dbURL = "jdbc:mysql://localhost:3306/BBS";
			String dbUser = "root";
			String dbPassword = "root";
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(dbURL, dbUser, dbPassword);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return con;
	}

}
