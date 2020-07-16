package lastPuyo.copy;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PuyoPanel extends JPanel {

	ImageIcon background;

	Puyo me;
	Puyo you;

	ArrayList<Puyo> puyos;

	int step; // 뿌요가 떨어질때의 칸수

	boolean endGame;

	public ExecutorService threadPool;

	public PuyoPanel() { // 생성자

		init();

		// TODO Auto-generated constructor stub
		this.background = new ImageIcon("./img/background.png");
		System.out.println(background.getIconWidth()); // 512
		System.out.println(background.getIconHeight()); // 1024
		setLayout(null);

		// 배경 라벨 맨 마지막에 추가 해야 보임 - 추후에 수정
//		JLabel b = new JLabel(background);
//		b.setBounds(0, 0, 512, 1024);
//		// this.setComponentZOrder(b, 0);
//		add(b);

		// 먼저 추가 되는것이 위에 뜬다...
//		JLabel aa = new Puyo().label();
//		aa.setBounds(0, 0, Puyo.puyoSize, Puyo.puyoSize);
//		add(aa);

		createPuyo(); // 뿌요생산 스타트
	} // 생성자 끝

	void init() {

		// 패널이 생성되어 add 되었다는것은 게임의 시작을 의미

		this.puyos = new ArrayList<Puyo>(); // 배열 초기화
		this.threadPool = Executors.newCachedThreadPool(); // 쓰레드 풀 초기화
		this.step = 3; // 뿌요가 떨어질때의 칸수
		this.endGame = false;

	}

	void createPuyo() { // 뿌요의 계속적인 생산을 위하여 쓰레드 사용

		int LocationX = (Puyo.puyoSize * 6) / 2; // 뿌요들의 생성 위치를 잡아기위한 x
		int LocationY = -Puyo.puyoSize; // 뿌요들의 생성 위치를 잡아기위한 y

		Runnable thread = new Runnable() { // 뿌요 생성 쓰레드

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) { // 게임이 끝날때까지 무한 반복한다.

					if (endGame) { // end 게임끝을 chk
						System.out.println("게임종료");
						JOptionPane.showMessageDialog(PuyoPanel.this, "게임 종료!");

						if (threadPool != null && !threadPool.isShutdown()) // 게임이 끝나고 쓰레드 풀이 열려 있다면
							threadPool.shutdown(); // 게임이 끝났으므로 모든 쓰레드를 죽임.

						// System.out.println(Thread.currentThread().getName()); // pool-1-thread-1 => ?

						return; // 게임 종료
					} // 게임종료

					if (me.stopChk && you.stopChk) {
						// 기준이 되는 me 생성
						me = new Puyo(PuyoPanel.this);
						JLabel meLb = me.Lb;
						meLb.setBounds(LocationX, LocationY, Puyo.puyoSize, Puyo.puyoSize);
						add(meLb);
						puyos.add(me);
						me.stopChk = false;

						// you 생성
						you = new Puyo(PuyoPanel.this);
						JLabel youLb = you.Lb;
						youLb.setBounds(LocationX, LocationY - Puyo.puyoSize, Puyo.puyoSize, Puyo.puyoSize);
						add(youLb);
						puyos.add(you);
						you.stopChk = false;

						///////////////////////////////////////////////////////////////////

						try {
							Thread.sleep(33);

//							me.Lb.setLocation(me.Lb.getX(), me.Lb.getY());
//							you.Lb.setLocation(you.Lb.getX(), you.Lb.getY());

							move(me);
							move(you);
//							flood();

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}

			}
		};

		threadPool.submit(thread); // 쓰레드를 사용하기 위해 쓰레드 풀에 등록
	}

//	void move() { // 자동적인 움직임은 y축만 업데이트 하면 됩니다.
//					// 뿌요가 생성 되면 x 축은 key보드로 조종하고, y축으론 자동으로 흐른다.
//		while (true) {
//
//			try {
//
//				if (!me.stopChk && !you.stopChk)// 뿌요 한쌍이 둘이 자리를 잡았다.
//					return; // 다음 뿌요 생성을 위해 반복문 종료
//
//				Thread.sleep(33); // 33초의 딜레이
//				endMove(me); // 뿌요가 밑으로 흘러내려 갑니다.
//				endMove(you); // 뿌요가 밑으로 흘러내려 갑니다.
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//
//	} // move 함수 끝

	void move(Puyo puyo) { // 뿌요가 마지막까지 흘러 내려 갈 수 있게 하는 함수

		int y = nodeY(puyo); // y값은 뿌요가 셋팅될 y축의 값

		// System.out.println(getSize());

		if (y <= getSize().height - Puyo.puyoSize) { // 뿌요의 y축의 값이 바닥과 닿으려 할때 까지만 조건에 충족
			// 여기서 바닥까지 갈껀데....
			// 그 전에 다른 요소가 있다면 stop
			if (inspectY(puyo)) { // 여길 들려서 y 값이 제자리에 셋팅이 될때는 뿌요가 자리를 잡았다라는 의미. 즉 바닥에 떨어 졌다.
				y -= step;
				puyo.stopChk = true; // 뿌요가 세로 방향시 윗 뿌요의 위치가 정지 되었을때 ....
				// bomb(puyo);
				System.out.println(puyo.Lb.getName());
				if (puyo.Lb.getY() <= 0) // 뿌요가 자리를 잡았는데 Y의 값이 0보다 작거나 같을때 즉 맨위까지 쌓였을때 게임을 끝냅
					endGame = true;

			}
			puyo.Lb.setLocation(puyo.Lb.getX(), y);
			// flood();
		}
		else {
			puyo.stopChk = true; // 아래 뿌요 또는 가로 일때 위치가 정지 되었을때....
			// bomb(puyo);
			System.out.println(puyo.Lb.getName());
		}

	} // endMove 끝

	int nodeY(Puyo puyo) { // 뿌요의 y좌표를 반환 받는 함수

		int y = puyo.Lb.getY();
		int result = y + step;

		return result;

	} // nodeY 끝

	boolean inspectY(Puyo puyo) { // 뿌요가 밑으로 흐를때 내 밑에 무엇인가 존재 한다면 y값을 다시 셋팅하여 반환
									// 문제점 : 즉 세로 방향일때 윗친구는 아랫 친구 때문에 못나오는 현상 발생
		boolean result = false;

		for (Puyo pu : puyos) {
			if (puyo.Lb.getY() < pu.Lb.getY()) { // 생성되는 뿌요의 y 축 값을 먼저 비교해 나의 y보다 큰 애들만 본다. => 나보다 밑에 있는 애들
				if (puyo.Lb.getX() == pu.Lb.getX()) { // x축이 같아야 동일 선상입니다.
					if (puyo.Lb.getY() + Puyo.puyoSize > pu.Lb.getY()) { // 나의 y가 밑에있는 뿌요의 y보다 같거나 커지려고 할때
						result = true;
					}
				}
			}

		}

		return result;

	} // inspectY 함수 끝

//	void flood() {
//
//		if (me.Lb.getX() == you.Lb.getX()) { // 세로 모드 일때 위의 뿌요가 아래 뿌요를 잡아 먹는 현상 발생 오류 잡이
//
//			if (me.Lb.getY() > you.Lb.getY()) {
//				you.Lb.setLocation(me.Lb.getX(), me.Lb.getY() - Puyo.puyoSize);
//			} else { // me 가 위에 있을때
//				me.Lb.setLocation(you.Lb.getX(), you.Lb.getY() - Puyo.puyoSize);
//			}
//
//		} else { // 가로 일때
//			
//		}
//
//	}
	void flood() { // 미완
		// 한개라도 땅에 닿았을때 이 함수를 실행하고, 여기서 작업들을 끝내고,
		// 빈공간을 메꾸는 메소드 실행후
		// 다음 뿌요가 생성 될 수 있게 true 로 바꾸어줌

		if (me.Lb.getX() == you.Lb.getX()) { // 세로 모드 일때 위의 뿌요가 아래 뿌요를 잡아 먹는 현상 발생 오류 잡이

			if (me.Lb.getY() > you.Lb.getY()) { // me 가 아래 있을때
				you.Lb.setLocation(me.Lb.getX(), me.Lb.getY() - Puyo.puyoSize);

				for (Puyo puyo : puyos) {

					if (me.Lb.getY() + Puyo.puyoSize >= puyo.Lb.getY() && me.Lb.getX() == puyo.Lb.getX())
						me.Lb.setLocation(me.Lb.getX(), puyo.Lb.getY());

				}

				if (me.Lb.getY() + Puyo.puyoSize >= this.getSize().height) // me가 혹시라도 바닥을 뚤고 나갈경우
					me.Lb.setLocation(me.Lb.getX(), this.getSize().height - Puyo.puyoSize);

			} else { // you가 아래 있을떄
				me.Lb.setLocation(you.Lb.getX(), you.Lb.getY() - Puyo.puyoSize);
			}

		} else { // 가로 일때

		}

		me.stopChk = true;
		you.stopChk = true;

	}

	void bomb(Puyo puyo) { // 폭발!!!!!!!!!! // 미완

		System.out.println("폭발 : 한번 떨어질때 두번 나와야 하는데 ?");

		// 현재 안착한 뿌요를 기준으로 동서남북으로 같은 요소가 있는지 검색
		int x = puyo.Lb.getX();
		int y = puyo.Lb.getY();
		String name = puyo.Lb.getName();

//		x의 시작점 : x - Puyo.puyoSize
		int startX = x - Puyo.puyoSize;

//		x의 끝점 : x 의 시작점 + (Puyo.puyoSize * 2)
		int endX = startX + (Puyo.puyoSize * 2);
//		
//		y의 시작점 : y - Puyo.puyoSize
		int startY = y - Puyo.puyoSize;
//		y의 끝점 : y 의 시작점 + (Puyo.puyoSize * 2)
		int endY = startY + (Puyo.puyoSize * 2);
//		
//		Arraylist 를 순회 하면서 x가 x의 시작점 부터 x의 끝점과 같고, 
//							y가 y의 시작점 부터 y의 끝점과 같으면서
//							이름이 현재의 뿌요랑 같은게 있니 ?

		System.out.println(name);
		int cnt = 0;
		for (Puyo pu : puyos) {

			if (pu.Lb.getX() >= startX || pu.Lb.getX() <= endX && pu.Lb.getY() >= startY || pu.Lb.getY() <= endY) {
				if (pu.Lb.getName().equals(name)) {
					cnt++;

					System.out.println(cnt);
					System.out.println("같아요");
					System.out.println("제대로 돌아요");
				}
			}

		}

	} // 폭발 끝!

	void empty() { // 터진 뿌요가 있으면 빈공간을 메꾸는 메소드 실행

	}

}
