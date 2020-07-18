package lastPuyo2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.TreeSet;
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
	HashSet<Puyo> bombArr;
	HashMap<String, HashSet<Puyo>> map;
	// HashMap<String, Puyo> map;

	int step, cutLine; // 뿌요가 떨어질때의 칸수z

	boolean endGame, meChk, youChk, bombChk, removeChk;

	public ExecutorService threadPool;

	public PuyoPanel() { // 생성자

		init();

		// TODO Auto-generated constructor stub
		this.background = new ImageIcon("./img/background.png");
		// System.out.println(background.getIconWidth()); // 512
		// System.out.println(background.getIconHeight()); // 1024
		setLayout(null);

		// 배경 라벨 맨 마지막에 추가 해야 보임 - 추후에 수정
//		JLabel b = new JLabel(background);
//		b.setBounds(0, 0, 512, 1024);
//		// this.setComponentZOrder(b, 0);

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
		this.meChk = false;
		this.youChk = false;
		this.map = new HashMap<String, HashSet<Puyo>>();
		this.bombArr = new HashSet<Puyo>();
		this.bombChk = false;
		this.removeChk = false;

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

					// 기준이 되는 me 생성

					if (!bombChk) {
						me = new Puyo(PuyoPanel.this);
						JLabel meLb = me.Lb;
						meLb.setBounds(LocationX, LocationY, Puyo.puyoSize, Puyo.puyoSize);
						add(meLb);
						puyos.add(me);
						addMap(me);

						// you 생성
						you = new Puyo(PuyoPanel.this);
						JLabel youLb = you.Lb;
						youLb.setBounds(LocationX, LocationY - Puyo.puyoSize, Puyo.puyoSize, Puyo.puyoSize);
						add(youLb);
						puyos.add(you);
						addMap(you);

						move(); // 무브
					} else {
						bomb(); // 쓰레드여서 계속 실행시키는 문제 발생
						// 항상 지켜보다가 같은 색이 4개 이상 모였을때 다음 로직을 진행
					}

				}

			}

		};

		threadPool.submit(thread); // 쓰레드를 사용하기 위해 쓰레드 풀에 등록
	}

	void move() { // 자동적인 움직임은 y축만 업데이트 하면 됩니다.
		// 뿌요가 생성 되면 x 축은 key보드로 조종하고, y축으론 자동으로 흐른다.
		while (true) {

			try {

				if (!me.stopChk && !you.stopChk) { // 뿌요 한쌍이 둘이 자리를 잡았다.
					this.bombChk = true; // 생산을 잠시 중단하고 터질 요소가 있는지 검색
					// if (bombArr.size() == 0) // 터졋다면 다음 뿌요를 생성
					return; // 다음 뿌요 생성을 위해 반복문 종료
				}

				Thread.sleep(33); // 33초의 딜레이
				endMove(me); // 뿌요가 밑으로 흘러내려 갑니다.
				endMove(you); // 뿌요가 밑으로 흘러내려 갑니다

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	} // move 함수 끝

	void endMove(Puyo puyo) { // 뿌요가 마지막까지 흘러 내려 갈 수 있게 하는 함수

		int y = nodeY(puyo); // y값은 뿌요가 셋팅될 y축의 값

		// System.out.println(getSize());

		if (y <= getSize().height - Puyo.puyoSize) { // 뿌요의 y축의 값이 바닥과 닿으려 할때 까지만 조건에 충족
			// 여기서 바닥까지 갈껀데....
			// 그 전에 다른 요소가 있다면 stop
			if (search(puyo)) { // 여길 들려서 y 값이 제자리에 셋팅이 될때는 뿌요가 자리를 잡았다라는 의미. 즉 바닥에 떨어 졌다.
				y = cutLine;
//				y -= step;
				puyo.stopChk = false; // 뿌요가 세로 방향시 윗 뿌요의 위치가 정지 되었을때 ....

				// System.out.println(puyo.Lb.getName() + " : " + puyo.stopChk);
				if (puyo.Lb.getY() <= 0) // 뿌요가 자리를 잡았는데 Y의 값이 0보다 작거나 같을때 즉 맨위까지 쌓였을때 게임을 끝냅
					endGame = true;

			}

			if (y >= getSize().height - Puyo.puyoSize)
				y = getSize().height - Puyo.puyoSize;

			puyo.Lb.setLocation(puyo.Lb.getX(), y);
		} else {
			puyo.stopChk = false; // 아래 뿌요 또는 가로 일때 위치가 정지 되었을때....
			// System.out.println(puyo.Lb.getName() + " : " + puyo.stopChk);
		}

	} // endMove 끝

	int nodeY(Puyo puyo) { // 뿌요의 y좌표를 반환 받는 함수

		int y = puyo.Lb.getY();
		int result = y + step;

		return result;

	} // nodeY 끝

	boolean search(Puyo puyo) { // 뿌요가 밑으로 흐를때 내 밑에 무엇인가 존재 한다면 y값을 다시 셋팅하여 반환
								// 문제점 : 즉 세로 방향일때 윗친구는 아랫 친구 때문에 못나오는 현상 발생
		boolean result = false;

		for (Puyo pu : puyos) {
			if (puyo.Lb.getY() < pu.Lb.getY()) { // 생성되는 뿌요의 y 축 값을 먼저 비교해 나의 y보다 큰 애들만 본다. => 나보다 밑에 있는 애들
				if (puyo.Lb.getX() == pu.Lb.getX()) { // x축이 같아야 동일 선상입니다.
					if (puyo.Lb.getY() + Puyo.puyoSize >= pu.Lb.getY()) { // 나의 y가 밑에있는 뿌요의 y보다 같거나 커지려고 할때

						this.cutLine = pu.Lb.getY() - Puyo.puyoSize;

						result = true;
					}
				}
			}

		}

		return result;

	} // inspectY 함수 끝

	void addMap(Puyo puyo) { // 생성될때 깔별로 맵으로 한번더 구분

		if (this.map.containsKey(puyo.name)) {

			map.get(puyo.name).add(puyo);

		} else {
			HashSet<Puyo> set = new HashSet<Puyo>();
			set.add(puyo);
			this.map.put(puyo.name, set);
		}

	}

	////////////// 폭발 로직

	void bomb() {

		int num = 1;

		for (HashSet<Puyo> puyo : map.values()) {

			// 맵에서 해당 색깔의 해쉬셋의 길이가 4미만이면 볼 필요도 없다.
			System.out.println("puyo.size() : " + puyo.size());

			if (puyo.size() >= 4) {

				deepBomb(puyo);
				System.out.println("deepBomb2 를 끝내고 다음 작업 gogo");
				remove();
				System.out.println("remove 끝");
				if (removeChk)
					empty();
				System.out.println("empty 끝");

				if (num == map.size()) {
					this.bombChk = false;
					return;
				} // 모든 작업이 끝나면 다시 뿌요 생성

				num++;
				System.out.println("num++" + num);
				System.out.println("설마 해당 맵에 변동이 있어서 그래 ?");
			} else {
				System.out.println("else 를 타야지 ");

				if (num == map.size()) {
					this.bombChk = false;
					return;
				} // 모든 작업이 끝나면 다시 뿌요 생성

				num++;
			}

		}

	}

	void deepBomb(HashSet<Puyo> colors) {

		System.out.println("deepBomb2 실행");

		HashSet<Puyo> equals = new HashSet<Puyo>(); // 붙어 있는 것중 제일 큰 덩어리들을 담은 배열
		int size = 0;

		for (Puyo puyo : colors) {

			HashSet<Puyo> equalsTemp = new HashSet<Puyo>();

			equalsTemp.add(puyo);

			// 십자가를 보기위해 기준이 되는 puyo의 좌표를 얻어옴
			int x = puyo.Lb.getX();
			int y = puyo.Lb.getY();

			// 기준이 되는 puyo의 십자가 시작점
			int startX = x - Puyo.puyoSize;
			int startY = y - Puyo.puyoSize;

			// 기준이 되는 puyo의 십자가 끝점
			int endX = startX + (Puyo.puyoSize * 2);
			int endY = startY + (Puyo.puyoSize * 2);

			for (Puyo pu : colors) {

				if (pu.Lb.getX() >= startX && pu.Lb.getX() <= endX && pu.Lb.getY() >= startY && pu.Lb.getY() <= endY) {
					equalsTemp.add(pu); // 범위 안에 존재 한다면 set에 추가
					// set 사용 이유 set은 중복되는 것 은 안들어 가기 때문에 너무 펴연난
				}

			}

			if (size < equalsTemp.size()) { // 최고 많이 붙어 있는 덩어리를 가림
				size = equalsTemp.size();
				equals = equalsTemp;
			}

			deepDeepBomb(colors, equals);

			System.out.println("deepBomb2 끝");
		}

	}

	void deepDeepBomb(HashSet<Puyo> colors, HashSet<Puyo> equals) {

		System.out.println("deepDeepBomb2 실행");

		HashSet<Puyo> removeColor = new HashSet<Puyo>(colors);
		// 이제부턴 내가 가지고 있어야 할 것이기 때문에 bombArr에 해야 하나 ?
		// result 를 이용하다가 마지막에만 추가 ?
		HashSet<Puyo> result = new HashSet<Puyo>(equals);
		removeColor.removeAll(result);

		System.out.println("removeColor : " + removeColor);
		System.out.println("result : " + result);

		for (Puyo puyo : result) {

			// 십자가를 보기위해 기준이 되는 puyo의 좌표를 얻어옴
			int x = puyo.Lb.getX();
			int y = puyo.Lb.getY();

			// 기준이 되는 puyo의 십자가 시작점
			int startX = x - Puyo.puyoSize;
			int startY = y - Puyo.puyoSize;

			// 기준이 되는 puyo의 십자가 끝점
			int endX = startX + (Puyo.puyoSize * 2);
			int endY = startY + (Puyo.puyoSize * 2);

			for (Puyo pu : removeColor) {

				if (pu.Lb.getX() >= startX && pu.Lb.getX() <= endX && pu.Lb.getY() >= startY && pu.Lb.getY() <= endY) {
					result.add(pu); // 범위 안에 존재 한다면 set에 추가
					// set 사용 이유 set은 중복되는 것 은 안들어 가기 때문에 너무 펴연난
				}

			}

			System.out.println("result : " + result);
			System.out.println("equals : " + equals);
			if (result.size() > equals.size()) {
				deepDeepBomb(colors, result);
			} else {
				if (result.size() >= 4) {
					bombArr = result;
				} else {
					return;
				}
			}

			System.out.println("deepDeepBomb2 끝");
			return; // 맨 처음이 못 빠져 나와 return

		}

	}

	void remove() {

		System.out.println("리무브");

		for (Puyo puyo : bombArr) {
			// 푸요 배열에서 삭제해주고
			// 패널에서도 삭제해줘야함
			remove(puyo.Lb); // 패널에서 지움
			setVisible(false); // update
			setVisible(true); // update
		}

		removeChk = true;

	}

	void empty() { // 터진 뿌요가 있으면 빈공간을 메꾸는 메소드 실행

		System.out.println("empty 진입");

		for (Puyo puyo : bombArr) {
			// 푸요 배열에서 삭제해주고
			// 패널에서도 삭제해줘야함
			puyos.remove(puyo); // 푸요들이 담긴 배열에서 삭제
			map.get(puyo.name).remove(puyo);
		}

		bombArr = new HashSet<Puyo>();

		System.out.println("empty 끝");
		emptyMove();

		// 여기서 emptyMove 가 다 끝날떄가 지 한번 잡아 줘야 겟ㄱ네 ?
	}

	void emptyMove() { // 자동적인 움직임은 y축만 업데이트 하면 됩니다.

		// 내밑에 요소가 없고 바닥이 아니라면 움직여야지

		int yPos;

		// 제일 아랫 줄에 있는 애들 먼저 탐색 을 해서 재구성을 해야 하네 ?
		TreeSet<Puyo> tree = new TreeSet<Puyo>(puyos);
		System.out.println("puyos : " + puyos);
		System.out.println("tree : " + tree);

		for (Puyo puyo : tree) {
			
			System.out.println("puyo.Lb.getY() : " + puyo.Lb.getY());

			System.out.println("Puyo.puyoSize : " + Puyo.puyoSize);
			
			System.out.println("puyo.Lb.getY() + Puyo.puyoSize : " + (puyo.Lb.getY() + Puyo.puyoSize));

			for (Puyo pu : tree) {

				System.out.println("pu.Lb.getY() : " + pu.Lb.getY());

				if (puyo.Lb.getX() == pu.Lb.getX() && puyo.Lb.getY() < pu.Lb.getY()) { // 내밑에 요소가 있다면
					System.out.println("무브, ㄱㄱ");
					System.out.println();
					yPos = pu.Lb.getY();
					emptyEndMove(puyo, yPos);
					break;
				}

			}

		}

		return;

	} // move 함수 끝

	void emptyEndMove(Puyo puyo, int yPos) {

		System.out.println("emptyEndMove");

		int y = puyo.Lb.getY();

		while (yPos > puyo.Lb.getY()) {

			System.out.println("puyo.Lb.getY() : " + puyo.Lb.getY());
			System.out.println("yPos : " + yPos);

			System.out.println("돌아요");

			int result = y + 3;
			puyo.Lb.setLocation(puyo.Lb.getX(), result);
			y = result;

		}

		removeChk = false;

	}

//	void searchElement() {
//
//		ArrayList<Puyo> newPuyo = new ArrayList<Puyo>();
//		ArrayList<Puyo> badak = new ArrayList<Puyo>();
//
//		// 제일 아랫 줄에 있는 애들 먼저 탐색 을 해서 재구성을 해야 하네 ?
//		// y가 클수록 아래있다.
//
//		for (Puyo puyo : puyos) {
//
//			if (puyo.Lb.getY() >= getSize().height - Puyo.puyoSize) {
//				badak.add(puyo);
//			} else {
//				for (Puyo pu : puyos) {
//					if (puyo.Lb.getY() < pu.Lb.getY()) {
//						if (newPuyo.size() == 0)
//							newPuyo.add(puyo);
//						else {
//
//						}
//					}
//				}
//			}
//
//		}
//
//	}

}
