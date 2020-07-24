package ddong;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientNetWork {

	ObjectOutputStream oos;
	ObjectInputStream ois;

	public String id;

	public DDongInter ddInter;

	public ClientNetWork(String id) {

		try {

			this.id = id;

			Socket soc = new Socket(InitData.ip, 7777);
			oos = new ObjectOutputStream(soc.getOutputStream());
			DDongData ddos = new DDongData();
			ddos.data = id;
			ddos.type = "login";
			ddos.chk = false;
			oos.writeObject(ddos);
			oos.flush();
			oos.reset();

			new Resiver(soc).start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void send(DDongData ddos) {

		try {

			ddos.src = id;

			oos.writeObject(ddos);
			oos.flush();
			oos.reset();

			System.out.println("�����ߵǿ�");

		} catch (Exception e) {
		}
	}

	public class Resiver extends Thread {

		public Resiver(Socket soc) {

			try {
				ois = new ObjectInputStream(soc.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {

			while (ois != null) {
				try {
					DDongData data = (DDongData) ois.readObject();
					ddInter.reciver(data);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
