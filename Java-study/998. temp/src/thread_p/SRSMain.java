package thread_p;

class SRS extends Thread {

	public SRS(String name) {
		super(name);
	}

	@Override
	public void run() {
		while (true) {
			try {
//				System.out.println(super.getName());
				System.out.println(getName());
				sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

public class SRSMain {

	public static void main(String[] args) throws Exception {

		// 이런 기능이 있다. 권장사항 X 과제는 다른방법으로 오류가 심함

		SRS s1 = new SRS("*");
		SRS s2 = new SRS("**");
		SRS s3 = new SRS("***");

		s1.start();
		s2.start();
		s3.start();

		Thread.sleep(2000);
		s1.suspend(); // 일시정지 상태 - 완전 정지가 아님
		Thread.sleep(2000);
		s2.suspend(); // 일시정지 상태 - 완전 정지가 아님
		Thread.sleep(2000);
		s3.suspend(); // 일시정지 상태 - 완전 정지가 아님

		Thread.sleep(2000);
		s1.resume(); // 다시 시작
		Thread.sleep(2000);
		s2.resume();
		Thread.sleep(2000);
		s3.resume();

		Thread.sleep(2000);
		s1.stop(); // 완전 정지
		Thread.sleep(2000);
		s2.stop();
		Thread.sleep(2000);
		System.out.println(Thread.activeCount()); // 2
		Thread.sleep(2000);
		s3.stop();

		System.out.println("끝후 다시 시작");

//		s1.start(); // Error stop 후 start 가 되지 않습니다.
		// 다시 살리는법
		s1 = new SRS("*");
		s1.start();

	}

}
