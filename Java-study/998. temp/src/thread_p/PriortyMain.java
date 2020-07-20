package thread_p;

class PriortyTh extends Thread {

	public PriortyTh(String name) {

		super(name);

	}

	@Override
	public void run() {

		for (int i = 0; i < 100; i++) {
			System.out.print(getName());

		}

	}

}

public class PriortyMain {

	public static void main(String[] args) {

		PriortyTh t1 = new PriortyTh("+");
		PriortyTh t2 = new PriortyTh("/");
		PriortyTh t3 = new PriortyTh("?");

		t1.setPriority(1); // 쓰레드의 순서조작
		t2.setPriority(5);
		t3.setPriority(10);

		t1.start();
		t2.start();
		t3.start();

	}

}
