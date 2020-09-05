package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataUtil {

	public static Connection getConnection() {

		Connection con = null;

		try {

			String url = "jdbc:mysql://localhost:3306/USERCHAT";
			String user = "root";
			String password = "root";
			Class.forName("com.mysql.jbdc.Driver");
			con = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

}
