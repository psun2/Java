package thread_p;

class InterruptTh extends Thread {

	public InterruptTh(String name) {
		super(name);
	}

	@Override
	public void run() {

		for (int i = 0; i < 100; i++) {

			// isInterrupted() : interrupt() 호출 유무
			if (isInterrupted())
				break;

			System.out.print(getName());

			try {
				sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(getName() + "종료");
	}

}

public class InterruptMain {

	public static void main(String[] args) throws Exception {

		// 다른 체크 방법을 권장

		InterruptTh t1 = new InterruptTh("+");
		InterruptTh t2 = new InterruptTh("/");
		InterruptTh t3 = new InterruptTh("?");

		System.out.println("\nt1 : " + t1.isInterrupted() + "\n"); // false
		System.out.println("\nt2 : " + t2.isInterrupted() + "\n"); // false
		System.out.println("\nt3 : " + t3.isInterrupted() + "\n"); // false

		System.out.println();

		t1.start();
		t2.start();
		t3.start();

		// Thread.sleep(3000);

		t1.interrupt(); // start 이후 호출 가능 // 일시 정지 // 시작 다음으로 잡아주어 정지 됩니다.

		System.out.println("\nt1 : " + t1.isInterrupted() + "\n"); // false
		System.out.println("\nt2 : " + t2.isInterrupted() + "\n"); // false
		System.out.println("\nt3 : " + t3.isInterrupted() + "\n"); // false
	}

}
