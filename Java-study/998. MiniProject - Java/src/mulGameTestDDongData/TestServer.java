package mulGameTestDDongData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestServer {
	// public class TestServer extends Thread {

	ServerSocket serverSocket;
	final int port = 7777;
	Socket client;
	ExecutorService threadPool = Executors.newCachedThreadPool();

	public TestServer() {
		// TODO Auto-generated constructor stub

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("시스템 가동 준비 완료");
			accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void accept() {

		while (true) {

			try {
				this.client = serverSocket.accept();
				System.out.println("[" + client.getLocalAddress() + "] 접속 완료");
				// testMethod();

				testThread();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	void testMethod() { // 다른 접속자가 와도 쌩깐다 쓰레드가 아니기 때문에 메인문이 정지

		while (true) {

			try {
				System.out.println("여기에서 쓰레드 일때 와 아닐때를 실험 : 나는 일반 메소드");
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

	void testThread() { // 다른 접속자가 오면 쓰레드로 돌기때문에 다른 접속자를 받아 들일 수 있음.

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) {

					try {
						System.out.println("여기에서 쓰레드 일때 와 아닐때를 실험 : 나는 쓰레드");
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}
		};

		this.threadPool.submit(thread);

	}

	public static void main(String[] args) {

		new TestServer();

	}

}
