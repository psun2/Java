package puyopuyo_failed1;

import java.awt.event.KeyListener;

public class KeyEvent implements KeyListener {

	PuyoFrame frame;
	Puyo currentPuyo;

	public KeyEvent(PuyoFrame frame, Puyo currentPuyo) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		this.currentPuyo = currentPuyo;
	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub

		// System.out.println(e.getKeyCode());

		int key = e.getKeyCode(); // 키보드로 입력시 키값을 가져옴

		int x = frame.currentPuyo.getX();
		int y = frame.currentPuyo.getY();

		switch (key) {
		case java.awt.event.KeyEvent.VK_LEFT:
			System.out.println("왼쪽");
			frame.currentPuyo.setLocation(x - 10, y);
			break;
		case java.awt.event.KeyEvent.VK_RIGHT:
			System.out.println("오른쪽");
			frame.currentPuyo.setLocation(x + 10, y);
			break;
		case java.awt.event.KeyEvent.VK_UP:
			System.out.println("위 : 뿌요가 반시계 방향으로 돌아요");
			changePosition();
			break;
		case java.awt.event.KeyEvent.VK_DOWN:
			System.out.println("아래 : 뿌요가 시계 방향으로 돌아요");
			changePosition();
			break;
		}

	}

	void changePositionUp() { // 반시계 방향 => 뿌요들을 담고 있는 패널의 크기와 you 뿌요의 위치를 변경하기 위한 함수

		System.out.println("changePosition 진입");

		// default shape
		// me
		// you
		// 반시계 방향 => 먼저 뿌요들을 담고 있는 패널의 bound 를 변경
		// 1. 기존의 width 와 height 의 크기가 서로 바뀝니다.
		int currentX = currentPuyo.getX();
		int currentY = currentPuyo.getY();
		currentPuyo.setLocation(currentY, currentX); // 세로 => 가로 가 되고, 가로 => 세로가 됩니다.

		Runnable thread = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) {

					Thread.sleep(33); // 부드럽게 돌아가기 위해서 쓰레드를 주었고 sleep을 걸어 줌.

					// 반시계 방향으로 돌려야 하기때문에 x, y 두 값이 모두 변합니다.
					currentPuyo.you.setLocation(x, y);

				}

			}
		};
		thread.run();
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
