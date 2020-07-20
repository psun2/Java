package thread_p;

class DaemonSave extends Thread {

	public DaemonSave() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		int i = 1;
		while (true) {

			System.out.println("자동저장\t" + i);
			long start = System.currentTimeMillis() / 1000;

			try {
				sleep(3000);
			} catch (Exception e) {
				e.getStackTrace();
			}

			i++;
			long end = System.currentTimeMillis() / 1000;

			System.out.println((end - start) + " 초 걸림");
		}

	}

}

public class DaemonThreadMain {

	public static void main(String[] args) throws Exception {

//		데몬쓰레드 : 주 쓰레드에 상주해 있다가, 주 쓰레드가 끝나면 보조 쓰레드가 끝남.

		DaemonSave save = new DaemonSave();

		save.setDaemon(true);

		save.start();

//		save.setDaemon(true); // Error start 하기 전에 Daemon 을 넣어줄수 있도록 합니다.

		new MulTimer().start();

		for (int i = 0; i < 10; i++) { // 이게 끝나면 DaemonSave 가 같이 끝나고 싶다.
			Thread.sleep(1000);
			System.out.println(i + "\t저장");
		}

		// main 에 귀속 된것이 아니라, 무엇이라도 한개가 사라져도 다른 쓰레드가 남으면 Daemon 은 살아 있습니다.
		// 마지막 Threa 가 끝나야 같이 끝이 납니다.

	}

}
