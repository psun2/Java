package an.tableBoard;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import hong.InitData;

public class PaymentDB_DAO {

	Connection con;
	Statement stmt;
	ResultSet rs;
	
	String sql;
	
	String url ="jdbc:oracle:thin:@"+InitData.ip+":1521:xe";
	String id = "hr";
	String pw = "hr";
	
	
	public PaymentDB_DAO() {
		
		
		try {
			con = DriverManager.getConnection(url,id,pw);
			
			stmt =  con.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public int insert(PaymentDB_DTO dto) {
		
		int res = 0;
		sql = "insert into member ";

		
		System.out.println(sql);
		
		
		try {
			res = stmt.executeUpdate(sql);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	
		return res;	
	}
	
	public void close() {
		if(rs!=null) try { rs.close();} catch (SQLException e) { }
		if(stmt!=null) try { stmt.close();} catch (SQLException e) { }
		if(con!=null) try { con.close();} catch (SQLException e) { }
	}
	
	
	
}
