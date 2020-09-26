package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	Socket socket;
	ObjectInputStream ois;
//	ObjectOutputStream oos;
	OutputStream os; // 생각 해보니 서버에서 InputStream 으로 받네 ...ㅠㅠ

	public Client(String host, int port) {
		// TODO Auto-generated constructor stub
		try {
			this.socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stopClient();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stopClient();
		}

		send();
		receive();
	}

	void receive() {

		try {

			ois = new ObjectInputStream(socket.getInputStream());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			stopClient();
		}

		Thread thread = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub

				try {

					while (ois != null) {

						String message = (String) ois.readObject();

						System.out.println("[서버로부터 받은 메시지 ] " + message);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					stopClient();
				}

			};
		};
		thread.start();
	}

	void send() {

		Scanner scanner = new Scanner(System.in);

		try {

//			oos = new ObjectOutputStream(socket.getOutputStream());
			os = socket.getOutputStream();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			stopClient();
		}

		Thread thread = new Thread() {

			public void run() {

				try {

					while (true) {

						String message = scanner.nextLine();
						byte[] buffer = message.getBytes();
						os.write(buffer);
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					stopClient();
				}

			};

		};
		thread.start();
	}

	void stopClient() {

		if (socket != null && !socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
