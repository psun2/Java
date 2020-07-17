package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import test.WhileFunctionTest;

class GamePanel extends JPanel {

	static final int puyoSize = 50;
	static final int width = puyoSize * 6;
	static final int height = puyoSize * 12;

	JLabel me; // 중심이 되는 뿌요
	JLabel you; // 그 옆에 있는 뿌요

	boolean chk; // 뿌요가 안착하면 다음 뿌요 생성을 위하여, chk

	ArrayList<JLabel> puyos;

	public GamePanel() {
		// TODO Auto-generated constructor stub

		this.chk = true;

		setLayout(null);
		// getContentPane().setBackground(Color.pink); // 임시 : 개발중 알아보기 편하도록 프레임 색깔 설정
		setBackground(Color.pink); // 임시 : 개발중 알아보기 편하도록 프레임 색깔 설정

		// createPuyo();

		// test();

		new WhileFunctionTest(this);

	}

	void test() {

		while (true) {
			JLabel asd = new JLabel("안보여");
			asd.setBounds(100, 100, width, height);
			add(asd);
		}
	}

	void createPuyo() { // 뿌요의 계속적인 생산을 위하여 쓰레드 사용

		this.puyos = new ArrayList<JLabel>();

		// (300 / 2) - (50 / 2)
		int LocationX = (width / 2) - (puyoSize / 2);
		int LocationY = puyoSize;

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

//				while (true) { // 여기서 화면이 안보임.....

				if (chk) {

					// 기준이 되는 me 생성
					GamePanel.this.me = new JLabel("me");
					me.setBounds(LocationX, LocationY, puyoSize, puyoSize);
					GamePanel.this.add(GamePanel.this.me);
					me.setBackground(Color.red); // 임시로 컬러 설정
					me.setOpaque(true);
					puyos.add(me);

					// you 생성
					GamePanel.this.you = new JLabel("me");
					you.setBounds(LocationX, LocationY - puyoSize, puyoSize, puyoSize);
					GamePanel.this.add(GamePanel.this.you);
					you.setBackground(Color.cyan); // 임시로 컬러 설정
					you.setOpaque(true);
					puyos.add(you);
					move();
				}

				chk = false;

//				}

			}
		};
		thread.run();
	}

	void move() {

		System.out.println("진입");

		int step = 5;
		int current = me.getY();

		while (true) {

			try {
				Thread.sleep(1000);
				me.setLocation(me.getX(), current + step);
				current = me.getY();
				System.out.println(current);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}

class GameInfo extends JPanel {

	public GameInfo() {
		// TODO Auto-generated constructor stub

		JLabel score = new JLabel("점수");
		JLabel combo = new JLabel("콤보");
		JLabel time = new JLabel("경과 시간");

		add(score);
		add(combo);
		add(time);

	}

}

public class PanelTest extends JFrame {


	GamePanel game;
	GameInfo info;

	public PanelTest() {
		// TODO Auto-generated constructor stub
		setSize(GamePanel.width, GamePanel.height + 30); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		setLayout(new BorderLayout()); // 레이아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon().getImage()); // 타이틀바 로고 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

		// 쓰레드를 동작하기 위해 setVisible 뒤에 각 해당 패널 생성

		// Center
		this.game = new GamePanel(); // 게임 패널 생성
		game.setPreferredSize(new Dimension(GamePanel.width, GamePanel.height));
		// 레이아웃이 잡혀 있기 때문에 setPreferredSize 통해 게임 패널 사이즈 설정
		add(game, "Center");
		// addKeyListener(new ActionKey(game)); // 키보드 이벤트 추가

		// System.out.println(game.getPreferredSize());
		// java.awt.Dimension[width=300,height=600]

		// Bottom
		this.info = new GameInfo(); // 정보 표시 패널 생성
		info.setPreferredSize(
				new Dimension(game.getPreferredSize().width, getSize().height - game.getPreferredSize().height));
		// 레이아웃이 잡혀 있기 때문에 setPreferredSize 통해 정보표시창 사이즈 설정
		// System.out.println(info.getPreferredSize());
		// java.awt.Dimension[width=300,height=30]
		add(info, "South");

	}

	public static void main(String[] args) {
		new PanelTest();
	}

}
