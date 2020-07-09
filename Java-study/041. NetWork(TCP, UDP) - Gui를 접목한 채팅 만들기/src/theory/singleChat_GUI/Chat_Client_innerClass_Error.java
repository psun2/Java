package theory.singleChat_GUI;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

// inner class 여서 계속 에러가남 이유는 잘 모르겠음....

 outer 가 Socket 을 초기화 시키는데 서버에서 생성시
 new Client.new Receive() 되어 클라이언트를 계속 초기화 합니다.
 그래서 에러의 원인으로 보입니다.

class Client_Error {

	private Socket socket;
	private String host;

	public Client_Error() {
		// TODO Auto-generated constructor stub
		init();
	}

	void init() {

		try {
			this.host = InetAddress.getLocalHost().getHostAddress();
			int port = 7777;
			System.out.println(host + ", " + port);
			this.socket = new Socket(host, port); // 종료하는 순간 소켓은 자동으로 닫힘
			new Receive(socket).start();
			new Send(socket).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class Receive_Error extends Thread { // 서버로 부터 읽어오기

		private Socket socket;
		private InputStream is;

		public Receive_Error(Socket socket) {
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
	}

	class Send_Error extends Thread { // 보내기

		private Socket socket;
		private OutputStream os;

		public Send_Error(Socket socket) {
			super();
			this.socket = socket;
			try {
				this.os = socket.getOutputStream();
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

					String message = "[" + host + "] " + sc.nextLine();
					byte[] buffer = message.getBytes("UTF-8");
					os.write(buffer);
					os.flush();
				}

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				sc.close();

				if (os != null)
					try {
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		}

	}

}

public class Chat_Client_innerClass_Error {

	Client client;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client();
	}

}
