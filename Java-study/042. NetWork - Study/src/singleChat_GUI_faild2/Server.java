package singleChat_GUI_faild2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private int port = 7777;
	private ServerSocket serverSocket;

	public Server() {
		// TODO Auto-generated constructor stub

		try {

			this.serverSocket = new ServerSocket(port);
			System.out.println("서버 시작");

			while (true) {

				Socket client = this.serverSocket.accept();
				System.out.println(client.getLocalAddress() + " : 접속");

				// recevie => 클라이언트의 대화를 받아옵니다.
				//new ServerRecevie(client).start();
				// send => 클라이언트에게 대화를 날립니다.
				// new Send(client).start();

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				this.serverSocket.close();
				System.out.println("Error 서버 종료");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {

			if (this.serverSocket != null && !this.serverSocket.isClosed()) {
				try {
					this.serverSocket.close();
					System.out.println("서버 종료");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
