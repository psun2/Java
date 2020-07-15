package puyopuyo;

import javax.swing.JLabel;
import javax.swing.JPanel;

class GameInfo extends JPanel {

	public GameInfo(PuyoFrame frame) {
		// TODO Auto-generated constructor stub

		JLabel score = new JLabel("점수");
		JLabel combo = new JLabel("콤보");
		JLabel time = new JLabel("경과 시간");

		add(score);
		add(combo);
		add(time);

	}

}