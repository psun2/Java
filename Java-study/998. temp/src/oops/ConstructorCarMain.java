package oops;

class ConstructorCar {
	String kind, name, Cargo; // kind and name
	int fuel, sum;
	int[] category;

	ConstructorCar(String name, int... category) { // 스포츠카, 승합차
		this.kind = new String[] { "스포츠카", "승합차" }[category.length - 2];
		this.name = name;
		this.category = category;
		calc();
		show();
	}

	ConstructorCar(String name, String Cargo, int... category) { // 트럭
		this.kind = "트럭";
		this.name = name;
		this.category = category;
		calc();
		show();
	}

	void calc() {
		for (int i : category) {
			sum += i;
		}
	}

	void show() {
		String result = this.kind + "\t" + this.name + "\t";

		for (int i : category) {
			result += i + "\t";
		}

		if (this.category.length == 2)
			result += "\t";

		result += sum;

		System.out.println(result);
	}

}

public class ConstructorCarMain {

	public static void main(String[] args) {

		// 자동차 성능 테스트 클래스를 구현하세요
		// 출력내용 : 차종, 차이름, 항목들, 점수

		// 차종 : 스포츠카 -- 속도 60, 연비 40
		// 승합차 -- 연비 30, 탑승인원 30, 안정성 40
		// 트럭 -- 연비 50, 적재량 50, 화물종류
		// 98 * 0.6 + 89*0.4 = 점수

		// 생성자를 이용하여 익명 클래스로 구현하세요

		new ConstructorCar("안녕", 60, 40);
		new ConstructorCar("안녕2", 30, 30, 40);
		new ConstructorCar("ㅌㄴㅁ", "돌", 50, 50);

	}

}
