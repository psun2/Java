package mulGameTestDDongDataTest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ddong.DDongData;

public class GameSend extends Thread {

	Socket socket;
	PuyoFrame frame;
	ObjectOutputStream oos;

	public GameSend(Socket socket, PuyoFrame frame) {
		// TODO Auto-generated constructor stub

		this.socket = socket;
		this.frame = frame;
		try {
			this.oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {

			while (true) {
				if (oos != null) {

//					System.out.println("난 샌드");
//					frame.me.data.data = frame.me.meInfo;
//					System.out.println(frame.me.meInfo);
//					System.out.println(frame.me.data.data);
//					System.out.println("난 샌드");
//					System.out.println(frame.me.data);
//					System.out.println(frame.me.data.data);
					oos.writeObject(frame.me.data);
					oos.flush();
					// oos.reset();
				}
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
