package jdbc_p;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ddong.InitData;

public class LobbyDAO {
   
   Connection con; // DB�� �������ִ� ����
   Statement stmt; // ������ ���ε�, SQL�� �ؼ����ִ� ��ü
   ResultSet rs; //DB���� select�� ����� ��´�
   
   String sql; // ���������� �� String
   
//    DB����� �ʿ��� url�� ID, PW�� ���� ���ϰ� ���� ������ ���־���
//   String url = "jdbc:oracle:thin:@localhost:1521:xe";
   String url = "jdbc:oracle:thin:@"+InitData.ip+":1521:xe";
   String id = InitData.id;
   String pw = InitData.pw;
   
   
   //----------------------------------------------
   // == public LobbyDAO() ���� =======() ���� =======
   public LobbyDAO() {
      
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
   public ArrayList<LobbyDTO> list(){
      
      ArrayList<LobbyDTO> res = new ArrayList<LobbyDTO>();
      // *�����͸� ����ִ� ����Ʈ
      sql = "select * from lobby";
      // ���� ���๮.DB���̺� �� lobby�� ���
      
      try {
         
         rs = stmt.executeQuery(sql);
         // ���� �������� �����´�. select ���� �϶��� ���
         
         while(rs.next()) {// �����Ͱ� ������ �����Ͱ� ���������� �����´�
            
            LobbyDTO dto = new LobbyDTO();
            // �����͸� Object�� ��ȯ�ؼ� �������� DTO����
            
            dto.id = rs.getString("id"); // DB���� id�� ������ dto���� ��ȯ�����ش�
            
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
   
   public int insert(String id) {
      
      int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�
      
      sql = "insert into lobby "+ "(id) " + "values " +
      "('" + id + "')";
      // �� v���� �߰����� ������ �޾ƿͼ� DB�־��� �������� �������
      
      try {
         
         res = stmt.executeUpdate(sql); // ���� ������Ʈ
      
      } catch (SQLException e) {
         e.printStackTrace();
      }finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
         close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
      }

      
      return res;
      // �߰��� �������� ������Ʈ�� ���������� �Ǹ� ������� 1�� ��ȯ���ش�
   } // == public int insert �� =======
   //--------------------------------------------------------
   
   
   //--------------------------------------------------------
   // == public int delete : java���� ������ ������ DB���� ���� =======
   public int delete(String id) { // ������ ���̵𰪸� �޾ƿͼ� Ȯ�� �� �����Ѵ�
      
      int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�
      
      sql = "delete from lobby where id = '" + id + "'";
      // �� v���� �����ϱ� ���� �޾ƿ� ���̵� ���� DB���� ã�Ƽ� �����Ѵ�
      
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
   
   
   public int deleteAll() { // ������ ���̵𰪸� �޾ƿͼ� Ȯ�� �� �����Ѵ�
      
      int res = 0; // ������ ��� ���� ���ڷ� �����Ƿ� int�� ����� �޾��ش�
      
      sql = "delete from lobby ";
      // �� v���� �����ϱ� ���� �޾ƿ� ���̵� ���� DB���� ã�Ƽ� �����Ѵ�
      
      try {
         
         res = stmt.executeUpdate(sql); // ������ ������ ������Ʈ
      
      } catch (SQLException e) {
         e.printStackTrace();
      }finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
         close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
      }
      
      
      return res;
      // ������ �������� ������Ʈ�� ���������� �Ǹ� ������� 0�� ��ȯ���ش�
   }
   
   
    public LobbyDTO detail(String id){
        LobbyDTO dto = new LobbyDTO();
         sql = "select * from lobby where id = '"+id+"'";
         try {
            
            rs = stmt.executeQuery(sql);
            
            if(rs.next()) {
               dto.id = rs.getString("id");
            }
         } catch (SQLException e) {
            e.printStackTrace();
         }finally {
            close();
         }
          
         return dto;
      }
   
   

}