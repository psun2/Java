package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "LectureEvalueation";
			String password = "oracle";
			
			Class.forName("oracle.jdbc.OracleDriver");
			
			return conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return conn;
		
	}

}
