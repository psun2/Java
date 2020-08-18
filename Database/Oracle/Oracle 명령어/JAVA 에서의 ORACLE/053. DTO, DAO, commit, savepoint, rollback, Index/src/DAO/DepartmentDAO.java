package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartmentDAO {

	Connection con;
	Statement stmt;
	ResultSet rs;
	String url, id, pw, sql;

	public DepartmentDAO() {
		this.url = "jdbc:oracle:thin:@localhost:1521:xe";
		this.id = "examuser";
		this.pw = "123456";
		System.out.println("url : " + url + ", 아이디 : " + id + ", 비번 : " + pw);
		// TODO Auto-generated constructor stub
		try {
			this.con = DriverManager.getConnection(url, id, pw);
			this.stmt = con.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			close();
		}
	}

	public ArrayList<DepartmentDTO> list() {

		ArrayList<DepartmentDTO> result = new ArrayList<DepartmentDTO>();

		this.sql = "select * from department";

		try {
			System.out.println(sql);
			this.rs = stmt.executeQuery(sql);

			while (rs.next()) {

				DepartmentDTO dto = new DepartmentDTO(rs.getString("id"), rs.getString("name"));
				result.add(dto);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			close();
		}

		return result;

	}

	public int insert(DepartmentDTO dto) {

		int result = 0;

		this.sql = "insert into department (id, name) values (" + dto.id + ", " + dto.name + ")";

		try {
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close();
		}

		return result;

	}

	// 쇼핑몰의 장바구니 같은 경우 어떤 물품은 주문되고, 어떤물품은 주문 안되고를 막을 수 있습니다.
	public void listInsert(ArrayList<DepartmentDTO> list) {

		try {

			con.setAutoCommit(false); // 다등록이 되어 들어갈때 까지 자동적으로 하는 commit 을 막아 줍니다.

			for (DepartmentDTO dto : list) {
				this.sql = "insert into department (id, name) values ('" + dto.id + "', '" + dto.name + "')";
				stmt.executeUpdate(sql);
			}

			con.commit(); // 정상적으로 등록되었을시 저장

		} catch (Exception e) {
			// TODO: handle exception

			try {
				con.rollback(); // 에러 발생시 롤백을 하여, 저장되지 않도록 합니다
			} catch (Exception e2) {
				// TODO: handle exception
			}

		} finally {
			close();
		}

	}

	// 위 listInsert 와 같은 기능을 하는 메소드 이나 insetAll 사용시
	// 모든것이 들어가지 않으면 자동 에러이기 때문에 위의 귀찬은 방법을 한번에 해결 할 수 있습니다.
	public void insertAll(ArrayList<DepartmentDTO> list) {

		this.sql = "insert all ";

		try {

			for (DepartmentDTO dto : list) {

				sql += "into department (id, name) values ('" + dto.id + "', '" + dto.name + "') ";

			}

			sql += "select + from dual";

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			close();
		}

	}

	void close() {

		try {
			if (this.rs != null && !rs.isClosed())
				rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (this.stmt != null && !stmt.isClosed())
				stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			if (this.con != null && !con.isClosed())
				con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
