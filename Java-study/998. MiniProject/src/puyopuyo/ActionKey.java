package puyopuyo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionKey implements KeyListener {

	PuyoFrame frame;

	int meX;
	int meY;

	int youX;
	int youY;

	public ActionKey(PuyoFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		meX = frame.me.getX();
		meY = frame.me.getY();

		youX = frame.you.getX();
		youY = frame.you.getY();

		int key = e.getKeyCode();

		if (frame.me == null || frame.you == null)
			return;

		switch (key) {

		case KeyEvent.VK_LEFT:
			System.out.println("왼쪽");
			meX -= frame.puyoSize;
			youX -= frame.puyoSize;
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("오른쪽");
			meX += frame.puyoSize;
			youX += frame.puyoSize;
			break;
		case KeyEvent.VK_DOWN:
			downKey();
			break;
		case KeyEvent.VK_UP:
			break;
		}

		if (meX < 0)
			meX = 0;
		if (meX > frame.getSize().width - frame.puyoSize)
			meX = frame.getSize().width - frame.puyoSize;
		if (youX < 0)
			youX = 0;
		if (youX > frame.getSize().width - frame.puyoSize)
			youX = frame.getSize().width - frame.puyoSize;

		frame.you.setLocation(youX, youY);
		frame.me.setLocation(meX, meY);

		System.out.println(youY + ", " + meY);

	}

	void downKey() {

		if (youX == meX) { // me 와 you가 y툭으로 일직선 즉 세로 방향 일때
			// System.out.println("youX : " + youX); // 통과
			// System.out.println("meX : " + meX); // 통과

			if (youY < meY) { // you가 me 보다 위에 존재 할때
				// you의 x 가 me의 오른쪽으로...
				// you의 y 가 me와 같음...
				youX = meX + frame.puyoSize;
				// System.out.println("youX : " + youX); // 통과
				// System.out.println("meX : " + meX); // 통과
				youY = meY;
				System.out.println("youY : " + youY);
				System.out.println("meY : " + meY);
				frame.you.setLocation(youX, youY);
			} else { // you가 me 보다 아래 존재 할때
				// you의 x 가 me의 왼쪽으로...
				// you의 y 가 me와 같음...
				youX = meX - frame.puyoSize;
				youY = meY;
			}

		} else { // me 와 you가 x툭으로 일직선 즉 가로 방향 일때

			if (youX < meX) { // you가 me 의 왼쪽에 존재 할때
				// you의 x가 me 와 같고
				// you의 y가 me의 위에 있음
				youX = meX;
				youY = meY - frame.puyoSize;
			} else { // you가 me 의 오른쪽에 존재 할때
				// you의 x가 me 와 같고
				// you의 y가 me의 아래에 있음
				youX = meX;
				youY = meY + frame.puyoSize;
			}

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
