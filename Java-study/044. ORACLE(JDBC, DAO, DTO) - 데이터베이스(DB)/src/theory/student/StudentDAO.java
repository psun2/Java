package theory.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentDAO {

	Connection con;
	Statement stmt;
	ResultSet rs;
	String url, user, password;

	public StudentDAO() {
		// TODO Auto-generated constructor stub
		this.url = "jdbc:oracle:thin:@localhost1521:xe";
		this.user = "hr";
		this.password = user;
		
		try {
			this.con = DriverManager.getConnection(url, user, password);
			this.stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
