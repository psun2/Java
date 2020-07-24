package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import data.Data;
import netdata.NetData;

public class Client {

	Socket socket;
	ObjectOutputStream oos;
	ObjectInputStream ois;
	public ClinetIter client;
	ExecutorService threadPool;
	Data data;

	public Client() {
		// TODO Auto-generated constructor stub

		try {

			this.socket = new Socket(NetData.host, NetData.port);
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
			this.threadPool = Executors.newCachedThreadPool();
			receive();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			try {

				if (socket != null && !socket.isClosed())
					this.socket.close();
				if (oos != null)
					this.oos.close();
				if (ois != null)
					this.ois.close();

			} catch (Exception e2) {
				// TODO: handle exception
			}

		}

	}

	public void send(Data data) {

		try {

			oos.writeObject(data);
			oos.flush();
			oos.reset();

		} catch (Exception e) {

			try {
				if (oos != null)
					oos.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}

		}
	}

	public void receive() {

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {

					while (ois != null) {

						Client.this.data = (Data) ois.readObject();
						client.receive(Client.this.data); // 얘가 왜 안돼죠 ?
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					try {
						if (ois != null)
							ois.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		};
		this.threadPool.submit(thread);
	}

	public static void main(String[] args) {
		new Client();
	}

}
