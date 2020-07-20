package game;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class GameSender {

	ObjectOutputStream oos;

	public GameSender(Socket socket) {
		// TODO Auto-generated constructor stub

		try {

			oos = new ObjectOutputStream(socket.getOutputStream());

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	void send(ArrayList<Puyo> puyos) {

		try {
			oos.writeObject(puyos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
