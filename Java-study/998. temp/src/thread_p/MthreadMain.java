package thread_p;

class MTh extends Thread {

	public MTh(String name) {

		super(name);

	}

	@Override
	public void run() {

		for (int i = 0; i < 100; i++) {
			System.out.print(getName());

		}

	}

}

public class MthreadMain {

	public static void main(String[] args) {

		MTh t1 = new MTh("+");
		MTh t2 = new MTh("/");
		MTh t3 = new MTh("?");

		t1.setPriority(1); // 쓰레드의 순서조작
		t2.setPriority(5);
		t3.setPriority(10);

		t1.start();
		t2.start();
		t3.start();

	}

}
