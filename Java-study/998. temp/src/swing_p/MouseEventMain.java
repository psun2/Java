package swing_p;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MouseEventMain extends JFrame implements MouseListener {

	JPanel imgs;

	public MouseEventMain() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 500;
		int height = 600;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setTitle("MouseEvent");
		setLayout(null);

		JPanel outer = new JPanel();
		outer.setBounds(50, 100, 400, 200);
		outer.setBackground(Color.pink);
		outer.setLayout(null);
		add(outer);

		String ttt = "난 너를 믿었었던 만큼 내 친구를 믿었기에 럳ㅈ0ㅐㅓㄹㅈㄷㄹ  ㅈㄹㄷ ㄷㄹㄷㄹㄷㄹㄹㄷ";
		imgs = new JPanel();
		imgX = 0;
		imgY = 30;
		imgs.setBounds(imgX, imgY, 2000, 150);
		imgs.setBackground(Color.yellow);
		imgs.setLayout(new BoxLayout(imgs, BoxLayout.X_AXIS));

		for (int i = 0; i < 7; i++) {
			JButton btn = new JButton(new ImageIcon("./img/son_" + i + ".jpg"));
			imgs.add(btn);
		}

		outer.add(imgs);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
		new Timer().start();
	}

	public static void main(String[] args) {
		new MouseEventMain();
	}

	int lastX, lastY, imgX, imgY, xx = 0, dd = 5;

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("click:" + e.getX() + "," + e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("press:" + e.getX() + "," + e.getY());
		lastX = e.getX();
		lastY = e.getY();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("release:" + e.getX() + "," + e.getY());

		imgX = imgs.getX();

		String dir;
//		dd = -1;
		int size = 500;
		if (lastX > e.getX()) {
			dir = "<-";
			xx -= size;
			dd = -5;
		} else {
			dir = "->";
			xx += size;
			dd = 5;
		}

		System.out.println("dir:" + dir);

		// imgX-=xx;
		// imgsMoving(dd, imgs.getX()-xx);
	}

	class Timer extends Thread {
		@Override
		public void run() {

			while (true) {

				try {
					Thread.sleep(5);
					if (imgX != xx) {
						imgX += dd;
						imgs.setLocation(imgX, imgY);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("enter:" + e.getX() + "," + e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("exit:" + e.getX() + "," + e.getY());
	}

}
