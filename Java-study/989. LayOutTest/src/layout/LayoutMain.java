package layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class LayoutMain extends JFrame {

	public LayoutMain() {
		// TODO Auto-generated constructor stub

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 136;
		int height = 353;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

		setBounds(x, y, width, height);
		setTitle("레이아웃");

		// setBackground(Color.pink); // 변화 없음
		// this.setBackground(Color.pink); // 변화 없음
		getContentPane().setBackground(Color.pink);

		setLayout(new FlowLayout());

		for (int i = 0; i < 10; i++) {
			add(new JButton(i + ""));
		}

		JButton btn = new JButton("getSize");
		add(btn);
		// btn.setHorizontalAlignment(SwingConstants.LEFT); // 한판이라 사용 불가
		// btn.setVerticalAlignment(SwingConstants.BOTTOM); // 한판이라 사용 불가

		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getFrameSize();
			}
		});

		JScrollPane p = new JScrollPane();
		add(p);
		p.setSize(new Dimension(100, 100)); // Layout이 잡혀 있어 사이즈 조절이 안먹힘
		p.setPreferredSize(new Dimension(100, 100)); // 강제적으로 조절

		// p.setBackground(Color.blue); // frame 와 같이 색깔 안먹힘
		p.getViewport().setBackground(Color.blue);
		System.out.println(p.getViewport());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	void getFrameSize() {

		int sizeX = getWidth();
		int sizeY = getHeight();

		System.out.println(sizeX + ", " + sizeY);

	}

	public static void main(String[] args) {
		new LayoutMain();
	}

}
