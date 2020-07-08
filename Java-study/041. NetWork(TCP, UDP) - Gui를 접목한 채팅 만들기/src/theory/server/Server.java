package theory.server;

import java.net.ServerSocket;
import java.net.Socket;

class _ServerSocket {

	// 서버 - ServerSocket => accept => Socket

	ServerSocket serverSocket;

	public _ServerSocket() {
		// TODO Auto-generated constructor stub

		init();
	}

	void init() {

		try {

			int port = 7777;
			this.serverSocket = new ServerSocket(port);
			System.out.println("[클라이언트 연결 대기중 ....]");

			// 클라이언트가 접속 될때까지 대기 합니다.

			Socket client = serverSocket.accept();
			System.out.println("[클라이언트 접속] => " + client.getLocalAddress() + " : " + Thread.currentThread().getName());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				serverSocket.close(); // 예외 발생시 서버를 닫아 줍니다.
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}

}

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new _ServerSocket();
	}

}
