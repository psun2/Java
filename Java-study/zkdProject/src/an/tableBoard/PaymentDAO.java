package an.tableBoard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import hong.InitData;

public class PaymentDAO {

	Connection con;
	Statement stmt;
	ResultSet rs;
	
	String sql;
	String url ="jdbc:oracle:thin:@"+InitData.ip+":1521:xe";
	String id = "hr";
	String pw = "hr";
	
	public PaymentDAO() {
	
		try {
			con = DriverManager.getConnection(url,id,pw);
			
			stmt = con.createStatement();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public ArrayList<PaymentDTO> payment() {
		
		ArrayList<PaymentDTO> res = new ArrayList<PaymentDTO>();
		sql = "select * from billslist";
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				PaymentDTO dto = new PaymentDTO();
				
				dto.bills_tablenum = rs.getString("bills_tablenum");
				dto.bills_orderedmenu = rs.getString("bills_orderedmenu");
				dto.bills_count = rs.getInt("bills_count");
				dto.bills_price = rs.getInt("bills_price");
			
				res.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}

		return res;
		
	}
		
	
	
	
	public void close ()  {
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
	}
	
	
	public static void main(String[] args) {
		for (PaymentDTO ss : new PaymentDAO().payment()) {
			System.out.println(ss);
		}
	}
	
	

}
