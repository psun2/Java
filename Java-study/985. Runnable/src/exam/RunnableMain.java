package exam;

class MyRunnable implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
//		run(); // Error
		while (true) {

			try {
				Thread.sleep(1000);

				System.out.println("Runnable");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}

class MyThread extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		while (true) {

			try {
				Thread.sleep(1000);

				System.out.println("Thread");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

public class RunnableMain {

	public static void main(String[] args) {

		// start 안됨 .....
		new MyRunnable().run();
		new MyRunnable().run();
		new MyRunnable().run();
		new MyRunnable().run();

		MyRunnable myRunnable = new MyRunnable();

		System.out.println("asd");

		new MyThread().start();
		new MyThread().start();
		new MyThread().start();
		new MyThread().start();

	}

}
