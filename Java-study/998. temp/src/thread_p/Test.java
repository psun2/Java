package thread_p;

import javax.swing.plaf.SliderUI;

class 타이머 extends Thread {

	boolean chk;
	int second;
	String name;

//	public 타이머(String name, boolean chk) {
	public 타이머(String name) {
		this.name = name;
//		this.chk = chk;
		chk = true;
	}

	@Override
	synchronized public void run() {

		second = 0;

		while (chk) {

//			System.out.println(chk);

//			if (chk)
//				break;

			second++;

			try {
				sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
//			System.out.println("여기가 계속 돔 ?");
		}

		System.out.println("타이머 종료 whil 끝");
//		System.out.println(name + "\t" + this.second);
	}

}

class Subject {

	타이머 타이머;
	String name;

	public Subject(String name) {
		this.name = name;
	}

	void test(int cnt) {

		String name = "타이머 이름" + cnt;

//		boolean chk
		타이머 = new 타이머(name);
//		타이머 = new 타이머(name, true);
		타이머.start();

		for (int i = cnt; i > 0; i--) {

			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

		타이머.chk = false;
		System.out.println(this.name + "\t" + 타이머.name + "\t" + 타이머.second);
	}

}

public class Test {

	public static void main(String[] args) {

//		타이머 t1 = new 타이머("타이머1");
//		타이머 t2 = new 타이머("타이머2");
//		타이머 t3 = new 타이머("타이머3");
//
//		t1.start();
//		t2.start();
//		t3.start();

		Subject 국어 = new Subject("국어");
		Subject 영어 = new Subject("영어");
		Subject 수학 = new Subject("수학");

		try {

			국어.test(10);
			영어.test(5);
			수학.test(25);
		} finally {
			System.out.println("나 언제 끝 ?");
		}
	}
}

