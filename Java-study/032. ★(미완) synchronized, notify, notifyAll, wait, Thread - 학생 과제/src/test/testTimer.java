package test;

class Timer extends Thread {

	AAA aa;

	public Timer(AAA aa) {
		super();
		this.aa = aa;
	}

	@Override
	public void run() {
		aa.test();
	}

}

class AAA {

	void test() {

		int i = 0;
		while (true) {

			try {
				Thread.sleep(1000);
				i++;
				System.out.println(i + " 초 경과");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (i == 20)
				break;

		}

	}

}

public class testTimer {

	public static void main(String[] args) {

		Timer t1 = new Timer(new AAA());

		t1.start();

	}

}
