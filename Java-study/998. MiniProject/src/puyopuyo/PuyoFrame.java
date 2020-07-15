package puyopuyo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PuyoFrame extends JFrame {

	ArrayList<Puyo> puyos;
	Puyo me;
	Puyo you;
	int puyoSize = 50;
	boolean chk = true; // 다음 뿌요 생성을 위한 값

	public ExecutorService threadPool;

	public PuyoFrame() {
		// TODO Auto-generated constructor stub
		setSize(500, 500); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		setLayout(null); // 레이아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon().getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.pink); // 임시 : 개발중 알아보기 편하도록 프레임 색깔 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

		createPuyo();
	}

	void createPuyo() { // 뿌요의 계속적인 생산을 위하여 쓰레드 사용

		this.puyos = new ArrayList<Puyo>(); // 뿌요들이 들어갈 공간 초기화
		threadPool = Executors.newCachedThreadPool(); // 쓰레드풀 초기화
		int LocationX = 150;
		int LocationY = puyoSize;

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) {

					if (chk) {
						// 기준이 되는 me 생성
						PuyoFrame.this.me = new Puyo(PuyoFrame.this, "me", Color.red);
						me.setBounds(LocationX, LocationY, puyoSize, puyoSize);
						PuyoFrame.this.add(PuyoFrame.this.me);
						puyos.add(me);

						// you 생성
						PuyoFrame.this.you = new Puyo(PuyoFrame.this, "you", Color.cyan);
						you.setBounds(LocationX, LocationY - puyoSize, puyoSize, puyoSize);
						PuyoFrame.this.add(PuyoFrame.this.you);
						puyos.add(you);

						// System.out.println(me.hashCode());
						// System.out.println(you.hashCode());

						me.move();
						you.move();
					}
				}

			}
		};
		threadPool.submit(thread);
	}

	public static void main(String[] args) {
		new PuyoFrame();
	}

}
