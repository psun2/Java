package theory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseEventMain_Slider_Vertical extends JFrame implements MouseListener {

	JPanel inner;
	int startY, viewY, increase, stepY;

	public MouseEventMain_Slider_Vertical() {

		int width = 500;
		int height = 600;

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setLayout(null);

		JPanel outer = new JPanel();
		add(outer);
		outer.setLayout(null);
		outer.setBackground(Color.pink);
		outer.setBounds(50, 100, 400, 200);

		inner = new JPanel();
		inner.setBackground(Color.yellow);
		inner.setBounds(25, 0, 350, 2000);
		outer.add(inner);

		for (int i = 1; i < 7; i++) {
			JLabel lb = new JLabel(new ImageIcon("./theory_imgs/" + i + ".png"));
			lb.setOpaque(true);
			inner.add(lb);
		}

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		addMouseListener(this);

		this.increase = 500;

		new Screen().start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MouseEventMain_Slider_Vertical();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.startY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		if (startY > e.getY()) {
			System.out.println("화면이 밑으로 가요");
			this.viewY = inner.getY() - increase;
			this.stepY = -5;
		} else {
			System.out.println("화면이 위로 가요");
			this.viewY = inner.getY() + increase;
			this.stepY = 5;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	class Screen extends Thread {

		@Override
		public void run() {

			while (true) {

				try {

					if (inner.getY() != viewY) {

//						System.out.println(inner.getY());
//						System.out.println(viewY);

						inner.setLocation(inner.getX(), inner.getY() + stepY); // 현재의 Y 에 증가할 y 를 계속 더해줍니다.

					}

					sleep(10);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
