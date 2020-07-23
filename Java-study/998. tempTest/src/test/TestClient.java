package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestClient {

	String host;
	int port;
	Socket socket;
	ExecutorService threadPool;

	ObjectOutputStream oos;
	ObjectInputStream ois;

	TestClass obj;

	public TestClient() {
		// TODO Auto-generated constructor stub
		init();
		thread();
		send();
		receive();
	}

	void init() {

		threadPool = Executors.newCachedThreadPool();

		this.obj = new TestClass();

		try {
			this.host = InetAddress.getLocalHost().getHostAddress();
			this.port = 7777;
			this.socket = new Socket(host, port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("에러");
		}

	}

	void thread() {

		System.out.println("thread 진입");

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					while (true) {
						if (obj != null) {

							obj.second++;
							System.out.println("obj.second : " + obj.second);
							Thread.sleep(1000);
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					System.out.println("obj 생성 에러");
				}

			}
		};
		this.threadPool.submit(thread);
	}

	void receive() {

		System.out.println("receive 진입");

		try {
			this.ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("ois 생성 에러");
		}

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					while (true) {

						if (ois != null && obj != null) {

							obj = (TestClass) ois.readObject();
							System.out.println("정보를 읽어오는데 성공 !!!");
							System.out.println(obj);
							// Thread.sleep(1000);
						}

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					System.out.println("ois read 에러");
				}

			}
		};
		this.threadPool.submit(thread);
	}

	void send() {

		System.out.println("send 진입");

		try {
			this.oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("oos 생성 에러");
		}

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					System.out.println("oos try");

					while (true) {
						if (oos != null && obj != null) {
							System.out.println("oos 조건문 진입");
							oos.writeObject(obj);
							oos.flush();
							oos.reset();
							System.out.println("정보를 쓰는데 성공 !!!");
							System.out.println(obj);
							// Thread.sleep(1000);
						}
					}
				} catch (Exception e) {
					System.out.println("oos catch");

					// TODO Auto-generated catch block
					// e.printStackTrace();
					System.out.println("oos writh 에러");
				}

			}
		};
		this.threadPool.submit(thread);
	}

	public static void main(String[] args) {
		new TestClient();
	}

}
