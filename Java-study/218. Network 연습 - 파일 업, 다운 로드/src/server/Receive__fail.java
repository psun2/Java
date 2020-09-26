package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Receive__fail {

	Socket socket;
	InputStream is;
	Send__fail send;

	public Receive__fail(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;

		init();
	}

	void init() {
		try {
			this.is = socket.getInputStream();
			this.send = new Send__fail(this.socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (socket != null && !socket.isClosed()) {
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public void receive() {

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

						System.out.println(message);

						send.send(message);

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			};

		};
		thread.start();
	}

}
