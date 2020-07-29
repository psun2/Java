package game_p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;
import jdbc_p.GameRoomDAO;
import jdbc_p.GameRoomDTO;

public class PuyoFrame extends JFrame implements DDongInter, WindowListener {

	public ClientNetWork cn;

	final int width = 806;
	final int height = 679 + 50;

	MePuyoPanel me;
	YouPuyoPanel you;

	JPanel state;

	JLabel meLb, youLb;

	String meId, enemyId;
	Integer roomNum;

	DDongData data;
	MeGameInfo enemyData;

	boolean enemyChk;

	ExecutorService threadPool;

	public PuyoFrame(Integer roomNum, String meId, String enemyId) {
		// TODO Auto-generated constructor stub

		init(roomNum, meId, enemyId);
		setSize(width, height); // ������ ������
		setLocationRelativeTo(null); // ������ ���۽� ����� �߾ӿ� ���
		setResizable(false); // ������ ������ ���� �� �� ����
		getContentPane().setLayout(null); // ����2�ƿ�
		setTitle("��������"); // Ÿ��Ʋ
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // Ÿ��Ʋ�� �ΰ� ����
		getContentPane().setBackground(Color.white);

		me = new MePuyoPanel(this);
		me.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(me);
		addKeyListener(new ActionKey(me));

		int labelH = 50;
		this.meLb = new JLabel(meId);
		meLb.setBounds(0, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, labelH);
		meLb.setHorizontalAlignment(JLabel.CENTER);
		add(meLb);

		you = new YouPuyoPanel();
		you.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(you);

		this.youLb = new JLabel(enemyId);
		youLb.setBounds(Puyo.PUYOSIZE * 10, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, labelH);
		youLb.setHorizontalAlignment(JLabel.CENTER);
		add(youLb);

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ �ݱ� �ɼ�
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true); // �������� ������

		addWindowListener(this); // �������� ���� ����� �ൿ�� �̺�Ʈ������

		update();

	}

	void init(Integer roomNum, String meId, String enemyId) {
		this.roomNum = roomNum;
		this.meId = meId;
		this.enemyId = enemyId;
		this.threadPool = Executors.newCachedThreadPool();
		this.enemyChk = false;

		ddongDataInit();

	}

	void ddongDataInit() {
		this.data = new DDongData();
		data.type = "������";
		data.dst = enemyId;

	}

	@Override
	public void reciver(DDongData dd) {
		// TODO Auto-generated method stub;

		if (enemyChk)
			return;

		if (dd.type.equals("������")) {

			if ((MeGameInfo) dd.data != null) {
				// enemyChk = dd.chk;
				you.paint((MeGameInfo) dd.data);
				this.enemyData = (MeGameInfo) dd.data;
				if (((MeGameInfo) dd.data).itemChk)
					me.itemChk = true;
			}

		}

	}

	void update() {

		int frame = (33 * 33) * 2;

		Runnable thread = new Runnable() {

			@Override
			public void run() {

				while (true) {

					try {

						if (PuyoFrame.this.enemyData != null) {
							if (me.meInfo.endGame || PuyoFrame.this.enemyData.endGame)
								return;

						}

						// ���濡�� ������ �� ������ ����
						cn.send(data);
						me.meInfo.itemChk = false;
						Thread.sleep(frame);

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		};
		this.threadPool.submit(thread);
	}

	void signal() {
		DDongData data = new DDongData();
		data.type = "�κ�";
		data.data = null;
		cn.send(data);
	}

	void updateRoomDb() {

		GameRoomDTO users = new GameRoomDAO().detailroom(roomNum);

		String user1 = users.getUser1();
		String user2 = users.getUser2();

		String[] userArr = { user1, user2 };

//		System.out.println(me.lobbyChk + "�κ�ý");
//		System.out.println(me.meInfo.endGame + "�ص����");
//		System.out.println(this.enemyData.endGame + "���ʹ� ������ �ص����");

		if (me.lobbyChk) { // �Ѵ� ������ ���� �Ǿ� �����ٸ� �� ����

			for (int i = 0; i < userArr.length; i++) {

				new GameRoomDAO().modifyUser4(new String[] { "user1", "user2" }[i], userArr[i], roomNum);

			}

		} else { // �Ѹ� �����ٸ�.... �� �Ѹ��� ������ ���� �Ǿ�� �ϱ� ������....

			for (int i = 0; i < userArr.length; i++) {

				if (meId.equals(userArr[i])) {
					new GameRoomDAO().modifyUser5(new String[] { "user1", "user2" }[i], userArr[i]); // �� ��񿡼� �� ����
					new GameRoomDAO().modifyUser6(new String[] { "user1", "user2" }[i], roomNum); // �� ��� �ӽ����� ����
				}

			}
			// �� ��� ������Ʈ ��
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		// ������ ���� �������� �г�...
		// ���� ���� �ߴٸ� �� ��񿡼� ����.... �κ� ���� ���� �ʿ�� ����...
		// �θ��� ������ ���� ��� �������� ���� ���� �ؾ� �ϱ� ������ ....

		me.meInfo.endGame = true; // ���� ���� �̱� ������ ���� ������ ���������� ����

		cn.send(data);

		updateRoomDb(); // ���� ����� �κ�� ���� �ʰ� �ٷ� ������ ����
		signal();

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