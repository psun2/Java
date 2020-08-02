package ddong;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import jdbc_p.GameRoomDAO;
import jdbc_p.GameRoomDTO;
import jdbc_p.LobbyDAO;

public class Main_Server {

	ArrayList<String> userList;
	ArrayList<String> info;
	ArrayList<ObjectOutputStream> datalist;

	String removeid = "";

	HashMap<String, ObjectOutputStream> userdata;

	void dbClear() {

		String nn = null;
		GameRoomDTO dto = new GameRoomDTO();
		dto = new GameRoomDTO();
		for (int i = 1; i < 19; i++) {

			dto.setNo(i);
			dto.setUser1(nn);
			dto.setUser2(nn);
			new GameRoomDAO().reset(dto);
		}

		new LobbyDAO().deleteAll();

	}

	public Main_Server() {
		try {
			dbClear();
			ServerSocket server = new ServerSocket(7777);
			System.out.println(" ____________________________________");
			System.out.println("|                                    |");
			System.out.println("|   S T A R T S E R V E R            |");
			System.out.println("|             T E A M  P Y O P Y O   |");
			System.out.println("|____________________________________|");
			System.out.println();

			userdata = new HashMap<String, ObjectOutputStream>();

			LobbyDAO dao = new LobbyDAO();
			while (true) {
				Socket client = server.accept();
				new Tcp_Server(client).start();

			}

		} catch (Exception e) {
			System.out.println("���ӽ���");
		}

	}

	public class Tcp_Server extends Thread {
		ObjectOutputStream oos;
		ObjectInputStream ois;
		String userid = "";

		public Tcp_Server(Socket soc) {
			try {

				oos = new ObjectOutputStream(soc.getOutputStream());
				ois = new ObjectInputStream(soc.getInputStream());

			} catch (Exception e) {
				System.out.println("���ù��� ���޾ҽ��ϴ�.");
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				// �α����� �����ϸ� map�� ���� ���� ���� , �����͸� �޴´�.

				DDongData data = (DDongData) ois.readObject();
				userid = (String) data.src;
				System.out.println(userid + ":" + "�����մϴ�");
				userdata.put(userid, oos);

				while (ois != null) {

					DDongData dataois = (DDongData) ois.readObject();

					if (dataois.type.equals("ä��") && dataois.dst == null) {
						sendtoChat(dataois);
					} else if (dataois.type.equals("�κ�") || dataois.type.equals("����") || dataois.type.equals("�������")) {
						sendAll(dataois);
					} else if (dataois.type.equals("������")) {
						sendSelect(dataois);
					}

				}

			} catch (Exception e) {
				System.out.println("������ �����ϴ�");
			} finally {
				userdata.remove(userid);
				System.out.println(userid + "������������");

			}
		}

		// ä��
		public void sendtoChat(DDongData dataois) {
			for (ObjectOutputStream ost : userdata.values()) {

				try {
					ost.writeObject(dataois);
					ost.flush();
					ost.reset();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		public void sendSelect(DDongData dataois) {
			try {
				if (dataois.dst == null) {
					return;
				}

				userdata.get(dataois.dst).writeObject(dataois);// ���� ����
				userdata.get(dataois.dst).flush();
				userdata.get(dataois.dst).reset();

			} catch (IOException e) {
			}

		}

		// ��޼���
		public void sendAll(DDongData dataois) {
			try {
				for (ObjectOutputStream ooo : userdata.values()) {
					ooo.writeObject(dataois);
					ooo.flush();
					ooo.reset();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		new Main_Server();
	}

}