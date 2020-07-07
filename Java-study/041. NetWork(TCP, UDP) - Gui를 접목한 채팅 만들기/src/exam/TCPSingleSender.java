package exam;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class TCPSingleSender extends Thread {

	DataOutputStream dos;
	String name;

	public TCPSingleSender(Socket socket) {
		// TODO Auto-generated constructor stub
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			name = "[" + socket.getLocalAddress() + "]";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		while (dos != null) {
			try {
				dos.writeUTF(name + sc.nextLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}