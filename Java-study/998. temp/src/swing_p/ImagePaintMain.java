package swing_p;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImagePaintMain extends JFrame {

	Image img = null; // null 을 안줘도 되지만 안정성을 위해서 줍니다.
	Graphics gimg = null;
	MyPanel p;

	public ImagePaintMain() {

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

		int width = 800;
		int height = 600;
		int x = (screen.width / 2) - (width / 2);
		int y = (screen.height / 2) - (height / 2);

//		this.img = createImage(600, 400); // Image 는 상관없지만.... 같이 가는 코드이므로 setVisible 다음으로...
//		this.gimg = img.getGraphics(); // setVisible 전에 실행된 코드이므로 Error

		setBounds(x, y, width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("ImagePaint");
		setLayout(new BorderLayout());

		p = new MyPanel();

		add(p, "South");
		add(new Board());

		setVisible(true); // ImagePaint 는 그려진 화면을 가져 오는 것 임으로서
		// 무조건 setVisible(true) 다음에 코드를 작성 합니다.

//		this.img = createImage(600, 400);
		this.img = createImage(1000, 1000);
		this.gimg = img.getGraphics(); // setVisible 후에 실행 되면 Error이 사라집니다.

		// this.gimg.fillOval(0, 0, 100, 100);
		this.gimg.setColor(Color.white);
//		this.gimg.fillRect(0, 0, 600, 400);
		this.gimg.fillRect(0, 0, 1000, 1000);

//		addMouseMotionListener(this);
	}

	class Board extends JPanel implements MouseMotionListener {

		public Board() {
			// TODO Auto-generated constructor stub
			addMouseMotionListener(this);
		}

		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);

//			g.drawImage(img, 100, 100, this); // this = 붙일 바닥 // 바닥을 기준으로 x축으로 + 100 y 축으로 + 100 만큼 떼어짐
			g.drawImage(img, 0, 50, this); // this = 붙일 바닥 // 바닥을 기준으로 x축으로 + 100 y 축으로 + 100 만큼 떼어짐
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			gimg.setColor(ImagePaintMain.this.p.defaultColor);
//			gimg.drawString("♡", e.getX() - 100, e.getY() - 100);
			gimg.drawString("♡", e.getX() , e.getY() - 50);

			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class MyPanel extends JPanel {

		ArrayList<JSlider> sliders;
		JLabel colorLb;
		Color defaultColor;

		public MyPanel() {

			this.sliders = new ArrayList<JSlider>();
			this.defaultColor = Color.black;

			for (int i = 0; i < 3; i++) {
				sliders.add(new JSlider(0, 255, 0));
				add(sliders.get(i));
			}

			this.colorLb = new JLabel("");
			colorLb.setBackground(defaultColor);
			colorLb.setOpaque(true);
			add(colorLb);
			colorLb.setPreferredSize(new Dimension(50, 50));

			for (int i = 0; i < sliders.size(); i++) {

				sliders.get(i).addChangeListener(new ChangeListener() {

					@Override
					public void stateChanged(ChangeEvent e) {

						MyPanel.this.colorLb.setBackground(new Color(sliders.get(0).getValue(),
								sliders.get(1).getValue(), sliders.get(2).getValue()));

						defaultColor = MyPanel.this.colorLb.getBackground();
					}
				});

			}

		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ImagePaintMain();
	}

}
