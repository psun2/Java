package an.adminLogin;

import java.sql.*;
import java.util.*;

public class AdminLogDAO {

	Connection con;
	Statement stmt;
	ResultSet rs;
	String sql;

	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "hr";
	String pw = "hr";

	public AdminLogDAO() {
		try {
			con = DriverManager.getConnection(url, id, pw);
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<AdminLogDTO> list() {
		ArrayList<AdminLogDTO> res = new ArrayList<AdminLogDTO>();// *�����͸� ����ִ� ����Ʈ

		sql = "select * from ADMIN";

		try {
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				AdminLogDTO dto = new AdminLogDTO();

				dto.AD_PW = rs.getString("AD_PW");

				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		} // = try-catch ������ ���� �� =
		return res;
	}
	
public AdminLogDTO detail(String AD_PW){
		
	AdminLogDTO dto = null; 
		
		sql = "select * from ADMIN where AD_NAME = '������'";

		
		try {
			
			rs = stmt.executeQuery(sql); 
			
			if(rs.next()) {
				
				dto = new AdminLogDTO();
				dto.AD_PW = rs.getString("AD_PW");
				
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally { 
			close();
		}
		 
		return dto;
	}

	public void close() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			} // resultset
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
			} // statement
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
			} // connection
	}

	public int modify(AdminLogDTO dto) {
		int res = 0;

		sql = "update ADMIN set AD_PW = '" + dto.AD_PW + "' where AD_NAME = '������'";
	
		

		try {
			res = stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return res;
	}

}
