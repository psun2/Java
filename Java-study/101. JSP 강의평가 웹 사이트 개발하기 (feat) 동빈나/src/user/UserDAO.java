package user;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utill.DatabaseUtill;

public class UserDAO {

	public int join(String userID, String userPassword) {
		
		System.out.println(userID + ", " + userPassword);

		String SQL = "insert into user values (?, ?)";
		System.out.println(SQL);

		try {
			Connection con = DatabaseUtill.getConnection();
			PreparedStatement psmt = con.prepareStatement(SQL);
			psmt.setString(1, userID);
			psmt.setString(2, userPassword);
			return psmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return -1;
	}

}
