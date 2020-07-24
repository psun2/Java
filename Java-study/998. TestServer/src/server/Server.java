package server;

import java.awt.BorderLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import data.Data;
import netdata.NetData;

public class Server extends JFrame {

	ServerSocket serverSocket;
	Socket client;
	ArrayList<ObjectOutputStream> clients;
	HashMap<String, ArrayList<ObjectOutputStream>> userMap;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	ExecutorService threadPool;
	JTextArea area;

	// 전체 유저에는 접속한 모든 유저가 있다가....
	// 다른곳으로 진입시에 그 사람을 그 쪽으로 빼주고 (map 사용)
	// 전체 에서 죽이고, 로비로 다시 나왔을때 또 추가해주고 해야함
	// 그작업을 아직 못했음 ... 해야함

	public Server() {
		// TODO Auto-generated constructor stub

		try {
			this.serverSocket = new ServerSocket(NetData.port);
			this.clients = new ArrayList<ObjectOutputStream>();
			Collections.synchronizedList(clients);
			this.userMap = new HashMap<String, ArrayList<ObjectOutputStream>>();
			Collections.synchronizedMap(userMap);
			this.threadPool = Executors.newCachedThreadPool();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				if (!serverSocket.isClosed() && serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		setSize(500, 500);
		setTitle("서버");
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		area = new JTextArea();
		area.setEditable(false);
		JScrollPane js = new JScrollPane(area);
		add(js, "Center");

		updateMessage("서버 준비 완료");
		System.out.println("서버 준비 완료");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		accept();
	}

	void accept() {

		try {
			while (true) {
				updateMessage("클라이언트 대기중");
				System.out.println("클라이언트 대기중");
				this.client = serverSocket.accept();
				updateMessage("[" + client.getLocalAddress() + "] 접속 완료");
				System.out.println("[" + client.getLocalAddress() + "] 접속 완료");
				receive();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void receive() {

		try {
			this.ois = new ObjectInputStream(client.getInputStream());
			this.oos = new ObjectOutputStream(client.getOutputStream());
			this.clients.add(oos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {

				if (ois != null && oos != null) {
					ois.close();
					oos.close();
				}
				this.clients.remove(oos);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				String type = null;

				try {
					while (ois != null) {
						Data data = (Data) ois.readObject();
						type = data.type;
						switch (type) {
						case "채팅":
							mapAdd(type);
							chatSend(type, data);
							updateMessage("[" + client.getLocalAddress() + "] 채팅 데이터 전송 => " + data);
							System.out.println("chatSend");
							break;
						case "게임":
							mapAdd(type);
							gameSend(type, data);
							updateMessage("[" + client.getLocalAddress() + "] 게임 데이터 전송 => " + data);
							System.out.println("gameSend");
							break;
						default:
							sendToAll(data);
							updateMessage("[전체유저] 데이터 전송");
							System.out.println("sendToAll");
							break;
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						ois.close();
						oos.close();
						Server.this.clients.remove(oos);
						Server.this.userMap.get(type).remove(oos);
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				} finally {

					updateMessage("[" + client.getLocalAddress() + "] 유저 나가요");

				}

			}
		};
		this.threadPool.submit(thread);
	}

	void mapAdd(String type) {

		if (this.userMap.containsKey(type)) {
			this.userMap.get(type).add(oos);
			System.out.println(this.userMap.get(type));
		} else {
			ArrayList<ObjectOutputStream> outs = new ArrayList<ObjectOutputStream>();
			Collections.synchronizedList(outs);
			outs.add(oos);
			this.userMap.put(type, outs);
			System.out.println(outs);
		}

	}

	void chatSend(String type, Data data) {

		ArrayList<ObjectOutputStream> chatOos = this.userMap.get(type);

		try {
			for (ObjectOutputStream oos : chatOos) {
				oos.writeObject(data);
				oos.flush();
				oos.reset();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ois.close();
				oos.close();
				Server.this.clients.remove(oos);
				Server.this.userMap.get(type).remove(oos);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}

	void gameSend(String type, Data data) {

		ArrayList<ObjectOutputStream> gameOos = this.userMap.get(type);

		try {
			for (ObjectOutputStream oos : gameOos) {
				oos.writeObject(data);
				oos.flush();
				oos.reset();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				ois.close();
				oos.close();
				this.clients.remove(oos);
				this.userMap.get(type).remove(oos);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}

	void sendToAll(Data data) {

		try {
			for (ObjectOutputStream oos : clients) {
				oos.writeObject(data);
				oos.flush();
				oos.reset();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				if (ois != null && oos != null) {
					ois.close();
					oos.close();
					this.clients.remove(oos);
					for (ArrayList<ObjectOutputStream> arr : userMap.values()) {

						for (ObjectOutputStream oos : arr) {
							if (this.oos.equals(oos)) {
								arr.remove(this.oos);
								break;
							}
						}

					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}

	void updateMessage(String message) {

		area.append(message + "\n");
		area.setCaretPosition(area.getDocument().getLength());

	}

	public static void main(String[] args) {
		new Server();
	}
}
