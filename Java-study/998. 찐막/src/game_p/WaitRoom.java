package game_p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;
import jdbc_p.GameRoomDAO;
import jdbc_p.GameRoomDTO;
import jdbc_p.LobbyDAO;
import lobby_p.Lobby_Main;

public class WaitRoom extends JFrame implements DDongInter {

	final int width = 806;
	final int height = 679 + 50;

	boolean meChk, youChk;

	public ClientNetWork cn;
	JToggleButton ready;
	WaitRoomPanel meP, youP;
	JPanel state;
	JLabel meLb, youLb;

	public Integer roomNum;
	public String id;
	String enenmy;

	DDongData data;

	ExecutorService threadPool;

	public WaitRoom(String id, Integer roomNum) {
		// TODO Auto-generated constructor stub

		init(id, roomNum);

		setSize(width, height); // ������ ������
		setLocationRelativeTo(null); // ������ ���۽� ����� �߾ӿ� ���
		setResizable(false); // ������ ������ ���� �� �� ����
		getContentPane().setLayout(null); // ����2�ƿ�
		setTitle("��������"); // Ÿ��Ʋ
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // Ÿ��Ʋ�� �ΰ� ����
		getContentPane().setBackground(Color.white);

		meP = new WaitRoomPanel();
		meP.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		meP.setBackground(Color.black);
		add(meP);

		int labelH = 50;
		this.meLb = new JLabel("ME");
		meLb.setBounds(0, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, labelH);
		meLb.setHorizontalAlignment(JLabel.CENTER);
		meLb.setText(id);
		add(meLb);

		state = new JPanel();
		state.setLayout(null);
		state.setBounds(Puyo.PUYOSIZE * 6, 0, Puyo.PUYOSIZE * 4, Puyo.PUYOSIZE * 13);
		state.setBackground(Color.white);
		add(state);

		ready = new JToggleButton("�غ�");
		JButton exit = new JButton("������");

		// ��ư ���� 50;
		// ��ư �� ���� 130;
		// ���� ���� 520;
		// 520 - 130 / 2
		int btnSizeW = 100;
		int btnSizeH = 40;
		int x = (state.getSize().width - btnSizeW) / 2;
		// System.out.println(x);
		int gap = 50;
		int y = (state.getSize().height - (btnSizeH * 2)) / 2;
		// System.out.println(y);

		// ready.setBackground(null);
		ready.setBorderPainted(false);
		// ready.setBorder(null);

		exit.setBorderPainted(false);

		ready.setBounds(x, y, btnSizeW, btnSizeH);
		exit.setBounds(x, y + gap, btnSizeW, btnSizeH);

		ready.setEnabled(false);

		ready.addActionListener(new ReadyBtn());
		exit.addActionListener(new ExitBtn());

		state.add(ready);
		state.add(exit);

		youP = new WaitRoomPanel();
		youP.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		youP.setBackground(Color.green);
		add(youP);

		this.youLb = new JLabel("�ٸ������� ���Ӵ��");
		youLb.setBounds(Puyo.PUYOSIZE * 10, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, labelH);
		youLb.setHorizontalAlignment(JLabel.CENTER);
		add(youLb);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	void init(String id, Integer roomNum) {
		this.id = id;
		this.roomNum = roomNum;
		this.meChk = false;
		this.threadPool = Executors.newCachedThreadPool();

		ddongDataInit();
	}

	void ddongDataInit() {
		this.data = new DDongData(); // �� ��� ����
		data.type = "����";

	}

	@Override
	public void reciver(DDongData dd) {
		// TODO Auto-generated method stub

		System.out.println(this.enenmy);

		if (dd.type.equals("����")) {

			searchUser();

			if (this.enenmy != null && dd.src.equals(enenmy)) { // ���� �����鼭 ���� ������ ������
				System.out.println("���� ���Ϳ� ?");
				this.youChk = dd.chk;

			}
			match();

		}

	}

	void searchUser() { // ����� ������ �� ã��

		GameRoomDTO users = new GameRoomDAO().detailroom(WaitRoom.this.roomNum); // ���ȣ�� ��� ����

		String user1 = users.getUser1();
		String user2 = users.getUser2();

		System.out.println("�� : " + id);

		String[] userArr = { user1, user2 };

		for (String user : userArr) {

			if (user != null) {
				if (!id.equals(user)) {
					System.out.println(" �� : " + user);
					this.enenmy = user;
					youLb.setText(enenmy);
					this.ready.setEnabled(true);
				}
			} else { // null �� �ѹ� �̶� �ִٸ�
				System.out.println(" �� : " + user);
				this.enenmy = null;
				youLb.setText("�ٸ������� ���Ӵ��");
				this.ready.setEnabled(false);
			}

		}

	}

	void match() { // �غ� ��ư�� �������� ����� ������ ����� ������ ���� ���� Ȯ��

		if (this.meChk && youChk) {

			this.dispose();

			PuyoFrame game = new PuyoFrame(roomNum, id, enenmy);
			game.cn = cn;
			cn.ddInter = game;

			data.type = "������";
			data.dst = enenmy;
			cn.send(data);

		}

	}

	class ReadyBtn implements ActionListener { // �غ� ��ư

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if (!WaitRoom.this.meChk) {
				WaitRoom.this.meChk = true;
				data.chk = true;
				cn.send(data);
			} else {
				WaitRoom.this.meChk = false;
				data.chk = false;
				cn.send(data);
			}

		}

	}

	class ExitBtn implements ActionListener { // ������ ��ư send �� �ؼ� ��ư Ȱ��ȭ�� ��Ȱ��ȭ�� ���� �����

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// �κ� ��� �濡�� ���� ���� �߰�
			new LobbyDAO().insert(id);
			// �κ� ���� ��

			// ��񿡼� �濡�� ���� ���� ����
			GameRoomDTO users = new GameRoomDAO().detailroom(WaitRoom.this.roomNum);

			String user1 = users.getUser1();
			String user2 = users.getUser2();

			String[] userArr = { user1, user2 };

			for (int i = 0; i < userArr.length; i++) {

				if (id.equals(userArr[i])) {
					new GameRoomDAO().modifyUser5(new String[] { "user1", "user2" }[i], userArr[i]);
					break;
				}

			}
			// �� ��� ������Ʈ ��

			// �� ȭ�� ����
			WaitRoom.this.dispose();

			// �κ�� ȭ������ ....
			new Lobby_Main(cn);

			cn.send(data);

		}

	}

}