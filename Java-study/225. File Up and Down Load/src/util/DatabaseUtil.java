package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {

	public static Connection getConnection() {

		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "FileUpload";
		String password = "oracle";

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

}
