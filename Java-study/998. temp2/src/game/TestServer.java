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
	ExecutorService threadPool = Executors.newCachedThreadPool();
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

		try {
			ois = new ObjectInputStream(client.getInputStream());
			oos = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println("receve Start");

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					while (true) { // 서버에서 읽어오는게 업데이트가 안됨

						if (ois != null) {
							send(ois.readObject());
							// System.out.println("여긴 서버 : " + ois.readObject());
							// System.out.println(obj); // 서버에서 읽어오다가 죽어버리네 ?
							// obj = ois.readObject();
							// System.out.println("유저 정보 읽는 중"); // 읽는 거 확인
						}
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					System.out.println("서버 오아이 에스 에러");
				} finally {
					try {
						if (ois != null)
							ois.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		};

		this.threadPool.submit(thread);

		System.out.println("receve 종료");

	}

	void send(Object obj) {

		System.out.println("send Start");

		System.out.println("여긴 서버 샌드 : " + obj);

		try {
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("서버 oos 에러");
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("send 종료");

	}

	public static void main(String[] args) {

		new TestServer();

	}

}
