package game_p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
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
import jdbc_p.LobbyDTO;
import lobby_p.Lobby_Main_Clear;

public class GameRoom_GUI extends JFrame implements DDongInter {

	final int width = 806;
	final int height = 679 + 50;

	boolean meChk, youChk;

	public ClientNetWork cn;
	JToggleButton ready;
	JPanel meP, youP, state;
	JLabel meLb, youLb;

	public Integer roomNum;
	public String id;
	String enenmy;

	DDongData data;

	// HashSet<String> dst;

	ExecutorService threadPool;

	public GameRoom_GUI(String id, Integer roomNum) {
		// TODO Auto-generated constructor stub

		this.id = id;
		this.roomNum = roomNum;
		// this.dst = new HashSet<String>();
		this.meChk = false;
		this.threadPool = Executors.newCachedThreadPool();
		// this.dst.add(id);
		this.data = new DDongData(); // �� ��� ����
		data.type = "����";

		/////////////////////////////////////////////////////////////////////

		System.out.println("Frame ���� == ���� ����");
		setSize(width, height); // ������ ������
		setLocationRelativeTo(null); // ������ ���۽� ����� �߾ӿ� ���
		setResizable(false); // ������ ������ ���� �� �� ����
		getContentPane().setLayout(null); // ����2�ƿ�
		setTitle("�ѿ�ѿ�"); // Ÿ��Ʋ
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // Ÿ��Ʋ�� �ΰ� ����
		getContentPane().setBackground(Color.blue);

		meP = new JPanel();
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
		state.setBackground(Color.red);
		add(state);

		System.out.println(state.getSize()); // 200 , 650

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

		youP = new JPanel();
		youP.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		youP.setBackground(Color.green);
		add(youP);

		this.youLb = new JLabel("YOU");
		youLb.setBounds(Puyo.PUYOSIZE * 10, Puyo.PUYOSIZE * 13, Puyo.PUYOSIZE * 6, labelH);
		youLb.setHorizontalAlignment(JLabel.CENTER);
		add(youLb);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

//		chkUser();
	}

//	void chkUser() {
//
//		Runnable thread = new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				while (true) {
//
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//
//					GameRoomDTO users = new GameRoomDAO().detailroom(GameRoom_GUI.this.roomNum);
//
//					String user1 = users.getUser1();
//					String user2 = users.getUser2();
//					String[] arr = { user1, user2 };
//
//					for (String user : arr) {
//						if (user != null) {
//							if (!id.equals(user)) {
//								if (GameRoom_GUI.this.dst.size() != 2) {
//									GameRoom_GUI.this.enenmy = user;
//									GameRoom_GUI.this.dst.add(user);
//									GameRoom_GUI.this.ready.setEnabled(true);
//									System.out.println("���� �� �� �ƴѰ���  ? : " + user);
//								}
//							}
//						}
//					}
//
//				}
//
//			}
//		};
//		this.threadPool.submit(thread);
//
//	}

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

		GameRoomDTO users = new GameRoomDAO().detailroom(GameRoom_GUI.this.roomNum); // ���ȣ�� ��� ����

		String user1 = users.getUser1();
		String user2 = users.getUser2();

		System.out.println("�� : " + id);

		String[] userArr = { user1, user2 };

		for (String user : userArr) {

			if (user != null) {
				if (!id.equals(user)) {
					System.out.println(" �� : " + user);
					this.enenmy = user;
					this.data.dst = enenmy;
					youLb.setText(enenmy);
					this.ready.setEnabled(true);
				}
			} else { // null �� �ѹ� �̶� �ִٸ�
				System.out.println(" �� : " + user);
				this.data.dst = null;
				this.enenmy = null;
				youLb.setText("");
				this.ready.setEnabled(false);
			}

		}

	}

	void match() { // �غ� ��ư�� �������� ����� ������ ����� ������ ���� ���� Ȯ��

		if (this.meChk && youChk) {

			this.dispose();

			PuyoFrame game = new PuyoFrame(id, enenmy);
			game.cn = cn;
			cn.ddInter = game;

			data.type = "������";
			cn.send(data);

		}

	}

	class ReadyBtn implements ActionListener { // �غ� ��ư

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if (!GameRoom_GUI.this.meChk) {
				GameRoom_GUI.this.meChk = true;
				data.chk = true;
				cn.send(data);
			} else {
				GameRoom_GUI.this.meChk = false;
				data.chk = false;
				cn.send(data);
			}

		}

	}

	class ExitBtn implements ActionListener { // ������ ��ư send �� �ؼ� ��ư Ȱ��ȭ�� ��Ȱ��ȭ�� ���� �����

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			// �迭���� ������ ��ư ��Ȱ��ȭ
//			GameRoom_GUI.this.dst.remove(GameRoom_GUI.this.enenmy);
//			GameRoom_GUI.this.ready.setEnabled(true);

			// �κ� ���
			new LobbyDAO().insert(id);
			// �κ� ���� ��

			// ��񿡼� ����2 ����
			GameRoomDTO users = new GameRoomDAO().detailroom(GameRoom_GUI.this.roomNum);

			String user1 = users.getUser1();
			String user2 = users.getUser2();

			if (user1.equals(cn.id)) {
				new GameRoomDAO().modifyUser3(cn.id);
			} else if (user2.equals(cn.id)) {
				new GameRoomDAO().modifyUser4(cn.id);
			}
//			GameRoomDTO dto = new GameRoomDTO();
//			dto.setNo(GameRoom_GUI.this.roomNum);
//			dto.setUser1(GameRoom_GUI.this.enenmy);
//			GameRoom_GUI.this.enenmy = null;
//			dto.setUser2(GameRoom_GUI.this.id);

			// new GameRoomDAO().modifyUser3(id);
			// �� ���� ��

			GameRoom_GUI.this.dispose();
			// ������ ��2

			// �κ�� �ٽ� �Ѱ� �ֱ�
			new Lobby_Main_Clear(cn);
//			lobby.cn = GameRoom_GUI.this.cn;
//			cn.ddInter = lobby;

			cn.send(data);

		}

	}

//	public static void main(String[] args) {
//		new GameRoom_GUI("asdad", 7);
//	}

}