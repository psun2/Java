package jdbc_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbc_p.RankDTO;
import ddong.InitData;

public class RankDAO {

	Connection con; // DB�� �������ִ� ����
	Statement stmt; // ������ ���ε�, SQL�� �ؼ����ִ� ��ü
	ResultSet rs; //DB���� select�� ����� ��´�

	String sql; // ���������� �� String

	// 	DB����� �ʿ��� url�� ID, PW�� ���� ���ϰ� ���� ������ ���־���
	//	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String url = "jdbc:oracle:thin:@"+InitData.ip+":1521:xe";
	String id = InitData.id;
	String pw = InitData.pw;


	//----------------------------------------------
	// == public RankDAO() ���� =======() ���� =======
	public RankDAO() {

		try {

			con = DriverManager.getConnection(url, id, pw); // DB����

			stmt = con.createStatement(); // ���� ���� �غ�

		} catch (SQLException e) {
			e.printStackTrace();
		} // = try-catch DB�����غ� �� =
	} // == public RankDAO() �� =======
	//--------------------------------------------------------


	//--------------------------------------------------------
	// == public void close() ���� =======
	public void close() { // ������ DB�� �����鼭 �ݱ� �� Ȯ�λ��� üũ �޼ҵ�

		// ���ȴ��� üũ�ϸ鼭 �ݾ�����Ѵ�. ���̺������� ���� ������ �Ǵ� 
		// resultset���� �ݴ�� Ȯ�� > statement > connection ������

		if(rs!=null) try { rs.close(); } catch (SQLException e) { }
		if(stmt!=null) try { stmt.close(); } catch (SQLException e) { }
		if(con!=null) try { con.close(); } catch (SQLException e) { }
		// ���� �������� �� �� ���� Ȯ���ϸ鼭 �ݾ��ָ鼭 ������
	} // == public void close() �� =======
	//--------------------------------------------------------


	//--------------------------------------------------------
	// == public ArrayList<RankDTO> list() : �����͸� ����� =======
	public ArrayList<RankDTO> list(){

		ArrayList<RankDTO> res = new ArrayList<RankDTO>();
		// *�����͸� ����ִ� ����Ʈ
		sql = "select * from userrank";
		// ���� ���๮.DB���̺� �� userrank�� ���

		try {

			rs = stmt.executeQuery(sql);
			// ���� �������� �����´�. select ���� �϶��� ���

			while(rs.next()) {// �����Ͱ� ������ �����Ͱ� ���������� �����´�

				RankDTO dto = new RankDTO();
				// �����͸� Object�� ��ȯ�ؼ� �������� DTO����

				dto.id = rs.getString("id"); // DB���� id�� ������ dto���� ��ȯ�����ش�
				dto.score = rs.getInt("score"); // DB���� score�� ������ dto���� ��ȯ�����ش�
				dto.rank = rs.getInt("rank"); // DB���� rank�� ������ dto���� ��ȯ�����ش�

				res.add(dto);
				// ������ ����� �� *�����͸� ����ִ� arraylist�� �����͸� �־��ش�.
			} // while�� - �����Ͱ������� ��

		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // DB�� ��� �� DB�� ���� ���� finally�� ����ؼ� �ݴ´�
			close(); // �����͸� ��� �� ��������, �������� ������ �ٸ�����ڰ� ��� �� �� �ִ�
		}


		return res;
		// �����͸� ����� arraylist�� ��ȯ ���ش�
	} // == public ArrayList<MemDTO> list() �� =======
	//--------------------------------------------------------


	//--------------------------------------------------------
	// public RankDTO detail : ���ϴ� ������ ã���� ����Ѵ�
	public RankDTO detail(String id) {
		
		RankDTO dto = new RankDTO();
		// ã�� ������ ���� �޾ƿ��� ���� ���, �´� ������ ���� ������ null��ȯ
		
		// java���� ã������ ���̵��� �˻����ִ� ������
	      sql = "select * from userrank where id = '" + id + "'";
		// �� RankMain_GUI���� Ȯ���ϱ� ���� �޾ƿ� ���̵� ���� DB���� ã�ƿ´�
		try {
			
			rs = stmt.executeQuery(sql); // �������� ã�ƺ���
			
			if(rs.next()) { // �������� ã�� �����͸�  DTO���� get�ؿ´�
				
				dto.id = rs.getString("id");
				dto.score = rs.getInt("score");
				dto.rank = rs.getInt("rank");
			} // if�� ��
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}
		
		
		return dto;
		// ã�� ������ ���� ��ȯ���ش�
	} // == public MemDTO detail �� =======
	//--------------------------------------------------------
	
	
	//--------------------------------------------------------

	public int insertId(String id) { //���� ������� �μ�Ʈ

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		sql = "insert into userrank "+ "(id) " + "values " +
				"('" + id + "')";
		// �� RankMain_GUI���� �߰����� ������ �޾ƿͼ� DB�־��� �������� �������

		try {

			res = stmt.executeUpdate(sql); // ���� ������Ʈ

		} catch (SQLException e) {
			e.printStackTrace();
		}finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}


		return res;
	}

	
	//--------------------------------------------------------
	public int modifyRank(RankDTO dto) { // ���� ����

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ������ ������ �������ִ� ������
		sql = "update userrank set rank = '" + dto.rank +
				"' where id = '" + dto.id +"'";
		// �� RankMain_GUI������������ ������ �޾ƿͼ� DB�־��� �������� �������

		try {

			res = stmt.executeUpdate(sql); // ������ ���� ������Ʈ

		} catch (SQLException e) {
			e.printStackTrace();
		}finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}


		return res;
		// ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
	} // == public int modify �� =======
	//--------------------------------------------------------


	//--------------------------------------------------------
	public int modifyScore(RankDTO dto) { // ���� ����

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		// java���� ������ ������ �������ִ� ������
		sql = "update userrank set score = '" +
				dto.score + "' where  id = '" + dto.id + "'"; 
		// �� RankMain_GUI������������ ������ �޾ƿͼ� DB�־��� �������� �������

		try {

			res = stmt.executeUpdate(sql); // ������ ���� ������Ʈ

		} catch (SQLException e) {
			e.printStackTrace();
		}finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}


		return res;
		// ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
	} // == public int modify �� =======
	//--------------------------------------------------------


	//--------------------------------------------------------
	// == public int delete : java���� ������ ������ DB���� ���� =======
	public int delete(String id) { // ȸ�� Ż��� ������ ���̵𰪸� �޾ƿͼ� Ȯ�� �� �����Ѵ�

		int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�

		sql = "delete from userrank where id = '" + id + "'";
		// �� RankMain_GUI���� �����ϱ� ���� �޾ƿ� ���̵� ���� DB���� ã�Ƽ� �����Ѵ�

		try {

			res = stmt.executeUpdate(sql); // ������ ������ ������Ʈ

		} catch (SQLException e) {
			e.printStackTrace();
		}finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
			close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
		}


		return res;
		// ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
	} // == public int delete �� =======


}
