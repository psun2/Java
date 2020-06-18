package theory;

class DaemonThread extends Thread {

	@Override
	public void run() {

		int i = 1;
		while (true) {

			System.out.println("DeamonThread : " + i);
//			long start = System.currentTimeMillis() / 1000;

			try {
				sleep(3000);
			} catch (Exception e) {
				e.getStackTrace();
			}

			i++;
//			long end = System.currentTimeMillis() / 1000;

//			System.out.println((end - start) + " 초 걸림");
		}

	}

}

public class DaemonThreadMain {

	public static void main(String[] args) throws Exception {

		// DaemonThread 는 모든 쓰레드가 끝난 후 끝납니다.

		DaemonThread daemon = new DaemonThread();

		daemon.setDaemon(true);

		daemon.start();

		new Timer().start();

//		자동저장	1
//		20 초
//		19 초
//		18 초
//		3 초 걸림
//		자동저장	2
//		17 초
//		16 초
//		15 초
//		3 초 걸림
//		자동저장	3
//		14 초
//		13 초
//		12 초
//		3 초 걸림
//		자동저장	4
//		11 초
//		10 초
//		9 초
//		3 초 걸림
//		자동저장	5
//		8 초
//		7 초
//		6 초
//		3 초 걸림
//		자동저장	6
//		5 초
//		4 초
//		3 초
//		3 초 걸림
//		자동저장	7
//		2 초
//		1 초

		for (int i = 0; i < 10; i++) { // 여기가 제일 빨리 끝나지만 아직 timer 이 끝나지 않아, 데몬쓰레드는 계속 지속됩니다.
			Thread.sleep(1000);
			System.out.println(i + "\t메인쓰레드");
			if (i == 9)
				System.out.println(i + "\t메인쓰레드 끝!!!!!!! => 하지만 데몬은 계속 됩니다.");
			// main 에 귀속 된것이 아니라, 무엇이라도 한개가 사라져도 다른 쓰레드가 남으면 Daemon 은 살아 있습니다.
			// 마지막 Threa 가 끝나야 같이 끝이 납니다.
		}
		
//		DeamonThread : 1
//		20 초
//		19 초
//		0	메인쓰레드
//		1	메인쓰레드
//		18 초
//		DeamonThread : 2
//		2	메인쓰레드
//		17 초
//		16 초
//		3	메인쓰레드
//		4	메인쓰레드
//		15 초
//		DeamonThread : 3
//		14 초
//		5	메인쓰레드
//		6	메인쓰레드
//		13 초
//		12 초
//		7	메인쓰레드
//		DeamonThread : 4
//		11 초
//		8	메인쓰레드
//		10 초
//		9	메인쓰레드
//		9	메인쓰레드 끝!!!!!!! => 하지만 데몬은 계속 됩니다.
//		9 초
//		DeamonThread : 5
//		8 초
//		7 초
//		6 초
//		DeamonThread : 6
//		5 초
//		4 초
//		3 초
//		DeamonThread : 7
//		2 초
//		1 초


	}

}
