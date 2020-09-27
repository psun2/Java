package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ResExamDAO {
	Connection con;
	Statement stmt;
	ResultSet rs;
	String sql;
	int pageCnt = 5; // 페이지당 보일 수 있는 수

	public ResExamDAO() {

		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "test_1", "123456");
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<ResExamDTO> list(int page) {

		ArrayList<ResExamDTO> res = new ArrayList<ResExamDTO>();

		int start = (page - 1) * pageCnt + 1;
		int end = start + pageCnt - 1;

		sql = "select * from res_exam where " + start + " <= rnum and rnum <= " + end + "";
		// 한페이지의 게시글이 10개 라는 가정....

		try {

			rs = stmt.executeQuery(sql);
			while (rs.next()) {

				ResExamDTO dto = new ResExamDTO();
				dto.rnum = rs.getInt("rnum");
				dto.id = rs.getInt("id");
				dto.pid = rs.getString("pid");
				dto.title = rs.getString("title");
				dto.regdate = rs.getDate("regdate");
				dto.kor = rs.getInt("kor");
				dto.eng = rs.getInt("eng");
				dto.tot = rs.getInt("tot");
				dto.avg = rs.getInt("avg");

				res.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return res;
	}

	public int insert(ResExamDTO dto) { // 다른 패키지에서 부를 것이기 때문에 public

		int res = 0;

		// 쿼리문에 세미콜론이 있어 insert charter 오류가 계속 났음...
		sql = "insert into exam (id, title, pid, regdate, kor, eng) values ((select max(id) +1 from exam), '"
				+ dto.title + "', '" + dto.pid + "', '" + dto.getRegdateStr() + "', " + dto.kor + ", " + dto.eng + ")";

		System.out.println();
		System.out.println(sql); // 우리가 원하는 문법이 아님니다 쓰지 못합니다. => 날짜 부분
		// insert into member(id, name, no, height, regdate, birth,
		// hobby)values('ggg','장정호',30,180.17,'Thu May 04 00:00:00 KST 2017','Fri Dec 18
		// 00:00:00 KST 1998','여자여자여자')
		System.out.println();

		try {
			res = stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return res;
	}

	public int insert2(ResExamDTO dto) { // 다른 패키지에서 부를 것이기 때문에 public

		int res = 0;

		// 쿼리문에 세미콜론이 있어 insert charter 오류가 계속 났음...
		sql = "insert into exam (id, title, pid, regdate, kor, eng) values (exam_id.nextval, '" + dto.title + "', '"
				+ dto.pid + "', '" + dto.getRegdateStr() + "', " + dto.kor + ", " + dto.eng + ")";

		System.out.println();
		System.out.println(sql); // 우리가 원하는 문법이 아님니다 쓰지 못합니다. => 날짜 부분
		// insert into member(id, name, no, height, regdate, birth,
		// hobby)values('ggg','장정호',30,180.17,'Thu May 04 00:00:00 KST 2017','Fri Dec 18
		// 00:00:00 KST 1998','여자여자여자')
		System.out.println();

		try {
			res = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return res;
	}

	public void insertList(ArrayList<ResExamDTO> list) {

		try {

			con.setAutoCommit(false);

			for (ResExamDTO dto : list) {
				sql = "insert into exam (id, title, pid, regdate, kor, eng) values (exam_id.nextval, '" + dto.title
						+ "', '" + dto.pid + "', '" + dto.getRegdateStr() + "', " + dto.kor + ", " + dto.eng + ")";
				stmt.executeUpdate(sql);

			}

			con.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			close();
		}

	}

	public void close() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
			}
		if (stmt != null)
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
			}
	}
}