package game_p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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

public class WaitRoomFrame extends JFrame implements DDongInter, WindowListener {

	final int width = 806;
	final int height = 679 + 50;

	boolean meChk, youChk;

	public ClientNetWork cn;

	String imgSrc;

	JToggleButton ready;
	WaitRoomPanel meP, youP;
	JPanel state;
	JLabel meLb, youLb;

	public Integer roomNum;
	public String id;
	String enenmy;

	DDongData data;

	ExecutorService threadPool;

	public WaitRoomFrame(String id, Integer roomNum) {
		// TODO Auto-generated constructor stub

		init(id, roomNum);

		setSize(width, height); // ������ ������
		setLocationRelativeTo(null); // ������ ���۽� ����� �߾ӿ� ���
		setResizable(false); // ������ ������ ���� �� �� ����
		getContentPane().setLayout(null); // ����2�ƿ�
		setTitle("��������"); // Ÿ��Ʋ
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // Ÿ��Ʋ�� �ΰ� ����
		getContentPane().setBackground(Color.white);
		this.imgSrc = "./img/background.png";

		meP = new WaitRoomPanel(imgSrc);
		meP.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(meP);

		this.meLb = new JLabel("ME");
		meLb.setBounds(0, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE);
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

		youP = new WaitRoomPanel(imgSrc);
		youP.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		youP.setBackground(Color.green);
		add(youP);

		this.youLb = new JLabel("�ٸ������� ���Ӵ��");
		youLb.setBounds(Puyo.PUYOSIZE * 10, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE);
		youLb.setHorizontalAlignment(JLabel.CENTER);
		add(youLb);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setVisible(true);

		addWindowListener(this);

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
		data.type = "�������";

	}

	@Override
	public void reciver(DDongData dd) {
		// TODO Auto-generated method stub

		// System.out.println(this.enenmy);

		if (dd.type.equals("�������") || dd.type.equals("����")) {

			searchUser();

			if (this.enenmy != null && dd.src.equals(enenmy)) { // ���� �����鼭 ���� ������ ������
				this.youChk = dd.chk;

			}
			match();

		}

	}

	void searchUser() { // ����� ������ �� ã��

		GameRoomDTO users = new GameRoomDAO().detailroom(WaitRoomFrame.this.roomNum); // ���ȣ�� ��� ����

		String user1 = users.getUser1();
		String user2 = users.getUser2();

		String[] userArr = { user1, user2 };

		for (String user : userArr) {

			if (user != null) {
				if (!id.equals(user)) {
					this.enenmy = user;
					youLb.setText(enenmy);
					this.ready.setEnabled(true);
				}
			} else { // null �� �ѹ� �̶� �ִٸ�
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

			try {
				Thread.sleep(33);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			data.type = "������";
			data.dst = enenmy;
			cn.send(data);

		}

	}

	void waitRoomDb() {

		// ��񿡼� �濡�� ���� ���� ����
		GameRoomDTO users = new GameRoomDAO().detailroom(WaitRoomFrame.this.roomNum);

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
		WaitRoomFrame.this.dispose();
		WaitRoomFrame.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	void signal() {
		DDongData data = new DDongData();
		data.type = "�κ�";
		data.data = null;
		cn.send(data);
	}

	class ReadyBtn implements ActionListener { // �غ� ��ư

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if (!WaitRoomFrame.this.meChk) {
				WaitRoomFrame.this.meChk = true;
				data.chk = true;
				cn.send(data);
			} else {
				WaitRoomFrame.this.meChk = false;
				data.chk = false;
				cn.send(data);
			}

		}

	}

	class ExitBtn implements ActionListener { // ������ ��ư send �� �ؼ� ��ư Ȱ��ȭ�� ��Ȱ��ȭ�� ���� �����

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new LobbyDAO().insert(id);
			waitRoomDb();
			new Lobby_Main(cn);
			signal();
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) { // ���� �����
		// TODO Auto-generated method stub
		waitRoomDb();
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