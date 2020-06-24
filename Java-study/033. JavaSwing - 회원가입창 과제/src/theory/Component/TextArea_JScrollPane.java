package theory.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextArea_JScrollPane {

	public static void main(String[] args) {

		JFrame frame = new JFrame("JTextArea_JScrollPane");

		frame.setLayout(null); // 레이아웃

		frame.setBounds(30, 30, 400, 400);

		JTextArea area = new JTextArea();
		JScrollPane scroll = new JScrollPane(area);
		scroll.setBounds(30, 30, 200, 100);
		frame.add(scroll);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
