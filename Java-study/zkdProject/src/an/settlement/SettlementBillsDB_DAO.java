package an.settlement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.text.DefaultEditorKit.InsertBreakAction;

import hong.InitData;

public class SettlementBillsDB_DAO {

	Connection con;
	Statement stmt;
	ResultSet rs;

	String sql;
	String url = "jdbc:oracle:thin:@"+InitData.ip+":1521:xe";
	String id = "hr";
	String pw = "hr";
	
	
	public SettlementBillsDB_DAO() {
	
		
		
		
		try {
			con = DriverManager.getConnection(url, id, pw);
			
			stmt = con.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<SettlementBillsDB_DTO> list (){
		
		ArrayList<SettlementBillsDB_DTO> res = new ArrayList<SettlementBillsDB_DTO>();
		sql = "select * from bills";
		
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				SettlementBillsDB_DTO dto = new SettlementBillsDB_DTO();
				dto.ordermenu = rs.getString("ordermenu");
				dto.count = rs.getInt("count");
				dto.price = rs.getInt("price");
				
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
	
	
	
	public ArrayList<SettlementBillsDB_DTO> billsOne(SettlementBillsDB_DTO dto) {
		
		
		ArrayList<SettlementBillsDB_DTO> res = new ArrayList<SettlementBillsDB_DTO>();
		sql = "select bills_tablenum,bills_orderedmenu, sum(bills_count) as cnt, "
				+ "sum(bills_price) as pri from billslist " + 
				"where bills_index = " + dto.index + 
				"group by (bills_tablenum,bills_orderedmenu)";
		
		
		System.out.println(sql);
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				SettlementBillsDB_DTO  ddd = new SettlementBillsDB_DTO();
				ddd.setCount(rs.getInt("cnt"));
				ddd.setPrice(rs.getInt("pri"));
				ddd.setOrdermenu(rs.getString("bills_orderedmenu"));
				ddd.setTablenum(rs.getString("bills_tablenum"));
				ddd.index = dto.index;
				res.add(ddd);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		
	SettlementBillsDB_DTO dto = new SettlementBillsDB_DTO();

		
		dto.index = 2;
		
		int tot = 0;
		
		for (SettlementBillsDB_DTO bills : new SettlementBillsDB_DAO().billsOne(dto)) {
			tot += bills.price;
			System.out.println(bills);
			
		}
		
		System.out.println(tot);
		
		
		
		
	}


}
