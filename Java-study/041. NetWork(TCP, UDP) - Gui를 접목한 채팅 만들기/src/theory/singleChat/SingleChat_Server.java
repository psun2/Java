package theory.singleChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Server {

	private ServerSocket serverSocket;
	private int port;
	Socket client;

	public Server() {
		// TODO Auto-generated constructor stub
		try {

			this.port = 7777;
			this.serverSocket = new ServerSocket(port);
			System.out.println("[서버 가동 - 클라이언트 대기중]");

			while (true) {

				client = serverSocket.accept();
				System.out.println(
						client.getLocalAddress() + " => [클라이언트 접속]" + " : " + Thread.currentThread().getName());

				new Receive(client).start();
				new Send(client).start();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
					System.out.println("서버를 종료 합니다.");
				}

				e.printStackTrace();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} finally {

			try {

				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
					System.out.println("서버를 종료 합니다.");
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}

public class SingleChat_Server {

	public static void main(String[] args) {
		new Server();
	}

}
