package theory;

public class BusDTO { // 데이타(DB) 생김새를 보기 위한 객체

	String no, type, district;
	int price;

	public BusDTO(String no, String type, String district, int price) {
		super();
		this.no = no;
		this.type = type;
		this.district = district;
		this.price = price;
	}

	@Override
	public String toString() {
		return "BusDTO [no=" + no + ", type=" + type + ", district=" + district + ", price=" + price + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
