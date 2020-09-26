package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	Socket socket;

	public Client(String host, int port) {
		// TODO Auto-generated constructor stub
		try {
			this.socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		try {
			String a = InetAddress.getLocalHost().getHostAddress();
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			// 192.168.0.9
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Client("192.168.0.9", 9999);
	}

}
