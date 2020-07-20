package swing_p;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class PaintExam extends JFrame implements MouseMotionListener {

	public PaintExam() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 500;
		int height = 600;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("PaintExam");

		addMouseMotionListener(this);

		setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		g.setFont(new Font("엽서체", Font.BOLD, 30));
		g.setColor(Color.blue);
		g.drawArc(20, 90, 200, 200, 90, 180);
//		g.fillArc(10, 10, 200, 200, 90, 180); // 다채움 반원

		g.fillRoundRect(50, 145, 150, 150, 150, 150);

		g.setColor(Color.white);
		g.fillRoundRect(70, 170, 40, 40, 150, 150);
		g.fillRoundRect(140, 170, 40, 40, 150, 150);
		g.fillRoundRect(70, 230, 40, 40, 150, 150);
		g.fillRoundRect(140, 230, 40, 40, 150, 150);

		g.fillRoundRect(117, 210, 20, 20, 150, 150);

		g.setColor(Color.BLACK);
		g.drawString("MOVIE", 50, 320);

		////////////////////////////////////////////

		g.setColor(Color.green);
		g.fillOval(400, 100, 30, 30);

		g.fillRect(408, 120, 15, 50);

		g.fillRoundRect(250, 170, 200, 300, 25, 25);

		g.setColor(Color.white);
		g.fillRoundRect(260, 180, 180, 100, 25, 25);

		g.setColor(Color.black);
		g.drawString("Mobile", 300, 235);

		g.setColor(Color.white);
		g.fillRoundRect(260, 300, 40, 40, 10, 10);
		g.fillRoundRect(330, 300, 40, 40, 10, 10);
		g.fillRoundRect(400, 300, 40, 40, 10, 10);

		g.fillRoundRect(260, 360, 40, 40, 10, 10);
		g.fillRoundRect(330, 360, 40, 40, 10, 10);
		g.fillRoundRect(400, 360, 40, 40, 10, 10);

		g.fillRoundRect(260, 420, 40, 40, 10, 10);
		g.fillRoundRect(330, 420, 40, 40, 10, 10);
		g.fillRoundRect(400, 420, 40, 40, 10, 10);

		g.setColor(new Color(97, 12, 177));

		g.fillPolygon(new int[] { 40, 170, 150, 60 }, new int[] { 385, 385, 470, 470 }, 4);

		g.fillPolygon(new int[] { 240, 185, 155, 175, 200, 240 }, new int[] { 340, 340, 470, 470, 360, 360 }, 6);

		g.fillRoundRect(80, 475, 10, 10, 5, 5);
		g.fillRoundRect(150, 475, 10, 10, 5, 5);
	}

	public static void main(String[] args) {

		new PaintExam();

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX() + ", " + e.getY());
	}

}
