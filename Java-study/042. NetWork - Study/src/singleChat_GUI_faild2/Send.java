package singleChat_GUI_faild2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

// GUI 를 접목하면 send 는 enter를 실행 할때만 하면 됩니다.
public class Send extends Thread {

	OutputStream out;

	public Send(Socket socket) {
		// TODO Auto-generated constructor stub

		try {
			this.out = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		try {
			while (true) {

				String message = sc.nextLine();
				byte[] buffer = message.getBytes();
				out.write(buffer);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
