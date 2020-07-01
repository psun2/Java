package test;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class AAA extends JFrame {
	
	JPanel p;

	public AAA() {

		setTitle("TODO");
		setBounds(710, 240, 500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		 p = new JPanel();
		p.setLayout(new GridLayout(13,1));
		p.setBackground(Color.pink);
//		p.setLayout(new FlowLayout());
		add(p);

		JButton btn = new JButton("asdasd");
		btn.setBounds(100, 100, 100, 100);

		p.add(btn);

//		btn.addContainerListener(l);
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JLabel lb = new JLabel("asd");
				
				
				AAA.this.p.add(lb);

//				btn.setVisible(false);


			}
		});
		
		JPanel asd = new Test();
		asd.setBackground(Color.red);
		
		p.add(asd);

		setVisible(true);
	}

	JLabel addL() {

		return new JLabel("wasd");

	}

}

public class Test extends JPanel {

	public Test() {
		setBounds(300, 300, 100, 100);
		setLayout(new GridBagLayout());
		add(new JLabel("sadasd"));
	}

	public static void main(String[] args) {
		new AAA();
	}

}
