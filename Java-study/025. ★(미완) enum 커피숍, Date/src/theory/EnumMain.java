package theory;

enum Fruit {

	APPLE, BANANA, TOMATO, STROBERRY, WATERMELON, PEACH, MELON;

}

enum Fruit2 {

	APPLE("사과"), BANANA("바나나"), TOMATO("토마토");

	String name;
	int price;

	private Fruit2(String name) {
		this.name = name;
	}

}

enum Fruit3 {

	APPLE("사과", 2000), BANANA("바나나", 3000), TOMATO("토마토", 4000);

	String name;
	int price;

	private Fruit3(String name, int price) {
		this.name = name;
		this.price = price;
	}

	void show() {
		System.out.println(this + " : " + name + ", " + price);
	}

}

public class EnumMain {

	public static void main(String[] args) {

		Fruit kind;

		kind = Fruit.APPLE;

		System.out.println(kind); // APPLE

		System.out.println();
		System.out.println("ordinal()---------------");
		int num = kind.ordinal();
		System.out.println("kind = Fruit.APPLE; kind.ordinal() : " + num + "번 index");
		// kind = Fruit.APPLE; kind.ordinal() : 0번 index
		kind = Fruit.TOMATO;
		num = kind.ordinal();
		System.out.println("kind = Fruit.TOMATO; kind.ordinal() : " + num + "번 index");
		// kind = Fruit.TOMATO; kind.ordinal() : 2번 index

		System.out.println();
		System.out.println("valueOf()--맴버이름으로 찾기-------------");
		kind = Fruit.valueOf("BANANA");
		System.out.println("kind = Fruit.valueOf(\"BANANA\") : " + kind);
		// kind = Fruit.valueOf("BANANA") : BANANA

		System.out.println();
		System.out.println("compareTo()----비교 대상과 몇번째 떨어져 있는지 알려줍니다.-----------");
		System.out.println("to : ~ 으로 부터");
		num = Fruit.STROBERRY.compareTo(Fruit.TOMATO);
		if (num > 0) {
			System.out.println("Fruit.STROBERRY 는 Fruit.TOMATO 으로 부터 [뒤]로 [" + num + "] 번째 존재 합니다.");
		} else {
			System.out.println("Fruit.STROBERRY 는 Fruit.TOMATO 으로 부터 [앞]으로 [" + num + "] 번째 존재 합니다.");
		}
		// Fruit.STROBERRY 는 Fruit.TOMATO 으로 부터 [뒤]로 [1] 번째 존재 합니다.

		num = Fruit.STROBERRY.compareTo(Fruit.WATERMELON);
		if (num > 0) {
			System.out.println("Fruit.STROBERRY 는 Fruit.WATERMELON 으로 부터 [뒤]로 [" + num + "] 번째 존재 합니다.");
		} else {
			System.out.println("Fruit.STROBERRY 는 Fruit.WATERMELON 으로 부터 [앞]으로 [" + num + "] 번째 존재 합니다.");
		}
		// Fruit.STROBERRY 는 Fruit.WATERMELON 으로 부터 [앞]으로 [-1] 번째 존재 합니다.

		num = Fruit.STROBERRY.compareTo(Fruit.APPLE);
		if (num > 0) {
			System.out.println("Fruit.STROBERRY 는 Fruit.APPLE 으로 부터 [뒤]로 [" + num + "] 번째 존재 합니다.");
		} else {
			System.out.println("Fruit.STROBERRY 는 Fruit.APPLE 으로 부터 [앞]으로 [" + num + "] 번째 존재 합니다.");
		}
		// Fruit.STROBERRY 는 Fruit.APPLE 으로 부터 [뒤]로 [3] 번째 존재 합니다.

		num = Fruit.STROBERRY.compareTo(Fruit.MELON);
		if (num > 0) {
			System.out.println("Fruit.STROBERRY 는 Fruit.MELON 으로 부터 [뒤]로 [" + num + "] 번째 존재 합니다.");
		} else {
			System.out.println("Fruit.STROBERRY 는 Fruit.MELON 으로 부터 [앞]으로 [" + num + "] 번째 존재 합니다.");
		}
		// Fruit.STROBERRY 는 Fruit.MELON 으로 부터 [앞]으로 [-3] 번째 존재 합니다.

		num = Fruit.STROBERRY.compareTo(Fruit.STROBERRY);
		System.out.println("Fruit.STROBERRY 는 Fruit.STROBERRY 으로 부터 [" + num + "] 번째 존재 합니다.");
		System.out.println("즉 동일 선상에 있습니다.");
		// Fruit.STROBERRY 는 Fruit.STROBERRY 으로 부터 [0] 번째 존재 합니다.
		// 즉 동일 선상에 있습니다.

		System.out.println();
		System.out.println("맴버 변경 -------------------------------");
		System.out.println("Fruit3.APPLE.name : " + Fruit3.APPLE.name);
		// Fruit3.APPLE.name : 사과
		Fruit3.APPLE.name = "애플";
		System.out.println("Fruit3.APPLE.name : " + Fruit3.APPLE.name);
		// Fruit3.APPLE.name : 애플

		System.out.println();
		System.out.println("values(), forEach ---배열로 따지면 values 로 인해 배열화 합니다.----------------------------");

		for (Fruit3 f : Fruit3.values()) {
			f.show();
		}
		// APPLE : 애플, 2000
		// BANANA : 바나나, 3000
		// TOMATO : 토마토, 4000

		// 같은 아메리카노 가 값이 2개 일 경우 enum class를 사용 할 수없다.
		// 024. 커피숍 과제

		System.out.println();

		String orderList = "APPLE,BANANA,BANANA,TOMATO,BANANA,APPLE,APPLE,BANANA,APPLE,BANANA,BANANA,TOMATO,TOMATO,BANANA,BANANA,APPLE,BANANA,BANANA,TOMATO";

		int tot = 0;

		for (String ol : orderList.split(",")) {
			tot += Fruit3.valueOf(ol).price;
			System.out.println(ol + "의 가격 " + Fruit3.valueOf(ol).price + "원 을 tot 에 더합니다." + tot);
		}

		System.out.println("tot : " + tot);

//		APPLE의 가격 2000원 을 tot 에 더합니다.2000
//		BANANA의 가격 3000원 을 tot 에 더합니다.5000
//		BANANA의 가격 3000원 을 tot 에 더합니다.8000
//		TOMATO의 가격 4000원 을 tot 에 더합니다.12000
//		BANANA의 가격 3000원 을 tot 에 더합니다.15000
//		APPLE의 가격 2000원 을 tot 에 더합니다.17000
//		APPLE의 가격 2000원 을 tot 에 더합니다.19000
//		BANANA의 가격 3000원 을 tot 에 더합니다.22000
//		APPLE의 가격 2000원 을 tot 에 더합니다.24000
//		BANANA의 가격 3000원 을 tot 에 더합니다.27000
//		BANANA의 가격 3000원 을 tot 에 더합니다.30000
//		TOMATO의 가격 4000원 을 tot 에 더합니다.34000
//		TOMATO의 가격 4000원 을 tot 에 더합니다.38000
//		BANANA의 가격 3000원 을 tot 에 더합니다.41000
//		BANANA의 가격 3000원 을 tot 에 더합니다.44000
//		APPLE의 가격 2000원 을 tot 에 더합니다.46000
//		BANANA의 가격 3000원 을 tot 에 더합니다.49000
//		BANANA의 가격 3000원 을 tot 에 더합니다.52000
//		TOMATO의 가격 4000원 을 tot 에 더합니다.56000
//		tot : 56000

	}

}
