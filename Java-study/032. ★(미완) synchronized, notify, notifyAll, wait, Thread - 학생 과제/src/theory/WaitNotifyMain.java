package theory;

class Synchronized { // 쓰레드의 동기화
	// 쓰레드의 동기화란 ?
	// 병렬 구성인 쓰레드들을 모아 직렬로 잡아 준다.

	synchronized void meth_A(String name) throws Exception {
//		notify(); // 임의의 1개를 풀어줌
		notifyAll(); // 모든 쓰레드를 다 풀어줌
		Thread.sleep(1000);
		System.out.println(name + " meth_A() 실행");
		wait();
		System.out.println(name + " 구속");
		Thread.sleep(1000);
	}

	synchronized void meth_B(String name) throws Exception {
//		notify(); // 남을 풀어주고 // 임의의 1개를 풀어줌
		notifyAll(); // 모든 쓰레드를 다 풀어줌
		Thread.sleep(1000);
		System.out.println(name + " meth_B() 실행");
		wait(); // 난 잠기고
		System.out.println(name + " 구속");
		Thread.sleep(1000);
	}

}

class MyThread1 extends Thread {

	Synchronized synchronized_A;

	public MyThread1(Synchronized synchronized_A, String name) {
		super(name);
		this.synchronized_A = synchronized_A;
	}

	@Override
	public void run() {
		while (true) {
			try {
				synchronized_A.meth_A(getName());
				System.out.println(getName() + " 자유다@@");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class MyThread2 extends Thread {

	Synchronized synchronized_A;

	public MyThread2(Synchronized synchronized_A, String name) {
		super(name);
		this.synchronized_A = synchronized_A;
	}

	@Override
	public void run() {
		while (true) {
			try {
				synchronized_A.meth_B(getName());
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

		Synchronized synchronized_A = new Synchronized();

		MyThread1 mt1_a = new MyThread1(synchronized_A, "mt1_a");
		MyThread1 mt2_a = new MyThread1(synchronized_A, "mt2_a");
		MyThread1 mt3_a = new MyThread1(synchronized_A, "mt3_a");
		MyThread2 mt1_b = new MyThread2(synchronized_A, "mt1_b");
		MyThread2 mt2_b = new MyThread2(synchronized_A, "mt2_b");
		MyThread2 mt3_b = new MyThread2(synchronized_A, "mt3_b");

		mt1_a.start();
		mt2_a.start();
//		mt3_a.start();
//		mt1_b.start();
//		mt2_b.start();
//		mt3_b.start();

//		synchronized 100% 사용 하기 팁
//		1. 본인의 클래스 내부에서 사용 불가
//		2. 쓰레드들이 한곳을 보는 지점을 찾는 것이 중요
//		3. 한곳을 본다면 그곳에 synchronized 키워드를 붙힌 메소드 생성 

//		mt1_a meth_A() 실행 // 멀티 쓰레드가 실행 병렬적으로 같이 돕니다.
//		mt2_a meth_A() 실행 // 멀티 쓰레드가 실행 병렬적으로 같이 돕니다.
//		mt1_a 구속 // 맨 처음에 들어간 m1_a 가 wait에 걸립니다.
//		mt1_a 자유다 // 그리고 mt2_a 가 메소드_A의 문을 두들기고 들어갑니다. 그럼 notify로 인해 wait 되었던 mt1_a 가 자유가 됩니다.
//		mt1_a meth_A() 실행 // 자유가 된 m1_a 가 synchronized 키워드가 붙은 메소드_A에 들어갑니다.
//		mt2_a 구속 // mt1_a를 풀어 주었던 mt2_a 가 구속 됩니다.
//		mt2_a 자유다 // a가 메소드에 진입하면서 notify 가 실행되 mt2_a가  자유가 됩니다.

	}

}
