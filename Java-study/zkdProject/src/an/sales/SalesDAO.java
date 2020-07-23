package an.sales;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import hong.InitData;

public class SalesDAO {
	
	Connection con;
	Statement stmt;
	ResultSet rs;

	String sql;
	
	
	String url = "jdbc:oracle:thin:@"+InitData.ip+":1521:xe";
	String id = "hr";
	String pw = "hr";
	
	
	public SalesDAO() {
		try {
			con = DriverManager.getConnection(url, id, pw);		
			stmt = con.createStatement();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<SalesDTO>list () {
		
		
		ArrayList<SalesDTO> res = new ArrayList<SalesDTO>();
		sql = "select * from billslist";
		
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				SalesDTO dto = new SalesDTO();
				
				dto.BILLS_INDEX = rs.getInt("BILLS_INDEX");
				dto.BILLS_TABLENUM = rs.getString("BILLS_TABLENUM");
				dto.BILLS_ORDEREDMENU = rs.getString("BILLS_ORDEREDMENU");
				dto.BILLS_COUNT = rs.getInt("BILLS_COUNT");
				dto.BILLS_PRICE = rs.getInt("BILLS_PRICE");
				dto.BILLS_TIME = rs.getTimestamp("BILLS_TIME");
				
				res.add(dto);	
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close();
		}	
		return res;		
	}
	
	
	
	public void close() {	
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
		
	}
	
	public static void main(String[] args) {	
		for (SalesDTO tt : new SalesDAO().list()) {
			System.out.println(tt);
		}		
	}
}
