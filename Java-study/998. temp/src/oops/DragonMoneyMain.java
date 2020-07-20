package oops;

// ✔ title: static 의 라이프 싸이클과 static의 특징

class DragonChild {

	static int mom = 100;

	String name;
	int myMoney = 0;

	void showMeTheMoney(int money) {
//		if (this.name.equalsIgnoreCase("아빠")) { // 대소문자 구문 없음
		if (this.name.equals("아빠")) { // 대소문자 구분
			System.out.println("뒤진다");
			System.out.println("\t잔액: " + mom);
			return;
		}
		DragonChild.mom -= money;
		this.myMoney += money;
		System.out.println(name + ": " + money + "(" + myMoney + ")");
		System.out.println("\t잔액: " + mom);

	}

	void missionComplete(int money) {
		// 아빠: 돈벌어와
		// 아들, 딸 건강하게만 자라다오.
//		if (this.name.equalsIgnoreCase("아빠")) { // 대소문자 구문 없음
		if (this.name.equals("아빠")) { // 대소문자 구분
			System.out.println("돈내놔 ㅅㄱ");
			DragonChild.mom += money;
			System.out.println("\t잔액: " + mom);
			return;
		}
		this.myMoney += money;
		System.out.println("사고만 치지말아다오.");
		System.out.println(name + ": " + money + "(" + myMoney + ")");
		System.out.println("\t잔액: " + mom);

	}

}

public class DragonMoneyMain {

	public static void main(String[] args) {

		DragonChild son = new DragonChild();
		DragonChild daughter = new DragonChild();
		DragonChild scv = new DragonChild();

		son.name = "아들";
		daughter.name = "딸";
		scv.name = "아빠";

		son.showMeTheMoney(15);
		daughter.showMeTheMoney(8);
		son.showMeTheMoney(12);
		scv.showMeTheMoney(12);
		daughter.showMeTheMoney(13);
		scv.missionComplete(100);
		son.showMeTheMoney(14);
		daughter.showMeTheMoney(9);
		son.missionComplete(1);
		daughter.missionComplete(11);

	}

}
