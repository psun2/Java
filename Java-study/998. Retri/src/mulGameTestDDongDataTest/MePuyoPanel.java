package mulGameTestDDongDataTest;

import java.awt.Graphics;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ddong.DDongData;

public class MePuyoPanel extends JPanel {

	ObjectOutputStream oos;
	DDongData data;
	String id;

	ImageIcon background;

	MeGameInfo meInfo;
	PuyoGameInfo info;

	Puyo me;
	Puyo you;

	MyLabel meLb;
	MyLabel youLb;

	ArrayList<MyLabel> puyoLbs;
	HashSet<MyLabel> bombArr;
	HashMap<String, HashSet<MyLabel>> map;

	int step, cutLine, score, jum, combo, comboCnt, comboChk, currentItemNum; // 뿌요가 떨어질때의 칸수

	boolean bombChk, itemChk;

	public ExecutorService threadPool;

	public MePuyoPanel(String id) {
		// TODO Auto-generated constructor stub

		this.id = id;

		init();

		// TODO Auto-generated constructor stub
		this.background = new ImageIcon("./img/background.png");
		setLayout(null);

		info = new PuyoGameInfo();
		info.setBounds(0, 0, Puyo.PUYOSIZE * 6, Puyo.PUYOSIZE);
		add(info);

		createPuyo(); // 뿌요생산 스타트
		new PuyoTimer(this).start(); // 타이머와 정보 업데이트
	}

	void init() {

		// 패널이 생성되어 add 되었다는것은 게임의 시작을 의미

		this.meInfo = new MeGameInfo(); // 정보 저장 클래스 생성
		this.data = new DDongData();
		data.src = this.id;
		data.type = "게임";
		this.threadPool = Executors.newCachedThreadPool(); // 쓰레드 풀 초기화
		this.step = 3; // 뿌요가 떨어질때의 칸수
		this.puyoLbs = new ArrayList<MyLabel>();
		this.map = new HashMap<String, HashSet<MyLabel>>();
		this.bombArr = new HashSet<MyLabel>();
		this.bombChk = false;
		this.score = 0;
		this.jum = 0;
		this.combo = 0;
		this.comboCnt = 0;
		this.itemChk = false;
		this.currentItemNum = 0;

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(background.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}

	void createPuyo() { // 뿌요의 계속적인 생산을 위하여 쓰레드 사용
		System.out.println("createPuyo => 뿌요생성");

		int LocationX = (Puyo.PUYOSIZE * 6) / 2; // 뿌요들의 생성 위치를 잡아기위한 x
		int LocationY = -Puyo.PUYOSIZE; // 뿌요들의 생성 위치를 잡아기위한 y

		Runnable thread = new Runnable() { // 뿌요 생성 쓰레드 => 프레임과 같이 돌아야 하므로 쓰레드를 줌

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) { // 게임이 끝날때까지 무한 반복한다.

					if (meInfo.endGame) { // end 게임끝을 chk
						System.out.println("게임종료");
						JOptionPane.showMessageDialog(MePuyoPanel.this, "게임 종료!");

						if (threadPool != null && !threadPool.isShutdown()) // 게임이 끝나고 쓰레드 풀이 열려 있다면
							threadPool.shutdown(); // 게임이 끝났으므로 모든 쓰레드를 죽임.

						try {
							if (oos != null)
								oos.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						return; // 게임 종료
					}

					if (!bombChk) {

						System.out.println("me, you 생성");

						// me 생성
						me = new Puyo();
						meLb = new MyLabel(new ImageIcon("./img/" + me.color + "-48.png"));
						meLb.setBounds(LocationX, LocationY, Puyo.PUYOSIZE, Puyo.PUYOSIZE);
						meLb.setName(me.color);
						add(meLb);
						meInfo.puyos.add(me); // 정보클래스에 정보 저장
						puyoLbs.add(meLb); // 라벨만 따로 저장
						addMap(meLb); // 본 패널에서 사용할 정보 저장

						// you 생성
						you = new Puyo();
						youLb = new MyLabel(new ImageIcon("./img/" + you.color + "-48.png"));
						youLb.setBounds(LocationX, LocationY - Puyo.PUYOSIZE, Puyo.PUYOSIZE, Puyo.PUYOSIZE);
						youLb.setName(you.color);
						add(youLb);
						meInfo.puyos.add(you);
						puyoLbs.add(youLb);
						addMap(youLb);

						move(); // 무브

					} else {

						if (bombChk()) { // 쓰레드여서 계속 실행시키는 문제 발생
							bomb(); // 항상 지켜보다가 같은 색이 4개 이상 모였을때 다음 로직을 진행
							bombChk = false; // 356 Line 에서 땡김 삭제 할것이 없고, 삭제가 되어 모든 로직이
							// bomb 메소드에서 끝이 나므로 이 메소드의 마지막에
							// 다른 요소가 나올소 있도록 boolean 값을변경
						} else {
							bombChk = false; // 356 Line 에서 땡김 삭제 할것이 없고, 삭제가 되어 모든 로직이
							// bomb 메소드에서 끝이 나므로 이 메소드의 마지막에
							// 다른 요소가 나올소 있도록 boolean 값을변경
						}

					}

				}

			}

		};

		threadPool.submit(thread); // 쓰레드를 사용하기 위해 쓰레드 풀에 등록
		// TestServer.threadPool.submit(thread);
		System.out.println("createPuyo => 뿌요생성 끝 게임 종료");
	}

	void addMap(MyLabel puyo) { // 생성될때 깔별로 맵으로 한번더 구분

		System.out.println("addMap 진입");
		if (this.map.containsKey(puyo.getName())) {

			map.get(puyo.getName()).add(puyo);

		} else {
			HashSet<MyLabel> set = new HashSet<MyLabel>();
			set.add(puyo);
			this.map.put(puyo.getName(), set);
		}

		System.out.println("addMap 끝");
	}

	void move() { // 자동적인 움직임은 y축만 업데이트 하면 됩니다.
		// 뿌요가 생성 되면 x 축은 key보드로 조종하고, y축으론 자동으로 흐른다.
		while (true) {

			try {

				if (me.stopChk && you.stopChk) { // 뿌요 한쌍이 둘이 자리를 잡았다.
					fixBug(me, meLb); // 가끔식 생기는 잡버그 수정
					fixBug(you, youLb); // 가끔식 생기는 잡버그 수정
					modifyNode();
					comboChk = -1; // 콤보 chk 초기화
					// System.out.println(comboChk);
					this.bombChk = true; // 생산을 잠시 중단하고 터질 요소가 있는지 검색
					return; // 다음 뿌요 생성을 위해 반복문 종료
				}

				Thread.sleep(33); // 33초의 딜레이
				endMove(me, meLb); // 뿌요가 밑으로 흘러내려 갑니다.
				endMove(you, youLb); // 뿌요가 밑으로 흘러내려 갑니다

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	} // move 함수 끝

	void endMove(Puyo puyo, MyLabel lb) { // 뿌요가 마지막까지 흘러 내려 갈 수 있게 하는 함수

		fixBug(puyo, lb);

		if (puyo.stopChk) {
			System.out.println("정지되어 움직이지 않음");
			return;
		}

		int y = nodeY(lb); // y값은 뿌요가 셋팅될 y축의 값

		if (y <= getSize().height - Puyo.PUYOSIZE) { // 뿌요의 y축의 값이 바닥과 닿으려 할때 까지만 조건에 충족
			// 여기서 바닥까지 갈껀데....
			// 그 전에 다른 요소가 있다면 stop
			if (search(lb)) { // 여길 들려서 y 값이 제자리에 셋팅이 될때는 뿌요가 자리를 잡았다라는 의미. 즉 바닥에 떨어 졌다.
				y = cutLine;
				puyo.stopChk = true; // 뿌요가 세로 방향시 윗 뿌요의 위치가 정지 되었을때 ....

				// System.out.println(puyo.Lb.getName() + " : " + puyo.stopChk);
				if (lb.getY() <= 0) // 뿌요가 자리를 잡았는데 Y의 값이 0보다 작거나 같을때 즉 맨위까지 쌓였을때 게임을 끝냅
					meInfo.endGame = true;

			}

			if (y >= getSize().height - Puyo.PUYOSIZE)
				y = getSize().height - Puyo.PUYOSIZE;

			lb.setLocation(lb.getX(), y);
		} else {
			System.out.println(puyo.color);
			puyo.stopChk = true; // 아래 뿌요 또는 가로 일때 위치가 정지 되었을때....
			// System.out.println(puyo.Lb.getName() + " : " + puyo.stopChk);
		}

	} // endMove 끝

	int nodeY(MyLabel puyo) { // 뿌요의 y좌표를 반환 받는 함수

		int y = puyo.getY();
		int result = y + step;

		return result;

	} // nodeY 끝

	boolean search(MyLabel puyo) { // 뿌요가 밑으로 흐를때 내 밑에 무엇인가 존재 한다면 y값을 다시 셋팅하여 반환
									// 문제점 : 즉 세로 방향일때 윗친구는 아랫 친구 때문에 못나오는 현상 발생
		boolean result = false;

		for (MyLabel pu : puyoLbs) {
			if (puyo.getY() < pu.getY()) { // 생성되는 뿌요의 y 축 값을 먼저 비교해 나의 y보다 큰 애들만 본다. => 나보다 밑에 있는 애들
				if (puyo.getX() == pu.getX()) { // x축이 같아야 동일 선상입니다.
					if (puyo.getY() + Puyo.PUYOSIZE > pu.getY()) { // 나의 y가 밑에있는 뿌요의 y보다 같거나 커지려고 할때
						System.out.println("puyo.Lb.getY() + Puyo.PUYOSIZE : " + (puyo.getY() + Puyo.PUYOSIZE));
						System.out.println(" pu.Lb.getY() : " + pu.getY());
						this.cutLine = pu.getY() - Puyo.PUYOSIZE;
						System.out.println("this.cutLine : " + this.cutLine);
						result = true;
					}
				}
			}

		}

		return result;

	} // inspectY 함수 끝

	void modifyNode() {

		System.out.println("modifyNode 진입");

		for (MyLabel puyo : puyoLbs) {

			// y 좌표가 자꾸 먹는 현상 발생!!!!
			// y 좌표를 50으로 나누어 0이 안되는 것 찾기

			if (puyo.getY() % Puyo.PUYOSIZE != 0) {
				// modifyArr.add(puyo);
				double y = puyo.getY();
				double remainder = Puyo.PUYOSIZE - (puyo.getY() % Puyo.PUYOSIZE);
				int result = (int) (y + remainder);
				// 게임 특성상 좌표가 일정하지 않게될 수 있음 사이즈 크기만큼 나눈 나머지 만큼만 더해주어 다시 셋팅

				puyo.setLocation(puyo.getX(), result);

			}

		}

		System.out.println("modifyNode 끝");

	}

	void fixBug(Puyo puyo, MyLabel lb) { // 가끔식 생기는 잡버그 수정 (중간에 걸리는 현상)

//		 System.out.println("★★★★★★★★fixBug 진입★★★★★★★★");

		if (!puyo.stopChk)
			return;

		int chk = 0;
		if (puyo.stopChk && lb.getY() != getSize().height - Puyo.PUYOSIZE) {

			for (MyLabel pu : puyoLbs) {

				if (lb.getY() + Puyo.PUYOSIZE == pu.getY())
					chk++;
			}

		}

		System.out.println(chk);

		if (chk == 0) {
			puyo.stopChk = false;
			endMove(puyo, lb);
		}

		// System.out.println("★★★★★★★★fixBug 종료★★★★★★★★");
	}

	////////////// TODO 폭발 로직

	boolean bombChk() {

		System.out.println("bombChk 진입");

		boolean result = false;

		for (HashSet<MyLabel> puyo : map.values()) {

			if (puyo.size() >= MeGameInfo.PANG)
				result = true;

		}

		System.out.println("bombChk 끝");
		return result;

	}

	void bomb() { // 배열을 돌아야 하는데 쓰레드로 부르기 때문에 비동기 사용

		System.out.println("bomb 진입");

		// for문이 돌아갈때 map도 remove가 되므로 클론을 하나 사용 합니다.
		HashMap<String, HashSet<MyLabel>> cloneMap = new HashMap<String, HashSet<MyLabel>>(this.map);

		for (HashSet<MyLabel> puyo : cloneMap.values()) {

			// 맵에서 해당 색깔의 해쉬셋의 길이가 4미만이면 볼 필요도 없다.
			System.out.println("puyo.size() : " + puyo.size());

			if (puyo.size() >= MeGameInfo.PANG) {

				deepBomb(puyo);
				System.out.println(bombArr);
				System.out.println(bombArr.size());

			}
		}

		System.out.println("bomb 끝");

	}

	void deepBomb(HashSet<MyLabel> colors) { // clolors 가 업데이트가 안됨

		System.out.println("deepBomb 실행");

		HashSet<MyLabel> equals = new HashSet<MyLabel>(); // 붙어 있는 것중 제일 큰 덩어리들을 담은 배열
		int size = 0;

		for (MyLabel puyo : colors) {

			HashSet<MyLabel> equalsTemp = new HashSet<MyLabel>();

			equalsTemp.add(puyo);

			// 십자가를 보기위해 기준이 되는 puyo의 좌표를 얻어옴
			int x = puyo.getX();
			int y = puyo.getY();

			for (MyLabel pu : colors) {

				if (x == pu.getX() && y == pu.getY() + Puyo.PUYOSIZE
						|| x == pu.getX() && y == pu.getY() - Puyo.PUYOSIZE)
					equalsTemp.add(pu);
				if (y == pu.getY() && x == pu.getX() + Puyo.PUYOSIZE
						|| y == pu.getY() && x == pu.getX() - Puyo.PUYOSIZE)
					equalsTemp.add(pu);
			}

			System.out.println("** equalsTemp: " + equalsTemp);

			if (size < equalsTemp.size()) { // 최고 많이 붙어 있는 덩어리를 가림
				size = equalsTemp.size();
				equals = equalsTemp;
			}

			if (equalsTemp.size() == 3)
				break;

			// System.out.println("colors : " + colors);
			// System.out.println("equals : " + equals);
		}

		equals = deepDeepBomb(colors, equals);
		// System.out.println(equals);

		if (equals.size() >= 4) {
			bombArr = equals;
			jum = 10;
			this.score += bombArr.size() * jum;
			remove();
			empty();
		}

		System.out.println("deepBomb 끝");
	}

	HashSet<MyLabel> deepDeepBomb(HashSet<MyLabel> colors, HashSet<MyLabel> equals) {
		System.out.println("deepDeepBomb 진입");

		if (equals.size() <= 1)
			return equals;

		HashSet<MyLabel> result = new HashSet<MyLabel>();
//		HashSet<MyLabel> resultColor = new HashSet<MyLabel>(equals);

		HashSet<MyLabel> removeColor = new HashSet<MyLabel>(colors);
		removeColor.removeAll(equals);

		for (MyLabel puyo : equals) {

			// 십자가를 보기위해 기준이 되는 puyo의 좌표를 얻어옴
			int x = puyo.getX();
			int y = puyo.getY();

			result.add(puyo);
			modifyNode();

			for (MyLabel pu : removeColor) {

				if (x == pu.getX() && y == pu.getY() + Puyo.PUYOSIZE
						|| x == pu.getX() && y == pu.getY() - Puyo.PUYOSIZE)
					result.add(pu);
				if (y == pu.getY() && x == pu.getX() + Puyo.PUYOSIZE
						|| y == pu.getY() && x == pu.getX() - Puyo.PUYOSIZE)
					result.add(pu);

			}

		}

		System.out.println("** 원본 colors : " + colors);
		System.out.println("** 같은 애들만 담아온 배열 : " + equals);
		System.out.println("**  원본 colors 에서 같은 애들을 제외한 배열 : " + removeColor);
		System.out.println("** result 배열 : " + result);

		if (result.size() > equals.size())
			result = deepDeepBomb(colors, result);

		System.out.println("deepDeepBomb 끝");

		return result;

	}

	void remove() {

		System.out.println("remove 진입");

		for (MyLabel puyo : bombArr) {
			// 터지는 뿌요 패널에서 삭제 작업
			remove(puyo); // 패널에서 지움
			setVisible(false); // update
			setVisible(true); // update
		}

		item(bombArr);

		comboChk++; // 처음 터졌을시 0 이되고
		// 재귀적으로 이구간을 또 거칠때 터졌으므로 1 이되서 연쇄 콤보 증가
		if (comboChk > 0)
			this.comboCnt++;
		combo = comboCnt * (jum * 2);

		System.out.println("remove 끝");

	}

	void item(HashSet bombArr) {

		ArrayList<MyLabel> item = new ArrayList<MyLabel>(bombArr);

		if (item.get(0).getName().equals("ninja")) {
			this.itemChk = true;
		}

		// 한줄을 기준으로 랜덤을 돌려 숫자가 겹치게 나올때는 그자리에 아이템 한개만...
		// 랜덤한 요소로 게임의 재미 up

		for (int i = 0; i < 3; i++) {

			pushItem((int) Math.random() * 7);

		}

	}

	void pushItem(int pos) {

		this.currentItemNum = pos;

		int[] xPos = { 0, 50, 150, 200, 250, 300 };

		int yPos = 0;
		for (MyLabel puyo : puyoLbs) {
			if (xPos[currentItemNum] == puyo.getX())
				if (yPos < puyo.getY())
					yPos = puyo.getY();
		}

		yPos = yPos + Puyo.PUYOSIZE;

		// 아이템을 줄 좌표는 다 구함 이제 상대방에게 어떻게 넘겨 줄 것인가 에 대한 고민을 ..pos..pos.
		System.out.println("아이템을 어떤식으루 사용 하누");

	}

	void empty() { // 터진 뿌요가 있으면 빈공간을 메꾸는 메소드 실행

		System.out.println("empty 진입");

		for (MyLabel puyo : bombArr) {
			// 터진 뿌요를 맵 과 어레이에서 삭제
			map.get(puyo.getName()).remove(puyo);
			meInfo.puyos.remove(equalsPuyo(puyo));
			puyoLbs.remove(puyo); // 푸요들이 담긴 배열에서 삭제
		}

		emptyMove();
		System.out.println("empty 끝");

		// 여기서 emptyMove 가 다 끝날떄가 지 한번 잡아 줘야 겟ㄱ네 ?
	}

	void emptyMove() { // 자동적인 움직임은 y축만 업데이트 하면 됩니다.

		System.out.println("emptyMove 진입");

		// 바닥에 있는 아이들음 움직임이 필요 없으므로 제외

		TreeSet<MyLabel> updatePuyo = new TreeSet<MyLabel>();

		// 아에 처음부터 업데이트 될 녀석만 가져오자

		System.out.println(bombArr);

		for (MyLabel puyo : bombArr) {

			for (MyLabel pu : puyoLbs) {

				if (puyo.getX() == pu.getX()) { // 삭제된 뿌요와 x가 같고
					if (puyo.getY() > pu.getY()) { // 삭제된 뿌요보다 위에 있다면...

						equalsPuyo(pu).stopChk = false;
						updatePuyo.add(pu);
						System.out.println("업데이트가 필요한 뿌요 목록");
						System.out.println(updatePuyo);
					}
				}

			}

		}

		if (updatePuyo.size() == 0) { // 업데이트 될 요소가 없다면 리턴
			return;
		}

		// 있다면 아래와 같이 진행

		bombArr = new HashSet<MyLabel>(); // 터질 목록은 이제 필요 없으므로 초기화

		emptyEndMove(updatePuyo); // 요소들이 터져서 이동이 끝난뒤
		modifyNode();

		if (bombChk()) { // 재귀적으로 터질 곳이 있나 검색합니다.
			bomb();
			modifyNode();

		}

		// 모든게 끝나고 메소드를 빠져 나가려 할때 콤보점수 업데이트

	} // move 함수 끝

	void emptyEndMove(TreeSet<MyLabel> updatePuyo) {

		System.out.println("emptyEndMove 진입"); // 진입 완료

		while (true) {

			for (MyLabel puyo : updatePuyo) {

				int index = 0;
				for (MyLabel pu : updatePuyo) {

					// System.out.println("puyo.stopChk : " + puyo.stopChk); // flase
					if (equalsPuyo(pu).stopChk)
						index++;

				}

				if (updatePuyo.size() == index) // 업데이트 목록 없음 retuen
					return;

				// System.out.println("puyo.stopChk : " + puyo.stopChk); // flase
				if (!equalsPuyo(puyo).stopChk) {

					// System.out.println("puyo.stopChk 너 진입은 하니 ?"); // 진입 거부
					try {

						endMove(equalsPuyo(puyo), puyo); // 뿌요가 밑으로 흘러내려 갑니다.
						Thread.sleep(33); // 33초의 딜레이

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}

	}

	Puyo equalsPuyo(MyLabel lb) {

		int index = puyoLbs.indexOf(lb);

		Puyo result = meInfo.puyos.get(index);

		return result;

	}

}