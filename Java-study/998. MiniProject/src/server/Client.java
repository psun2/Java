package server;

import java.net.InetAddress;
import java.net.Socket;

public class Client {

	Socket socket;
	int port;
	String host;

	public Client() {
		// TODO Auto-generated constructor stub
		init();

	}

	void init() {
		try {

			this.port = 7777;
			this.host = InetAddress.getLocalHost().getHostAddress();
			Socket socket = new Socket(host, port);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		new Client();

	}

}
