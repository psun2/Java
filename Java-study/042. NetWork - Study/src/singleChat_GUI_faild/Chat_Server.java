package singleChat_GUI_faild;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

	int port;
	ServerSocket serverSocket;

	public Server() {
		// TODO Auto-generated constructor stub

		init();
	}

	void init() {
		// 초기화
		this.port = 7777;
		try {
			this.serverSocket = new ServerSocket(port);
			System.out.println("서버를 가동 합니다.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		accept();
	}

	void accept() {

		try {

			while (true) {

				// 클라이언트 대기
				Socket client = this.serverSocket.accept();

				System.out.println(
						client.getLocalAddress() + " => [클라이언트 접속]" + " : " + Thread.currentThread().getName());

//				new Client().new Receive(client).start();
				new Receive(client).start();

//				new Client().new Send(client).start();
//				new Send(client).start();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block

			try {
				System.out.println("예외 발생으로 서버를 종료 합니다.");
				serverSocket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}

public class Chat_Server {

	public static void main(String[] args) {
		new Server(); // 서버 가동
	}

}
