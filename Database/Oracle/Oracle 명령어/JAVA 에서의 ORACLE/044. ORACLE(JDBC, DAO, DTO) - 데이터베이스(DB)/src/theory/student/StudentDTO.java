package theory.student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StudentDTO {

	String id, name, hobby;

	Integer no;
	// int no; // int 보단 Integer 로 해합니다.
	// why ? null 값이 있어서 ....

	Double height;

	Date regDate, birth;

	SimpleDateFormat sdf; // date의 set과 get을 이용하기 위해 필요

	public StudentDTO() {
		// TODO Auto-generated constructor stub
		this.sdf = new SimpleDateFormat("yyyy-MM-dd");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Date getRegDate() {
		return regDate;
	}

	public String getRegDateStr() { // String 형태로 변환하여 반환 합니다. => why? 데이터 베이스에 들어갈 sql 문법에서 스트링으로 받기 때문 입니다.
		return sdf.format(regDate);
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public void setRegDateStr(String regDate) { // 스트링 형태로 받아 Date로 변환 하여 set해주기 위한 작업

		try {
			this.regDate = sdf.parse(regDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Date getBirth() {
		return birth;
	}

	public String getBirthStr() { // String 형태로 변환하여 반환 합니다. => why? 데이터 베이스에 들어갈 sql 문법에서 스트링으로 받기 때문 입니다.
		return sdf.format(birth);
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public void setBirthStr(String birth) { // 스트링 형태로 받아 Date로 변환 하여 set해주기 위한 작업
		try {
			this.birth = sdf.parse(birth);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "StudentDTO [id=" + id + ", name=" + name + ", hobby=" + hobby + ", no=" + no + ", height=" + height
				+ ", regDate=" + regDate + ", birth=" + birth + "]";
	}

}
