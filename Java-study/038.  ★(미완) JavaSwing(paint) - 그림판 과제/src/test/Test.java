package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class CBA extends JFrame {

	ABC a;

	public CBA() {
		super();
		setBounds(100, 100, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		a = new ABC();
		a.setBackground(Color.pink);
		a.setBounds(50, 50, 50, 50);
		add(a);
		setVisible(true);

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// super.paint(g);
		a.repaint();
	}

}

class ABC extends JPanel {

	boolean chk = true;

	public ABC() {
		System.out.println("ABC 생성자");
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		System.out.println("어떻게 돌아 ? 1");
		// super.paint(g);
		if (chk) {
			System.out.println("어떻게 돌아 ? 2");
			add(new JLabel("ABC 페인트"));
			add(new JLabel("ABC 페인트1"));
			add(new JLabel("ABC 페인트2"));
			add(new JLabel("ABC 페인트3"));
			setBackground(Color.black);
			chk = false;
		}

		System.out.println("어떻게 돌아 ? 3");

	}

}

public class Test {
	public static void main(String[] args) {
		new CBA();
	}

}
