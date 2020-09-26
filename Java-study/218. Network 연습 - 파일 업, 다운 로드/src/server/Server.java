package server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Server {

//	private static final int PORT = 9999;
	private ServerSocket server;

//	private Vector<Client> clients; 
	static ArrayList<Client> clients = new ArrayList<Client>();;
//	- Vector : 자동 동기화 보장
//	- ArrayList : 동기화를 보장하지 않음
//
//	Vector : ArrayList에 동기화가 보장되도록 최적화한 클래스
//	ArrayList : 배열에 동적 메모리 증가 기능을 구현한 클래스

	private String host;
	private int port;

	public Server(String host, int port) {
		// TODO Auto-generated constructor stub
		this.host = host;
		this.port = port;
		init();
	}

	void init() {
		try {

			this.server = new ServerSocket(port);
//			InetAddress local = InetAddress.getLocalHost();
//			System.out.println(local); // DESKTOP-EJRGSL9/192.168.0.9

//			String host = local.getHostAddress();
//			System.out.println(host); // 192.168.0.9

//			host = "127.0.0.1";

//			System.out.println(new InetSocketAddress(host, PORT)); // /127.0.0.1:9999

//			server.bind(new InetSocketAddress(host, PORT));
//			System.out.println(server); // ServerSocket[addr=/127.0.0.1,localport=9999]

//			server.bind(new InetSocketAddress(host, port));

//			this.clients = new Vector<Client>();
//			this.clients = new ArrayList<Client>();
			Collections.synchronizedList(clients);
			System.out.println(clients.toString());
		} catch (Exception e) {
			e.printStackTrace();
			stopServer();
		}
	}

	void startServer() {

		System.out.println("[서버 가동]");

		try {

			while (true) {

				System.out.println("[클라이언트 대기중.....]");

				Socket client = server.accept();

				System.out.println("[" + client.getInetAddress().getHostAddress() + " 접속]");

				clients.add(new Client(client));

				System.out.println(clients.toString());

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			stopServer();
		}

	}

	void stopServer() {
		try {
			if (server != null && !server.isClosed()) {
				server.close();
				System.out.println("[서버 종료]");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Server("127.0.0.1", 9999).startServer();

	}
}
