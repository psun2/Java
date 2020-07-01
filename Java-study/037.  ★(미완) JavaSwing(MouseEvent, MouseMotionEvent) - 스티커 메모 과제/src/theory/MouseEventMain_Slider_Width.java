package theory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MouseEventMain_Slider_Width extends JFrame implements MouseListener {

	JPanel inner;
	int innerX, innerY, getX, getY, endX, endY, go, max, step, innerWidth, innerHeight;

	public MouseEventMain_Slider_Width() {

		this.step = 5;
		this.innerWidth = 2000;
		this.innerHeight = 150;
		this.innerX = 0;
		this.innerY = 30;
		this.max = 500;
		this.go = innerX;

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

		JPanel outer = new JPanel();
		outer.setBounds(50, 100, 400, 200);
		outer.setBackground(Color.pink);
		outer.setLayout(null);
		add(outer);

		this.inner = new JPanel();
		outer.add(inner);
		inner.setBounds(innerX, innerY, innerWidth, innerHeight);
		inner.setBackground(Color.yellow);
		inner.setLayout(new BoxLayout(inner, BoxLayout.X_AXIS));

		for (int i = 1; i < 7; i++) {
			JButton btn = new JButton(new ImageIcon("./theory_imgs/" + i + ".png"));
			System.out.println(i);
			inner.add(btn);
		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);

		new WidthSlider().start();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MouseEventMain_Slider_Width();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseClicked" + e.getX() + ", " + e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseEntered" + e.getX() + ", " + e.getY());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseExited" + e.getX() + ", " + e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mousePressed" + e.getX() + ", " + e.getY());
		this.getX = e.getX();
		this.getY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseReleased" + e.getX() + ", " + e.getY());
		this.endX = e.getX();
		this.endY = e.getY();

		innerX = inner.getX();

		if (getX > endX) {
			System.out.println("go");
			go -= max;
			System.out.println("max : " + max);
//			step *= -1;
			step = -5;
			System.out.println("inner.getX() : " + inner.getX());
		} else {
			System.out.println("back");
			go += max;
//			step *= 1;
			step = 5;
		}

	}

	class WidthSlider extends Thread {

		@Override
		public void run() {

			while (true) {

				try {
					sleep(10);

					if (innerX != go) { // if문을 try 밖으로 빼면 왜 에러지 ? 아니.. 작동이 되질 않습니다.

//					System.out.println("asd");
						System.out.println(go);
						System.out.println(innerX);

						innerX += step;
						// innerX 에 step 을 더하면 서 결국엔 go 까지 갑니다.

						MouseEventMain_Slider_Width.this.inner.setLocation(innerX, innerY);

					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}

}
