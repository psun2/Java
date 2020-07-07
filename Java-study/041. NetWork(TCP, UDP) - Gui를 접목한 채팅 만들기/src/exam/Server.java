package exam;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	// 클라이언트 명단
	ArrayList<ObjectOutputStream> clients;

	public Server() {
		// TODO Auto-generated constructor stub

		clients = new ArrayList<ObjectOutputStream>();
		ServerSocket server = null;

		try {

			server = new ServerSocket(7777);
			System.out.println("서버 시작");

			while (true) {
				Socket client = server.accept();
				new Reciever(client).start();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				server.close();
				System.out.println("서버 종료");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	void sendToAll(String msg) {

		for (ObjectOutputStream obj : clients) { // 모두가 볼 수 있게 말을 전달 해야 함
			try {
				obj.writeUTF(msg);
				obj.flush();
				obj.reset();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	class Reciever extends Thread {

		String name;
		ObjectOutputStream oos;
		ObjectInputStream ois;

		public Reciever(Socket socket) {
			// TODO Auto-generated constructor stub
			name = "[" + socket.getLocalSocketAddress() + "]";
			System.out.println(name); // [/192.168.0.9:7777]
			name = "[" + socket.getInetAddress() + "]";
			System.out.println(name); // [/192.168.0.9]

			try {
				
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void run() {

			try {

				sendToAll(name + " 입장");

				clients.add(oos);

				sendToAll("접속자 수 : " + clients.size());

				while (ois != null) {

					// sendToAll(name + ois.readUTF());
					sendToAll(ois.readUTF());

				}

			} catch (Exception e) {
				// TODO: handle exception
			} finally {

				clients.remove(oos); // 입장과 퇴장시 add 와 remove의 위치가 중요

				sendToAll(name + " 퇴장");

				sendToAll("접속자 수 : " + clients.size());
			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Server();
	}
}
