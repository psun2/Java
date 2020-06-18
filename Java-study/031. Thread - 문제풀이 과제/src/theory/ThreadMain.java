package theory;

class MyThread extends Thread {

	public MyThread(String name) {
		super(name); // 쓰레드의 이름 set
		// setName(name); // 위와 같은 결과
	}

	@Override
	public void run() {

//		System.out.println("\nMyThread start!!");

//		System.out.println("현재 Thread = " + Thread.currentThread()); // currentThread() : 현재 돌아가는 쓰레드의 이름

		for (int i = 0; i < 50; i++) {
			System.out.print(getName());

			try {
				sleep(100); // 딜레이
			} catch (Exception e) {
				e.getMessage();
			}
		}

//		System.out.println("\nMyThread end!!");

	}
}

public class ThreadMain {

	public static void main(String[] args) {

		MyThread t1 = new MyThread("○");
		MyThread t2 = new MyThread("▲");
		MyThread t3 = new MyThread("□");

//		t1.run();
//		t2.run();
//		t3.run();
//		// 1끝나고 2끝나고 3끝나고 => 싱글 쓰레드
		// ○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○○▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□

		// 멀티 쓰레드 - 쓰레드끼리 섞입니다.
		t1.start();
		t2.start();
		t3.start();
		// ○▲□○▲□□○▲▲□○○□▲○▲□○□▲○▲□○□▲▲○□□○▲▲□○□○▲□○▲□▲○▲□○○▲□□▲○○▲□○▲□□▲○□○▲□▲○□○▲▲□○▲○□▲○□○□▲○▲□□○▲○□▲○□▲□○▲▲○□○□▲○▲□□○▲○▲□▲○□○□▲○▲□□▲○□▲○○□▲▲□○○□▲▲□○□▲○□○▲▲□○

		// active Thread() : 현재 돌아가는 쓰레드 개수

		System.out.println("현재 수행중인 전체 쓰레드 개수 = " + Thread.activeCount()); // 현재 수행중인 전체 쓰레드 개수 = 4
		// MyThread + main

	}

}
