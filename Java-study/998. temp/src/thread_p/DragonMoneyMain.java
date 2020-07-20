package thread_p;

class Mom {
	int total = 100;

	synchronized boolean showMeTheMoney(int money, String name) {

		boolean res = false;
		if (res = !name.equals("클레멘타인") && total >= money) {
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (res = total < 30 && name.equals("클레멘타인")) {
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
		while (mom.total > 0) {
			int money = (int) (Math.random() * 50) + 1;

			if (mom.showMeTheMoney(money, getName())) {
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
