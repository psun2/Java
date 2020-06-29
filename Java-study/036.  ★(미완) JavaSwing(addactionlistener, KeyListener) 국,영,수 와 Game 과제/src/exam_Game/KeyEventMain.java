package exam_Game;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyEventMain extends JFrame implements KeyListener {

	JLabel me;

	ArrayList<Enemy> you;

	boolean chk = false;

	class Enemy extends JLabel { // inner 이면서 JLabel 을 상속 받는 적을 생성하는 클래스 선언

		int size;

		int dirX, dirY, x, y;

		public Enemy() {

			dirX = (int) (Math.random() * 7 + 1) * dirGo(); // 1 ~ 7 + random -1 또는 1을 반환 받아 음수 혹은 양수로 ....
			dirY = (int) (Math.random() * 7 + 1) * dirGo();

			x = (int) (Math.random() * 200 + 200); // 200 ~ 499 까지 random 위치
			y = (int) (Math.random() * 200 + 200); // 200 ~ 499 까지 random 위치

			size = (int) (Math.random() * 5) * 5 + 30; // 최소 30 ~ (4 * 5 + 30) 50 까지의 size 를 가짐

			setBounds(x, y, size, size);
			setBackground(new Color((int) (Math.random() * 200 + 55), (int) (Math.random() * 200 + 55),
					(int) (Math.random() * 200 + 55))); // 색깔도 랜덤
			setOpaque(true);

			KeyEventMain.this.add(this); // 생성이 될때 frame 에 추가
		}

		int dirGo() {
			// 생성시 처음에만 작동을함... 즉... 몇칸을 가느냐....즉 speed 가 랜덤....
			// 음수와 양수를 랜덤으로 줌으로써 ... 방향또한 랜덤....
			int res = (int) (Math.random() * 2); // 0 과 1 의 난 수를 발생
			if (res == 0)
				return -1;

			return 1;
		}

		void move() {

			x = getX() + dirX; // move 즉 움직일때 현재의 좌표에서 speed 의 칸수맞큼을 더함..
			y = getY() + dirY;

			if (x < 0 || x > 500 - 10 - size)
				dirX *= -1; // 가로축을 벗어나려 한다면
//				x  = getX(); // 이렇게 되면 한군데로 붙어 버림
//				dirX *= dirGo(); // 개인적으론 이런 편이 랜덤의 취지에 맞다고 생각...
			if (y < 0 || y > 600 - 35 - size)
				dirY *= -1; // 세로축을 벗어나려 한다면 ....
//				y = getY(); // 이렇게 되면 한군데로 붙어 버림
//				dirY *= dirGo(); // 개인적으론 이런 편이 랜덤의 취지에 맞다고 생각...

			setLocation(x, y);

			if (x < me.getX() + 50 && x + size > me.getX() && y < me.getY() + 50 && y + size > me.getY()) {
				// 범위 안에 포함된다를 표현
				// 즉 me의 두 x 좌표와 두 y 좌표를 생각해봐야함....
				chk = true;
				System.out.println("게임오버");
			}
		}

	}

	public KeyEventMain() {

		int youCnt = 5;
		setBounds(100, 50, 500, 600);
		setTitle("KeyEvent");
		setLayout(null);

		me = new JLabel("ME");
		me.setBounds(0, 0, 50, 50);
		me.setBackground(Color.pink);
		me.setOpaque(true);

		add(me);

		you = new ArrayList<KeyEventMain.Enemy>();

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
		// 위와 같은 방법 외에도....
		// removeKeyListener(this); // 이런 방법도 존재하나....
		// 이벤트를 지우는것은 완전히 필요가 있지 않는 이상.... 굉장한 리스크를 가질것 같으므로,,, 제 생각엔 비추됩니다.

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
		new KeyEventMain();
	}

}