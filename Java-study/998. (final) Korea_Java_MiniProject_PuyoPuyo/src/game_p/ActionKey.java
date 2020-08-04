package game_p;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

public class ActionKey implements KeyListener {

	MePuyoPanel panel;

	int meX;
	int meY;

	int youX;
	int youY;

	boolean retateChk, keyChk;

	public ActionKey(MePuyoPanel panel) { // 생성시 메인 패널
		// TODO Auto-generated constructor stub
		this.panel = panel;
		this.retateChk = false;
		this.keyChk = true;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) { // 키 이벤트
		// TODO Auto-generated method stub

		if (panel.me.stopChk || panel.you.stopChk) // 뿌요가 하나라도 스탑되면 키를 사용하지 못함
			return;

		meX = panel.meLb.getX(); // 프레임을 통해 x값과 y값을 설정
		meY = panel.meLb.getY();

		youX = panel.youLb.getX();
		youY = panel.youLb.getY();

		int key = e.getKeyCode();

		switch (key) {

		case KeyEvent.VK_LEFT:

			// System.out.println("왼쪽");

			if (retateChk) // 돌리면서 양쪽으로 와리 가리 칠때 버그가 생긴다고함...
				return;

			if (meX == 0 || youX == 0)
				return;

			if (inspectLeft())
				return;

			if (keyChk) { // 키의 겹침으로 한키가 눌릴땐 다른키를 못 누르게 설정
				keyChk = false;
				meX -= Puyo.PUYOSIZE;
				youX -= Puyo.PUYOSIZE;
			}

			// fixBug();
			// printNode();
			break;

		case KeyEvent.VK_RIGHT:

			// System.out.println("오른쪽");

			if (retateChk) // 돌리면서 양쪽으로 와리 가리 칠때 버그가 생긴다고함...
				return;

			if (meX + Puyo.PUYOSIZE == panel.getSize().width || youX + Puyo.PUYOSIZE == panel.getSize().width)
				return;

			if (inspectRight())
				return;

			if (keyChk) { // 키의 겹침으로 한키가 눌릴땐 다른키를 못 누르게 설정
				keyChk = false;
				meX += Puyo.PUYOSIZE;
				youX += Puyo.PUYOSIZE;

			}
			// printNode();
			// fixBug();
			break;

		case KeyEvent.VK_UP:

			if (fix()) {
				// System.out.println("키버그 진입");
				return;
			}

			if (keyChk) { // 키의 겹침으로 한키가 눌릴땐 다른키를 못 누르게 설정

				keyChk = false;

				this.retateChk = true;

				rotate();

				this.retateChk = false;
			}
			// fixBug();
			break;

		case KeyEvent.VK_DOWN:

			int speed = 10;
			// 너무 짧게 잡으면 바닥을 뚫고 들어가는 버그 로 인해 넉넉하게 한뿌요의 크기 정도를 잡아 리턴
			// 가로 모양 일때

			if (fix())
				return;

			if (keyChk) { // 키의 겹침으로 한키가 눌릴땐 다른키를 못 누르게 설정
				keyChk = false;
				meY += speed;
				youY += speed;
				// printNode();
				// fixBug();
			}
			break;
		}

		// me와 you 중 누가 벽쪽에 있는지 알아야함
		// 오른쪽 벽 : x 좌표가 더 큼
		// 왼쪽 벽 : x 좌표가 더 작음

		// 세로방향일땐 누가 누군지 상관없음

		panel.meLb.setLocation(meX, meY);
		fixBug();
		panel.youLb.setLocation(youX, youY);
		panel.fixBug(panel.equalsPuyo(panel.meLb), panel.meLb);
		panel.fixBug(panel.equalsPuyo(panel.youLb), panel.youLb);
		keyChk = true;
//		printNode();

	}

	boolean fix() {

		boolean result = false;

		if (meY >= (panel.getSize().height - Puyo.PUYOSIZE * 2) || youY >= (panel.getSize().height - Puyo.PUYOSIZE * 2))
			result = true;

		for (MyLabel puyo : panel.puyoLbs) {
			if (!puyo.equals(panel.meLb) && !puyo.equals(panel.youLb)) {
				if (meX == puyo.getX() || youX == puyo.getX()) {
					if (meY >= (puyo.getY() - Puyo.PUYOSIZE * 2) || youY >= (puyo.getY() - Puyo.PUYOSIZE * 2))
						result = true;
				}
			}
		}

		return result;

	}

	boolean inspectLeft() { // 나의 맴버를 기준으로 오른쪽과 왼쪽 요소들만 따로 봐야 하겠구나...

		boolean result = false;

		// me 와 you가 왼쪽으로 움직이려 할때 그옆에 요소가 있나 없나를 체크
		// me 와 you 가 세로방향일때 둘 중하나라고 옆에 요소가 있으면 못 움직임
		for (JLabel puyo : panel.puyoLbs) {
			if (!panel.meLb.equals(puyo) && !panel.youLb.equals(puyo)) { // 비교 대상에서 나와 너는 제외
				if (panel.meLb.getX() > puyo.getX() || panel.youLb.getX() > puyo.getX()) {
					if (panel.meLb.getY() >= puyo.getY() || panel.youLb.getY() >= puyo.getY()) {
						// 나 또는 너의 y 값이 옆의 요소의 y값에 포함될때 옆에 요소가 있음을 알 수 있음
						if (panel.meLb.getX() <= puyo.getX() + Puyo.PUYOSIZE
								|| panel.youLb.getX() <= puyo.getX() + Puyo.PUYOSIZE) {
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
		for (JLabel puyo : panel.puyoLbs) {
			if (!panel.meLb.equals(puyo) && !panel.youLb.equals(puyo)) { // 비교 대상에서 나와 너는 제외
				if (panel.meLb.getX() < puyo.getX() || panel.youLb.getX() < puyo.getX()) {
					if (panel.meLb.getY() >= puyo.getY() || panel.youLb.getY() >= puyo.getY()) {
						// 나 또는 너의 y 값이 옆의 요소의 y값에 포함될때 옆에 요소가 있음을 알 수 있음
						if (panel.meLb.getX() + Puyo.PUYOSIZE >= puyo.getX()
								|| panel.youLb.getX() + Puyo.PUYOSIZE >= puyo.getX()) {
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

		if (youX == meX) { // me 와 you가 y툭으로 일직선 즉 세로 방향 일때

			if (youY < meY) { // you가 me 보다 위에 존재 할때
				if (searchRight()) {
					return;
				}
				// you의 x 가 me의 오른쪽으로...
				// you의 y 가 me와 같음...
				youX = meX + Puyo.PUYOSIZE;
				youY = meY;

			} else { // you가 me 보다 아래 존재 할때
				if (searchLeft()) {
					return;
				}
				// you의 x 가 me의 왼쪽으로...
				// you의 y 가 me와 같음...
				youX = meX - Puyo.PUYOSIZE;
				youY = meY;

			}

		} else { // me 와 you가 x툭으로 일직선 즉 가로 방향 일때
			// y가 같고 x축만 고려 해줄 경우

			// me의 밑에 무엇인가 존재 하면 회전 못시키게 만들면 되겟네...
			for (MyLabel puyo : panel.puyoLbs) {
				if (meX == puyo.getX() && meY + (Puyo.PUYOSIZE * 2) == puyo.getY()) {
					// System.out.println("여기가 언제 진입함 ?????????????");
					return;
				}
			}

			if (youX < meX) { // you가 me 의 왼쪽에 존재 할때
				// you 를 me 의 위로 이동
				// you의 x가 me 와 같고
				// you의 y가 me의 위에 있음
				youX = meX;
				youY = meY - Puyo.PUYOSIZE;
			} else { // you가 me 의 오른쪽에 존재 할때
				// you를 me 의 아래로 이동
				// you의 x가 me 와 같고
				// you의 y가 me의 아래에 있음
				youX = meX;
				youY = meY + Puyo.PUYOSIZE;
			}

		}

	}

	boolean searchRight() { // (세로방향의 뿌요) 오른쪽으로 회전시 => 즉 you가 me의 위에 있을때

		// 오른쪽에 벽 또는 다른 뿌요가 있다면...
		// me 를 기준으로 you 가 회전함 you의 좌표만이 고려대상

		boolean result = false;

		for (JLabel puyo : panel.puyoLbs) {

			if (youX + Puyo.PUYOSIZE == puyo.getX())
				if (youY + Puyo.PUYOSIZE >= puyo.getY())
					result = true;

		}

		if (youX + Puyo.PUYOSIZE == panel.getSize().width)
			result = true;

		return result;

	}

	boolean searchLeft() { // (세로방향의 뿌요) 왼쪽으로 돌경우 => 즉 you가 me의 아래 있을때

		// 왼쪽에 벽 또는 다른 뿌요가 있다면...
		// me 를 기준으로 you 가 회전함 you의 좌표만이 고려대상

		boolean result = false;

		for (JLabel puyo : panel.puyoLbs) {

			if (youX - Puyo.PUYOSIZE == puyo.getX())
				if (youY + Puyo.PUYOSIZE >= puyo.getY())
					result = true;

		}

		if (youX == 0)
			result = true;

		return result;

	}

	void fixBug() {

		if (meX == youX) { // 세로 방향일때 틀 어짐을 잡아줌.... // me 를 기준으로 하기 때문 you의 X 는 me 의 xe다
			youX = panel.meLb.getX();

		} else {

			if (meX > youX)
				youX = panel.meLb.getX() - Puyo.PUYOSIZE;
			else
				youX = panel.meLb.getX() + Puyo.PUYOSIZE;
		}

	}

	void printNode() {
		System.out.println("me : " + meX + ", " + meY);
		System.out.println("you : " + youX + ", " + youY);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		int key = e.getKeyCode();
		// System.out.println(key == KeyEvent.VK_DOWN);

	}

}
