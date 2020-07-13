package Teacher_MulChat;

import java.io.DataInputStream;
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

		System.out.println("Dos : " + dos.toString());
		System.out.println(dos.toString().toString());

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

class TCPSingleReceiver extends Thread {
	DataInputStream dis;

	public TCPSingleReceiver(Socket socket) {
		// TODO Auto-generated constructor stub
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println(dis.toString());
		System.out.println(dis.toString().toString());

		while (dis != null) {
			try {
				System.out.println(dis.readUTF());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class TCPSingleChatClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Socket socket = new Socket("192.168.0.9", 7777);

			System.out.println("서버 연결 성공");

			new TCPSingleSender(socket).start();
			new TCPSingleReceiver(socket).start();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
