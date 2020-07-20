package swing_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class CharPaintMain2 extends JFrame {

	Color cc;
	int size = 20;
	String str = "●", shadow = "○";

	class MyPanel extends JPanel implements MouseMotionListener {

		int x = -100, y = -100;

		boolean first = true;

		public MyPanel() {
			setBounds(0, 0, 400, 600);
			addMouseMotionListener(this);
		}

		@Override
		public void mouseDragged(MouseEvent e) {

			System.out.println(e.getModifiers() + "," + e.getModifiersEx());

			if (e.getModifiers() == MouseEvent.BUTTON1_MASK) {
				cc = ccLb.getBackground();
			} else if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
				cc = Color.white;
			}

			x = e.getX();
			y = e.getY();

			repaint();// paint() 호출
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void paint(Graphics g) {
			System.out.println("paint() 실행");
			// super.paint(g); 화면 초기화

			if (first) {
				g.setColor(Color.white);
				g.fillRect(0, 0, 400, 600);
				first = false;
			}
			g.setFont(new Font("굴림", Font.PLAIN, size));
			g.setColor(cc);
			g.drawString(str, x, y);

			g.setColor(Color.black);
			g.drawString(shadow, x, y);

		}
	}

	JSlider[] ccArr = { new JSlider(JSlider.HORIZONTAL, 0, 255, 0), new JSlider(JSlider.HORIZONTAL, 0, 255, 0),
			new JSlider(JSlider.HORIZONTAL, 0, 255, 255) };

	JLabel ccLb = new JLabel();

	public CharPaintMain2() {
		setBounds(100, 50, 800, 600);
		// setBackground(Color.yellow);

		// getContentPane().setBackground(Color.yellow);
		setLayout(null);
		setTitle("CharPaint");

//		Color [] cc = {
//				new Color(255, 0, 0),
//				new Color(255, 100, 0),
//				new Color(255, 255, 0),
//				new Color(0, 255, 0),
//				new Color(0, 0, 255),
//				new Color(0, 50, 150),
//				new Color(200, 0, 255),
//				new Color(0, 0, 0)
//		};

		add(new MyPanel());

		for (int j = 0; j < ccArr.length; j++) {
			ccArr[j].setBounds(420, 30 * j, 100, 30);
			add(ccArr[j]);
			ccArr[j].addChangeListener(new ChangeListener() {

				@Override
				public void stateChanged(ChangeEvent e) {
					ccLb.setBackground(new Color(ccArr[0].getValue(), ccArr[1].getValue(), ccArr[2].getValue()));
				}
			});
		}

		ccLb.setBounds(430, 140, 70, 70);
		ccLb.setBackground(Color.blue);
		ccLb.setOpaque(true);
		add(ccLb);

		JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 5, 100, 20);
		sizeSlider.setBounds(420, 230, 100, 30);
		add(sizeSlider);
		sizeSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				size = ((JSlider) e.getSource()).getValue();
			}
		});

		int i = 0;
		for (String ttt : shape) {
			JButton btn = new JButton(ttt);

			btn.setBounds(420, 50 * i + 270, 50, 50);
			add(btn);

			btn.addActionListener(new ShapeAction(i));

			i++;

		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	class ShapeAction implements ActionListener {

		int i;

		public ShapeAction(int i) {
			super();
			this.i = i;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			str = shape[i];
			shadow = border[i];

		}
	}

	String[] shape = { "●", "★", "▲", "♥" };
	String[] border = { "○", "☆", "△", "♡" };

	public static void main(String[] args) {
		new CharPaintMain2();
	}

}
