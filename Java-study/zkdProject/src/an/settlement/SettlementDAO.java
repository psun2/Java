package an.settlement;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import hong.InitData;

public class SettlementDAO {
	
	Connection con ;
	Statement stmt;
	ResultSet rs;
	String sql;
	
	String url ="jdbc:oracle:thin:@"+InitData.ip+":1521:xe";
	String id = "hr";
	String pw = "hr";
	
	
	public SettlementDAO() {
		try {
			con = DriverManager.getConnection(url,id,pw);
			
			stmt = con.createStatement();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public ArrayList<SettlementDTO> settlement () {
		

		ArrayList<SettlementDTO> res = new ArrayList<SettlementDTO>();
		sql = "select * from billslist";
		
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				SettlementDTO dto = new SettlementDTO();
				
				dto.bills_time = rs.getDate("BILLS_TIME");
				//dto.bills_INDEX= rs.getInt("BILLS_INDEX");
				dto.bills_PRICE = rs.getInt("bills_PRICE");
				
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
		for (SettlementDTO ss : new SettlementDAO().settlement()) {
			System.out.println(ss);

		}
	}
}
