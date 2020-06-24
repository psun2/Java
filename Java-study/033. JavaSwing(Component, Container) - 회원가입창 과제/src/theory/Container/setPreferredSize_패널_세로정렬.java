package theory.Container;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class setPreferredSize_패널_세로정렬 {

	public static void main(String[] args) {

		JFrame frame = new JFrame("setPreferredSize_패널_세로정렬");

		frame.setBounds(100, 100, 400, 400);
		frame.setLayout(null);

		// 가로 방향 scroll (Default)
		JPanel p1 = new JPanel();
		p1.setBackground(Color.yellow);

		JScrollPane scroll1 = new JScrollPane(p1);
		scroll1.setBounds(10, 10, 100, 100);
		frame.add(scroll1);

		// 세로방향 scroll
		JPanel p2 = new JPanel();
		p2.setBackground(Color.green);

		// setPreferredSize(Dimension preferredSize)
		// Dimension 형태로 받음
		// p2.setPreferredSize(100,210); // Error
		// p2.setPreferredSize(new Dimension(10, 10));
		// 크기가 작을 경우 모든 컴포넌트가 나오지 않음

		p2.setPreferredSize(new Dimension(100, 210));

		JScrollPane scroll2 = new JScrollPane(p2);
		scroll2.setBounds(150, 10, 100, 100);
		frame.add(scroll2);

		for (int i = 0; i < 5; i++) {
			p1.add(new JButton("버튼" + i));

			JButton btn = new JButton("버튼" + i);
			btn.setBounds(15, i * 30, 70, 30);
			p2.add(btn);
		}

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
