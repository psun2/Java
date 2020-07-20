package swing_p;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CharPaintMain extends JFrame implements MouseMotionListener {

	@Override
	public void mouseDragged(MouseEvent e) {

		System.out.println(e.getModifiers() + "," + e.getModifiersEx());

		if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
			cc = front;
		} else if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
			cc = Color.white;
		}

		x = e.getX() - 10;
		y = e.getY() - 10;

		repaint();// paint() 호출
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public CharPaintMain() {
		setBounds(100, 50, 500, 600);
		// setBackground(Color.yellow);

		// getContentPane().setBackground(Color.yellow);
		setLayout(null);
		setTitle("CharPaint");

		Color[] cc = { new Color(255, 0, 0), new Color(255, 100, 0), new Color(255, 255, 0), new Color(0, 255, 0),
				new Color(0, 0, 255), new Color(0, 50, 150), new Color(200, 0, 255), new Color(0, 0, 0) };
		int i = 0;
		for (Color color : cc) {
			JButton btn = new JButton();
			btn.setBackground(color);
			btn.setBounds(i * 50 + 50, 500, 50, 50);

			add(btn);
			i++;
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					front = ((JButton) e.getSource()).getBackground();
				}
			});
		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseMotionListener(this);
	}

	int x = -100, y = -100;
	Color front = Color.blue, cc;

	boolean first = true;

	@Override
	public void paint(Graphics g) {
		System.out.println("paint() 실행");
		// super.paint(g); 화면 초기화

		if (first) {
			g.setColor(Color.white);
			g.fillRect(0, 0, 500, 500);
			first = false;
		}

		g.setColor(cc);
		g.fillOval(x, y, 20, 20);

	}

	public static void main(String[] args) {
		new CharPaintMain();
	}

}
