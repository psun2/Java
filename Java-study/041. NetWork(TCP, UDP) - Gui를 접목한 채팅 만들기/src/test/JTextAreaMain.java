package test;

import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class JTextAreaMain extends JFrame {

	JTextArea a = new JTextArea();

	public JTextAreaMain() {
		// TODO Auto-generated constructor stub

		setBounds(710, 240, 500, 600);

		add(a);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JTextAreaMain a = new JTextAreaMain();
		
		while(true) {
			
			a.a.append("과연");
			
		}
		
	}

}
