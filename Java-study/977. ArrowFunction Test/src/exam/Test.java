package exam;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Test extends JFrame {

	public Test() {
		// TODO Auto-generated constructor stub

		setSize(500, 500);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		JButton btn = new JButton("Arrow");

		btn.addActionListener(event -> {

			System.out.println("화살표 버튼");
			System.out.println(event);

		});

		add(btn, "North");

		JButton btn2 = new JButton("extends");

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				System.out.println("일반 버튼");
				System.out.println(e);

			}
		});

		add(btn2, "South");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) {

		new Test();

	}

}
