package exam;

class reserve {

	private String id, name, phone, time;
	// private 로 정보를 은닉하여 외부에서 접근하지 못하게 캡슐화 합니다.

	private int number;

	public reserve(String id, String name, String phone, String time) {
		// 생성자를 이용해 private 한 맴버 변수를 초기화 할 수 있습니다.
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.time = time;
		number();
	}

	void number() {
		this.number = (int) (Math.random() * 60) + 1;
	}

	// getter 와 setter 을 사용하여, 외부에서 정보에 접근하거나 바꿀수 있습니다.

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public String getTime() {
		return time;
	}

	public int getNumber() {
		return number;
	}

	void show() {
		String result = getId() + ", " + getName() + ", " + getPhone() + ", " + getTime() + ", " + getNumber();
		System.out.println(result);
	}

}

public class Movies {

	public static void main(String[] args) {

		reserve rv = new reserve("아이디", "아무개", "010-1111-2222", "3:20");

		System.out.println(
				rv.getId() + ", " + rv.getName() + ", " + rv.getPhone() + ", " + rv.getTime() + ", " + rv.getNumber());

		rv.show();

		System.out.println("이름과 전화번호 변경");

		rv.setName("홍길동");

		rv.setPhone("018-1111-9999");

		System.out.println(
				rv.getId() + ", " + rv.getName() + ", " + rv.getPhone() + ", " + rv.getTime() + ", " + rv.getNumber());

		rv.show();
	}

}