package mulGameTestDDongDataTest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import ddong.DDongData;

public class GameReceive extends Thread {

	Socket socket;
	PuyoFrame frame;
	ObjectInputStream ois;
	DDongData data;

	public GameReceive(Socket socket, PuyoFrame frame) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		this.frame = frame;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		System.out.println("reseive ois 생성 완료");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {

			while (ois!=null) { // 서버로 부터 지속적으로 데이터를 받아옴

					// null 이 아닐때만 지속적으로 데이터를 받음

				DDongData data = (DDongData) ois.readObject();
				data.type="게임즈테스트";
				
					System.out.println(ois.readObject());
					System.out.println("data : " + data);
					
					System.out.println(data.data);
					
					//if (data.src.equals(frame.me.id)) { // 받아오는 src가 나의 id와 같을때 처리
						frame.you.paint((MeGameInfo) data.data);
						System.out.println("(MeGameInfo) data.data : " + (MeGameInfo) data.data);
					//} else { // 나와 다르다 상대의 아이디 상대일때 처리
						frame.you.paint((MeGameInfo) data.data);
						System.out.println("(MeGameInfo) data.data : " + (MeGameInfo) data.data);
					//}
				
				Thread.sleep(500); // 1초에 한번씩 씁니다.
			}

		} catch (Exception e) {
			// TODO: handle exception
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

}
