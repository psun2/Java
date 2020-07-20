package swing_p;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Exam extends JFrame {

	Mypanel colors;
	Bord bord;

	public Exam() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 500;
		int height = 600;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Exam");
		setLayout(new BorderLayout());

		colors = new Mypanel();

		bord = new Bord(colors);
		add(bord, "Center");

		add(colors, "South");

		setVisible(true);
	}

}

class Bord extends JPanel implements MouseMotionListener {

	Mypanel colors;
	int x, y;
	Color currentColor;
	boolean chk = false, frist = true;

	public Bord(Mypanel colors) {

		this.colors = colors;
		this.x = -100;
		this.y = -100;

		addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
//		super.paint(g);

		if (frist == true) {
			g.setColor(Color.white);
			g.fillRect(0, 0, 500, 600);
			frist = false;
		}

		currentColor = colors.currentColor;

		if (chk) {
			currentColor = Color.white;
			chk = false;
		}

		g.setColor(currentColor);

		g.fillOval(x, y, 10, 10); // 시작위치x, 시작위치y, 굵기, 굵기
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
			chk = true;

		this.x = e.getX() - 5;
		this.y = e.getY() - 5;

		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

class Mypanel extends JPanel {

	ArrayList<JButton> btns;
	Color currentColor;

	public Mypanel() {

		this.btns = new ArrayList<JButton>();
		this.currentColor = Color.black;

		setLayout(new FlowLayout());

		Color[] arr = { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, new Color(0, 0, 99),
				new Color(155, 77, 203), Color.cyan };

		for (Color color : arr) {
			JButton btn = new JButton();
			btn.setPreferredSize(new Dimension(40, 50));
			btn.setBackground(color);
			btn.setOpaque(true);
			btns.add(btn);
			add(btn);
		}

		for (JButton btn : btns) {
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					JButton btn = (JButton) e.getSource();

					currentColor = btn.getBackground();

					System.out.println(currentColor);
				}
			});
		}

	}

}

public class PaintExam2 {

	public static void main(String[] args) {

		new Exam();

	}

}
