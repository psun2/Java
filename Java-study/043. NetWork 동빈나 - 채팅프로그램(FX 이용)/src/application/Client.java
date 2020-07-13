package application;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	// 챗서버가 한명의 클라이언트와 통신하기 위하여 필요한 기능을 모아 놓은 class

	Socket socket;

	public Client(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		receive();
	}

	// 클라이언트로부터 메시지를 전달 받는 메소드입니다.
	public void receive() {
		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					while (true) {
						// 반복적으로 클라이언트로 부터 어떠한 내용을 전달 받 을 수 있도록 합니다.
						InputStream in = socket.getInputStream();
						// buffer => 내용이 담긴 크기
						byte[] buffer = new byte[1024];
						int length = in.read(buffer);

						// 메세지를 읽어드림에 있어서 오류가 발생했을때
						while (length == -1)
							throw new Exception();

						System.out.println("[메시지 수신 성공]" + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName());
						String message = new String(buffer, 0, length, "UTF-8");

						// 전달 받은 메세지를 다른 클라이언트 들에게도 보낼 수 있도록 해 줍니다.
						for (Client client : Main.clients) {

							client.send(message);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					try {
						System.out.println("[메시지 수신 오류]" + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName());
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		};

		// 쓰레드 풀에 등록하는 과정입니다.
		Main.threadPool.submit(thread); // 바로접근 할 수 있게 static 으로 생성 했습니다.
		System.out.println("client - receive => threadPool" + Main.threadPool);
	}

	// 클라이언트에게 메시지를 전송하는 메소드 입니다.
	public void send(String message) {
		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					// 오류가 발생하지 않았다면 write 합니다.
					out.write(buffer); // 서버 => 클라이언트로 전송
					// flush: 붉어지다, 상기되다
					out.flush(); // => 성공적으로 여기까지 전송을 했다는 것을 알리는 역할을 합니다.
				} catch (Exception e) {
					// TODO: handle exception
					try {
						System.out.println("메시지 송신 오류]" + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName());
						// 오류가 난다면 쓰레드가 등록되어 있는 쓰레드 풀에서
						// 현재 등록 되어 있는 쓰레드를 지워 줍니다.
						Main.clients.remove(Client.this);
						socket.close(); // 오류가 생긴 클라이언트의 소켓을 닫아줍니다.
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		};

		// 쓰레드 풀에 등록하는 과정입니다.
		Main.threadPool.submit(thread); // 바로접근 할 수 있게 static 으로 생성 했습니다.
		System.out.println("client - send => threadPool" + Main.threadPool);
	}

}
