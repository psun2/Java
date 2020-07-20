package login;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//
//dto = new GameDTO();
//dto.uname = rs.getString("uname");
//dto.pw = rs.getInt("pw");
//dto.name = rs.getString("name");
//dto.birth= rs.getDouble("birth");
//dto.email = rs.getTimestamp("email");


public class GameDTO {

	JoinTest jt;
	String id, pw, name, email,birth;

	
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
	return birth;
}

	public void setBirth(String birth) {
		this.birth = birth;
	}

//	public Date getBirth() {
//		return birth;
//	}
//	public String getBirthStr() {
//		return sdf.format(birth);
//	}
//
//	public void setBirth(Date birth) {
//		this.birth = birth;
//	}
//	public void setBirthStr(String birth) {
//		try {
//			this.birth = sdf.parse(birth);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	

	@Override
	public String toString() {
//		return "GameDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", birth ="+birth+ ", email=" + email + "]";
		return "name ���� [id=" + id + ", pw=" + pw+"]";
	}
	
	
	
}
