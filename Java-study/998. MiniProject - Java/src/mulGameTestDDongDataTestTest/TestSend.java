package mulGameTestDDongDataTestTest;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import ddong.DDongData;

public class TestSend extends Thread {

	Socket socket;
	ObjectOutputStream oos;
	DDongData data;

	public TestSend(Socket socket, DDongData data) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.data = data;

		try {
			this.oos = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println("유저 샌더 ㄱㄱ");

		try {

			while (true) {
				if (oos != null) {

					DDongData temp = new DDongData();
					temp = data;
					System.out.println("샌더 : " + data);
					oos.writeObject(data);
					oos.flush();
					oos.reset();
				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
