package theory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseMotionMain2 extends JFrame implements MouseMotionListener {

	JLabel me;

	public MouseMotionMain2() {

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

		me = new JLabel("me");
		me.setBounds(200, 400, 100, 100);
		me.setBackground(Color.pink);
		me.setOpaque(true);
		add(me);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseMotionListener(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MouseMotionMain2();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("dragged:" + e.getX() + "," + e.getY());
		me.setLocation(e.getX() - me.getWidth() / 2, 400);
		// e.getX() - me.getWidth() : me Label 을 마우스를 기준으로 me 의 x 축의 중간으로 셋팅
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("moved:" + e.getX() + "," + e.getY());
	}

}
