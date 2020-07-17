package lastPuyo2;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ActionKey implements KeyListener {

	PuyoPanel panel;

	int meX;
	int meY;

	int youX;
	int youY;

	public ActionKey(PuyoPanel panel) { // 생성시 메인 프레임을 가져옴
		// TODO Auto-generated constructor stub
		this.panel = panel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) { // 키 이벤트
		// TODO Auto-generated method stub

		if (!panel.me.stopChk || !panel.you.stopChk) // 뿌요가 하나라도 스탑되면 키를 사용하지 못함
			return;

		meX = panel.me.Lb.getX(); // 프레임을 통해 x값과 y값을 설정
		meY = panel.me.Lb.getY();

		youX = panel.you.Lb.getX();
		youY = panel.you.Lb.getY();

		int key = e.getKeyCode();

		switch (key) {

		case KeyEvent.VK_LEFT:
			System.out.println("왼쪽");
			meX -= Puyo.puyoSize;
			youX -= Puyo.puyoSize;
			if (inspectLeft()) {
				meX = panel.me.Lb.getX();
				youX = panel.you.Lb.getX();
			}
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("오른쪽");
			meX += Puyo.puyoSize;
			youX += Puyo.puyoSize;
			if (inspectRight()) {
				meX = panel.me.Lb.getX();
				youX = panel.you.Lb.getX();
			}
			break;
		case KeyEvent.VK_UP:
			rotate();
			break;
		case KeyEvent.VK_DOWN:
			int speed = 3;
			// 너무 짧게 잡으면 바닥을 뚫고 들어가는 버그 로 인해 넉넉하게 한뿌요의 크기 정도를 잡아 리턴
			// 가로 모양 일때
			if (meY == youY) {
				if (meY >= panel.getSize().height - Puyo.puyoSize || youY >= panel.getSize().height - Puyo.puyoSize)
					return;
			}
			// 세로 모양 일떄
			if (meX == youX) {
				if (meY >= panel.getSize().height - Puyo.puyoSize
						|| youY >= (panel.getSize().height - Puyo.puyoSize * 2))
					return;
			}
			meY += speed;
			youY += speed;
			break;
		}

		// me와 you 중 누가 벽쪽에 있는지 알아야함
		// 오른쪽 벽 : x 좌표가 더 큼
		// 왼쪽 벽 : x 좌표가 더 작음

		// 세로방향일땐 누가 누군지 상관없음

		if (meX <= 0)
			meX = 0;
		if (meX + Puyo.puyoSize >= panel.getSize().width)
			meX = panel.getSize().width - Puyo.puyoSize;
		if (youX <= 0)
			youX = 0;
		if (youX + Puyo.puyoSize >= panel.getSize().width)
			youX = panel.getSize().width - Puyo.puyoSize;

		panel.you.Lb.setLocation(youX, youY);
		panel.me.Lb.setLocation(meX, meY);

		System.out.println(youY + ", " + meY);

	}

	boolean inspectLeft() { // 나의 맴버를 기준으로 오른쪽과 왼쪽 요소들만 따로 봐야 하겠구나...

		boolean result = false;

		// me 와 you가 왼쪽으로 움직이려 할때 그옆에 요소가 있나 없나를 체크
		// me 와 you 가 세로방향일때 둘 중하나라고 옆에 요소가 있으면 못 움직임
		for (Puyo puyo : panel.puyos) {
			if (!panel.me.equals(puyo) && !panel.you.equals(puyo)) { // 비교 대상에서 나와 너는 제외
				if (panel.me.Lb.getX() > puyo.Lb.getX() || panel.you.Lb.getX() > puyo.Lb.getX()) {
					if (panel.me.Lb.getY() >= puyo.Lb.getY() || panel.you.Lb.getY() >= puyo.Lb.getY()) {
						// 나 또는 너의 y 값이 옆의 요소의 y값에 포함될때 옆에 요소가 있음을 알 수 있음
						if (panel.me.Lb.getX() <= puyo.Lb.getX() + Puyo.puyoSize
								|| panel.you.Lb.getX() <= puyo.Lb.getX() + Puyo.puyoSize) {
							// me 또는 you의 x값이 옆 요소의 x 값을 침범 하려 할때....
							result = true; // true가 되면 옆으로 가지 못하게 함
						}
					}
				}
			}

		}

		return result;

	}

	boolean inspectRight() { // 나의 맴버를 기준으로 오른쪽과 왼쪽 요소들만 따로 봐야 하겠구나...

		boolean result = false;

		// me 와 you가 왼쪽으로 움직이려 할때 그옆에 요소가 있나 없나를 체크
		// me 와 you 가 세로방향일때 둘 중하나라고 옆에 요소가 있으면 못 움직임
		for (Puyo puyo : panel.puyos) {
			if (!panel.me.equals(puyo) && !panel.you.equals(puyo)) { // 비교 대상에서 나와 너는 제외
				if (panel.me.Lb.getX() < puyo.Lb.getX() || panel.you.Lb.getX() < puyo.Lb.getX()) {
					if (panel.me.Lb.getY() >= puyo.Lb.getY() || panel.you.Lb.getY() >= puyo.Lb.getY()) {
						// 나 또는 너의 y 값이 옆의 요소의 y값에 포함될때 옆에 요소가 있음을 알 수 있음
						if (panel.me.Lb.getX() + Puyo.puyoSize >= puyo.Lb.getX()
								|| panel.you.Lb.getX() + Puyo.puyoSize >= puyo.Lb.getX()) {
							// me 또는 you의 x값이 옆 요소의 x 값을 침범 하려 할때....
							result = true; // true가 되면 옆으로 가지 못하게 함
						}
					}
				}
			}

		}

		return result;

	}

	void rotate() {

		// 버그 잡는 로직
		// 가로상태 일때 너무 밑에서 방향키로 회전 시키면 이상하게 쌓이는 현상 해결
//		if (youY + Puyo.puyoSize >= Puyo.puyoSize * 11) {
//			if (youY == meY)
//				return;
//		}

		if (youX == meX) { // me 와 you가 y툭으로 일직선 즉 세로 방향 일때
			// System.out.println("youX : " + youX); // 통과
			// System.out.println("meX : " + meX); // 통과

			if (youY < meY) { // you가 me 보다 위에 존재 할때
				// you의 x 가 me의 오른쪽으로...
				// you의 y 가 me와 같음...
				youX = meX + Puyo.puyoSize;
				// System.out.println("youX : " + youX); // 통과
				// System.out.println("meX : " + meX); // 통과
				youY = meY;
				System.out.println("youY : " + youY);
				System.out.println("meY : " + meY);
				changeShapeRight(); // 왼쪽으로 돌경우 => 즉 you가 me의 위에 있을때
			} else { // you가 me 보다 아래 존재 할때
				// you의 x 가 me의 왼쪽으로...
				// you의 y 가 me와 같음...
				youX = meX - Puyo.puyoSize;
				youY = meY;
				changeShapeLeft(); // 오른쪽으로 회전시 => 즉 you가 me의 아래 있을때
			}

		} else { // me 와 you가 x툭으로 일직선 즉 가로 방향 일때

			if (youX < meX) { // you가 me 의 왼쪽에 존재 할때
				// you의 x가 me 와 같고
				// you의 y가 me의 위에 있음
				youX = meX;
				youY = meY - Puyo.puyoSize;
			} else { // you가 me 의 오른쪽에 존재 할때
				// you의 x가 me 와 같고
				// you의 y가 me의 아래에 있음
				youX = meX;
				youY = meY + Puyo.puyoSize;
			}

		}

	}

	void changeShapeRight() { // 왼쪽으로 돌경우 => 즉 you가 me의 위에 있을때
		// me 를 기준으로 you가 회전함

		// System.out.println(panel.getSize().width); // 288 즉 패널의 끝선이 된다

		// 현재상태 : 세로 상태 => me만 고려해줘도 됨
		if (panel.you.Lb.getX() + Puyo.puyoSize == panel.getSize().width) { // 회전시 벽이라면 ?
			// System.out.println("걸립니까 ?"); // 벽에 걸리는 거 확인 가능
			youY = meY - Puyo.puyoSize;
			// youX = meX;
		}
	}

	void changeShapeLeft() { // 오른쪽으로 회전시 => 즉 you가 me의 아래 있을때
		// me 를 기준으로 you가 회전함

		// 현재상태 : 세로 상태 => me만 고려해줘도 됨

		if (panel.you.Lb.getX() == 0) { // 회전시 벽이라면 ?
			// System.out.println("걸립니까 ?"); // 벽에 걸리는 거 확인 가능 // 걸립니까 ?
			youY = meY + Puyo.puyoSize;
			// youX = meX;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		int key = e.getKeyCode();
		System.out.println(key == KeyEvent.VK_DOWN);

	}

}
