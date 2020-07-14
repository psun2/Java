package puyopuyo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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

public class PuyoFrame extends JFrame {

	GameInfo info;

	int puyoSize = 50;
	int width = 316;
	int height = puyoSize * 12;

	JLabel me; // 중심이 되는 뿌요
	JLabel you; // 그 옆에 있는 뿌요

	boolean chk = true; // 뿌요가 안착하면 다음 뿌요 생성을 위하여, chk

	ArrayList<JLabel> puyos;

	public ExecutorService threadPool;

	public PuyoFrame() {
		// TODO Auto-generated constructor stub

		setSize(width, height + 30); // 프레임 사이즈
		setLocationRelativeTo(null); // 프레임 시작시 모니터 중앙에 출력
		setResizable(false); // 프레임 사이즈 조절 할 수 없음
		setLayout(null); // 레이아웃
		setTitle("뿌요뿌요"); // 타이틀
		setIconImage(new ImageIcon().getImage()); // 타이틀바 로고 설정
		getContentPane().setBackground(Color.pink);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫기 옵션
		setVisible(true); // 프레임을 보여줌

		addKeyListener(new ActionKey(PuyoFrame.this));
		threadPool = Executors.newCachedThreadPool(); // 쓰레드풀 초기화
		createPuyo();
		// getFrameSize();

	}

	void createPuyo() { // 뿌요의 계속적인 생산을 위하여 쓰레드 사용

		this.puyos = new ArrayList<JLabel>();

		int LocationX = 150;
		int LocationY = puyoSize;

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) {

					// 기준이 되는 me 생성
					PuyoFrame.this.me = new JLabel("me");
					me.setBounds(LocationX, LocationY, puyoSize, puyoSize);
					PuyoFrame.this.add(PuyoFrame.this.me);
					me.setBackground(Color.red); // 임시로 컬러 설정
					me.setOpaque(true);
					puyos.add(me);

					// you 생성
					PuyoFrame.this.you = new JLabel("you");
					you.setBounds(LocationX, LocationY - puyoSize, puyoSize, puyoSize);
					PuyoFrame.this.add(PuyoFrame.this.you);
					you.setBackground(Color.cyan); // 임시로 컬러 설정
					you.setOpaque(true);
					puyos.add(you);

					System.out.println(puyos.size());

					move();
				}

			}
		};
		threadPool.submit(thread);
	}

	void move() {

		int step = 3;
		int meY = me.getY();
		int youY = you.getY();
		boolean meChk = true;
		boolean youChk = true;

		while (true) {

			try {

				if (!meChk && !youChk) {
					return;
				}

				Thread.sleep(33);

				// 높이 수정 요망
				if (meY < getSize().height - 100) {
					me.setLocation(me.getX(), meY + step);
					meY = me.getY();
				} else {
					meChk = false;
					this.me = null;
				}

				if (youY < getSize().height - 100) {
					you.setLocation(you.getX(), youY + step);
					youY = you.getY();
				} else {
					youChk = false;
					this.you = null;
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	void getFrameSize() {

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {

					System.out.println(getSize().width);
				}
			}
		};
		threadPool.submit(thread);

	}

	public static void main(String[] args) {
		new PuyoFrame();
	}

}
