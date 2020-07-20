package swing_p;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MouseMotionMain3 extends JFrame implements MouseMotionListener, MouseListener {

	ArrayList<JLabel> me;

	JLabel curr = null;
	Color currCC = null;

	public MouseMotionMain3() {
		setBounds(100, 50, 500, 600);
		setTitle("MouseMotion3");
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

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("dragged:" + e.getX() + "," + e.getY());

		for (JLabel jLabel : me) {

			if (curr != null) {
				curr.setBackground(Color.white);
				curr.setLocation(e.getX() - 50, e.getY() - 50);
			} else {
				if (e.getX() < jLabel.getX() + 100 && e.getX() > jLabel.getX() && e.getY() < jLabel.getY() + 100
						&& e.getY() > jLabel.getY()) {
//					if (curr == null) {
						curr = jLabel;
						currCC = jLabel.getBackground();
//					}

					break;
				}
			}
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("moved:" + e.getX() + "," + e.getY());

	}

	public static void main(String[] args) {
		new MouseMotionMain3();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("진입이야");
		curr.setBackground(currCC);
		curr = null;
		currCC = null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
