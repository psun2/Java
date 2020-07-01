package test;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

class asd extends JFrame {

	ArrayList<JLabel> qwe = new ArrayList<JLabel>();

	public asd() {

		setBounds(100, 100, 500, 600);
		setLayout(new GridLayout(13, 1));

		JButton btn = new JButton("일정생성");
		add(btn);

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel aaa = new JLabel("d");
				qwe.add(aaa);
				asd.this.add(aaa);

			}
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btn2 = new JButton("제거");
		add(btn2);

		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (JLabel jLabel : qwe) {
					remove(jLabel);
				}

			}
		});

		new TTT(this).start();
		;

	}

}

class TTT extends Thread {

	asd q;

	public TTT(asd q) {
		this.q = q;
	}

	@Override
	public void run() {

		while (true) {

			q.setVisible(true);

		}

	}

}

public class Test2 {

	public static void main(String[] args) {
		new asd();
	}

}
