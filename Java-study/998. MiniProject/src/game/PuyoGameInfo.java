package game;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PuyoGameInfo extends JPanel {

	JLabel score; // 점수 라벨
	JLabel combo; // 콤보 라벨
	JLabel time; // 시간 라벨

	public PuyoGameInfo() {
		// TODO Auto-generated constructor stub

		setLayout(null);
		setBackground(Color.cyan);

		score = new JLabel("점수");
		combo = new JLabel("콤보");
		time = new JLabel("경과 시간");

		score.setBounds(0, 0, 96, 50);
		combo.setBounds(96, 0, 192, 50);
		time.setBounds(192, 0, 288, 50);

		add(score);
		add(combo);
		add(time);

	}
}