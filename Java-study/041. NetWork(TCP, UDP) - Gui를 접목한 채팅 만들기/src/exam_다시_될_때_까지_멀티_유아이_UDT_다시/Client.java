package exam_다시_될_때_까지_멀티_유아이_UDT_다시;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class ClientSocket {

	UI ui;

	public ClientSocket() {
		// TODO Auto-generated constructor stub

		try {

			InetAddress iAddr = InetAddress.getLocalHost();
			String addr = iAddr.getHostAddress();
			// System.out.println(addr);

			Socket socket = new Socket(addr, 7777);
			System.out.println("클라이언트 서버 연결 성공");
			ui = new UI(); // 서버에 연결이 성공되면 채팅화면 오픈

			new Receive(socket).start();
			new Send(socket).start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class Receive extends Thread { // 받아서 읽는 역할

		ObjectInputStream ois;

		public Receive(Socket socket) {
			// TODO Auto-generated constructor stub

			try {

				ois = new ObjectInputStream(socket.getInputStream());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			try {
				System.out.println(ois != null);

				while (ois != null) {

					ois.readUTF();

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				try {
					ois.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

	class Send extends Thread {

		String name;
		ObjectOutputStream oos;

		public Send(Socket socket) {
			// TODO Auto-generated constructor stub

			try {

				this.name = "[" + InetAddress.getLocalHost() + "]";

				oos = new ObjectOutputStream(socket.getOutputStream());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Scanner sc = new Scanner(System.in);
			try {

				while (oos != null) {

					System.out.println(sc.nextLine());

					if (ui.chk) {

						oos.writeUTF(name + ui.msg);

						oos.flush(); // 자신의 메모리 갱신
						oos.reset();

						ui.chk = false;
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				// TODO: handle finally clause
				try {

					oos.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}
}

public class Client {

	public static void main(String[] args) {

		new ClientSocket();

	}

}
