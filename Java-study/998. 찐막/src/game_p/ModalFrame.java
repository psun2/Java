package game_p;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;
import jdbc_p.GameRoomDAO;
import jdbc_p.GameRoomDTO;
import jdbc_p.LobbyDAO;
import lobby_p.Lobby_Main;

public class ModalFrame extends JFrame {

	ClientNetWork cn;

	PuyoFrame frame;

	int width, height, second;

	JLabel textLb2;

	ExecutorService threadPool;

	public ModalFrame(PuyoFrame frame, String result) {
		// TODO Auto-generated constructor stub

		init(frame);

		setSize(width, height); // ������ ������
		setLocationRelativeTo(null); // ������ ���۽� ����� �߾ӿ� ���
		setResizable(false); // ������ ������ ���� �� �� ����
		getContentPane().setLayout(new GridLayout(2, 1)); // ����2�ƿ�
		setTitle("���� ����");
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // Ÿ��Ʋ�� �ΰ� ����
		getContentPane().setBackground(Color.white);

		JLabel textLb = new JLabel(frame.enemyId + " ���� �ºο��� " + result);
		textLb.setHorizontalAlignment(JLabel.CENTER);
		add(textLb);

		this.textLb2 = new JLabel();
		textLb2.setHorizontalAlignment(JLabel.CENTER);
		add(textLb2);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ �ݱ� �ɼ�
		setVisible(true); // �������� ������

		endTimer();

	}

	void init(PuyoFrame frame) {

		this.frame = frame;
		this.width = 400;
		this.height = 200;
		this.second = 5;
		this.threadPool = Executors.newCachedThreadPool();

	}

	void endTimer() {

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (second >= 0) {

					try {
						ModalFrame.this.textLb2.setText(second + "�� �� �κ�� �̵��˴ϴ�.");
						Thread.sleep(1000);
						second--;
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
				goLobby();
			}
		};
		this.threadPool.submit(thread);
	}

	void goLobby() {

		try {
			if (ModalFrame.this.frame.threadPool != null && !ModalFrame.this.frame.threadPool.isShutdown()) {
				ModalFrame.this.frame.threadPool.shutdown();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		ModalFrame.this.dispose();
		ModalFrame.this.frame.dispose();

		new LobbyDAO().insert(frame.meId);

		if (frame.me.lobbyChk) { // �Ѵ� ������ ���� �Ǿ� �����ٸ� �� ����
			GameRoomDTO users = new GameRoomDAO().detailroom(frame.roomNum);

			String user1 = users.getUser1();
			String user2 = users.getUser2();

			String[] userArr = { user1, user2 };

			for (int i = 0; i < userArr.length; i++) {

				new GameRoomDAO().modifyUser5(new String[] { "user1", "user2" }[i], userArr[i]);

			}

		}

		new Lobby_Main(frame.cn);

		DDongData data = new DDongData();
		data.type = "�κ�����";
		frame.cn.send(data);

	}

}
