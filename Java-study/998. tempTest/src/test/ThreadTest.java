package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

	ExecutorService threadPool = Executors.newCachedThreadPool();

	void test() {

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("쓰레드,");
			}
		};
		this.threadPool.submit(thread);
	}

	public static void main(String[] args) {
		new ThreadTest().test();
	}

}
