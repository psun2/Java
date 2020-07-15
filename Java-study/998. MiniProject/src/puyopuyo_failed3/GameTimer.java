package puyopuyo_failed3;

public class GameTimer extends Thread { // 타이머 쓰레드

	PuyoFrame frame; // 프레임에 있는 정보를 업데이트 하기 위해 받아옴
	int second; // 초

	public GameTimer(PuyoFrame frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
		this.second = 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {

			try {

				if (frame.end) // 게임이 끝날때까지 계속 돈다
					return;
				frame.info.time.setText("경과 시간 : " + second + "s");
				sleep(1000);
				second++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
