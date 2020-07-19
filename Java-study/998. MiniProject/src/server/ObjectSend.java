package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjectSend {

	ObjectOutputStream oos;
	Socket socket;

	public ObjectSend(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		init();
	}

	void init() {

		try {

			oos = new ObjectOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				if (oos != null)
					oos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	void sender(Object obj) {

		try {
			oos.writeObject(obj);
			System.out.println("전송 성공");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("전송 실패");
		}

	}

}
