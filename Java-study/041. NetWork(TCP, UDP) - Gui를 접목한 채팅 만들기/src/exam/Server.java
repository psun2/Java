package exam;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

class ChatServer {

	ArrayList<ObjectOutputStream> clients;

	public ChatServer() {
		// TODO Auto-generated constructor stub

		ServerSocket server = null;

		try {

			server = new ServerSocket(7777);
			System.out.println("서버 시작");

			// 서버가 실행되면 클라이언트들을 담을 수 있는 ArrayList 초기화
			clients = new ArrayList<ObjectOutputStream>();
			Collections.synchronizedList(clients);

			while (true) {

				Socket client = server.accept(); // 서버에 접근 하는 자를 받아옴...

				new Receiver(client).start();

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	class Receiver extends Thread { // 서버는 리시버를 가지고 있다.

		String name;
		ObjectOutputStream oos;
		ObjectInputStream ois;

		public Receiver(Socket client) {
			// TODO Auto-generated constructor stub

			this.name = "[" + client.getLocalAddress() + "]";

			try {

				oos = new ObjectOutputStream(client.getOutputStream());
				ois = new ObjectInputStream(client.getInputStream());

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
					// 서버에 접근하는 자가 생길시 여기에 멈춰서 반복합니다.

					System.out.println("asdasd" + ois.readUTF());
					sendToAll(ois.readUTF());

				}

			} catch (Exception e) {
				// TODO: handle exception
			} finally {

				clients.remove(oos);

				sendToAll(name + " 퇴장");

				sendToAll("접속자 수 : " + clients.size());

			}

		}

		void sendToAll(String msg) {

			for (ObjectOutputStream client : clients) {
				try {
					client.writeUTF(msg);
					client.flush();
					client.reset();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChatServer();
	}

}
