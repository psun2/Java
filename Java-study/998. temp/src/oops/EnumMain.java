package oops;

enum ZZiGae {

	// 배열 => singleTon 개념
	KimChi("김치찌개", 6000), Doenjang("된장찌개", 5500), Military("부대찌개", 7000);

	// 각 항목을 클래스로 봄
	// 클래스의 정의
	String name;
	// 이 밑부턴 클래스의 정의부와 같이 사용
	int price;

	ZZiGae(String name, int price) {
		this.name = name;
		this.price = price;
	}

	void ppp() {
		System.out.println(this + " : " + name + ", " + price);
	}

}

public class EnumMain {

	public static void main(String[] args) {

		System.out.println(ZZiGae.KimChi + " : " + ZZiGae.KimChi.ordinal());
		// KimChi
		System.out.println(ZZiGae.Doenjang + " : " + ZZiGae.Doenjang.ordinal());
		// Doenjang
		System.out.println(ZZiGae.Military + " : " + ZZiGae.Military.ordinal());
		// Military

		ZZiGae zg;
		zg = ZZiGae.Doenjang;
		System.out.println("ordinal() -------------------------------");
		System.out.println(zg);
		// Doenjang
		System.out.println(zg.ordinal()); // 1
		System.out.println("valueOf() -------------------------------");
		zg = ZZiGae.valueOf("Military");
		System.out.println(zg);
		// Military
		System.out.println(zg.ordinal()); // 1
		System.out.println("compareTo() -------------------------------");
		// Doenjang 의 순서가 KimChi 보다 빠르다, Doenjang > KimChi : 1
		// kimchi 보다 doenjang 이 뒤로 1번째 있으니 1
		System.out.println(ZZiGae.Doenjang.compareTo(ZZiGae.KimChi));
		// Doenjang == Doenjang : 0
		// 같은 자리 이니 0
		System.out.println(ZZiGae.Doenjang.compareTo(ZZiGae.Doenjang));
		// Doenjang < Military : -1
		// 부대보다 된장은 -1 번째 로 앞에 있습니다.
		System.out.println(ZZiGae.Doenjang.compareTo(ZZiGae.Military));

		System.out.println("values() -------------------------------");

		for (ZZiGae zz : ZZiGae.values()) {
			System.out.println(zz + " : " + zz.name + ", " + zz.price);
		}

		System.out.println("맴버 변경 -------------------------------");
		ZZiGae.Doenjang.name = "청국장찌개";
		ZZiGae.Doenjang.price = 8000;

		for (ZZiGae zz : ZZiGae.values()) {
//			System.out.println(zz + " : " + zz.name + ", " + zz.price);
			zz.ppp();
		}

		System.out.println();

		String orderList = "KimChi,Doenjang,Doenjang,Military,KimChi,Doenjang,KimChi,Military,KimChi";

		int tot = 0;

		for (String or : orderList.split(",")) {
			tot += ZZiGae.valueOf(or).price;
		}

		System.out.println(tot);

		// 같은 아메리카노 가 값이 2개 일 경우 사용 할 수없다. . 커피숍 과제

	}

}
