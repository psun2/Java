package application;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	// ExecutorService => 여러개의 쓰레드를 효율적으로 관리하는 라이브러리
	// 쓰레드의 수를 제한할 수 있어 갑작스러운 사용자 증가에도 서버의 성능유지에 좋습니다.
	public static ExecutorService threadPool; // 쓰레드 들을 관리

	// Vector => 쉽게 사용할 수 있는 배열
	public static Vector<Client> clients = new Vector<Client>(); // 접속한 클라이언트들을 관리

	ServerSocket server;

	// 서버를 구동시켜서 클라이언트의 연결을 기다리는 메소드입니다.
	public void startServer(String IP, int port) {

		try {

			server = new ServerSocket();

			// 자신의 아이피 주소와 포트번호로 특정한 클라이언트의 접속을 기다리게 할 수 있습니다.
			server.bind(new InetSocketAddress(IP, port));

		} catch (Exception e) {
			// TODO: handle exception
			if (!server.isClosed()) {
				// 에러 발생시 서버가 닫혀 있는 상태가 아니라면
				stopServer(); // 에러시 서버 종료
			}
		}

		// 클라이언트가 접속할 때까지 계속 기다리는 쓰레드 입니다.
		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Socket socket = server.accept(); // 클라이언트의 접속을 기다립니다.

						// 새로운 클라이언트가 접속 했다면 클라이언트 배열에 push
						clients.add(new Client(socket));
						System.out.println("[클라이언트 접속]" + socket.getRemoteSocketAddress() + ": "
								+ Thread.currentThread().getName());
					} catch (Exception e) {
						// TODO: handle exception
						// 오류가 발생했다면
						if (!!server.isClosed()) { // 서버가 닫히지 않았다면
							stopServer();
						}
						break;
					}
				}
			}
		};

		threadPool = Executors.newCachedThreadPool(); // 쓰레드 풀 초기화
		threadPool.submit(thread); // 현재 접속 을 한 쓰레드를 submit 합니다.
	}

	// 서버의 작동을 중지시키는 메소드입니다.
	public void stopServer() {

		try {
			// 현재 작동 중인 모든 소켓 닫기

			Iterator<Client> iterator = clients.iterator();
			while (iterator.hasNext()) {
				Client client = iterator.next();
				client.socket.close();
				iterator.remove();
			}

			// 서버 소켓 객체 닫기
			if (server != null && server.isClosed()) {
				// 서버소켓이 생성이 되어있고, 현재 서버가 열려있다면
				server.close();
			}

			// 쓰레드 풀 종료하기
			if (threadPool != null && !threadPool.isShutdown()) {
				threadPool.shutdown();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	// UI를 생성하고, 실질적으로 프로그램을 동작시키는 메소드입니다.
	@Override
	public void start(Stage primaryStage) {

	}

	// 프로그램의 진입점 입니다.
	public static void main(String[] args) {
		launch(args);
	}
}
