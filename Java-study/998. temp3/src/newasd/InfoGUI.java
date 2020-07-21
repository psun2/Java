package newasd;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoGUI extends JPanel {

	JLabel score;
	JLabel combo;
	JLabel time;

	public InfoGUI() {
		// TODO Auto-generated constructor stub

		setLayout(null);
		setBackground(Color.black);

		score = new JLabel("점수");
		combo = new JLabel("콤보");
		time = new JLabel("경과 시간");

		score.setForeground(Color.white);
		combo.setForeground(Color.white);
		time.setForeground(Color.white);

		score.setBounds(0, 0, 100, 50);
		combo.setBounds(100, 0, 200, 50);
		time.setBounds(200, 0, 300, 50);

		add(score);
		add(combo);
		add(time);

	}

}
