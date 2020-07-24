package game_p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import ddong.ClientNetWork;
import ddong.DDongData;
import ddong.DDongInter;

public class GameRoom_GUI extends JFrame implements DDongInter {

	final int width = 806;
	final int height = 679 + 50;
	boolean readyChk = false;

	public ClientNetWork cn;

	JPanel meP, youP, state;
	JLabel meLb, youLb;

	String enemy;

	DDongData data;

	public GameRoom_GUI() {
		// TODO Auto-generated constructor stub

		cn = new ClientNetWork("asdasd");
		
		this.readyChk = false;
		this.enemy = null;

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
		add(meLb);

		state = new JPanel();
		state.setLayout(null);
		state.setBounds(Puyo.PUYOSIZE * 6, 0, Puyo.PUYOSIZE * 4, Puyo.PUYOSIZE * 13);
		state.setBackground(Color.red);
		add(state);

		System.out.println(state.getSize()); // 200 , 650

		JToggleButton ready = new JToggleButton("�غ�");
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

	}

	class ReadyBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			JToggleButton temp = (JToggleButton) e.getSource();

			if (readyChk) {
				temp.setText("�غ�");
				readyChk = false;
			} else {
				temp.setText("�غ� �Ϸ�");
				readyChk = true;
				temp.setEnabled(false);
				cn.send(data); // ������ ���ÿ� ���ù��� �ޱ� ������ ���� ���� ������ �ְ��� ?
				System.out.println("���⼭ �����͸� �� �������� �ʹ� ������ !!" + data);

				// ���� �غ��϶� ������ ��Ż��¸� �޾�
				// ���� �̽��� ��....
				// me �гΰ� you �г��� ������
//				GameRoom_GUI.this.remove(meP);
//				GameRoom_GUI.this.remove(youP);
//
//				me = new MePuyoPanel();
//				me.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
//				GameRoom_GUI.this.addKeyListener(new ActionKey(me));
//				GameRoom_GUI.this.add(me);
//
//				YouPuyoPanel you = new YouPuyoPanel();
//				you.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
//				GameRoom_GUI.this.add(you);
//
//				GameRoom_GUI.this.setVisible(false);
//				GameRoom_GUI.this.setVisible(true);
//				GameRoom_GUI.this.me.addKeyListener(new ActionKey(me));

			}

		}

	}

	class ExitBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			GameRoom_GUI.this.dispose();

		}

	}

	public static void main(String[] args) {
		new GameRoom_GUI();
	}

	@Override
	public void reciver(DDongData dd) { // ���� ������ �ʿ� �� ���� ....
		// TODO Auto-generated method stub

		if (dd.type.equals("����")) {

			if (dd.src.equals(cn.id)) {

				this.data = dd;

				if (dd.dst.size() == 2) {

					for (String id : dd.dst) {
						if (!cn.id.equals(id))
							this.enemy = id;
					}

				}

			}

			if (enemy != null && this.readyChk) {

				if (dd.src.equals(enemy) && dd.chk) {
					GameRoom_GUI.this.dispose();
					PuyoFrame goGame = new PuyoFrame();
					goGame.cn = cn;
					cn.ddInter = goGame;
					cn.send(dd); // ������ �״�� �־� ����� �̾� ������....
					// �̰� ������ ���� ���� �� �ֳ� �𸣰ڳ� .... ?
				}

			}

		}

	}

}
