package swing_p;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Teacher_Game extends JFrame implements KeyListener {

	JLabel me;

	ArrayList<Enemy> you;

	boolean chk = false;

	class Enemy extends JLabel {

		int size;

		int dirX, dirY, x, y;

		int dirGo() {
			int res = (int) (Math.random() * 2);
			if (res == 0)
				return -1;

			return 1;
		}

		public Enemy() {
			dirX = (int) (Math.random() * 7 + 1) * dirGo();
			dirY = (int) (Math.random() * 7 + 1) * dirGo();
			x = (int) (Math.random() * 200 + 200);
			y = (int) (Math.random() * 200 + 200);

			size = (int) (Math.random() * 5) * 5 + 30;

			setBounds(x, y, size, size);
			setBackground(new Color((int) (Math.random() * 200 + 55), (int) (Math.random() * 200 + 55),
					(int) (Math.random() * 200 + 55)));
			setOpaque(true);

			Teacher_Game.this.add(this);
		}

		void move() {
			x = getX() + dirX;
			y = getY() + dirY;

			if (x < 0 || x > 500 - 10 - size)
				dirX *= -1;
			if (y < 0 || y > 600 - 35 - size)
				dirY *= -1;

			setLocation(x, y);

			if (x < me.getX() + 50 && x + size > me.getX() && y < me.getY() + 50 && y + size > me.getY()) {
				chk = true;
				System.out.println("게임오버");
			}
		}

	}

	public Teacher_Game() {

		int youCnt = 5;
		setBounds(100, 50, 500, 600);
		setTitle("KeyEvent");
		setLayout(null);

		me = new JLabel("ME");
		me.setBounds(0, 0, 50, 50);
		me.setBackground(Color.pink);
		me.setOpaque(true);

		add(me);

		you = new ArrayList<Teacher_Game.Enemy>();

		for (int i = 0; i < youCnt; i++) {
			you.add(new Enemy());
		}

		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addKeyListener(this);

		new Timer().start();
	}

	class Timer extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					sleep(33);

					for (Enemy en : you) {
						en.move();
					}
					if (chk)
						break;

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int key = e.getKeyCode();

		// System.out.println(key);
		if (chk) // 부딪혓다면 키를 못움직이게 계속 return 시켜 버림
			return;

		int x = me.getX(), y = me.getY();

		switch (key) {

		case KeyEvent.VK_UP:
			System.out.println("위");
			y -= 10;
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("아래");
			y += 10;
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("왼쪽");
			x -= 10;
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("오른쪽");
			x += 10;
			break;
		}

		if (x < 0)
			x = 0;
		if (x > 500 - 60)
			x = 500 - 60;
		if (y < 0)
			y = 0;
		if (y > 600 - 85)
			y = 600 - 85;

		me.setLocation(x, y);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new Teacher_Game();
	}

}
