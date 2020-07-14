package puyopuyogame_failed2;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PuyoFrame extends JFrame {

	boolean chk = true; // 뿌요 생성
	int width = 50;
	int height = 50;
	JLabel asd;
	Puyo currentPuyo;
	JLabel mePuyo;
	JLabel youPuyo;

	public PuyoFrame() {
		// TODO Auto-generated constructor stub
		setSize(500, 600); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
//		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		setLayout(null); // 레이아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon().getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.pink); // 임시 : 개발중 알아보기 편하도록 프레임 색깔 설정

		addKeyListener(new ActionKey(this));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

		gameStart();

		// 쓰레드를 돌려서 생성된 뿌요를 계속 아래로 진입 시켜야 함.... => 밑으로 떨어지는건 완성이 됬으니 게임 시작 버튼을 눌렀을때....
		// 게임이 진행되도록...
		// tip : 나와 상대방이 모두 준비 완료 버튼을 눌렀을시 게임이 시작되므로 서버에서 나에게 무엇인가를 던져 줘야 가능 -
		// 2020-07-14

	}

	void gameStart() {

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) {

					if (chk) {

						PuyoFrame.this.youPuyo = new JLabel("you");
						PuyoFrame.this.youPuyo.setBounds(250, 0, width, height);
						PuyoFrame.this.youPuyo.setBackground(Color.red);
						PuyoFrame.this.youPuyo.setOpaque(true);

						PuyoFrame.this.mePuyo = new JLabel("me");
						PuyoFrame.this.mePuyo.setBounds(250, youPuyo.getSize().width, width, height);
						PuyoFrame.this.mePuyo.setBackground(Color.blue);
						PuyoFrame.this.mePuyo.setOpaque(true);

						PuyoFrame.this.add(youPuyo);
						PuyoFrame.this.add(mePuyo);
						// move();

					}

					chk = false; // 뿌요가 자리에 정착할때까지 생성되지 않아야 함.
					// 뿌요가 정착이 되면 true 바뀌어 다음 뿌요가 재생

				}

			}
		};

		thread.run();
	}

	void move() {

		// System.out.println("move 진입 확인");

		int step = mePuyo.getSize().width;

		while (true) {


			try {

				int youX = this.youPuyo.getX();
				int youY = this.youPuyo.getY();
				int meX = this.mePuyo.getX();
				int meY = this.mePuyo.getY();

				Thread.sleep(1000);

				youPuyo.setLocation(youX, youY + step);
				youY = youPuyo.getY();

				mePuyo.setLocation(meX, meY + step);
				meY = mePuyo.getY();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PuyoFrame();
	}

}
