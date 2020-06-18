package theory;

class TTT extends Thread {
	public void run() {
		System.out.println("\nTTT start!!");
// currentThread() : 현재 돌아가는 쓰레드의 이름 
		System.out.println("현재 Thread = " + Thread.currentThread());
		System.out.println("\nTTT end!!");
	}
}

public class ThreadStudyMain {
	public static void main(String[] args) {
		System.out.println("Main Start!");
		// active Thread() : 현재 돌아가는 쓰레드 개수
		System.out.println("현재 수행중인 전체 쓰레드 개수 = " + Thread.activeCount());
		TTT ap = new TTT();
		// setPriority() : 쓰레드의 우선순위값 정하기. start전에... 숫자 크면 우선순위 높다.
		// MAX_PRIORITY : 10 / NORM_PRIORITY : 5 / MIN_PRIORITY : 1 //
		// ap.setPriority(1);
		// 이렇게 해도 되고.... ap.setPriority(Thread.MIN_PRIORITY);
		// setDaemon(true) : 메인 쓰레드가 끝나면 나머지도 전부 종료.
		// ap.setDaemon(true) ap.start();
		System.out.println("현재 수행중인 전체 쓰레드 개수 = " + Thread.activeCount());
		// enumerate() : 현재 동작중인 모든 쓰레드를 쓰레드 배열로 가져온다.
		// getName() : 쓰레드 이름.. 밑에서 Thread-0, main과 같은것들.
		// cur Thread = Thread[main,5,main] : main에 귀속적인 main 쓰레드가 우선순위 5등을 가지고 나타났다.
		// [main(쓰레드 이름),5(동시진행시 우선순위. 디폴트는 5),main(어떤 쓰레드에 의해 동작되었는가)]
		Thread[] th = new Thread[Thread.activeCount()];
		Thread.enumerate(th);
		for (int i = 0; i < th.length; i++) {
			System.out.println("쓰레드 " + i + "번째 : " + th[i].getName());
		} /*
			 * try{ Thread.sleep(3000); } catch (InterruptedException ee){} // 지연동안의 예외처리
			 */
		// ap.interrupt();
		// interrupt() : 특정 객체를 멈추고자 할 때. 객체에 대해.
		// Thread.currentThread().interrupt();
		// interrupted() : 현재 쓰레드 중단.
		/*
		 * for(int i = 1; i<100; ++i){ System.out.print(i); if(i%10==0)
		 * System.out.println(""); else System.out.print("\t"); }
		 */ // } }
	}
}
