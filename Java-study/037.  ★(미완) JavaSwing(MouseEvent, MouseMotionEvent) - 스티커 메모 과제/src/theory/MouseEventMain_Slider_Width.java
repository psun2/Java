package theory;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MouseEventMain_Slider_Width extends JFrame implements MouseListener {

	public MouseEventMain_Slider_Width() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 500;
		int height = 600;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setTitle("MouseMotion");
		setName("MouseMotion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		asdasdjkl;as
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		미완
	}

}
