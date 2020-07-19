package theory.pac_2;

public class America {

//	private String kind, name; // 클래스 내부 처리가 아닌 다른 클래스에서 받기때문에 private 불가
//	private int price; // 클래스 내부 처리가 아닌 다른 클래스에서 받기때문에 private 불가
	private String kind, name;
	private int price;

	// 다른 패키지 내에서 접근이 가능 해야 하기때문에 public
	// 상속 받는 경우가 있다면 protected => 실패 아직 생각이 안됨
	public America(String kind, String name, int price) {
		this.kind = kind;
		this.name = name;
		this.price = price;
	}

//	protected String show() {
	public String show() {
		String print = "[ " + kind + ", " + name + ", " + price + " ]";
		return print;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
