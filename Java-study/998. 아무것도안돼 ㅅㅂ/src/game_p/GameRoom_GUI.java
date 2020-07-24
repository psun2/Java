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

		this.readyChk = false;
		this.enemy = null;

		System.out.println("Frame 시작 == 게임 시작");
		setSize(width, height); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		getContentPane().setLayout(null); // 레이2아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // 타이틀바 로고 설정
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

		JToggleButton ready = new JToggleButton("준비");
		JButton exit = new JButton("나가기");

		// 버튼 간격 50;
		// 버튼 총 길이 130;
		// 남은 길이 520;
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
				temp.setText("준비");
				readyChk = false;
			} else {
				temp.setText("준비 완료");
				readyChk = true;
				temp.setEnabled(false);
				cn.send(data); // 생성과 동시에 리시버로 받기 때문에 여긴 나의 정보가 있겟지 ?
				System.out.println("여기서 데이터를 못 가져오면 너무 힘들어요 !!" + data);

				// 내가 준비일때 상대방의 통신상태를 받아
				// 게임 이시작 됨....
				// me 패널과 you 패널을 리무브
//            GameRoom_GUI.this.remove(meP);
//            GameRoom_GUI.this.remove(youP);
//
//            me = new MePuyoPanel();
//            me.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
//            GameRoom_GUI.this.addKeyListener(new ActionKey(me));
//            GameRoom_GUI.this.add(me);
//
//            YouPuyoPanel you = new YouPuyoPanel();
//            you.setBounds(Puyo.PUYOSIZE * 10, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
//            GameRoom_GUI.this.add(you);
//
//            GameRoom_GUI.this.setVisible(false);
//            GameRoom_GUI.this.setVisible(true);
//            GameRoom_GUI.this.me.addKeyListener(new ActionKey(me));

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
	public void reciver(DDongData dd) { // 로직 수정이 필요 할 수도 ....
		// TODO Auto-generated method stub
		
		System.out.println(dd + "똥");

		if (dd.type.equals("게임")) {

			if (dd.src.equals(cn.id)) {

				this.data = dd;
				System.out.println(dd);

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
					cn.send(dd); // 받은걸 그대로 주어 통신을 이어 가도록....
					// 이걸 생성된 내가 받을 수 있나 모르겠네 .... ?
				}

			}

		}

	}

}