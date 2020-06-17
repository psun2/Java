package exam;

import java.util.Random;

class InnerCoffee { // 주문 클래스

	String name; // 메뉴이름

	int price; // 가격

	int cnt; // 수량

	public InnerCoffee(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return name + "(" + price + ")";
	}

}

class OuterHO { // 본사

	String name; // 본사이름

	InnerCoffee[] menu; // 메뉴와 가격은 본사가 결정

	public OuterHO(String name, InnerCoffee[] menu) {
		super();
		this.name = name;
		this.menu = menu;
	}

	int coffeeIndex(String coffee) {

		for (int i = 0; i < menu.length; i++) {
			if (menu[i].name.equals(coffee))
				return i;
		}

		return -1;
	}

	void ppp() {
		System.out.println(name + ">>>>>>>>>>");
		for (InnerCoffee ic : menu) {
			System.out.println(ic + "\t" + ic.cnt + "\t" + ic.cnt * ic.price);
		}

	}

	class InnerBO { // 지사

		String name;

		int[] cnt = new int[menu.length];

		public InnerBO(String name) {
			super();
			this.name = name;

		}

		void order(String coffee, int cnt) { // 주문은 지사가 받음 본사는 판매하지 않음
			// 메뉴의 이름과 수량을 받음

			int index = coffeeIndex(coffee);

			if (index < 0) {
				System.out.println(coffee + " 메뉴가 없어요");
				return;
			}
			menu[index].cnt += cnt; // menue => 본사의 메뉴와 가격 InnerCoffee 클래스
			// OuterHO.this.menu[index].cnt 와 동일 지사와 이름이 겹치지 않으므로, menu 까지만 적어도 됨.
			this.cnt[index] += cnt;
			String ttt = OuterHO.this.name + " " + name + "점 " + coffee + ":" + cnt + "주문(" + menu[index].price * cnt
					+ ")";

			System.out.println(ttt);
		}

		void ppp() {
			System.out.println("[[[" + OuterHO.this.name + " " + name + " 점]]]");

			for (int i = 0; i < menu.length; i++) {

				System.out.println(menu[i] + "\t" + cnt[i] + "\t" + cnt[i] * menu[i].price);
			}

		}

	}
}

public class InnerCoffeeShopMain {

	public static void main(String[] args) {

		// ✔ 요구사항
		//
		// 본사 - 메뉴 와 가격 (+ 다른 회사의 메뉴와 가격은 가지고 있을 필요 ❌)
		// 지점 - 판매(메뉴, 몇잔 주세요)

		OuterHO star = new OuterHO("별다방", new InnerCoffee[] { new InnerCoffee("아메리카노", 2000),
				new InnerCoffee("아시아노", 2300), new InnerCoffee("아프리카노", 2700) });

		OuterHO bean = new OuterHO("커피콩", new InnerCoffee[] { new InnerCoffee("아메리카노", 2100),
				new InnerCoffee("아시아노", 2500), new InnerCoffee("유럽노", 2200) });

		OuterHO.InnerBO[] bos = { star.new InnerBO("역삼"), star.new InnerBO("종로"), star.new InnerBO("신촌"),
				bean.new InnerBO("역삼"), bean.new InnerBO("구로"), bean.new InnerBO("청량리") };

		String[] title = "유럽노,아메리카노,아시아노,아프리카노".split(",");

		Random rd = new Random();
		for (int i = 0; i < 20; i++) {
			bos[rd.nextInt(bos.length)].order(title[rd.nextInt(title.length)], rd.nextInt(7) + 1);
		}
		for (OuterHO.InnerBO bo : bos) {
			bo.ppp();
		}

		star.ppp();
		bean.ppp();

	}

}