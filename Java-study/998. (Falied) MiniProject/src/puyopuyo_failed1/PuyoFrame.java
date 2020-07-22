package puyopuyo_failed1;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PuyoFrame extends JFrame {

	int width = 50;
	int height = 50;
	JLabel asd;
	Puyo currentPuyo;

	public PuyoFrame() {
		// TODO Auto-generated constructor stub
		setSize(500, 600); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
//		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		setLayout(null); // 레이아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon().getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.pink); // 임시 : 개발중 알아보기 편하도록 프레임 색깔 설정

		addKeyListener(new KeyEvent(this, currentPuyo));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

		// 쓰레드를 돌려서 생성된 뿌요를 계속 아래로 진입 시켜야 함.... => 밑으로 떨어지는건 완성이 됬으니 게임 시작 버튼을 눌렀을때....
		// 게임이 진행되도록...
		// tip : 나와 상대방이 모두 준비 완료 버튼을 눌렀을시 게임이 시작되므로 서버에서 나에게 무엇인가를 던져 줘야 가능 -
		// 2020-07-14
		this.currentPuyo = new Puyo(); // cuerrent Puyo
		// currentPuyo.setLocation(250, 0); => 레이아웃이 널이라 로케이션만 잡아주면 안 보임
		// 그래서 .... 키보드로 뿌요의 방향을 변경하고자 할때 현재의 뿌요 패널의 크기와 위치를 건들어 주어야함
		// 결론 : 키이벤트 클래스에 현재의 커런트 뿌요 클래스도 가지고 들어가야함.
		// but 뿌요가 생성 되었는지 안되 었는지 알 수 가 없다.
		currentPuyo.setBounds(250, 0, 50, 100);
		add(currentPuyo);
		move();

	}

	void move() { // 뿌요가 아래로 계속 떨어 지도록 하는 move 메소드

		Runnable thread = new Runnable() { // 쓰레드를 사용하여 계속적으로 떨어지게 함

			int step = 5; // 밑으로 내려갈때 y의 변화값 5씩 늘어남
			int currentY = 0; // default(초기값) 0, 현재의 y 값
			boolean chk = true; // 뿌요가 진행 될곳이 없을때 false로 바뀌어 뿌요의 아래로의 진행을 막는다.

			@Override
			public void run() {
				// TODO Auto-generated method stub

				// System.out.println("진입");

				while (chk) {

					// System.out.println("while 진입");

					// setLocation 을 통해 뿌요 패널의 위치를 계속하여 내림 x의 값은 변동 없이 키보드 로만 사용
					PuyoFrame.this.currentPuyo.setLocation(PuyoFrame.this.currentPuyo.getX(), currentY + step);

					currentY = PuyoFrame.this.currentPuyo.getY(); // 현재의 y값을 지금의 y 값으로 변경

					try {
						Thread.sleep(33);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		};

		thread.run(); // move 함수가 실행 될시 내부에 있는 쓰레드를 런
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PuyoFrame();
	}

}
