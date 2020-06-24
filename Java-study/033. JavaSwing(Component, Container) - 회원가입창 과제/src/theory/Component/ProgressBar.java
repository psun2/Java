package theory.Component;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class ProgressBar {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JProgressBar");

		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		JProgressBar bar = new JProgressBar();
		bar.setBounds(30, 30, 300, 30);
		bar.setValue(50); // 바의 상태를 결정
		frame.add(bar);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
