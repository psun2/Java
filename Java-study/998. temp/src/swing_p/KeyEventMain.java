package swing_p;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyEventMain extends JFrame implements KeyListener {

	int xpos, ypos, width, height;
	JLabel me, you;

	public KeyEventMain() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		this.width = 500;
		this.height = 600;
		this.xpos = (screen.width / 2) - (width / 2);
		this.ypos = (screen.height / 2) - (height / 2);

		setBounds(xpos, ypos, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("KeyEvent");
		setIconImage(new ImageIcon("./img/logo.png").getImage());
		setLayout(null);

		me = new JLabel();
		me.setBounds(250, 300, 50, 50);
		me.setBackground(Color.pink);
		me.setOpaque(true);

		add(me);

		you = new JLabel();
		you.setBounds(100, 100, 50, 50);
		you.setBackground(Color.blue);
		you.setOpaque(true);

		add(you);

		addKeyListener(this);

		setResizable(false); // 화면의 사이즈 변경을 막습니다.
		setVisible(true);

		new Timer().start();

	}

	int youX = 5, youY = 5;

	void youMove() {
		int x = you.getX() + youX, y = you.getY() + youY;

		if (x < 0 || x > 500 - 60)
			youX *= -1;
		if (y < 0 || y > 600 - 85)
			youY *= -1;

		you.setLocation(x, y);
	}

//	void youMove() {
//
//		int x = you.getX() + (int) (Math.random() * 5);
//		int y = you.getY() + (int) (Math.random() * 5);
//		boolean chk = false;
//
//		if (x < 0)
//			x = 0;
//		if (y < 0)
//			y = 0;
//		if (x > 430 && y > 505)
//			youBack();
//
//		you.setLocation(x, y);
//
//	}
//
//	void youBack() {
//
//		while (true) {
//			int x = you.getX() - (int) (Math.random() * 5);
//			int y = you.getY() - (int) (Math.random() * 5);
//			try {
//				Thread.sleep(33);
//				you.setLocation(x, y);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			if (x < 0 && y < 0)
//				return;
//		}
//
//	}

	public static void main(String[] args) {

		new KeyEventMain();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) { // 누굴 눌렀는지 알 수 잇습니다.
		// TODO Auto-generated method stub
//		System.out.println(e);

//		System.out.println(e.getKeyChar()); // 키보드의 방향키는 입력 되지 않습니다.

		int x = me.getX();
		int y = me.getY();

		int key = e.getKeyCode();
//		System.out.println(key);

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
		default:
			x = 250;
			y = 300;
			break;
		}

		if (x < 0)
			x = 0;
		if (x > 500 - 70)
			x = 500 - 70;
		if (y < 0)
			y = 0;
		if (y > 600 - 95)
			y = 600 - 95;

		me.setLocation(x, y);
	}

	class Timer extends Thread {
		@Override
		public void run() {
			while (true) {
				try {

					sleep(33); // 움직임을 부드럽게 하는 정도

					youMove();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
