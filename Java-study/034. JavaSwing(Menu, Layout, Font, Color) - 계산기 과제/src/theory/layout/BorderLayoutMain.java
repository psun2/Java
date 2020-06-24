package theory.layout;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

class Border extends JFrame {

	public Border(String title) {
		setTitle(title);
		init();
	}

	void init() {

		setBounds(10, 10, 300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		Parameters:hgap the horizontal gap.vgap the vertical gap.
//		new BorderLayout(hgap, vgap)
//		new BorderLayout(가로간격, 세로간격)
//		값이 없을시 default

		setLayout(new BorderLayout());
		add(new JButton("btn1"), "North");
		add("South", new JButton("btn2"));
		add("East", new JButton("btn3"));
		add(new JButton("btn4"), "West");
		add(new JButton("btn5"), "Center");

		setVisible(true);

	}

}

public class BorderLayoutMain {

	public static void main(String[] args) {

		new Border("BorderLayout");

	}

}
