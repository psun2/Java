package game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServer extends Thread {

	ServerSocket server = null;

	public TestServer() {
		// TODO Auto-generated constructor stub

		try {
			server = new ServerSocket(7777);
			System.out.println("시스템 가동 준비완료!");
			start();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {

			while (true) {

				Socket client = server.accept();

				System.out.println(client.getLocalAddress() + " 이새끼 접속함");

			}
		} catch (Exception e) {

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

	public static void main(String[] args) {

		new TestServer();

	}

}
