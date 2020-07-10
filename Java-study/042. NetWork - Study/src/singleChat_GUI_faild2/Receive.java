package singleChat_GUI_faild2;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import javax.swing.JTextArea;

public class Receive extends Thread {

	InputStream in;
	String message;
	JTextArea textView;

	public Receive(Socket socket, JTextArea textView) {
		// TODO Auto-generated constructor stub

		System.out.println(socket);

		try {
			in = socket.getInputStream();
			this.textView = textView;
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
				this.message = new String(buffer, 0, length, "UTF-8");
				this.textView.append(message);
				this.textView.setCaretPosition(textView.getDocument().getLength());
				System.out.println(message);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
