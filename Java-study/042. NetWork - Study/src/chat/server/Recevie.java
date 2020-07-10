package chat.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Recevie extends Thread {

	InputStream in;
	OutputStream out;

	public Recevie(Socket socket) {
		// TODO Auto-generated constructor stub
		try {
			this.in = socket.getInputStream();
			this.out = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		byte[] buffer = new byte[1024];
		try {
			int length = in.read(buffer);
			String message = new String(buffer, 0, length, "UTF-8");

			byte[] buffre2 = message.getBytes();
			out.write(buffre2);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
