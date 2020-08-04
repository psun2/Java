package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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
		BorderPane root = new BorderPane();
		root.setPadding(new Insets(5));

		HBox hbox = new HBox();
		hbox.setSpacing(5);

		TextField userName = new TextField();
		userName.setPrefWidth(150);
		userName.setPromptText("닉네임을 입력하세요.");

		// HBox 내부에서 해당 TextFieled 가
		// 출력이 될수 있도록 ....
		HBox.setHgrow(userName, Priority.ALWAYS);

		// 기본적으로 사용자 자신의 IP가 들어 갈 수 있게 해 줍니다.
		TextField IPText = new TextField("127.0.0.1");

		TextField portText = new TextField("7777");
		portText.setPrefWidth(80);

		// 실질적으로 hbox 내부에 3개의 텍스트 필드가 추가 될 수 있도록 해줍니다.
		hbox.getChildren().addAll(userName, IPText, portText);

		// hbox 를 윗쪽에 배치
		root.setTop(hbox);
		
		textArea = new TextArea();

		// 내용수정 불가
		textArea.setEditable(false);

		// 레이아웃의 중간에 textArea 배치
		root.setCenter(textArea);

		TextField input = new TextField();
		input.setPrefWidth(Double.MAX_VALUE);

		// 접속하기 이전에는 메세지 전송을 불가 하게 만들어 줍니다.
		input.setDisable(true);

		input.setOnAction(event -> {
			send(userName.getText() + ": " + input.getText() + "\n");
			input.setText("");

			// 보낸후 다시 보낼수 있도록 포커스를 재배치
			input.requestFocus();
		});

		Button sendButton = new Button("보내기");

		// 접속하기 이전엔 비활 성화
		sendButton.setDisable(true);

		sendButton.setOnAction(event -> {
			send(userName.getText() + ": " + input.getText() + "\n");
			input.setText("");

			// 보낸후 다시 보낼수 있도록 포커스를 재배치
			input.requestFocus();
		});

		Button connectionButton = new Button("접속하기");
		connectionButton.setOnAction(event -> {
			if (connectionButton.getText().equals("접속하기")) {
				// 기본적인 포트번호가 설정되어 있고
				// 사용자가 다른 포트를 설정한다면 변경되어
				// 해당 포트에 연결할 수 있도록 합니다.
				int port = 7777;
				try {
					port = Integer.parseInt(portText.getText());
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				startClient(IPText.getText(), port);
				Platform.runLater(() -> {
					textArea.appendText("[ 채팅방 접속 ]\n");
				});
				connectionButton.setText("종료하기");
				input.setDisable(false);
				sendButton.setDisable(false);
				input.requestFocus();
			} else {
				stopClient();
				Platform.runLater(() -> {
					textArea.appendText("[ 채팅방 퇴장 ]\n");
				});
				connectionButton.setText("접속하기");
				input.setDisable(true);
				sendButton.setDisable(true);
				input.requestFocus();
			}
		});

		BorderPane pane = new BorderPane();
		pane.setLeft(connectionButton);
		pane.setCenter(input);
		pane.setRight(sendButton);

		root.setBottom(pane);
		Scene scene = new Scene(root, 400, 400);

		primaryStage.setTitle("[ 채팅 클라이언트 ]");
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(event -> stopClient());
		primaryStage.show(); // 보여지도록 만듬
		
		connectionButton.requestFocus();
	}

	// 프로그램의 진입점입니다.
	public static void main(String[] args) {
		launch(args);
	}
}
