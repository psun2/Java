package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

	ServerSocket serverSocket;
	ObjectRecive re;
	ObjectSend se;

	public Server() {
		// TODO Auto-generated constructor stub

		try {
			serverSocket = new ServerSocket(7777);

			System.out.println("클라이언트 접속 대기");
			
			re = new ObjectRecive(socket)
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {

			try {

				Socket client = serverSocket.accept();
				System.out.println("클라이언트 접속");

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		new Server().start();
		;
	}

}
