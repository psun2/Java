package sup.menuManagement;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import hong.InitData;
import oracle.sql.BLOB;
import sup.menu.Menu;
import sup.menu.MenuMainView;

public class MenuManagementDBControl {
	Connection con;
	Statement stmt;
	ResultSet rs;
	String sql;
	
	public MenuManagementDBControl() {
		
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@"+InitData.ip+":1521:xe", "hr", "hr");
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public String createMenu_code(String menu_catag) {
		String code="";
		int code1 = 1;
		if(menu_catag.equals("안주")) {
			code="A";
			for (int i = 1; i <= new MenuManagementDBControl().Alist().size()+1; i++) {				
				code1=i;
			}
		}
		if(menu_catag.equals("술")) {
			code="B";
			for (int i = 1; i <= new MenuManagementDBControl().Blist().size()+1; i++) {				
				code1=i;
			}
		}
		DecimalFormat df = new DecimalFormat("000");
		System.out.println();
		
		code += df.format(code1);
		System.out.println(code);
		return code;
	}
	public void create_menu(String menu_name, String menu_catag, int menu_price) {
//		byte[] b = setBlob();
		sql = "insert into menu (menu_code,menu_name, menu_catag, menu_price, menu_img) values('"+
				createMenu_code(menu_catag)+"','"+menu_name+"','"+menu_catag+"',"+menu_price+")";
		try {
			stmt.executeUpdate(sql);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	}
	public void del_menu(String menu_code) {
		
		sql = "delete from menu where menu_code = '"+menu_code+"'";
		
		try {
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
	}
	public void update_menu(Menu menu) {
		InputStream fis = null;
//		byte [] b = setBlob(menu.getImageF());
//		sql = "update menu set menu_name='"
//				+menu.getMenu_name()+"', menu_price="+menu.getMenu_price()+", menu_catag='"
//				+menu.getMenu_catag()+"', menu_img= "+blob+" where menu_code='"+menu.getMenu_code()+"'";
		sql = "update menu set menu_name=?, menu_price= ? , menu_catag=? where menu_code='"+menu.getMenu_code()+"'";
		String sql2 = "update menu set menu_img= EMPTY_BLOB()  where menu_code='"+menu.getMenu_code()+"'";
//		Blob emptyBlob= null;
		try {
			fis = new FileInputStream(menu.getImageF().getPath());
//			stmt.executeUpdate(sql2);
//			rs = stmt.executeQuery("select menu_img from menu where menu_code = '"+menu.getMenu_code()+"'");
//			if(rs.next()) 
//				emptyBlob = rs.getBlob("menu_img");
//			oracle.sql.BLOB bol = (oracle.sql.BLOB)emptyBlob;
//			OutputStream os = bol.getBinaryOutputStream();
//			int size = bol.getBufferSize();
//			byte[] buffer = new byte[size];
//			int length = -1;
//			while((length = fis.read(buffer)) != -1) {
//				os.write(buffer, 0, length);
//			}
//			os.close();
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, menu.getMenu_name());
			pstmt.setInt(2, menu.getMenu_price());
			pstmt.setString(3, menu.getMenu_catag());
			pstmt.setBinaryStream(4, fis , (int)menu.getImageF().length());
			pstmt.executeUpdate();
			
			fis.close();
			pstmt.close();
//			stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close();
		}
	}
	
	public ArrayList<Menu> list(){
		ArrayList<Menu> res = new ArrayList<Menu>();
		sql = "select * from menu";

		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Menu menuDto = new Menu(rs.getString("menu_code"),rs.getString("menu_name"),
										rs.getString("menu_catag"),rs.getInt("menu_price"));
				res.add(menuDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		return res;
	}
	public File getBlob(Blob blob) {
		File imageFile = null;

		try {
			InputStream	in = blob.getBinaryStream();
			FileOutputStream fos = new FileOutputStream("/img/aa.png");
			int nFileSize = (int)blob.length();
			byte[] buf = new byte [nFileSize];
			int i;
			while((i = in.read(buf)) != -1){
				fos.write(buf,0,i);
			}
//			int nReadSize = in.read(buf, 0, nFileSize);
			
			in.close();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imageFile = new File("/img/aa.png");
		return imageFile;
	}
	public byte[] setBlob(File f) {
		byte[] buf = null;
		try {
			System.out.println(f);
			InputStream is = new FileInputStream(f);
			int fileSize = (int)f.length();
			buf = new byte[fileSize];
			is.read(buf);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return buf;
	}
	public ArrayList<Menu> Alist(){
		ArrayList<Menu> res = new ArrayList<Menu>();
		sql = "select * from menu where menu_catag = '안주'";

		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Menu menuDto = new Menu(rs.getString("menu_code"),rs.getString("menu_name"),
										rs.getString("menu_catag"),rs.getInt("menu_price"));
				res.add(menuDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		return res;
	}
	public ArrayList<Menu> Blist(){
		ArrayList<Menu> res = new ArrayList<Menu>();
		sql = "select * from where menu_catag = '술'";

		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Menu menuDto = new Menu(rs.getString("menu_code"),rs.getString("menu_name"),
										rs.getString("menu_catag"),rs.getInt("menu_price"));
				res.add(menuDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}

		return res;
	}
	public void close() {
		if(rs!=null)try {rs.close();} catch (SQLException e) {e.printStackTrace();}
		if(stmt!=null)try {stmt.close();} catch (SQLException e) {e.printStackTrace();}
		if(con!=null)try {con.close();} catch (SQLException e) {e.printStackTrace();}

	}
}
