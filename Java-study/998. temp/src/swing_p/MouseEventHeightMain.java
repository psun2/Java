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
import javax.swing.JLabel;
import javax.swing.JPanel;

import swing_p.MouseEventMain.Timer;

public class MouseEventHeightMain extends JFrame implements MouseListener {

	JPanel inner;

	public MouseEventHeightMain() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 500;
		int height = 600;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setTitle("MouseEvent");
		setLayout(null);

		JPanel outer = new JPanel();
		outer.setBounds(50, 100, 400, 400);
		outer.setBackground(Color.pink);
		outer.setLayout(null);
		add(outer);

		startY = 0;
		inner = new JPanel();
		inner.setBounds(50, startY, 300, 10000);
		inner.setBackground(Color.blue);
		inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
		outer.add(inner);

		for (int i = 0; i < 7; i++) {
			JLabel btn = new JLabel(new ImageIcon("./img/son_" + i + ".jpg"));
			inner.add(btn);
		}

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);

		new Timer().start();

	}

	int startY, pressY, stepY, animation;

	public static void main(String[] args) {
		new MouseEventHeightMain();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		pressY = e.getY();
		System.out.println("mousePressed : " + e.getX() + ", " + e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("mouseReleased : " + e.getX() + ", " + e.getY());

		String dir;
		int step = 500;
		if (pressY < e.getY()) {
			stepY += step;
			animation = 5;
			System.out.println("화면이 위로 올라감");
		} else {
			stepY -= step;
			animation = -5;
			System.out.println("화면이 아래로 내려감");
		}
	}

	class Timer extends Thread {

		@Override
		public void run() {

			while (true) {

				try {
					Thread.sleep(10);
					if (startY != stepY) { // 처음 위치 + step의 수 가 아니라면 처음위치에 움직임을 부드럽게 해주는 수를 더하거나
						// 빼주면서 계속 위치를 셋팅
						startY += animation; // moveY에 부드럽게 해주는 animation 을 더해가면서 계속 셋팅
						inner.setLocation(inner.getX(), startY); // 5씩 늘거나 줄면서 부드럽게 해주기 위해 필요
					}

					// 여기가 끝나게 되면 startY = stepY 가 됨....
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

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

}
