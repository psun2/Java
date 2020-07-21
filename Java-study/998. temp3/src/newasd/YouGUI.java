package newasd;

import java.net.Socket;

import javax.swing.JPanel;

public class YouGUI extends JPanel {

	Socket socket;
	Game game;

	public YouGUI(Socket socket, Game game) {
		// TODO Auto-generated constructor stub

		this.socket = socket;
		this.game = game;
	}

}
