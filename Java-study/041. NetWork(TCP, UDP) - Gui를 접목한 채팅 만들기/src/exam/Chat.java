package exam;

import java.net.Inet4Address;
import java.net.Socket;

import javax.swing.JFrame;

class ChatView extends JFrame {

}

public class Chat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			System.out.println(Inet4Address.getLocalHost());
			// 192.168.0.9

			Socket socket = new Socket("192.168.0.9", 7777);
			System.out.println("클라이언트 서버 연결 성공");

			new TCPSingleReceiver(socket).start();
			new TCPSingleSender(socket).start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
