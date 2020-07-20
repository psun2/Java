package thread_p;

class FirstTh extends Thread {

	public FirstTh(String name) {

		super(name);

	}

	@Override
	public void run() {

		for (int i = 0; i < 100; i++) {
			System.out.print(getName());

			try {
				sleep(100); // 딜레이
			} catch (Exception e) {
				e.getMessage();
			}

		}

	}

}

public class ThreadMain {

	public static void main(String[] args) {

		FirstTh t1 = new FirstTh("+");
		FirstTh t2 = new FirstTh("/");
		FirstTh t3 = new FirstTh("?");

//		long start = System.currentTimeMillis();
//		t1.run();
//		t2.run();
//		t3.run();
//		// 1끝나고 2끝나고 3끝나고 => 싱글 쓰레드
//		long end = System.currentTimeMillis();
//		System.out.println();
//		System.out.println(end - start + "초 걸림");

		System.out.println();
		long start2 = System.currentTimeMillis();
		t1.start();
		t2.start();
		t3.start();
		// 섞이기 시작함.
		long end2 = System.currentTimeMillis();
		System.out.println();
		System.out.println(end2 - start2 + "초 걸림");

		System.out.println(Thread.activeCount());
		// 현재 돌고 있는 쓰레드의 갯수를 반환
		System.out.println(Thread.currentThread());

		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n" + Thread.activeCount() + "\n");

	}

}
