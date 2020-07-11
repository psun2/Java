package theory.bus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BusDAO { // 데이타(DB) 접근을 위한 객체

	Connection con;
	Statement stmt;
	ResultSet rs;
	String sql;

	public BusDAO() {
		// TODO Auto-generated constructor stub

//		this.con = DriverManager.getConnection(url, user, password);

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";

		try {

			this.con = DriverManager.getConnection(url, user, password);
			this.stmt = con.createStatement();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	ArrayList<BusDTO> list() {

		ArrayList<BusDTO> result = new ArrayList<BusDTO>();

		this.sql = "SELECT * FROM BUS";

		try {
			this.rs = stmt.executeQuery(sql);

			while (rs.next()) {

				BusDTO dto = new BusDTO(rs.getString("no"), rs.getString("type"), rs.getString("district"),
						rs.getInt("price"));

				result.add(dto);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(); // 열어 줬으면 닫아주어야 합니다.
			// 닫아주는 것은 열어 주는 것의 역순으로 합니다.
		}

		return result;

	}

	void close() {

		try {
			if (this.rs != null && !this.rs.isClosed())
				this.rs.close();
			if (this.stmt != null && !this.stmt.isClosed())
				this.stmt.close();
			if (this.con != null && !this.con.isClosed())
				this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
