package game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestServer {
	// public class TestServer extends Thread {

	ServerSocket server;
	Socket client;
	public static ExecutorService threadPool = Executors.newCachedThreadPool();
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Object obj;

	public TestServer() {
		// TODO Auto-generated constructor stub

		try {
			server = new ServerSocket(7777);
			System.out.println("시스템 가동 준비완료!");
			accept();

		} catch (Exception e) {
			// TODO: handle exception

			if (server != null && !server.isClosed()) {
				try {
					server.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}

	void accept() {

		try {
			while (true) {
				this.client = server.accept();
				receve();
				send();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			if (client != null && !client.isClosed())
				try {
					client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}

		System.out.println(client.getLocalAddress() + " 이새끼 접속함");

	}

	void receve() {

		System.out.println("receve Start");

		try {
			ois = new ObjectInputStream(client.getInputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					while (true) {
						if (ois != null) {
							// ois.readBoolean();
							obj = ois.readObject();
							// System.out.println("유저 정보 읽는 중"); // 읽는 거 확인
						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		};

		this.threadPool.submit(thread);

		System.out.println("receve 종료");

	}

	void send() {

		System.out.println("send Start");

		try {
			oos = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					while (true) {

						if (oos != null) {

							oos.writeObject(obj);
							oos.flush();
							// oos.reset();
							// System.out.println("유저에게 정보 전송"); // 확인

						}

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};

		this.threadPool.submit(thread);

		System.out.println("send 종료");

	}

	public static void main(String[] args) {

		new TestServer();

	}

}
