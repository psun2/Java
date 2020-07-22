package puyopuyo_failed3_여기하는중;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionKey implements KeyListener {

	PuyoFrame frame;

	int meX;
	int meY;

	int youX;
	int youY;

	public ActionKey(PuyoFrame frame) { // 생성시 메인 프레임을 가져옴
		// TODO Auto-generated constructor stub
		this.frame = frame;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) { // 키 이벤트
		// TODO Auto-generated method stub

		meX = frame.me.getX(); // 프레임을 통해 x값과 y값을 설정
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
			if (inspect(frame.me, frame.you)) {
				meX = frame.me.getX();
				youX = frame.you.getX();
			}
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("오른쪽");
			meX += frame.puyoSize;
			youX += frame.puyoSize;
			if (inspect(frame.me, frame.you)) {
				meX = frame.me.getX();
				youX = frame.you.getX();
			}
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

	방향키 오른쪽 왼쪽 다시 잡아야 함
	
	int leftMax() {

		int result = 0;

		int temp = 0;

		for (Puyo puyo : frame.puyos) {

			if (frame.me.getX() <= puyo.getX() + frame.puyoSize) {
				if (puyo.getX() + frame.puyoSize > temp) {
					temp = puyo.getX() + frame.puyoSize;
					result = temp;
				}
			}
//			System.out.println("temp : " + temp);
//			System.out.println("result : " + result);
		}

//		System.out.println("leftMax : " + result);

		return result;

	}

	int rightMin() {

		int result = 0;

		int temp = frame.getSize().width;

		for (Puyo puyo : frame.puyos) {

			if (frame.me.getX() + frame.puyoSize >= puyo.getX()) {
				if (puyo.getX() < temp) {
					temp = puyo.getX();
					result = temp;
				}
			}

		}

//		System.out.println("rightMin : " + result);

		return result;

	}

	boolean inspect(Puyo me, Puyo you) {
		// 왼쪽 방향으로 이동할때
		// 이동 방향에 요소가 존재하고 밑에뿌요는 못움직이는 상황에 위쪽 뿌요만 움직이는 현상 발생을 잡아주기 위한 매소드

		boolean result = false; // 초기값 => 옆에 요소가 없는 경우

		for (Puyo puyo : frame.puyos) {

			if (!me.equals(puyo) && !you.equals(puyo)) { // me 와 you 는 키보드로 움직여야 하기때문에 제외 합니다.

				if (me.getY() + frame.puyoSize >= puyo.getY() || you.getY() + frame.puyoSize >= puyo.getY()) {

					if (me.getX() <= leftMax() || me.getX() + frame.puyoSize >= rightMin()) {
						result = true;
					}

				}
			}
		}

		System.out.println(result);

		return result;

	}

	boolean inspectRight(Puyo me, Puyo you) {
		// 오른쪽 방향으로 이동할때
		// 이동 방향에 요소가 존재하고 밑에뿌요는 못움직이는 상황에 위쪽 뿌요만 움직이는 현상 발생을 잡아주기 위한 매소드

		boolean result = false; // 초기값 => 옆에 요소가 없는 경우

		for (Puyo puyo : frame.puyos) {
			if (!me.equals(puyo) && !you.equals(puyo)) { // me 와 you 는 키보드로 움직여야 하기때문에 제외 합니다.

				if (me.getY() + frame.puyoSize >= puyo.getY() || you.getY() + frame.puyoSize >= puyo.getY()) {
					if (me.getX() >= puyo.getX() + frame.puyoSize || you.getX() >= puyo.getX() + frame.puyoSize) {
						if (me.getX() + frame.puyoSize >= puyo.getX() || you.getX() + frame.puyoSize >= puyo.getX()) {

							// 옆에 요소가 존재 할때 옆으로 가지 못하고 현재의 위치를 설정 할 수 있게 합니다.
							// 즉 me의X 와 you의 X에 한번이라도 걸리면 옆에 다른 요소가 존재 함
							result = true;
						}
					}
				}
			}
		}

		return result;

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
