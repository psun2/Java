package jdbc_p;

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
	String url, id, pw, sql;

	public ResExamDAO() {
		// TODO Auto-generated constructor stub
		this.url = "jdbc:oracle:thin:@localhost:1521:xe";
		this.id = "test_1";
		this.pw = "123456";

		try {
			this.con = DriverManager.getConnection(url, id, pw);
			this.stmt = con.createStatement();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public ArrayList<ResExamDTO> list() {

		ArrayList<ResExamDTO> result = new ArrayList<ResExamDTO>();

		this.sql = "select * from res_exam";
		System.out.println(sql);

		try {

			this.rs = stmt.executeQuery(sql);

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

				result.add(dto);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return result;

	}

	public ArrayList<ResExamDTO> page(int page) {
		// 한페이지당 5개씩 출력하고 싶다.
		int cnt = 5;
		int start = (page - 1) * cnt + 1;
		int end = start + cnt - 1;

		ArrayList<ResExamDTO> result = new ArrayList<ResExamDTO>();

		this.sql = "select * from res_exam where rnum >= " + start + " and rnum <= " + end + "";
		System.out.println(sql);

		try {

			this.rs = stmt.executeQuery(sql);

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

				result.add(dto);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return result;

	}

	// id 값으로 정보 조회
	public ResExamDTO detail(Integer id) {

		ResExamDTO dto = null;

		this.sql = "select * from res_exam where id = " + id + "";
		System.out.println(sql);

		try {
			this.rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dto = new ResExamDTO();
				dto.rnum = rs.getInt("rnum");
				dto.id = rs.getInt("id");
				dto.pid = rs.getString("pid");
				dto.title = rs.getString("title");
				dto.regdate = rs.getDate("regdate");
				dto.kor = rs.getInt("kor");
				dto.eng = rs.getInt("eng");
				dto.tot = rs.getInt("tot");
				dto.avg = rs.getInt("avg");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return dto;

	}

	public int insert(ResExamDTO dto) {

		int res = 0;

		this.sql = "insert into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, '" + dto.pid + "', '"
				+ dto.title + "', '" + dto.getRegdateStr() + "', " + dto.kor + ", " + dto.eng + ")";
		System.out.println(sql);

		try {

			res = stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return res;
	}

	public void insertList(ArrayList<ResExamDTO> list) {

		try {

			// 자동 커밋을 방지하여, 모두다 들어 갈 수 있게 해줌 => sequence 와 max 함수는 insert all 에 약점을 보이기
			// 때문...
			con.setAutoCommit(false);

			for (ResExamDTO dto : list) {
				this.sql = "insert into exam (id, pid, title, regdate, kor, eng) values (exam_id.nextval, '" + dto.pid
						+ "', '" + dto.title + "', '" + dto.getRegdateStr() + "', " + dto.kor + ", " + dto.eng + ")";
				System.out.println(sql);
				stmt.executeUpdate(sql);
			}

			// 정상적으로 다 되었을때만 commit
			con.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// 에러시 롤백
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			close();
		} finally {
			close();
		}

	}

	public int delete(Integer id) {

		int res = 0;

		this.sql = "delete exam where id = " + id + "";
		// this.sql = "delete from exam where id = " + id + "";
		System.out.println(sql);

		try {

			res = stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return res;

	}

	public int modify(ResExamDTO dto) {

		int res = 0;

		this.sql = "update exam set id = " + dto.id + ", pid = '" + dto.pid + "', title = '" + dto.title
				+ "', regdate = '" + dto.getRegdateStr() + "', kor = " + dto.kor + ", eng = " + dto.eng
				+ " where id = " + dto.id + "";
		System.out.println(sql);

		try {

			res = stmt.executeUpdate(sql);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			close();
		} finally {
			close();
		}

		return res;

	}

	void close() {

		try {
			if (rs != null && !rs.isClosed())
				rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			if (stmt != null && !stmt.isClosed())
				stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			if (con != null && !con.isClosed())
				con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
