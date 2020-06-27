package exam;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class test extends JFrame {

	public test() {

		setBounds(100, 100, 500, 500);

		setLayout(new BorderLayout());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel tt = new JPanel();
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
		tt.add(new JLabel("오잉"));
//		tt.setBounds(0, 0, 500, 500);
		add(tt, "North");

		setVisible(true);

	}

	public static void main(String[] args) {

		new test();

	}

}
