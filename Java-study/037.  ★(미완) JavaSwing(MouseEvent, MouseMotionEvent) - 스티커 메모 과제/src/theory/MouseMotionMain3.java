package theory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseMotionMain3 extends JFrame implements MouseMotionListener, MouseListener {

	ArrayList<JLabel> me;
	JLabel currentLb = null;
	Color currentC = null;

	public MouseMotionMain3() {

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

		me = new ArrayList<JLabel>();

		Color[] cc = { Color.pink, Color.yellow, Color.green };

		for (Color color : cc) {
			JLabel lb = new JLabel();
			lb.setBounds(200, 200, 100, 100);
			lb.setBackground(color);
			lb.setOpaque(true);
			add(lb);
			me.add(lb);
		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MouseMotionMain3();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("dragged:" + e.getX() + "," + e.getY());
		// me.setLocation(e.getX() - me.getWidth() / 2, 400);
		// e.getX() - me.getWidth() : me Label 을 마우스를 기준으로 me 의 x 축의 중간으로 셋팅

		for (JLabel lb : me) {

			if (currentLb != null) {

				currentLb.setBackground(Color.white);
				currentLb.setBounds(e.getX() - (currentLb.getWidth() / 2), e.getY() - (currentLb.getHeight() / 2), 100,
						100);

			} else {

				if (e.getX() >= lb.getX() && e.getX() <= lb.getX() + lb.getWidth() && e.getY() >= lb.getY()
						&& e.getY() <= lb.getY() + lb.getHeight()) {

					currentLb = lb;
					currentC = lb.getBackground();

					break;

				}
			}

		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("moved:" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (currentC != null) {
			currentLb.setBackground(currentC);
			currentLb = null;
			currentC = null;
		}
	}

}
