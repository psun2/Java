package thread_p;

class SSSS {

	synchronized void meth_A(String name) throws Exception {
//		notify(); // 임의의 1개를 풀어줌
		notifyAll(); // 모든 쓰레드를 다 풀어줌
		System.out.println(name + " meth_A() 실행");
		wait();
		System.out.println(name + " 구속");
		Thread.sleep(1000);
	}

	synchronized void meth_B(String name) throws Exception {
//		notify(); // 남을 풀어주고 // 임의의 1개를 풀어줌
		notifyAll(); // 모든 쓰레드를 다 풀어줌
		System.out.println(name + " meth_B() 실행");
		wait(); // 난 잠기고
		System.out.println(name + " 구속");
		Thread.sleep(1000);
	}
}

class Th_A extends Thread {
	SSSS ss;

	public Th_A(SSSS ss, String name) {
		super(name);
		this.ss = ss;
	}

	@Override
	public void run() {
		while (true) {
			try {
				ss.meth_A(getName());
				System.out.println(getName() + " 자유다@@");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class Th_B extends Thread {
	SSSS ss;

	public Th_B(SSSS ss, String name) {
		super(name);
		this.ss = ss;
	}

	@Override
	public void run() {
		while (true) {
			try {
				ss.meth_B(getName());
				System.out.println(getName() + " 자유다@@");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

public class WaitNotifyMain {

	public static void main(String[] args) {
		SSSS ss = new SSSS();

		Th_A a1 = new Th_A(ss, "a1");
		Th_B b1 = new Th_B(ss, "b1");
		Th_A a2 = new Th_A(ss, "a2");
		Th_B b2 = new Th_B(ss, "b2");
		Th_A a3 = new Th_A(ss, "a3");
		Th_B b3 = new Th_B(ss, "b3");

		a1.start();
		b1.start();
		a2.start();
		b2.start();
		a3.start();
		b3.start();

	}

}
