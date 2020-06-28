package exam_Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Game extends JFrame implements KeyListener {

	JLabel timeLb, me;
	ArrayList<JLabel> enemy;
	boolean death;
	int meXpos, meYpos;

	public Game(String title) {

		this.enemy = new ArrayList<JLabel>();
		this.death = true;
		this.meXpos = 225;
		this.meYpos = 235;

		setTitle(title);
		setIconImage(new ImageIcon("./img/" + title + ".png").getImage());
		setLayout(null);
		init();
	}

	void init() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 500;
		int height = 600;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addKeyListener(this);

		timeLb = new JLabel("Game Start");
		timeLb.setBounds(20, -30, 100, 100);
//		timeLb.setHorizontalAlignment(JLabel.CENTER);

		add(timeLb);

		Color[] colors = { new Color(255, 0, 0), new Color(255, 50, 0), new Color(255, 255, 0), new Color(0, 255, 0),
				new Color(0, 0, 255), new Color(0, 5, 255), new Color(100, 0, 255) };
		int index = (int) (Math.random() * 6) + 1;
		me = new JLabel();
		me.setBounds(this.meXpos, meYpos, 50, 50);
		me.setBackground(colors[index]);
		me.setOpaque(true);

		add(me);

		for (int i = 0; i < 5; i++) { // 적 생성
			JLabel you = new JLabel();
			you.setBackground(Color.cyan);
			you.setOpaque(true);

			you.setBounds(xpos(), ypos(), 50, 50); // 시작시 적위치 랜덤

			add(you);
			this.enemy.add(you);
		}

		setVisible(true);

		new Timer().start();
		new YouMove().start();
	}

	int xpos() {
		return (int) (Math.random() * 420) + 10;
	}

	int ypos() {
		return (int) (Math.random() * 500) + 10;
	}

	void youMove() {

		for (JLabel en : enemy) {

			int x = en.getX() + movePos();
			int y = en.getY() + movePos();

			if (x < 10 || x > 420)
				x = en.getX();

			if (y < 10 || y > 500)
				y = en.getY();

			en.setLocation(x, y);

		}
	}

	int movePos() {

		int result = (int) (Math.random() * 2) + 1;

		if (result == 1)
			result = 10;
		else
			result = -10;

		return result;

	}

	// e.getKeyChar() : 눌린 키의 문자를 리턴

	// e.getKeyCode() : 눌린 키의 아스키코드를 리턴

	// e.getModifiers() : Shift, Ctrl, Alt키 인식 각각 1,2,8 리턴

	@Override
	public void keyTyped(KeyEvent e) { // 키보드를 누른순간 호출
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) { // 키보드를 눌렀다 뗏을 경우 호출
		// TODO Auto-generated method stub

		int x = me.getX();
		int y = me.getY();

		int key = e.getKeyCode();
//		System.out.println(key);

		switch (key) {

		case KeyEvent.VK_UP:
//			System.out.println("위");
//			System.out.println(me.getLocation());
			y -= 10;
			this.meYpos = y;
			break;
		case KeyEvent.VK_DOWN:
//			System.out.println("아래");
//			System.out.println(me.getLocation());
			y += 10;
			this.meYpos = y;
			break;
		case KeyEvent.VK_LEFT:
//			System.out.println("왼쪽");
//			System.out.println(me.getLocation());
			x -= 10;
			this.meXpos = x;
			break;
		case KeyEvent.VK_RIGHT:
//			System.out.println("오른쪽");
//			System.out.println(me.getLocation());
			this.meXpos = x;
			x += 10;
			break;

		}

//		y  = 10 ~ 500
//		x = 10 ~ 420

		if (x < 10)
			x = 10;
		if (x > 420)
			x = 420;
		if (y < 10)
			y = 10;
		if (y > 500)
			y = 500;

		me.setLocation(x, y);

	}

	@Override
	public void keyReleased(KeyEvent e) { // 키보드를 눌렀을 때 호출// 모든 키보드의 키에 반응
		// TODO Auto-generated method stub

	}

	class Timer extends Thread {

		@Override
		public void run() {

			int time = 0;

			while (Game.this.death) {

				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				time++;

				Game.this.timeLb.setText("경과 시간 : " + time + "초");

			}

		}

	}

	class YouMove extends Thread {

		@Override
		public void run() {

			while (Game.this.death) {

				death();

				Game.this.youMove();

				try {
					sleep(33); // 움직임이 최대한 자연스러운 지연시간
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			Game.this.removeKeyListener(Game.this); // 게임이 종료되면 키를 못움직이게 키 이벤트 삭제

		}

		void death() {

			// 적과 나의 사이즈는 50 X 50;

			int mex = Game.this.meXpos;
			int mex2 = Game.this.meXpos + 50;
			int mey = Game.this.meYpos;
			int mey2 = Game.this.meYpos + 50;

			for (JLabel en : enemy) {

				if (mex <= en.getX() && mex2 >= en.getX()
						|| mex <= en.getX() + en.getSize().width && mex2 >= en.getX() + en.getSize().width)
					if (mey <= en.getY() && mey2 >= en.getY()
							|| mey <= en.getY() + en.getSize().height && mey2 >= en.getY() + en.getSize().height)
						Game.this.death = false;

			}
		}

	}
}

public class Main {

	public static void main(String[] args) {

		new Game("Game");

	}

}
