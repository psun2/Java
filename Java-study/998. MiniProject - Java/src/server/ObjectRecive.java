package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ObjectRecive {

	ObjectInputStream ois;
	Socket socket;

	public ObjectRecive(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		init();
	}

	void init() {

		try {

			ois = new ObjectInputStream(socket.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				if (ois != null)
					ois.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	void reciver() {

		try {
			ois.readObject();
			System.out.println("읽기 성공");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("읽기 실패");
		}

	}

}
