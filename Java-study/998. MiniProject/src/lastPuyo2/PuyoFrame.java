package lastPuyo2;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PuyoFrame extends JFrame {

	PuyoGameInfo info;
	PuyoPanel main;
	final int width = 306;
	final int height = 680;

	public PuyoFrame() {
		// TODO Auto-generated constructor stub
		System.out.println("Frame 시작 == 게임 시작");
		setSize(width, height); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		getContentPane().setLayout(null); // 레이2아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon("./img/logo.png").getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.blue);

		info = new PuyoGameInfo();
		info.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE);
		add(info);

		main = new PuyoPanel();
		main.setBounds(0, 50, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 12);
		add(main);

		addKeyListener(new ActionKey(main));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

		new PuyoTimer(this).start();
		// new getSiz().start();

	}

	public static void main(String[] args) {
		new PuyoFrame();
	}

	class getSiz extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			while (true) {

				try {
					sleep(1000);
					System.out.println(getSize());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

}
