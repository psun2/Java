package jdbc_p;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResExamDTO {

	Integer rnum, id, kor, eng, tot, avg;
	String pid, title;
	Date regdate;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	// 값을 조회해서 출력을 위한 생성자
	public ResExamDTO() {
		// TODO Auto-generated constructor stub
	}

	// modify 를 위한 생성자
	public ResExamDTO(Integer id, String pid, String title, Date regdate, Integer kor, Integer eng) {
		super();
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.regdate = regdate;
		this.kor = kor;
		this.eng = eng;
	}

	// insert 를 위한 생성자
	public ResExamDTO(String pid, String title, Date regdate, Integer kor, Integer eng) {
		super();
		this.pid = pid;
		this.title = title;
		this.regdate = regdate;
		this.kor = kor;
		this.eng = eng;
	}

	public Date getRegdate() {
		return regdate;
	}

	public String getRegdateStr() {
		return sdf.format(regdate);
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public void setRegdateStr(String regdate) {
		try {
			this.regdate = sdf.parse(regdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "ResExamDTO [rnum=" + rnum + ", id=" + id + ", pid=" + pid + ", title=" + title + ", regdate=" + regdate
				+ ", kor=" + kor + ", eng=" + eng + ", tot=" + tot + ", avg=" + avg + "]";
	}

}
