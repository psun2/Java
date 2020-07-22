package mulGameTest;

import java.net.InetAddress;
import java.net.Socket;

public class TestClient {

	String host;
	int port;
	Socket socket;

	public TestClient() {
		// TODO Auto-generated constructor stub
		init();
	}

	void init() {

		try {
			this.host = InetAddress.getLocalHost().getHostAddress();
			this.port = 7777;
			this.socket = new Socket(host, port);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new TestClient();
	}

}
