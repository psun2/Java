package test;

import javax.swing.JOptionPane;

class MyThread extends Thread {

	int second = 0;
	boolean timerChk = true;

	public MyThread(String name) {
		super(name);
	}

	@Override
	public void run() {

//		for (int i = 20; i > 0; i--) {
//			System.out.print(i + "\t[" + getName() + "]");
////			System.out.print(i);
//
//			try {
//				sleep(1000);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}

		System.out.println(timerChk);

		while (timerChk) {

			try {
				second++;
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		System.out.println(timerChk);
	}

}

class AAA {

	MyThread th;

	String subject;

	public AAA(String subject) {
		this.subject = subject;

//		java.lang.IllegalThreadStateException
//		이미 시작되어 있는 쓰레드를 다시 시작할때 나오는 에러

		test();

	}

	void test() {

		th = new MyThread(this.subject);

		th.start();

		for (int i = 0; i < 5; i++) {
			String res = JOptionPane.showInputDialog(this.subject);
			System.out.println(i + "\t" + res );
		}

		th.timerChk = false;

		System.out.println(subject + th.second + " 초");

//		try {
//			th.start();
//		} catch (Exception e) {
//			System.out.println("뭐가 에러야 도대체?");
//		}
	}

}

class Main {
	public static void main(String[] args) {

//		java.lang.IllegalThreadStateException
//		이미 시작되어 있는 쓰레드를 다시 시작할때 나오는 에러

		AAA t1 = new AAA("국");
		AAA t2 = new AAA("영");
		AAA t3 = new AAA("수");

		t1.test();
		t2.test();
		t3.test();

	}
}