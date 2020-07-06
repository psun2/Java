package exam;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Main extends JFrame {

	public Main() {
		// TODO Auto-generated constructor stub

		setLayout(null);
		setSize(500, 500);

		System.out.println(getContentPane().getX());
		System.out.println(getContentPane().getY());
		System.out.println(getWidth());
		System.out.println(getSize().width);

		JTextField tf = new JTextField();
		tf.setBounds(getContentPane().getX(), getContentPane().getY(), getSize().width, 100);
		tf.setBackground(null);
		tf.setBorder(null);
		add(tf);

		JLabel lb = new JLabel("ID");
		lb.setBounds(getContentPane().getX(), getContentPane().getY(), getSize().width, 100);
		add(lb);
		JLabel lb1 = new JLabel("ID");
		lb1.setBounds(getContentPane().getX(), getContentPane().getY(), getSize().width, 100);
		add(lb1);
		JLabel lb2 = new JLabel("ID");
		lb2.setBounds(getContentPane().getX(), getContentPane().getY(), getSize().width, 100);
		add(lb2);
		JLabel lb3 = new JLabel("ID");
		lb3.setBounds(getContentPane().getX(), getContentPane().getY(), getSize().width, 100);
		add(lb3);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Main();
	}

}
