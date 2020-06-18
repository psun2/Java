package theory;

class InterruptThread extends Thread {

	public InterruptThread(String name) {
		super(name);
	}

	@Override
	public void run() {

		for (int i = 0; i < 50; i++) {

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

	public static void main(String[] args) {

//		interrupted()함수 자신이 호출후에 무조건 false로 변경한다. 이 함수는 interrupt()가 여러번 호출될 경우에 사용할 수 있는 검사 함수이다.

		InterruptThread t1 = new InterruptThread("○");
		InterruptThread t2 = new InterruptThread("▲");
		InterruptThread t3 = new InterruptThread("□");

		System.out.println("\nt1 : " + t1.isInterrupted() + "\n"); // false
		System.out.println("\nt2 : " + t2.isInterrupted() + "\n"); // false
		System.out.println("\nt3 : " + t3.isInterrupted() + "\n"); // false

		System.out.println();

		t1.start();
		t2.start();
		t3.start();

		t1.interrupt(); // start 이후 호출 가능 // 일시 정지 // 시작 다음으로 잡아주어 정지 됩니다.
		// but 시간이 너무 빨리 설정된 경우 interrupt는 걸려 있지만 출력이 되고 예외를 발생시킵니다.

		System.out.println("\nt1 : " + t1.isInterrupted() + "\n"); // true
		System.out.println("\nt2 : " + t2.isInterrupted() + "\n"); // false
		System.out.println("\nt3 : " + t3.isInterrupted() + "\n"); // false

//		t1.start(); // Error 예수가 아닙니다. 죽었다가 살아나지 않습니다.
		

//t1 : false
//
//
//t2 : false
//
//
//t3 : false
//
//
//
//t1 : true
//
//
//t2 : false
//
//
//t3 : false
//
//○종료
//▲□▲□▲□▲□▲□□▲▲□▲□□▲▲□▲□▲□□▲▲□□▲□▲▲□▲□▲□□▲□▲▲□□▲▲□□▲▲□□▲▲□▲□▲□▲□□▲□▲▲□▲□▲□▲□□▲▲□▲□▲□□▲▲□▲□▲□□▲▲□□▲□▲□▲□종료
//▲종료


	}

}
