package theory.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentDAO {

	Connection con;
	Statement stmt;
	ResultSet rs;
	String url, user, password, sql;

	public StudentDAO() {
		// TODO Auto-generated constructor stub
		this.url = "jdbc:oracle:thin:@localhost:1521:xe";
		this.user = "hr";
		this.password = user;

		try {
			this.con = DriverManager.getConnection(url, user, password);
			this.stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<StudentDTO> list() { // 전체 데이터를 받아 옵니다.

		ArrayList<StudentDTO> result = new ArrayList<StudentDTO>();

		String sql = "select * from student";

		try {
			this.rs = stmt.executeQuery(sql); // 쿼리 명령어를 통해 디비 데이터를 가져 옵니다.

			// ResultSet 은 iterator 처럼 사용 가능 하여 반복문을 사용 할 수 있습니다.
			while (rs.next()) {

				StudentDTO dto = new StudentDTO();
				dto.id = rs.getString("id");
				dto.name = rs.getString("name");
				dto.no = rs.getInt("no");
				dto.height = rs.getDouble("height");
				dto.regDate = rs.getTimestamp("regdate");
				dto.birth = rs.getDate("birth");
				dto.hobby = rs.getString("hobby");

				result.add(dto);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(); // 시작 했으면 항상 닫아 줍니다.
		}

		return result;

	}

	public int insert(StudentDTO dto) { // C : create => 쓰기 => 즉 데이터 베이스에 저장하기 위해 필요한 메소드

		int result = 0; // 추가된 갯수 반환

//		String sql = "insert into student (id, name, no, height, regdate, birth, hobby) values ('" + dto.getId()
//				+ "', '" + dto.getName() + "', " + dto.getNo() + ", " + dto.getHeight() + ", '" + dto.getRegDate()
//				+ "', '" + dto.getBirth() + "', '" + dto.getHobby() + "');"; // 맨뒤 sql문법의 ; 또한 에러의 원인이 됩니다.

		// System.out.println(sql);

		// insert into student (id, name, no, height, regdate, birth, hobby) values
		// ('tjddjs90', '박성언', null, 175.5, 'Tue May 12 00:00:00 KST 2020', 'Mon Jul 13
		// 00:00:00 KST 2020', '몰라요');

		// => DTO 을 만들어서 삽입할때 set 을 이용하여, String 형태를 Date로 바꾸어 주었고,
		// sql 문법으로 맴버 변수인 regdate 와 birth 를 삽입할때 다시 string 형태로 변환 해주어야 하기때문에 DTO으 get
		// 메소드를 커스터마이징 합니다.

		String sql = "insert into student (id, name, no, height, regdate, birth, hobby) values ('" + dto.getId()
				+ "', '" + dto.getName() + "', " + dto.getNo() + ", " + dto.getHeight() + ", '" + dto.getRegDateStr()
				+ "', '" + dto.getBirthStr() + "', '" + dto.getHobby() + "')";

		System.out.println(sql);

		try {
			result = stmt.executeUpdate(sql); // update 사용 으로 추가 합니다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(); // 시작 했으면 항상 닫아 줍니다.
		}

		return result;

	}

	public int delete(String id) { // R : remove => 삭제 => 즉 데이터 베이스에서 데이터를 삭제하기 위해 필요한 메소드

		int result = 0; // 삭제된 갯수 반환

		String sql = "delete from student where id = '" + id + "' ";

		System.out.println(sql);
//		delete from student where id = 'eee' 
//		1

		try {
			result = stmt.executeUpdate(sql); // update 사용 으로 추가 합니다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(); // 시작 했으면 항상 닫아 줍니다.
		}

		return result;

	}

	public int modify(StudentDTO dto) { // 수정 => 데이터 베이스에 있는 자료를 수정하기 위한 메소드 insert는 추가라서 사용하게되면 전 데이터는 그대로 남아있기때문에
										// modify 사용
		// 아이디를 통한 업데이트이므로, 수정할때 아이디 정보는 수정이 불가능 합니다.

		int result = 0; // 수정된 갯수 반환

		String sql = "update student set name = '" + dto.getName() + "', no = " + dto.getNo() + ", height = "
				+ dto.getHeight() + ", regdate = '" + dto.getRegDateStr() + "', birth = '" + dto.getBirthStr()
				+ "', hobby = '" + dto.getHobby() + "' where id = '" + dto.getId() + "'";

		System.out.println(sql);
//		update student set name = '박성언', no = null, height = 175.5, regdate = '2020-05-12', birth = '2020-07-13', hobby = '몰라요' where id = 'tjddjs90'
//		7

		try {
			result = stmt.executeUpdate(sql); // update 사용 으로 추가 합니다.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(); // 시작 했으면 항상 닫아 줍니다.
		}

		return result;

	}

	public StudentDTO detail(String id) { // 검색의 기능 => id를 통한 데이터 검색

		StudentDTO dto = null; // 찾아서 객체화 하여 반환

		String sql = "select * from student where id = '" + id + "'";

		System.out.println(sql);
		// select * from student where id = 'tjddjs90'

		try {

			rs = stmt.executeQuery(sql);

			while (rs.next()) {

				dto = new StudentDTO();
				dto.id = rs.getString("id");
				dto.no = rs.getInt("no");
				dto.name = rs.getString("name");
				dto.height = rs.getDouble("height");
				dto.regDate = rs.getTimestamp("regdate");
				dto.birth = rs.getDate("birth");
				dto.hobby = rs.getString("hobby");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(); // 시작 했으면 항상 닫아 줍니다.
		}

		return dto;

	}

	void close() {

		try {
			if (this.con != null && !this.con.isClosed())
				this.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (this.stmt != null && !this.stmt.isClosed())
				this.stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (this.rs != null && !this.rs.isClosed())
				this.rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
