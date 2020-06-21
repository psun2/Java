package exam;

class AAA {

	String name;

	AAA(String name) {
		this.name = name;
	}

}

class BBB {

	void Address(AAA aaa) {

		aaa.name = "이름변경";
		// 하지만 내부에서 이름 변경을 가능 합니다.

		AAA aaa2 = new AAA("새로운 AAA");
		System.out.println(aaa2.name); // 새로운 AAA

		aaa = aaa2;
		System.out.println(aaa.name); // 새로운 AAA
		// return 을 하지 않기 때문에 주소가 바뀌지 않습니다.
		// 내부에서 바꾸는 주소는 외부에서 파라미터로 받아오는
		// 원본 객체의 주소까진 바꾸지 않습니다.
		// 블록스코프를 완전 활용 100%
		
//		‼‼ 총 정리 => GenericBagMain
//		메소드 내부에서 바꾸는 주소는 외부에 영향을 주지 않습니다.
//		why ? return 을 시키지 않기 때문에 메소드 내부에서만 사용 합니다.
//		main for문 [전] : 366712642
//		메소드 내부에서의 map의 주소 : 366712642
//		main for문 [후] : 366712642
//		map 에 put 을 하고, 
//		map 을 sub 으로 바꾸는 이유는
//		원본 map 의 안에 있는 또다른 sub 이라는 주소로 접근을 하기 위해서 입니다.
//		그럼 이때 map 가르키는건 원본 map 안에 있는 또다른 map 이 됩니다.

	}

	AAA Address2(AAA aaa) {

		AAA aaa2 = new AAA("더 새로운 AAA");

		aaa = aaa2;

		return aaa;
	}

}

class AddressTest {

	public static void main(String[] args) {

		AAA aaa = new AAA("AAA");

		System.out.println(aaa.name); // AAA

		BBB bbb = new BBB();

		bbb.Address(aaa);
		System.out.println(aaa.name); // 새로운 AAA

		bbb.Address2(aaa);

		System.out.println(aaa.name); // 새로운 AAA

		aaa = bbb.Address2(aaa);

		System.out.println(aaa.name); // 더 새로운 AAA

	}
}