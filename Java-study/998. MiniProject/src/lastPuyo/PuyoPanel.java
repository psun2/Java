package lastPuyo;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.omg.CORBA.Current;

public class PuyoPanel extends JPanel {

	ImageIcon background;

	Puyo me;
	Puyo you;

	ArrayList<Puyo> puyos;
	ArrayList<Puyo> memberPuyos;

	boolean end;

	public ExecutorService threadPool;

	public PuyoPanel() {

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

		createPuyo();
	}

	void init() {

		// 패널이 생성되어 add 되었다는것은 게임의 시작을 의미

		this.puyos = new ArrayList<Puyo>(); // 배열 초기화
		this.memberPuyos = new ArrayList<Puyo>(); // 배열 초기화
		this.threadPool = Executors.newCachedThreadPool(); // 쓰레드 풀 초기화
		this.end = false;

	}

	void createPuyo() { // 뿌요의 계속적인 생산을 위하여 쓰레드 사용

		// this.puyos = new ArrayList<Puyo>(); // 게임이 시작되면 전체뿌요를 관리 하는 어레이 초기화
		// memberPuyos = new ArrayList<Puyo>(); // 게임이 시작되면 한쌍의 맴버 뿌요를 담는 어레이

		int LocationX = (Puyo.puyoSize * 6) / 2; // 뿌요들의 위치를 잡아기위한 x
		int LocationY = Puyo.puyoSize; // 뿌요들의 위치를 잡아기위한 y

		Runnable thread = new Runnable() { // 뿌요 생성 쓰레드

			@Override
			public void run() {
				// TODO Auto-generated method stub

				while (true) { // 게임이 끝날때까지 무한 반복한다.

					if (end) { // end 게임끝을 chk
						System.out.println("게임종료");
						JOptionPane.showMessageDialog(PuyoPanel.this, "게임 종료!");

						if (threadPool != null && !threadPool.isShutdown())
							threadPool.shutdown(); // 게임이 끝났으므로 모든 쓰레드를 죽임.

						// System.out.println(Thread.currentThread().getName()); // pool-1-thread-1 => ?

						return; // 게임 종료
					}

					// 기준이 되는 me 생성
					me = new Puyo(PuyoPanel.this);
					JLabel meLb = me.Lb;
					meLb.setBounds(LocationX, LocationY, Puyo.puyoSize, Puyo.puyoSize);
					// me.x = LocationX;
					// me.y = LocationY;
					add(meLb);
					puyos.add(me);
					memberPuyos.add(me); // 맴버들을 담는 어레이에 me 를 추가

//					PuyoFrame.this.me = new Puyo("me"); // me Label 생성
//					me.setBounds(LocationX, LocationY, puyoSize, puyoSize); // me의 위치 설정
//					getContentPane().add(PuyoFrame.this.me); // me를 프레임에 추가
//					me.setBackground(Color.red); // 임시로 컬러 설정
//					me.setOpaque(true);
//					puyos.add(me); // 뿌요들을 담는 어레이에 me를 추가
//					memberPuyos.add(me); // 맴버들을 담는 어레이에 me 를 추가

					// you 생성
					you = new Puyo(PuyoPanel.this);
					// JLabel youLb = you.label();
					JLabel youLb = you.Lb;
					youLb.setBounds(LocationX, LocationY - Puyo.puyoSize, Puyo.puyoSize, Puyo.puyoSize);
					// you.x = LocationX;
					// you.y = LocationY;
					add(youLb);
					puyos.add(you);
					memberPuyos.add(you); // 맴버들을 담는 어레이에 you 를 추가

//					PuyoFrame.this.you = new Puyo("you"); // you Label 생성
//					you.setBounds(LocationX, LocationY - puyoSize, puyoSize, puyoSize); // you의 위치 설정
//					getContentPane().add(PuyoFrame.this.you); // you를 프레임에 추가
//					you.setBackground(Color.cyan); // 임시로 컬러 설정
//					you.setOpaque(true);
//					puyos.add(you); // 뿌요들을 담는 어레이에 you 를 추가
//					memberPuyos.add(you); // 맴버들을 담는 어레이에 you 를 추가

					move(); // 뿌요들이 생성 되었으면 뿌요들을 y축으로 떨어 뜨리는 함수 시작
				}

			}
		};
		threadPool.submit(thread); // 쓰레드를 사용하기 위해 쓰레드 풀에 등록
	}

	void move() { // 뿌요들이 생성되면 뿌요들을 밑으로 떨어뜨리기 위한 함수

		while (true) { // 어딘가에 정착할때까지 무한으로 내려온다.

			try {

				if (!me.chk && !you.chk) { // chk => 뿌요들의 정착여부를 알고 있는 boolean 변수
					memberPuyos = new ArrayList<Puyo>(); // 모든 작업이 끝났고, 다음 맴버를 위해 초기화 합니다.
					this.me = null; // current me 뿌요 초기화
					this.you = null; // current you 뿌요 초기화
					return; // 반복문 종료
				}

				Thread.sleep(33); // 딜레이 => 33초

				if (me.chk || you.chk) { // chk => 뿌요들의 정착여부를 알고 있는 boolean 변수
					endMove(me); // me가 정착하기 위해 범위를 잡아주는 함수 실행
					endMove(you); // you가 정착하기 위해 범위를 잡아주는 함수 실행
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	void endMove(Puyo puyo) { // 뿌요들을 y축의 맨 끝으로 이동시키는 함수

		int step = 3; // 한번이동할때 3씩 이동
		int yPos = puyo.Lb.getY(); // 들어온 뿌요의 y축의 값을 가져옴
		int result = yPos + step; // 현재 뿌요의 y축의 위치와 한 걸음 만큼을 더해주어 셋팅 하기 위한 변수

		if (yPos < Puyo.puyoSize * 12 - Puyo.puyoSize) { // 뿌요의 y값이 맨 밑이 아닐때

			// me를 기준으로 you가 me 위에 있으면 you 와 me를 제외한 뿌요들을 고려

			if (comparison(puyo)) // 뿌요가 나오면 세로를 기준으로 위 와 아래를 구분하는 메소드
				result = differentUp(puyo, result); // y축을 업데이트 함
			else
				result = differentDown(puyo, result); // y축을 업데이트 함

			puyo.Lb.setLocation(puyo.Lb.getX(), result); // 뿌요들의 위치를 변경 즉 밑으로 떨궈 줍니다.
			yPos = puyo.Lb.getY(); // y 좌표 업데이트

			// ㅁㄴㅇ여기 수정좀
			if (result <= 0) {
				end = true; // 게임 종료
				return;
			}

		} else { // 처음 differentDown 함수의 조건을 맞추기 위해 사용
			puyo.chk = false; // 처음 한번의 맴버 어레이의 조건을 맞출 수 없어 변경되는 chk
		}
	}

	boolean comparison(Puyo puyo) { // 누가 위에 있는 뿌요고 누가 아래있는 뿌요인지를 가려내는 메소드

		boolean result = false; // default false

		for (Puyo member : memberPuyos) { // 한쌍만 담겨있는 어레이를 돌면서 y값을 비교하며 누가 위 아래인지 구분합니다.
			if (!puyo.equals(member) && puyo.Lb.getY() < member.Lb.getY()) // 나의 y가 더 작으면 나는 member 보다 위에 있다.
				result = true; // 현재 들어 와있는 뿌요가 맴버에 있는 다른 뿌요 보다 아래에 있으면 true 반환
			// 이 작업을 하는이유 : 마지막에 바닥에 꽃 힐때 위에있는 뿌요가 밑에 있는 뿌요를 덮어 버리기 때문에
			// 방지하고자 하는 작업
		}

		return result;

	}

	int differentUp(Puyo me, int result) { // 내가 위에있는 뿌요라면 아래있는 뿌요보다 위에 쌓여야 하기때문에 사용되는 메소드

		int yPos = result; // result 는 뿌요의 y값과 한스텝을 이동할 거리를 더한 값

		for (Puyo puyo : puyos) { // 뿌요들이 있는 어레이를 돈다
			if (!me.equals(puyo)) { // 나를 제외한 모든 뿌요들의 y값을 분석
				if (me.Lb.getX() == puyo.Lb.getX() && yPos + Puyo.puyoSize > puyo.Lb.getY()) {
					// x값이 같으면 내 바로 아래에 있는 뿌요다 + 뿌요의 y끝 좌표가 밑에있는 뿌요를 침범 한다면
					yPos = puyo.Lb.getY() - Puyo.puyoSize; // 뿌요에게 아래 있는 뿌요의 윗값을 준다
					me.chk = false; // 다른 뿌요를 생성 해야 하기 때문에 false의 값을 입력
					break;
				}
			}
		}

		return yPos; // 조건에 일치하지 않는 경우 원래 받아온 값을 그대로 리턴 => 즉 움직이면 된다.

	}

	int differentDown(Puyo me, int result) { // 내가 아래 있을 경우 맴버뿌요의 위치를 고려 하지 않아도 된다.

		int yPos = result;
		Puyo member = null;

		for (Puyo mem : memberPuyos) { // 맴버를 고려 대상에서 제외해야 하기때문에 맴버가 누구 인지를 찾는다
			if (!me.equals(mem)) { // 나와 다르다면
				member = mem; // 현재의 나의 맴버를 셋팅
				break;
			}
		}

		for (Puyo puyo : puyos) { // 위와 같이 뿌요들이 담긴 어레이를 돈다
			if (!me.equals(puyo) && !member.equals(puyo)) { // 나와 나의 맴버 뿌요는 비교 대상이 아님
				if (me.Lb.getX() == puyo.Lb.getX() && yPos + Puyo.puyoSize > puyo.Lb.getY()) {
					// x값이 같으면 내 바로 아래에 있는 뿌요다 + 뿌요의 y끝 좌표가 밑에있는 뿌요를 침범 한다면
					yPos = puyo.Lb.getY() - Puyo.puyoSize;
					// 뿌요에게 아래 있는 뿌요의 윗값을 준다
					me.chk = false;
					// 다음 뿌요 생성을 위해 false => 즉 여기 까지 왓다는건 마지막에 도달했다는 증거
					break;
				}
			}
		}

		return yPos; // 마지막에 도달 하지 않다면 기존 값을 반환

	}

}
