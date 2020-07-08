package theory.client;

import java.net.InetAddress;
import java.net.Socket;

class ClientSocket {

	// 클라이언트 - Socket

	Socket socket;

	public ClientSocket() {
		// TODO Auto-generated constructor stub
		init();
	}

	void init() {

		try {
			String host;
			host = InetAddress.getLocalHost().getHostAddress();
			System.out.println(host);
			// Socket(String host, int port)
			this.socket = new Socket(host, 7777);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ClientSocket();
	}

}
