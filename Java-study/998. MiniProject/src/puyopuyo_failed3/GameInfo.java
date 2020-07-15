package puyopuyo_failed3;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameInfo extends JPanel {

	JLabel score; // 점수 라벨
	JLabel combo; // 콤보 라벨
	JLabel time; // 시간 라벨

	public GameInfo() {
		// TODO Auto-generated constructor stub

		setLayout(null);

		score = new JLabel("점수");
		combo = new JLabel("콤보");
		time = new JLabel("경과 시간");

		score.setBounds(0, 0, 100, 30);
		combo.setBounds(100, 0, 100, 30);
		time.setBounds(200, 0, 100, 30);

		add(score);
		add(combo);
		add(time);

	}
}