package mulGameTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestGameServer {

	ServerSocket serverSocket;
	final int port = 7777;
	Socket client;
	ExecutorService threadPool = Executors.newCachedThreadPool();
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Object obj;

	public TestGameServer() {
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

	void accept() { // 클라이언트의 접속을 받음

		while (true) {

			try {
				this.client = serverSocket.accept();
				System.out.println("[" + client.getLocalAddress() + "] 접속 완료");
				receive();
				send();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				if (client != null) {
					try {
						client.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		}

	}

	void receive() { // 유저로 부터 정보를 읽어옴

		System.out.println("receive 진입");

		try {
			this.ois = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ois 초기화 에러 발생 ois를 닫습니다.");
			try {
				ois.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.println("ois 초기화 완료");

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {

					while (true) {
						if (ois != null) {
							obj = ois.readObject();
							System.out.println("유저로 부터 정보를 읽어 오는데 성공!!!!");
							System.out.println(obj);
							Thread.sleep(1000); // 1초에 한번씩 읽습니다.
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println("ois read 실패 ois 를 닫습니다.");
					try {
						ois.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		};
		this.threadPool.submit(thread);
	}

	void send() { // 유저로 부터 받은 정보를 다시 유저에게 보내는 메소드

		System.out.println("send 진입");

		try {
			this.oos = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ois 초기화 에러 발생 ois를 닫습니다.");
			try {
				oos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		System.out.println("oos 초기화 완료");

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {

					while (true) {
//						System.out.println(!((MeGameInfo) obj).endGame); // 지금 이게 null
//						null 이 아닐때 !!
//						if (oos != null && !((MeGameInfo) obj).endGame) {
						if (oos != null) {
							oos.writeObject(obj);
							oos.flush();
							oos.reset();
							System.out.println("유저로 객체 전달 성공!!!!!");
							System.out.println(obj);
							Thread.sleep(1000); // 1초에 한번씩 씁니다.
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					System.out.println("oos writh 실패 oos 를 닫습니다.");
					try {
						oos.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		};
		this.threadPool.submit(thread);
	}

	public static void main(String[] args) {

		new TestGameServer();

	}

}
