package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Main extends Application {

	Socket socket;
	TextArea textArea; // fx lib import

	// 클라이언트 프로그램 동작 메소드 입니다.
	public void startClient(String IP, int port) {
		// 서버와는 다르게 굳이 서버프로그램과 다르게 여러개의 쓰레드가 동시
		// 다발적으로 일어나지 않기 때문에 runable 객체 보단 Thread 객체를 생성

		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket(IP, port);
					receive();
				} catch (Exception e) {
					// TODO: handle exception
					if (!socket.isClosed() && socket != null) {
						stopClient();
						System.out.println("[서버 접속 실패]");
						Platform.exit();
						// 스윙과 동일
						// System.exit(0);
					}
				}
			}
		};
		thread.start();
	}

	// 클라이언트 프로그램 종료 메소드입니다.
	public void stopClient() {
		try {
			if (!socket.isClosed() && socket != null) {
				socket.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// 서보로부터 메시지를 전달받는 메소드입니다.
	public void receive() {
		while (true) {

			try {
				InputStream in = socket.getInputStream();
				byte[] buffer = new byte[1024];
				int length = in.read(buffer);
				// length == -1 : 읽을수 없을때
				if (length == -1)
					throw new IOException();
				String message = new String(buffer, 0, length, "UTF-8");
				Platform.runLater(() -> {
					textArea.appendText(message);
				});
			} catch (Exception e) {
				// TODO: handle exception
				stopClient();
			}

		}
	}

	// 서버로 메시지를 전송하는 메소드입니다.
	public void send(String message) {
		Thread thread = new Thread() {
			public void run() {
				try {
					OutputStream out = socket.getOutputStream();
					byte[] buffer = message.getBytes("UTF-8");
					out.write(buffer);
					out.flush();
				} catch (Exception e) {
					// TODO: handle exception
					stopClient();
				}
			}
		};
		thread.start();
	}

	// 실제로 프로그램을 동작시키는 메소드입니다.
	@Override
	public void start(Stage primaryStage) {

	}

	// 프로그램의 진입점입니다.
	public static void main(String[] args) {
		launch(args);
	}
}
