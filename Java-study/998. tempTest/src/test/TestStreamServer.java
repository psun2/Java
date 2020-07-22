package test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestStreamServer {

	ServerSocket serverSocket;
	final int port = 7777;
	Socket client;
	ExecutorService threadPool = Executors.newCachedThreadPool();
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Object obj;

	public TestStreamServer() {
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
				System.out.println("[" + client.getLocalAddress() + "] 접속 완료 !");
				receive();
				send();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	void receive() {

		try {
			this.ois = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					while (true) {

						if (ois != null) {

							obj = ois.readObject();
							System.out.println("정보를 읽어오는데 성공 !!!");
							System.out.println(obj);
							Thread.sleep(1000);
						}

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		this.threadPool.submit(thread);
	}

	void send() {

		try {
			this.oos = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {
					while (true) {
						if (oos != null && obj != null) {
							oos.writeObject(obj);
							oos.flush();
							oos.reset();
							System.out.println("정보를 쓰는데 성공 !!!");
							System.out.println(obj);
							Thread.sleep(1000);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		};
		this.threadPool.submit(thread);
	}

	public static void main(String[] args) {
		new TestStreamServer();
	}

}
