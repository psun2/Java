package mulGameTestDDongDataTestTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import ddong.DDongData;

public class TestReceive extends Thread {

	Socket socket;
	ObjectInputStream ois;

	public TestReceive(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;

		try {
			this.ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		System.out.println("유저 리시버 ㄱㄱ");

		try {

			while (true) {
				if (ois != null) {

					DDongData temp = new DDongData();
					temp = (DDongData) ois.readObject();

					// System.out.println(temp); // 똥 오는것 확인

					System.out.println("리시버 : " + temp.data);


				}
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
