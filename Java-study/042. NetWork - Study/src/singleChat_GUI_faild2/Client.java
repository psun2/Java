package singleChat_GUI_faild2;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	int port;
	String url;
	Socket socket;

	public Client(String url, String port) {
		// TODO Auto-generated constructor stub
		this.url = url;
		this.port = Integer.parseInt(port);

		try {
			this.socket = new Socket(this.url, this.port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
