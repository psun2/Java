package game_p;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;
import jdbc_p.LobbyDAO;
import lobby_p.Lobby_Main;

public class ModalFrame extends JFrame implements WindowListener {

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

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ �ݱ� �ɼ�
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // ������ �ݱ� ��Ȱ��ȭ
		setVisible(true); // �������� ������

		addWindowListener(this); // ������â�� x�� ������ ������ �Ѵ�

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

		ModalFrame.this.dispose();
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �̰� ������ ?
		ModalFrame.this.frame.dispose();
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		new LobbyDAO().insert(frame.meId);

		frame.updateRoomDb();

		new Lobby_Main(frame.cn);

		DDongData data = new DDongData();
		data.type = "�κ�";
		data.data = null;
		frame.cn.send(data);

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) { // ���� �����.....
		// TODO Auto-generated method stub
		frame.updateRoomDb();

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
