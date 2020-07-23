package mulGameTestDDongDataTest;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JButton;

public class GameRoomFrame_temp extends JFrame {

//	클라이언트 서버 
	// 센더 리ㅅ씨버

	JPanel pBack;
	JPanel pMe;
	JPanel pYou;
	JButton exitRoom;
	JToggleButton startGame;

	Socket socket;

	public GameRoomFrame_temp() {

		try {
			socket = new Socket("192.168.0.22", 7777);
			new TCPGameSender(socket).start();
			new TCPGameReceiver(socket).start();
			System.out.println("GameRoomFrame  시작");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getContentPane().setLayout(null);
		setSize(796, 720);
		setResizable(false);
		setLocationRelativeTo(null);

		pBack = new JPanel();
		pBack.setBackground(Color.WHITE);
		pBack.setBounds(0, 0, 796, 680);
		getContentPane().add(pBack);
		pBack.setLayout(null);
		pBack.setVisible(true);

		pMe = new JPanel();
		pMe.setBounds(0, 0, 306, 680);
		pBack.add(pMe);
		pMe.setVisible(true);

		pYou = new JPanel();
		pYou.setBounds(490, 0, 306, 680);
		pBack.add(pYou);
		pYou.setVisible(true);

		exitRoom = new JButton("방 나가기");
		exitRoom.setBackground(Color.LIGHT_GRAY);
		exitRoom.setBounds(306, 332, 185, 29);
		pBack.add(exitRoom);
		exitRoom.addActionListener(ActBtn);

		startGame = new JToggleButton("게임 준비");
		startGame.setBackground(Color.LIGHT_GRAY);
		startGame.setBounds(306, 300, 185, 29);
		pBack.add(startGame);
		startGame.addActionListener(ActBtn);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	static boolean runing = false;
	static String sendmsg = "";
	int i = 0;
	ActionListener ActBtn = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == startGame) {
				startGame.setText("게임 준비 취소");
				i++;
				runing = true;
				if (i % 2 == 1) {
					sendmsg = "true";
//				System.out.println(sendmsg);
				} else if (i % 2 == 0) {
					startGame.setText("게임 준비");
					sendmsg = "false";
//					System.out.println(sendmsg);
				}

			}

			if (e.getSource() == exitRoom) {
				dispose();

				// 여기에 디비 정보지우는거 넣기

			}

		}
	};

	class TCPGameReceiver extends Thread {
		ObjectInputStream ois;

		public TCPGameReceiver(Socket socket) {

			try {
				ois = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			while (ois != null) {
				try {
					String ttt = ois.readUTF();
					System.out.println();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	class TCPGameSender extends Thread {

		String name;
		ObjectOutputStream oos;
		GameRoomFrame_temp grf;

		public TCPGameSender(Socket socket) {
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		public void run() {
			try {
				while (oos != null) {
					if (grf.runing) {
						System.out.println("test" + grf.runing);
						grf.runing = false;
//	            		System.out.println("if false 이후 "+grf.runing);
//	            		System.out.println("센드 메세지"+grf.sendmsg);
						oos.writeUTF(grf.sendmsg);
//	            		System.out.println("센드 메세지222"+grf.sendmsg);
					} else {

					}
					oos.flush();// 현재 버퍼에 저장되어 있는 내용을 클라이언트로 전송하고 버퍼를 비운다
					oos.reset();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					oos.close();
				} catch (Exception e) {

				}
			}
		}// run의 끝부분

	}

	public static void main(String[] args) {

		new GameRoomFrame_temp();

	}

}
