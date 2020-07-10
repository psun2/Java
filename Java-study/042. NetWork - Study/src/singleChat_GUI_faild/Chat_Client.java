package singleChat_GUI_faild;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

// inner class 여서 계속 에러가남 이유는 잘 모르겠음....

class Client {

	private Socket socket;
	private String host;
	private UI ui;

	public Client() {
		// TODO Auto-generated constructor stub
		init();
	}

	void init() {

		try {
			this.host = InetAddress.getLocalHost().getHostAddress();
			int port = 7777;
			System.out.println(host + ", " + port);
			this.socket = new Socket(host, port); // 종료하는 순간 소켓은 자동으로 닫힘
			this.ui = new UI(socket);
			Receive receive = new Receive(socket);
//			Send send = new Send(socket);

			receive.addUI(ui);
//			send.addUI(ui);
			receive.start();
//			send.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class Receive extends Thread { // 서버로 부터 읽어오기

	private Socket socket;
	private InputStream is;
	private UI ui;

	public Receive(Socket socket) {
		super();
		this.socket = socket;
		try {
			this.is = socket.getInputStream();

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
				int length = is.read(buffer);
				String message = new String(buffer, 0, length, "UTF-8");

				ui.ta.append(message);
				ui.ta.setCaretPosition(ui.ta.getDocument().getLength());

				System.out.println(message);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}

	}

	void addUI(UI ui) {
		this.ui = ui;
	}
}

//class Send extends Thread { // 보내기
//
//	private Socket socket;
//	private OutputStream os;
//	private UI ui;
//
//	public Send(Socket socket) {
//		super();
//		this.socket = socket;
//		try {
//			this.os = socket.getOutputStream();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//
//		Scanner sc = new Scanner(System.in);
//
//		try {
//
//			while (true) {
//
//				String message = "[" + socket.getLocalAddress() + "] " + sc.nextLine();
//				byte[] buffer = message.getBytes("UTF-8");
//				os.write(buffer);
//				os.flush();
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			sc.close();
//
//			if (os != null)
//				try {
//					os.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//		}
//	}
//
//	void addUI(UI ui) {
//		this.ui = ui;
//	}
//
//}

public class Chat_Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client();
	}

}
