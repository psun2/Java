package theory.singleChat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

class Client {

	Socket socket;

	public Client() {
		// TODO Auto-generated constructor stub
		try {
			String host = InetAddress.getLocalHost().getHostAddress();
			this.socket = new Socket(host, 7777);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new Receive(socket).start();
		new Send(socket).start();
	}

}

class Receive extends Thread {

//	ObjectInputStream ois;
	InputStream is;
	Socket socket;

	public Receive(Socket socket) {
		// TODO Auto-generated constructor stub

		this.socket = socket;
		try {
//			this.ois = new ObjectInputStream(socket.getInputStream());
			this.is = socket.getInputStream();
		} catch (Exception e) {
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
				// 원인: read 시 상대방 socket close 된 경우
//				java.net.SocketException: Connection reset
//				at java.net.SocketInputStream.read(Unknown Source)
//				at java.net.SocketInputStream.read(Unknown Source)
//				at java.net.SocketInputStream.read(Unknown Source)
//				at theory.singleChat.Receive.run(SingleChat_Client.java:56)

				String message = new String(buffer, 0, length, "UTF-8");

				System.out.println(message);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			System.out.println("원인: read 시 상대방 socket close 된 경우");
			System.out.println("해결방법 없음");
			System.out.println("정상적인 에러");
			e.printStackTrace();
			
		} finally {
			try {
				is.close();
//				socket.close(); // 소켓은 프로그램이 종료 될때 자동으로 닫힙니다. 필요 없음 서버만 신경
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

class Send extends Thread {

//	ObjectOutputStream oos;
	OutputStream os;
	Socket socket;

	public Send(Socket socket) {
		// TODO Auto-generated constructor stub

		this.socket = socket;
		try {
//			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.os = socket.getOutputStream();
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

			while (true) {

				String message = "[" + socket.getLocalAddress().getHostAddress() + "] => ";
				message += sc.nextLine();
				byte[] buffer = message.getBytes("UTF-8");
				os.write(buffer);
				os.flush();
//			this.oos.writeUTF(str);

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				sc.close();
				os.close();
//				socket.close(); // 소켓은 프로그램이 종료 될때 자동으로 닫힙니다. 필요 없음 서버만 신경
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}

public class SingleChat_Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Client();
	}

}
