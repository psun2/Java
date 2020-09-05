package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataUtil {

	public static Connection getConnection() {

		Connection con = null;
		String dbURL = "jdbc:mysql://localhost:3306/LectureEvaluation";
		String dbID = "root";
		String dbPassword = "root";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return con;

	}

}
