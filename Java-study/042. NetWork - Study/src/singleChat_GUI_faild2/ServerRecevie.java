package singleChat_GUI_faild2;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ServerRecevie extends Thread {

	InputStream in;

	public ServerRecevie(Socket socket) {
		// TODO Auto-generated constructor stub

		System.out.println(socket);

		try {
			in = socket.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			while (true) {

				byte[] buffer = new byte[1024];
				int length = in.read(buffer);
				System.out.println(length);
				String message = new String(buffer, 0, length, "UTF-8");
				System.out.println("나는 서버" + message);

			}

		} catch (IOException e) {
			System.out.println("서버에러");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
