package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResExamDTO {

	public Integer rnum, id, kor, eng, tot, avg;

	public String title, pid;

	public Date regdate;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public ResExamDTO(Integer rnum, Integer id, String pid, String title, Date regdate, Integer kor, Integer eng,
			Integer tot, Integer avg) {
		super();
		this.rnum = rnum;
		this.id = id;
		this.pid = pid;
		this.title = title;
		this.regdate = regdate;
		this.kor = kor;
		this.eng = eng;
		this.tot = tot;
		this.avg = avg;
	}

	public ResExamDTO(String pid, String title, Date regdate, Integer kor, Integer eng) {
		super();
		this.pid = pid;
		this.title = title;
		this.regdate = regdate;
		this.kor = kor;
		this.eng = eng;
	}

	public ResExamDTO(String pid, String title, String regdate, Integer kor, Integer eng) {
		super();
		this.pid = pid;
		this.title = title;
		setRegdateStr(regdate);
		this.kor = kor;
		this.eng = eng;
	}

	public ResExamDTO() {
		// TODO Auto-generated constructor stub
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