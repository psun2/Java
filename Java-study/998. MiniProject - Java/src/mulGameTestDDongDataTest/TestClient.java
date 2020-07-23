package mulGameTestDDongDataTest;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ddong.DDongData;
import mulGameTestDDongData.GameSend;

class Test implements Serializable {

	private static final long serialVersionUID = 3L;

	int i = 0;

}

public class TestClient {

	String host;
	int port;
	Socket socket;

	Test test;
	DDongData data;

	ExecutorService threadPool;

	public TestClient() {
		// TODO Auto-generated constructor stub
		init();

		test();

		new TestSend(socket, data).start();
		new TestReceive(socket).start();

	}

	void init() {

		try {
			this.host = InetAddress.getLocalHost().getHostAddress();
			this.port = 7777;
			this.socket = new Socket(host, port);
			this.threadPool = Executors.newCachedThreadPool();
			data = new DDongData();
			data.src = "클라이언트다";
			data.type = "TEST";
			test = new Test();
			data.data = test;
			System.out.println(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void test() {

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) {

					try {
						Thread.sleep(1000);
						test.i++;
						// System.out.println(((Test) data.data).i); // 시간 확인 완료
						// 제대로 업데이트 됨
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		};
		this.threadPool.submit(thread);
	}

	public static void main(String[] args) {
		new TestClient();
	}

}
