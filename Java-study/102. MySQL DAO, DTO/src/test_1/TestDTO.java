package test_1;

public class TestDTO {
	
	public int id;
	public String sid;
	
	public TestDTO() {
		// TODO Auto-generated constructor stub
	}

	public TestDTO(String sid) {
		super();
		this.sid = sid;
	}

	@Override
	public String toString() {
		return "TestDTO [id=" + id + ", sid=" + sid + "]";
	}
	

}
