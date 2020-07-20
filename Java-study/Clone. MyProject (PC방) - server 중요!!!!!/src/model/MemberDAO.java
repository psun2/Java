package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberDAO {

	private final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String user = "hr";
	private final String password = "hr";
	String sql;

//	Connection con;
//	Statement stmt;
//	ResultSet rs;

	public MemberDAO() {
		// TODO Auto-generated constructor stub
//		this.con = DriverManager.getConnection(url, user, password);
//		this.stmt = con.createStatement();

		// rs = stmt.executeQuery(sql);

	}

}
