package game;

import javax.swing.JPanel;

public class PuyoTimer extends Thread { // 타이머 쓰레드 // 점수도 같이 관리

	PuyoPanel2 panel; // 프레임에 있는 정보를 업데이트 하기 위해 받아옴
	int second; // 초

	public PuyoTimer(PuyoPanel2 panel) {
		// TODO Auto-generated constructor stub
		this.panel = panel;
		this.second = 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {

			try {

				if (panel.endGame) // 게임이 끝날때까지 계속 돈다
					return;

				if (second % 30 == 0) { // 30초마다 속도가 2씩 증가

					panel.step += 2;
				}

				panel.info.time.setText("경과 시간 : " + second + "s");
				panel.info.score.setText("점수 : " + (panel.score + panel.combo) + "점");
				panel.info.combo.setText("연쇄 : " + panel.comboCnt + "연쇄");
				sleep(1000);
				second++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
