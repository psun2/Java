package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//DAO �����ͺ��̽��� �����ؼ� �۾��� �ϴ� �κ��� ������ Ŭ���� 
public class GameDAO {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs= null;
	String sql =null;
	JoinTest jt = null;
	PreparedStatement ps=null;
	
	
	public GameDAO() {
		
		try {
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"hr", 
					"hr");
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<GameDTO> list(){
		
		ArrayList<GameDTO> res = new ArrayList<GameDTO>();
		sql = "select * from gamemember";
		
		try {
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				GameDTO dto = new GameDTO();
				dto.id = rs.getString("id");
				dto.pw = rs.getString("pw");
				dto.name = rs.getString("name");
				dto.birth= rs.getString("birth");
//				dto.birth= rs.getDate("birth");
				dto.email = rs.getString("email");

				
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
	
	 
	   public int Login(String id, String pw) {
	        
	        int result = 1;
	 
	        try {
	            stmt = con.createStatement();
	 
	            sql = "select * from gamemember where id='" + id + "'";
	            rs = stmt.executeQuery(sql); // �о���°Ŷ� �ٸ��� ����    //����Ÿ���� ResultSet
	 
	            if (rs.next() == false || (id.isEmpty()) == true) { // id�� ����x
	                result = 1;
	            } else {
	                sql = "select * from (select * from gamemember where id='" + id + "')";
	                rs = stmt.executeQuery(sql);
	                while (rs.next() == true) {         // ��������
	                    if (rs.getString(2).equals(pw)) { // pw�� ������ ��
	                    	
	                        result = 0;         // ������ �α��� ����
	                        break;
	                    } else {                // ���̵�°��� pw�� �ٸ����
	                        result = 1;
	                        
	                    break;
	                    }
	                }
	            }
	        } catch (Exception ee) {
	            System.out.println("��������");
	            ee.printStackTrace();
	        }
	        return result;
	    }
	
//	   if (rs.next() == false || (id.isEmpty()) == true) { // id�� ����x
//           result = 1;
//       } else {
//           sql = "select * from (select * from gamemember where id='" + id + "')";
//           rs = stmt.executeQuery(sql);
//           while (rs.next() == true) {      
//               if (rs.getString(1).equals(id)) { 
//                   result = 0;         
//                   break;
//               }                 
//               break;
//               
//           }
//       }
//	   .equals(id) && rs.getString(3).equals(name)&&
//		rs.getString(4).equals(birth) && rs.getString(5).equals(email)
	   public GameDTO find(String id,String name, String birth, String email){
			
			GameDTO dto = new GameDTO();
			sql = "select * from gamemember where id = '"+id+"'";
			
			try {
				
				rs = stmt.executeQuery(sql);
				if(rs.next()) {
					if (rs.getString(1).equals(id) && rs.getString(3).equals(name)&&
							rs.getString(4).equals(birth) && rs.getString(5).equals(email)) { 
						sql = "select * from (select * from gamemember where id='" + id + "')";
						
						dto.id = rs.getString("id");
						dto.pw = rs.getString("pw");
						dto.name = rs.getString("name");
						dto.birth = rs.getString("birth");
						
//						dto.birth= rs.getDate("birth");
						dto.email = rs.getString("email");
						System.out.println("����");
						
//						}else {                
//					sql = "select * from (select * from gamemember where id='" + id + "')";
//					while(rs.next() == true) {
//						if(rs.getString(1) .equals(id)) {
//					dto.id = rs.getString("id");
//					dto.pw = rs.getString("pw");
//					dto.name = rs.getString("name");
//					dto.birth = rs.getString("birth");
//					
////					dto.birth= rs.getDate("birth");
//					dto.email = rs.getString("email");
//					System.out.println("����");
//					
//					}
					
	               }
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			 
			return dto;
		}
	
	
	public GameDTO detail(String id){
		
		GameDTO dto = new GameDTO();
		sql = "select * from gamemember where id = '"+id+"'";
		
		try {
			
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				
				dto.id = rs.getString("id");
				dto.pw = rs.getString("pw");
				dto.name = rs.getString("name");
				dto.birth = rs.getString("birth");
				
//				dto.birth= rs.getDate("birth");
				dto.email = rs.getString("email");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		 
		return dto;
	}
	
	
	
	public int insert(GameDTO dto){
		
		int res = 0;
		sql = "insert into gamemember " + 
				"(id, pw, name, birth, email) " + 
				"values " + 
				"('"+dto.id+"','"+dto.pw+"','"+dto.name+"','"+dto.birth+"','"+dto.email+"')";
//				","+dto.birth+",'"+dto.email+"','"+dto.getBirthStr()+"')";
		
		System.out.println(sql);
		try {
			
			res = stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}

		return res;
	}
	
	
//	public int modify(GameDTO dto){
//		
//		int res = 0;
//		sql = "select from gamemember where id = '"+
//				dto.id+"'";
//				
////				dto.id+"', pw = "+
////				dto.pw+", name = "+
////				dto.name+" , birth = '"+
////				dto.birth+"', email = '"+
////				dto.email+"'";
//		if(sql.equals())
//		System.out.println(sql);
//		try {
//			
//			res = stmt.executeUpdate(sql);
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			close();
//		}
//
//		return res;
//	}
//	
	
	public int delete(String id){
		
		int res = 0;
		sql = "delete from gamemember where id = '"+id+"'";
		
		System.out.println(sql);
		try {
			
			res = stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}

		return res;
	}
	 
	
//		public boolean idC(String id){ // �޾ƿ�  ���̵��� �ִ��� ã���ش�
//			
//			GameDTO dto = null; // ã�� ������ ���� �޾ƿ��� ���� ���
//			boolean res = true;
//			// java���� ã������ ���̵��� �˻����ִ� ������
//			sql = "select id from gamemember where id = '"+id+"'";
//			// �� MemberMain���� Ȯ���ϱ� ���� �޾ƿ� ���̵� ���� DB���� ã�ƿ´�
//			
//			try {
//				
//				rs = stmt.executeQuery(sql); // �������� ã�ƺ���
//				
//				if(rs.next()) { // �������� ã�� �����͸�  DTO���� get�ؿ´�
//					
//					dto = new GameDTO();
//					dto.id = rs.getString("id");
//					res = false;
//					
//				} 
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}finally { // �������� ���������� DB��� �� DB�� �ݾ��ش�
//				close(); // DB�� �ݱ� �� Ȯ���� ���� �޼ҵ�
//			}
//			 
//			return res;
//			// ã�� ������ ���� ��ȯ���ش�, �����Ͱ� ������ null�� ��ȯ
//		} // == public MemDTO detail �� =======
public int idd(String id) {
	       
	        int result = 1;
	 
	        try {
	            stmt = con.createStatement();
	 
	            sql = "select * from gamemember where id='" + id + "'";
	            rs = stmt.executeQuery(sql); // �о���°Ŷ� �ٸ��� ����    //����Ÿ���� ResultSet
	 
	            
	            if (rs.next() == false || (id.isEmpty()) == true) { // id�� ����x
	                result = 1;
	            } else {
	                sql = "select * from (select * from gamemember where id='" + id + "')";
	                rs = stmt.executeQuery(sql);
	                while (rs.next() == true) {      
	                    if (rs.getString(1).equals(id)) { 
	                        result = 0;         
	                        break;
	                    }                 
	                    break;
	                    
	                }
	            }
	        } catch (Exception ee) {
	            System.out.println("��������");
	            ee.printStackTrace();
	        }
	        return result;
	    }
	
	
	
	public void close() {
		if(rs!=null) try { rs.close();} catch (SQLException e) { }
		if(stmt!=null) try { stmt.close();} catch (SQLException e) { }
		if(con!=null) try { con.close();} catch (SQLException e) { }
	}

	
}
