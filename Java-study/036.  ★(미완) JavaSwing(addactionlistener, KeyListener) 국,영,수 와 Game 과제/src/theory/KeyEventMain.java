package theory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyEventMain extends JFrame implements KeyListener {

	JLabel me, you;

	public KeyEventMain() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 500;
		int height = 600;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("KeyEvent");
		setLayout(null);
		setFont(new Font("굻림체", Font.ITALIC, 12));

		addKeyListener(this); // 키 이벤트 추가

		me = new JLabel();
		me.setBackground(Color.pink);
		me.setOpaque(true);
		me.setBounds(250, 300, 50, 50);
		add(me);

		you = new JLabel();
		you.setBackground(Color.blue);
		you.setOpaque(true);
		you.setBounds(100, 100, 50, 50);
		add(you);

		setVisible(true);

		new Enemy().start();

	}

	int youX = 5, youY = 5; // 메소드 밖의 맴버로 해줌으로써
	// 경계에 다가 왔을때 - 값으로 대체 시켜 주면 되지만... 한쪽방향씩으로
	// 계속 유지 됩니다.

	void youMove() {

		int x = you.getX() + youX, y = you.getY() + youY;

		if (x <= 10 || x > 420) {
			youX *= -1;
			System.out.println("youX : " + youX);
		}
		if (y <= 10 || y > 510) {
			youY *= -1;
			System.out.println("youY : " + youY);
		}

		you.setLocation(x, y);

	}

	// e.getKeyChar() : 눌린 키의 문자를 리턴

	// e.getKeyCode() : 눌린 키의 아스키코드를 리턴

	// e.getModifiers() : Shift, Ctrl, Alt키 인식 각각 1,2,8 리턴

	@Override
	public void keyTyped(KeyEvent e) { // 키보드를 누른순간 호출
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) { // // 키보드를 눌렀다 뗏을 경우 호출

		int x = me.getX(), y = me.getY();

		int key = e.getKeyCode();

//		System.out.println(key);

		switch (key) {
		case KeyEvent.VK_UP:
			System.out.println("위쪽");
			System.out.println(me.getLocation());
			y -= 10;
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("아래쪽");
			System.out.println(me.getLocation());
			y += 10;
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("왼쪽");
			System.out.println(me.getLocation());
			x -= 10;
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("오른쪽");
			System.out.println(me.getLocation());
			x += 10;
			break;

		}

		if (x <= 10 || x > 420)
			x = me.getX();
		if (y <= 10 || y > 510)
			y = me.getY();

		me.setLocation(x, y);

	}

	@Override
	public void keyReleased(KeyEvent e) { // 키보드를 눌렀을 때 호출// 모든 키보드의 키에 반응
		// TODO Auto-generated method stub

	}

	class Enemy extends Thread {

		@Override
		public void run() {

			while (true) {

				youMove();

				try {
					sleep(33);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // 움직이 가장 부드러운 초

			}
		}
	}

	public static void main(String[] args) {
		new KeyEventMain();
	}
}
