package swing_p;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MouseMotionMain4 extends JFrame implements MouseMotionListener, MouseListener {

	ArrayList<PostIt> me;

	PostIt curr = null;
	Color currCC = null;

	public MouseMotionMain4() {
		setBounds(100, 50, 500, 600);
		setTitle("MouseMotion4");
		setLayout(null);

		me = new ArrayList<PostIt>();

		JTextField tf = new JTextField();
		tf.setBounds(50, 50, 100, 30);
		add(tf);

		tf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField jj = ((JTextField) e.getSource());
				new PostIt(jj.getText());

				jj.setText("");
			}
		});

		view();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	void view() {
		setVisible(false);
		setVisible(true);
	}

	class PostIt extends JPanel {

		public PostIt(String ttt) {

			setBounds(50, 100, 100, 100);
			setLayout(new BorderLayout());
			setBackground(new Color((int) (Math.random() * 200000) + 50));
			MouseMotionMain4.this.add(this);
			me.add(this);
			JButton btn = new JButton("삭제");
			add(btn, "North");
			add(new JLabel(ttt), "Center");

			view();

			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					MouseMotionMain4.this.remove(PostIt.this);
					me.remove(PostIt.this);

					view();
				}
			});

		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("dragged:" + e.getX() + "," + e.getY());

		for (PostIt jLabel : me) {

			if (curr != null) {
				curr.setBackground(Color.white);
				curr.setLocation(e.getX() - 50, e.getY() - 50);
			} else {
				if (e.getX() < jLabel.getX() + 100 && e.getX() > jLabel.getX() && e.getY() < jLabel.getY() + 100
						&& e.getY() > jLabel.getY()) {
					if (curr == null) {
						curr = jLabel;
						currCC = jLabel.getBackground();
					}

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
		new MouseMotionMain4();
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
