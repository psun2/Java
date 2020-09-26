package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Send__fail {

	ObjectOutputStream oos;
//	OutputStream os;
	Socket socket;

	public Send__fail(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;

		init();

	}

	void init() {
		try {
			this.oos = new ObjectOutputStream(socket.getOutputStream());
//			this.os = socket.getOutputStream();
		} catch (Exception e) {
			// TODO: handle exception
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

	void send(String message) {
//		this.os.write(b, off, len);
		try {
			this.oos.writeObject(message);
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

}
