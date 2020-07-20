package pac.pac_1;

import pac.pac_2.America;

class Korea {

	private String name;
	private America pp1, pp2;
	private int price;

	public Korea(String name, America pp1, America pp2) {
		this.name = name;
		this.pp1 = pp1;
		this.pp2 = pp2;
		this.price = pp1.price() + pp2.price();
//		this.price = pp1.getPrice() + pp2.getPrice();
	}

	void ppp() {
		String ttt = "Korea " + name + ", " + pp1.ppp() + ", " + pp2.ppp() + ", " + price;

		System.out.println(ttt);
	}

}

public class KoreaMain {

	public static void main(String[] args) {

		America engine = new America("엔진", " 4기통", 200);
		America tm = new America("문짝", "트렁크문", 60);

		Korea kk = new Korea("현대", engine, tm);

		kk.ppp();
	}

}
