package ddong;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientNetWork {

	Socket soc;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public String id;

	public DDongInter ddInter;

	public ClientNetWork(String id) {

		this.id = id;

		try {
			soc = new Socket(InitData.ip, 7777);
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

		System.out.println(ddos);

		try {
			ddos.src = id;
			oos.writeObject(ddos);
			oos.flush();
			oos.reset();

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
					System.out.println((DDongData) ois.readObject());
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
