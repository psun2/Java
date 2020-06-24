package theory.Container;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JPanel");

		frame.setBounds(100, 100, 400, 400);
		frame.setLayout(null);

		JPanel p1 = new JPanel(); // 컨테이너 입니다.
		p1.setBounds(10, 10, 100, 100);
		p1.setBackground(Color.yellow);

		for (int i = 0; i < 5; i++) {
			p1.add(new JButton("버튼" + i));
		}

		frame.add(p1);

		JPanel p2 = new JPanel();
		p2.setBounds(120, 10, 100, 100);
		p2.setBackground(Color.pink);
		frame.add(p2);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
