package ddong;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import jdbc_p.GameRoomDAO;
import jdbc_p.GameRoomDTO;
import jdbc_p.GameUserDAO;
import jdbc_p.GameUserDTO;
import jdbc_p.LobbyDAO;
import jdbc_p.LobbyDTO;

public class Main_Server {

	ArrayList<String> userList;
	ArrayList<String> info;
	ArrayList<ObjectOutputStream> datalist;

	boolean chk = false;
	boolean chkk = false;
	String robiid;
	String removeid = "";
	String userid = "";
	HashMap<String, ObjectOutputStream> userdata;

	void dbClear() {

		String nn = null;
		GameRoomDTO dto = new GameRoomDTO();

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

			userList = new ArrayList<String>();
			Collections.synchronizedList(userList);

			datalist = new ArrayList<ObjectOutputStream>();
			Collections.synchronizedCollection(datalist);

			LobbyDAO dao = new LobbyDAO();

			while (true) {
				Socket client = server.accept();
				new Tcp_Server(client).start();
				System.out.println("유저접속");
			}

		} catch (Exception e) {
			System.out.println("접속실패");

		}

	}

	public class Tcp_Server extends Thread {
		ObjectOutputStream oos;
		ObjectInputStream ois;

		public Tcp_Server(Socket soc) {
			try {

				oos = new ObjectOutputStream(soc.getOutputStream());
				ois = new ObjectInputStream(soc.getInputStream());

				System.out.println(userid);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {

				// 로그인을 성공하면 map을 통해 유저 정보 , 데이터를 받는다.

				DDongData data = (DDongData) ois.readObject();
				// datalist.add(oos);
				// removeid = data.src;
				userid = (String) data.src;
				System.out.println(userid);
				// System.out.println(data.src);

				userdata.put(userid, oos);

				while (ois != null) {
					DDongData dataois = (DDongData) ois.readObject();
					System.out.println(dataois.src + "  :이름");
					System.out.println(dataois.type + "  :타입");
					System.out.println(dataois.data + "  :데이터");
					System.out.println(dataois.dst + "  :dst");

					if (dataois.type.equals("채팅") && dataois.dst == null) {
						// System.out.println("채팅 들어와요");
						sendtoChat(dataois);

					} else if (dataois.type.equals("로비") || dataois.type.equals("게임")) {
						// System.out.println("로비 입장");
						// System.out.println(dataois.type+"-타입이 뭐니");

						sendAll(dataois);
					} else if (dataois.type.equals("게임중") && dataois.dst != null) {
						// System.out.println("게임중 들어와요"+dataois.data);
						sendSelect(dataois);
					}

					// else if(dataois.type.equals("게임") ){
					// System.out.println("게임 들어와요");
//   sendSelect(dataois);
//            }else if(dataois.type.equals("login") ) {
//               System.out.println("유저 로그인 입장");
//               sendLogin(dataois);

					// }
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				System.out.println("유저나가요");
				userdata.remove(userid, oos);
			}

		}

		// 채팅
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
			System.out.println("src:" + dataois.src + ", dst:" + dataois.dst);
			System.out.println("data:" + dataois.data);

			try {
				userdata.get(dataois.dst).writeObject(dataois);// 여기 에러
				for (String name : userdata.keySet()) {
					System.out.println("name : " + name);
				}
				userdata.get(dataois.dst).flush();
				userdata.get(dataois.dst).reset();
				System.out.println("유저한테 데이터가 잘보내져요");

			} catch (IOException e) {
			}

		}

		// 룸메세지
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

		public void sendLogin(DDongData dataois) {
			try {
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		new Main_Server();
	}

}