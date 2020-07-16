package lastPuyo.copy;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PuyoFrame extends JFrame {

	PuyoInfo info;
	PuyoPanel main;

	public PuyoFrame() {
		// TODO Auto-generated constructor stub
		setSize(512, 1024); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		// setResizable(false); // 프레임 사이즈 조절 할 수 없음
		getContentPane().setLayout(null); // 레이2아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon().getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.pink);

		info = new PuyoInfo();
		info.setBounds(0, 0, Puyo.puyoSize * 6, 50);
		add(info);

		main = new PuyoPanel();
		main.setBounds(0, 50, Puyo.puyoSize * 6, Puyo.puyoSize * 12);
		add(main);

		addKeyListener(new ActionKey(main));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

		new PuyoTimer(this).start();

	}

	public static void main(String[] args) {
		new PuyoFrame();
	}

}
