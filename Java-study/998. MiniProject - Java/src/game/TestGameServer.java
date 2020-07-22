package game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestGameServer {
	// public class TestServer extends Thread {

	ServerSocket serverSocket;
	final int port = 7777;
	Socket client;
	ExecutorService threadPool = Executors.newCachedThreadPool();
	ObjectOutputStream oos;
	ObjectInputStream ois;
	MeGameInfo obj;

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

	void receive() { // 클라이언트가 보내는 오브젝트를 읽어 드림

		System.out.println("---서버 [receive] 가동---");

		try {
			this.ois = new ObjectInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (client != null || ois != null) {
				try {
					client.close();
					ois.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}

		}

		System.out.println("ois 초기화 완료");

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) {

					try {

						if (ois != null) {

							try {
								TestGameServer.this.obj = (MeGameInfo) ois.readObject();
								System.out.println("유저 정보 읽기 성공");
								System.out.println("receive : " + TestGameServer.this.obj);
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								if (ois != null) {
									try {
										ois.close();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										// e1.printStackTrace();
										System.out.println("유저 퇴장으로 ois 닫침");
									}
								}
							}

						}

						Thread.sleep(1000);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						// e1.printStackTrace();

						if (ois != null) {
							try {
								ois.close();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								// e1.printStackTrace();
								System.out.println("유저 퇴장으로 ois 닫침");
							}
						}
					}

				}

			}
		};

		this.threadPool.submit(thread);

	}

	void send() { // 클라이언트에게 읽어드린 오브젝트를 써줌

		System.out.println("---서버 [send] 가동---");

		try {
			this.oos = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (client != null || oos != null) {
				try {
					client.close();
					oos.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		}

		System.out.println("oos 초기화 완료");

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) {

					try {

						if (oos != null && obj != null) {
							try {
								oos.writeObject(obj);
								System.out.println("유저 정보 쓰기 성공");
								System.out.println("send : " + obj);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								if (oos != null) {
									try {
										oos.close();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										// e1.printStackTrace();
										System.out.println("유저 퇴장으로 oos 닫침");
									}
								}
							}
						}

						Thread.sleep(1000);

					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						// e1.printStackTrace();
						if (oos != null) {
							try {
								oos.close();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								// e1.printStackTrace();
								System.out.println("유저 퇴장으로 oos 닫침");
							}
						}
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
