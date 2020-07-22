package single;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Main_Server {

	// 게임룸에서 주고받고 게임룸에서 들어오는것은 [object]이다.
	// 로비에서 주고받고
	// 서버는 값을 받아서 전달만 해주면된다.

	// 리시버는 총 4개
	// 로비,게임룸,게임룸의user1,user2

	ArrayList<ObjectOutputStream> dataList;
	ArrayList<ObjectOutputStream> p1List;
	ArrayList<ObjectOutputStream> p2List;
	ArrayList<Object> sendobjct;
	boolean p1chk = false;
	boolean p2chk = false;
	boolean playg = false;

	ObjectOutputStream oos;
	ObjectInputStream ois;
	String user;

	public Main_Server() { // 서버에 접근하는 유저들을알아냄
		try {
			ServerSocket server = new ServerSocket(7777);
			System.out.println(" ________________");
			System.out.println("|                |");
			System.out.println("|  start server  |");
			System.out.println("|                |");
			System.out.println("|________________|");
			System.out.println();
			System.out.println();

			while (true) {
				Socket client = server.accept();
				user = "[" + client.getInetAddress() + "]";
				System.out.println(user + ":" + "서버 입장");

				new Tcp_Server(client).start();

			}

		} catch (IOException e) {
		}

	} // 메인 생성자끝

	public class Tcp_Server extends Thread {
		boolean robichk = false;

		public Tcp_Server(Socket soc) {
			try {

				dataList = new ArrayList<ObjectOutputStream>();
				Collections.synchronizedList(dataList);

				p1List = new ArrayList<ObjectOutputStream>();
				Collections.synchronizedList(p1List);

				p2List = new ArrayList<ObjectOutputStream>();
				Collections.synchronizedList(p2List);

				oos = new ObjectOutputStream(soc.getOutputStream());
				ois = new ObjectInputStream(soc.getInputStream());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// 클라이언트 <-----> 로비 <----> 서버 <----->유저1
		@Override
		public void run() {
			try {

				switch (ois.readUTF()) {
				case "로비":
					robichk = true;
					dataList.add(oos);
					break;

				case "플레이어1":
					p1chk = true;
					p1List.add(oos);
					break;

				case "플레이어2":
					p2chk = true;
					p2List.add(oos);
					break;
				case "게임고":
					playg = true;
					System.out.println("게임시작");
					break;

				}
				while (ois != null) {

					try {

						if (robichk) {
							sendToAll(ois.readObject()); // send메세지에 전달해줌 읽은걸
						} else if (p1chk) {
							testgo(ois.readObject());
						} else if (p2chk) {
							pleayA(ois.readObject());

						}

					} catch (Exception e) {
					}

				}

			} catch (Exception e) {
				System.out.println("데이터타입 맞지않음");

			} finally {
				dataList.remove(oos);
				p1List.remove(oos);
				p2List.remove(oos);
				sendToAll("데이터 빠짐");

			}
		}

		void sendToAll(Object msg) // 데이터를 담아놓고 뺴주자
		{

			if (robichk) {
				System.out.println("서버 데이터수신완료:" + msg);
				try {
					for (ObjectOutputStream dd : dataList) { // 상대방이보낸거 재전송하는거임
						try {
							// sleep(1000);
							dd.writeObject(msg);
							dd.flush();
							dd.reset();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		void testgo(Object msg) {
			if (playg) {
				if (p1chk) {
					System.out.println("게임플레이어로 부터 정보가옵니다:" + msg);
					if (p2chk) {
						for (ObjectOutputStream dd : p2List) { // 상대방이보낸거 재전송하는거임
							try {
								dd.writeObject(msg);
								dd.flush();
								dd.reset();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}

					} else {
						for (ObjectOutputStream dd : p1List) { // 상대방이보낸거 재전송하는거임
							try {
								dd.writeObject(msg);
								dd.flush();
								dd.reset();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}

		void pleayA(Object msg) {
			if (playg) {
				if (p2chk) {
					System.out.println("게임플레이어로 부터 정보가옵니다:" + msg);

					for (ObjectOutputStream dd : p2List) { // 상대방이보낸거 재전송하는거임
						try {
							dd.writeObject(msg);
							dd.flush();
							dd.reset();
						}

						catch (IOException e) {
							e.printStackTrace();
						}

					}

				}
			}
		}

	}

	public static void main(String[] args) {

		new Main_Server();
	}

}