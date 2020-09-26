package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

	Socket socket;
	InputStream is;
	ObjectOutputStream oos;

	public Client(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		init();
	}

	void init() {
		receive();
	}

	void receive() {

		try {
			this.is = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stopClient();
		}

		Thread thread = new Thread() {

			public void run() {

				try {

					while (is != null) {

						byte[] buffer = new byte[1024];
						int leng = is.read(buffer);
						// read 는 원래 한글자를 int 형으로 반환하는 수를 반환합니다.
						// 하지만 read의 인자로 배열이 들어 갔을때,
						// read 는 배열의 길이를 반환 합니다.

						String message = new String(buffer, 0, leng);

						System.out.println("읽어 온 메시지 : " + message);

						for (Client client : Server.clients) {

							client.send(message);
						}

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		};
		thread.start();
	}

	void send(String message) {

		System.out.println("메시지 전송 완료");

		try {
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(message);
			oos.flush(); // 쓰기의 끝을 알림
			oos.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			stopClient();
		}

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

}
