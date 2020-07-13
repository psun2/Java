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
	// 쓰레드의 수를 제한할 수 있어 갑작스러운 사용자(클라이언트) 증가에도 서버의 성능유지에 좋습니다.
	public static ExecutorService threadPool; // 쓰레드 들을 관리

	// Vector => 쉽게 사용할 수 있는 배열
	public static Vector<Client> clients = new Vector<Client>(); // 접속한 클라이언트들을 관리

	ServerSocket server;

	// 서버를 구동시켜서 클라이언트의 연결을 기다리는 메소드입니다.
	public void startServer(String IP, int port) {

		try {

			server = new ServerSocket(); // 내가 알던 서버소켓 => new ServerSocket(port);

			// 자신의 아이피 주소와 포트번호로 특정한 클라이언트의 접속을 기다리게 할 수 있습니다.
			server.bind(new InetSocketAddress(IP, port));
			// binding => 값이 확정되어 더 이상 변경할 수 없는 구속 상태가 됩니다.
			/*
			 * int a = 2; Data Type 이 int라는 것으로 바인딩 되고, a 라는 변수명이 바인딩 되고, 1이라는 값이 바인딩 되는
			 * 겁니다.
			 */

		} catch (Exception e) {
			// TODO: handle exception
			if (!server.isClosed()) {
				// 에러 발생시 서버가 닫혀 있는 상태가 아니라면
				stopServer(); // 에러시 서버 종료
			}
			return; // 서버가 오류로 인해 종료시 함수를 끝내기 위햐여 return 합니다.
		}

		// 클라이언트가 접속할 때까지 계속 기다리는 쓰레드 입니다.
		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) { // 계속해서 새로운 클라이언트가 접속할 수 있게 기다립니다.
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

		// 만약 일 없이 60초 동안 아무일을 하지 않으면 쓰레드를 종료시키고 스레드 풀에서 제거 한다.
		// 서버는 계속 돌아야 하기 때문에 쓰레드이고, 서버는 하나 밖에 들어 오지 않는 다는 가정하에 만들어 져있습니다.
		// 즉 서버가 리스타트 될때를 생각해 기존의 찌꺼기 서버를 버림 쓰레드풀 초기화
		// 그리고 초기화된 쓰레드 풀에 새로운 서버의 쓰레드를 추가합니다.
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
