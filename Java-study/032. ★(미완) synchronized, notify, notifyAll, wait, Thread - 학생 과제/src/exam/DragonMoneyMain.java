package exam;

class Mom { // synchronized 를 사용 하기 위해 쓰레드들이 공통으로 바라볼 곳이 있어야함... 그것이 엄마 지갑...

	int total = 100;

	synchronized boolean showMeTheMoney(int money, String name) {

		boolean res = false;

		if (res = !name.equals("클레멘타인") && total >= money) { // 엄마의 지갑에 있는 돈보다 많은 돈을 요구 할 순 없지...
			// 엄마의 지갑에 있는 돈보다 작거나 같은 금액을 요구할때만 true 반환이네 ?
			// 아빠가 아니면서....
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (res = total < 30 && name.equals("클레멘타인")) { // 아빠이면서 엄마의 지갑에 돈이 30 미만일때 true 반환
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return res;
	}
}

class SyChild extends Thread {
	int myMoney = 0;
	Mom mom;

	public SyChild(String name, Mom mom) {
		super(name);
		this.mom = mom;
	}

	@Override
	public void run() {

		while (mom.total > 0) { // 엄마지갑의 잔고가 0원이 되기 전까지 무한으로 start 됨.....
			int money = (int) (Math.random() * 50) + 1;

			if (mom.showMeTheMoney(money, getName())) { // showMeTheMoney 의 반환 값이 true 가 될때만 진행
				myMoney += money;
				mom.total -= money;
				System.out.println(getName() + ":" + money + "(" + myMoney + ")");
				System.out.println("\t잔액:" + mom.total);
			}
		}
	}
}

class SySCV extends Thread {
	int myMoney = 0;
	Mom mom;

	public SySCV(Mom mom) {
		super("클레멘타인");
		this.mom = mom;
	}

	@Override
	public void run() {
		while (mom.total > 0) {

			int money = (int) (Math.random() * 50) + 50;

			if (mom.showMeTheMoney(money, getName())) {
				myMoney += money;
				mom.total += money;
				System.out.println(getName() + ":" + money + "(" + myMoney + ")");
				System.out.println("\t잔액:" + mom.total);
			}
		}
	}
}

public class DragonMoneyMain {

	public static void main(String[] args) {
		Mom mom = new Mom();
		SyChild son = new SyChild("아들", mom);
		SyChild daughter = new SyChild("딸", mom);
		SySCV scv = new SySCV(mom);

		scv.start();
		son.start();
		daughter.start();
	}

}