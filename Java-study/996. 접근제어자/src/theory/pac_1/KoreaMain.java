package theory.pac_1;

import theory.pac_2.America;

class Korea {

	private String name;
	private America[] america;
	private int price;

	Korea(String name, America[] america) {
		this.name = name;
		this.america = america;
		calcPrice(america);
	}

	private void calcPrice(America[] america) {

		for (America am : america) {
			this.price += am.getPrice();
		}

	}

	protected String show() {
		String print = "[ " + name + ", ";

		for (America am : america) {
			print += am.show() + ", ";
		}

		print += price + " ]";

		return print;
	}

}

public class KoreaMain {

	public static void main(String[] args) {

		String[] item = { "엔진", "문짝" };
		String[] summay = { "4기통", "트렁크문" };
		int[] price = { 200, 60 };

		America[] america = new America[item.length];

		for (int i = 0; i < america.length; i++) {
			america[i] = new America(item[i], summay[i], price[i]);
		}

		Korea manufacturing = new Korea("현대", america);

		String result = manufacturing.show();

		System.out.println(result);

	}
}