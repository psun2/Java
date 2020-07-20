package swing_p;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImagePaintMain_Teacher extends JFrame {

	class MyPanel extends JPanel implements MouseMotionListener {
		public MyPanel() {
			setBounds(100, 100, 600, 400);
			addMouseMotionListener(this);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			gImg.setColor(Color.red);
			gImg.drawString("*", e.getX(), e.getY());

			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		Image img = null;
		Graphics gImg = null;

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			if (img == null) {
				img = createImage(600, 400);
				gImg = img.getGraphics();
				gImg.setColor(Color.white);
				gImg.fillRect(0, 0, 600, 400);
			}

			g.drawImage(img, 0, 0, this);

		}
	}

	public ImagePaintMain_Teacher() {

		setBounds(100, 50, 800, 600);
		setLayout(null);
		setTitle("ImagePaint");
		add(new MyPanel());

		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new ImagePaintMain_Teacher();

	}

}
