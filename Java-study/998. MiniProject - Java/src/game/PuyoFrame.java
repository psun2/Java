package game;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PuyoFrame extends JFrame {

	// PuyoGameInfo info;
	PuyoPanel me;
	YouPuyoPanel you;
//	final int width = 306; // 싱글 
	final int width = 605; // 멀티
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

		me = new PuyoPanel();
		me.setBounds(Puyo.PUYOSIZE * 6, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
		add(me);

		addKeyListener(new ActionKey(me));

//		you = new YouPuyoPanel();
//		you.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE * 13);
//		add(you);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

	}

	public static void main(String[] args) {
		new PuyoFrame();
	}

}
