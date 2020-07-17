package test;

import java.awt.event.KeyListener;

public class ActionKey implements KeyListener {

	PuyoFrame frame;

	public ActionKey(PuyoFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
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

		int meX = frame.mePuyo.getX();
		int meY = frame.mePuyo.getY();

		int youX = frame.youPuyo.getX();
		int youY = frame.youPuyo.getY();

		switch (key) {
		case java.awt.event.KeyEvent.VK_LEFT:
			System.out.println("왼쪽");
			frame.mePuyo.setLocation(meX - 50, meY);
			frame.youPuyo.setLocation(youX - 50, youY);
			break;
		case java.awt.event.KeyEvent.VK_RIGHT:
			System.out.println("오른쪽");
			frame.mePuyo.setLocation(meX + 50, meY);
			frame.youPuyo.setLocation(youX + 50, youY);
			break;
		case java.awt.event.KeyEvent.VK_UP:
			System.out.println("위 : 뿌요가 반시계 방향으로 돌아요");
			changePositionUp(meX, meY, youX, youY);
			break;
//		case java.awt.event.KeyEvent.VK_DOWN:
//			System.out.println("아래 : 뿌요가 시계 방향으로 돌아요");
//			changePosition(meX, meY, youX, youY);
//			break;
		}

	}

	void changePositionUp(int meX, int meY, int youX, int youY) { // 반시계 방향 => 뿌요들을 담고 있는 패널의 크기와 you 뿌요의 위치를 변경하기 위한 함수

		System.out.println("changePosition 진입");

		// default shape
		// me
		// you
		// me는 항상 고정 you 에게 움직음을 준다.

		if (youX == meX) { // 세로 배치 되어 있을때

			// 배치를 비교 했으니 y로 me의 위 또는 아래 있는지를 판별합니다.
			if (youY < meY) { // me 위에 you 가 있음

				frame.youPuyo.setLocation(meX - frame.mePuyo.getSize().width, meY);

			} else { // me 아래 you가 있음

				frame.youPuyo.setLocation(meX + frame.mePuyo.getSize().width, meY);

			}

		} else { // 가로로 배치 되어 있을 경우

			if (youX < meX) { // me 오른쪽에 you 가 있음

				// frame.youPuyo.setLocation(meX, meY - frame.mePuyo.getSize().height);
				frame.youPuyo.setLocation(meX, meY + frame.mePuyo.getSize().height);

			} else { // me 왼쪽에 you가 있음

				frame.youPuyo.setLocation(meX, meY - frame.mePuyo.getSize().height);
				// frame.youPuyo.setLocation(meX, meY + frame.mePuyo.getSize().height);

			}

		}

//		currentPuyo.setLocation(currentY, currentX); // 세로 => 가로 가 되고, 가로 => 세로가 됩니다.
//
//		Runnable thread = new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//
//				while (true) {
//
//					Thread.sleep(33); // 부드럽게 돌아가기 위해서 쓰레드를 주었고 sleep을 걸어 줌.
//
//					// 반시계 방향으로 돌려야 하기때문에 x, y 두 값이 모두 변합니다.
//					frame.youPuyo.setLocation(x, y);
//
//				}
//
//			}
//		};
//		thread.run();
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
