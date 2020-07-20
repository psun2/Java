package pac.pac_2;

public class America {

	String kind, name;
//	private int price;
	int price;

	public America(String kind, String name, int price) {
		this.kind = kind;
		this.name = name;
		this.price = price;
	}

	public int price() {
		return price;
	}
//	public int getPrice() {
//		return price;
//	}

	public String ppp() {
		return "[ America" + kind + ", " + name + ", " + price + " ]";
	}

}
